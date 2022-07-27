package com.prasannasilva.pds.GestionePianodiStudi.model;

public class Student {

	private int studentid;
	private String degree;
	private String fname;
	private String lname;
	private String username;
	private String password;
	
	
	
	private StudyPlan studyplan;
	private DegreeCourse degreecourse;


	public Student(int studentid, String degree, String fname, String lname, String username, String password) {

		this.studentid = studentid;
		this.degree = degree;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
		
		studyplan = null;
		degreecourse= null;
		
	}



	public int getStudentid() {
		return studentid;
	}



	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}



	public String getDegree() {
		return degree;
	}



	public void setDegree(String degree) {
		this.degree = degree;
	}



	public String getFname() {
		return fname;
	}



	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}



	public void setLname(String lname) {
		this.lname = lname;
	}



	public String getUsername() {
		return username;
	}



	public StudyPlan getStudyplan() {
		return studyplan;
	}



	public void setStudyplan(StudyPlan studyplan) {
		this.studyplan = studyplan;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public DegreeCourse getDegreecourse() {
		return degreecourse;
	}



	public void setDegreecourse(DegreeCourse degreecourse) {
		this.degreecourse = degreecourse;
	}
	
	
	
	
	
	
	
	
	
	
	
}
