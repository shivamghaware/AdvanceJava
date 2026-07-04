package com.cdac.dependent;

import com.cdac.dependency.Teacher;

public class PublicSchool implements School {	
	private Teacher subjectTeacher;//=new ScienceTeacher();
	
	public PublicSchool(Teacher myTeacher) {
		System.out.println("In constructor - " + getClass());
		this.subjectTeacher=myTeacher;
	}

	@Override
	public void manageAcademics() {
		System.out.println("Managing academics here -");
		subjectTeacher.teach();
	}

	

}
