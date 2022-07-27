package com.prasannasilva.pds.GestionePianodiStudi.repository;

import com.prasannasilva.pds.GestionePianodiStudi.model.Student;
import com.prasannasilva.pds.GestionePianodiStudi.model.StudyPlan;

public interface StudentRepository {
	
	
	public boolean findStudent(String username, String password);
	
	public void deleteStudyPlan(int StringId);
		
	public void insertStudyPlan(Student student, StudyPlan sp);
	
	public void updateStudyPlan(Student student, StudyPlan sp);
	
	public Student findById(int studentId);
	 
	
	
}
