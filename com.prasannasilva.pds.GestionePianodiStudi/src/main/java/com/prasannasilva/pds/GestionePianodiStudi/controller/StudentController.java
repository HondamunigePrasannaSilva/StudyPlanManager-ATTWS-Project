package com.prasannasilva.pds.GestionePianodiStudi.controller;

import com.prasannasilva.pds.GestionePianodiStudi.model.Student;
import com.prasannasilva.pds.GestionePianodiStudi.model.StudyPlan;
import com.prasannasilva.pds.GestionePianodiStudi.repository.StudentRepository;

public class StudentController {
	
	
	
	private StudentRepository studentrepository;
	
	
	
	public StudentController(StudentRepository studentrepository)
	{
		this.studentrepository = studentrepository;
	}

	
	public boolean auth(String username, String password)
	{
		if( (username == null || password == null) || (username.isBlank() || password.isBlank()) )
			throw new IllegalArgumentException("Username or Password invalid!");
		
		boolean isLogged = studentrepository.findStudent(username, password);
		return isLogged;
		
	}


	public boolean deleteStudentStudyPlan(Student student) {
		
		Student s = studentrepository.findById(student.getStudentid());
		if(s != null)
		{
			studentrepository.deleteStudyPlan(student.getStudentid());
			return true;
		}
		return false;
	}


	public boolean InsertStudyPlan(Student student, StudyPlan sp) {
		Student s = studentrepository.findById(student.getStudentid());
		if(s != null)
		{
			if(sp.getCredits() >= s.getDegreecourse().getDegreetype().getCredit())
			{
				studentrepository.insertStudyPlan(student, sp);
				return true;
			}
			
			return false;
		}
		
		return false;
	}


	public boolean UpdateStudyPlan(Student student, StudyPlan newStudyPlan) {
		Student s = studentrepository.findById(student.getStudentid());
		if(s != null)
		{
			if(newStudyPlan.getCredits() >= student.getDegreecourse().getDegreetype().getCredit())
			{
				studentrepository.updateStudyPlan(student, newStudyPlan);
				return true;
			}
			
			return false;
		}
		
		return false;
	}


	
	
	





}
