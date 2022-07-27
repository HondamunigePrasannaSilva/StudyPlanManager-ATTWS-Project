package com.prasannasilva.pds.GestionePianodiStudi.model;

import java.util.ArrayList;

public class StudyPlan {

	
	private ArrayList<Course> lCourse;
	private int credits;


	public StudyPlan() {
		
	} 

	public StudyPlan(ArrayList<Course> lCourse) 
	{	
		this.lCourse = lCourse;
		this.credits = sumCredits();
		
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	private int sumCredits()
	{
		for (Course course : lCourse) {
			credits += course.getCredits();
		}
		return credits;
		
	}
	
}
