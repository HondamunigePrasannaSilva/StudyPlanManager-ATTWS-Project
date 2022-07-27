package com.prasannasilva.pds.GestionePianodiStudi.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.InjectMocks;
import org.mockito.Mock;


import com.prasannasilva.pds.GestionePianodiStudi.model.DegreeCourse;
import com.prasannasilva.pds.GestionePianodiStudi.model.DegreeCourse.DegreeType;
import com.prasannasilva.pds.GestionePianodiStudi.model.Student;
import com.prasannasilva.pds.GestionePianodiStudi.model.StudyPlan;
import com.prasannasilva.pds.GestionePianodiStudi.repository.StudentRepository;

@DisplayName("Test for students")
@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {


	@Mock
	private StudentRepository studentrepository;	// MOCK
	
	@InjectMocks
	private StudentController studentcontroller;	//SUT
	
	 
	@Nested
	@DisplayName("Login tests")
	class loginClass
	{		
			
		@Test @DisplayName("Authentication with correct username and password")
		public void testAuthValidUsernameAndPasswordSuccess()
		{
			when(studentrepository.findStudent(anyString(),anyString())).thenReturn(true);
			boolean isLogged = studentcontroller.auth("username","12345");
			assertTrue(isLogged);
		}
		
		@Test @DisplayName("Authentication with incorrect username and password")
		public void testAuthValidUsernameAndPasswordFail()
		{
			when(studentrepository.findStudent(anyString(),anyString())).thenReturn(false);
			boolean isLogged = studentcontroller.auth("username","12345");
			assertFalse(isLogged);
		}
		
		@Test @DisplayName("Authentication with correct username and password without IAE")
		public void testAuthValidUsernameAndPasswordDoesNotThrowIAE()
		{
			when(studentrepository.findStudent("testUser","testPass")).thenReturn(true);
			assertDoesNotThrow(()->studentcontroller.auth("testUser","testPass"));
			boolean isLogged = studentcontroller.auth("testUser","testPass");
			assertTrue(isLogged);
		}
		
		@ParameterizedTest @DisplayName("Authentication with blank or null username or password")
		@CsvSource({"," , "'test', " , " ,'test'",
					"'',''", "'test', ''" , "'' ,'test'"})
	
		public void testAuthInvalidCredentialShouldThrowIAE(String usert,String pswdt)
		{
			
			IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
					()->studentcontroller.auth(usert,pswdt));
			assertEquals("Username or Password invalid!", e.getMessage());
			
		}
		
		
	}
	
	@Nested
	@DisplayName("Study Plan Tests")
	class StudyPlanTest
	{
		
		@Test
		public void testDeleteStudyPlanWhenExists()
		{
			Student student = new Student(1, "tdegree","tfname","tlname","tuser","tpwd");
			when(studentrepository.findById(1)).thenReturn(student);
			boolean isDelete = studentcontroller.deleteStudentStudyPlan(student);
			verify(studentrepository).deleteStudyPlan(1);
			assertTrue(isDelete);
		}
		
		@Test
		public void testDeleteStudyPlanWhenDoesNotExist()
		{
			Student student = new Student(1, "tdegree","tfname","tlname","tuser","tpwd");
			when(studentrepository.findById(1)).thenReturn(null);
			boolean isDelete = studentcontroller.deleteStudentStudyPlan(student);
			assertFalse(isDelete);
		}
		
		@ParameterizedTest
		@EnumSource(DegreeType.class)
		public void testInsertStudyPlanWhenStudentExistsAndCredits(DegreeType dt)
		{
			Student student = new Student(1, "tdegree","tfname","tlname","tuser","tpwd");
			StudyPlan sp = new StudyPlan();
			sp.setCredits(dt.getCredit());
			DegreeCourse dg = new DegreeCourse();
			dg.setDegreetype(dt);
			student.setDegreecourse(dg);
			
			when(studentrepository.findById(1)).thenReturn(student);
			
			boolean isInsert = studentcontroller.InsertStudyPlan(student,sp);
			assertTrue(isInsert);	
		}
		
		@Test
		public void testInsertStudyPlanWhenCreditsLess()
		{
			Student student = new Student(1, "tdegree","tfname","tlname","tuser","tpwd");
			StudyPlan sp = new StudyPlan();
			DegreeCourse dg = new DegreeCourse();
			dg.setDegreetype(DegreeType.LAUREA_TRIENNALE);
			student.setDegreecourse(dg);
			sp.setCredits(dg.getDegreetype().getCredit()-10);
			
			when(studentrepository.findById(1)).thenReturn(student);
			
			boolean isInsert = studentcontroller.InsertStudyPlan(student,sp);
			assertFalse(isInsert);	
		}
		@ParameterizedTest
		@EnumSource(DegreeType.class)
		public void testInsertStudyPlanWhenStudentDoesNotExist(DegreeType dt)
		{
			Student student = new Student(1, "tdegree","tfname","tlname","tuser","tpwd");
			StudyPlan sp = new StudyPlan();
			sp.setCredits(dt.getCredit());
			DegreeCourse dg = new DegreeCourse();
			dg.setDegreetype(dt);
			student.setDegreecourse(dg);
			
			when(studentrepository.findById(1)).thenReturn(null);
			
			boolean isInsert = studentcontroller.InsertStudyPlan(student,sp);
			assertFalse(isInsert);		
		}
		
		
		@ParameterizedTest
		@EnumSource(DegreeType.class)
		public void testUpdateStudyPlanWhenStudentDoesNotExists(DegreeType dt)
		{
			Student student = new Student(1, "tdegree","tfname","tlname","tuser","tpwd");
			StudyPlan sp = new StudyPlan();
			sp.setCredits(dt.getCredit());
			DegreeCourse dg = new DegreeCourse();
			dg.setDegreetype(dt);
			student.setDegreecourse(dg);
			
			StudyPlan newStudyPlan = new StudyPlan();
			newStudyPlan.setCredits(dt.getCredit());
			
			
			when(studentrepository.findById(1)).thenReturn(null);
			
			boolean isUpdate = studentcontroller.UpdateStudyPlan(student, newStudyPlan);
			assertFalse(isUpdate);
			
		}
		
		@ParameterizedTest
		@EnumSource(DegreeType.class)
		public void testUpdateStudyPlanWhenCreditsEqual(DegreeType dt)
		{
			Student student = new Student(1, "tdegree","tfname","tlname","tuser","tpwd");
			StudyPlan sp = new StudyPlan();
			sp.setCredits(dt.getCredit());
			DegreeCourse dg = new DegreeCourse();
			dg.setDegreetype(dt);
			student.setDegreecourse(dg);
			
			StudyPlan newStudyPlan = new StudyPlan();
			newStudyPlan.setCredits(dt.getCredit());
			
			
			when(studentrepository.findById(1)).thenReturn(student);
			
			boolean isUpdate = studentcontroller.UpdateStudyPlan(student, newStudyPlan);
			assertTrue(isUpdate);
			
		}
		@ParameterizedTest
		@EnumSource(DegreeType.class)
		public void testUpdateStudyPlanWhenCreditsMore(DegreeType dt)
		{
			
			Student student = new Student(1, "tdegree","tfname","tlname","tuser","tpwd");
			StudyPlan sp = new StudyPlan();
			sp.setCredits(dt.getCredit());
			DegreeCourse dg = new DegreeCourse();
			dg.setDegreetype(dt);
			student.setDegreecourse(dg);
			
			StudyPlan newStudyPlan = new StudyPlan();
			
			newStudyPlan.setCredits(dt.getCredit()+10);
			
			
			when(studentrepository.findById(1)).thenReturn(student);
			
			boolean isUpdate = studentcontroller.UpdateStudyPlan(student, newStudyPlan);
			assertTrue(isUpdate);
			
		}
		@ParameterizedTest
		@EnumSource(DegreeType.class)
		public void testUpdateStudyPlanWhenCreditsLess(DegreeType dt)
		{
			
			Student student = new Student(1, "tdegree","tfname","tlname","tuser","tpwd");
			StudyPlan sp = new StudyPlan();
			sp.setCredits(dt.getCredit());
			DegreeCourse dg = new DegreeCourse();
			dg.setDegreetype(dt);
			student.setDegreecourse(dg);
			
			StudyPlan newStudyPlan = new StudyPlan();
			
			newStudyPlan.setCredits(dt.getCredit()-10);
			
			
			when(studentrepository.findById(1)).thenReturn(student);
			
			boolean isUpdate = studentcontroller.UpdateStudyPlan(student, newStudyPlan);
			assertFalse(isUpdate);
			
		}
		
	}


}










