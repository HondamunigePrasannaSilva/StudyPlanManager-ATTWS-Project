package com.prasannasilva.pds.GestionePianodiStudi.model;

public class Course {

	private int id;
	private String coursename;
	private int coursecod;
	private int credits;


	public Course() {
		// TODO Auto-generated constructor stub
	}


	public Course(int id, String coursename, int coursecod, int credits) {
		this.id = id;
		this.coursename = coursename;
		this.coursecod = coursecod;
		this.credits = credits;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCoursename() {
		return coursename;
	}


	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}


	public int getCoursecod() {
		return coursecod;
	}


	public void setCoursecod(int coursecod) {
		this.coursecod = coursecod;
	}


	public int getCredits() {
		return credits;
	}


	public void setCredits(int credits) {
		this.credits = credits;
	}


	
	
	
	
	
	
}
