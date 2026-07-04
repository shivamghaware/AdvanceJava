package com.admissions.pojos;

public enum Course {
CORE_JAVA(80),PYTHON(75),SPRING_BOOT(85),MICROSERVICES(88);
	private int minScore;
	private Course(int minScore)
	{
		this.minScore=minScore;
	}
	//getter
	public int getMinScore() {
		return minScore;
	}
	
}
