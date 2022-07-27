package com.prasannasilva.pds.GestionePianodiStudi.model;

public class DegreeCourse {

	
	public enum DegreeType{
		
		LAUREA_TRIENNALE(180), LAUREA_MAGISTRALE(120),
		CICLO_UNICO(300),CICLO_UNICO_2(360),MASTER(60);
		
		private int credits;
		DegreeType(int credits) {
			this.credits =  credits;
		}
		public int getCredit()
		{
			return credits;
		}
	
	}

	
	private String degreename;
	private String degreeclass;
	private DegreeType degreetype;
	
	
	
	public DegreeCourse() {
	
	}

	public DegreeCourse(String degreename, String degreeclass, DegreeType degreetype) {
	
		this.degreename = degreename;
		this.degreeclass = degreeclass;
		this.degreetype = degreetype;
		
	}
	
	
	public String getDegreename() {
		return degreename;
	}
	public void setDegreename(String degreename) {
		this.degreename = degreename;
	}
	public String getDegreeclass() {
		return degreeclass;
	}
	public void setDegreeclass(String degreeclass) {
		this.degreeclass = degreeclass;
	}

	public DegreeType getDegreetype() {
		return degreetype;
	}

	public void setDegreetype(DegreeType degreetype) {
		this.degreetype = degreetype;
	}

	
	
}
