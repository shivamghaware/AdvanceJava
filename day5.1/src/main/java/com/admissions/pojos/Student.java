package com.admissions.pojos;

public class Student {
	private String firstName;
	private String lastName;
	private int score;
	private Course chosenCourse;
	private boolean admitted;
	public Student(String firstName, String lastName, int score, Course chosenCourse) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.score = score;
		this.chosenCourse = chosenCourse;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Course getChosenCourse() {
		return chosenCourse;
	}
	public void setChosenCourse(Course chosenCourse) {
		this.chosenCourse = chosenCourse;
	}
	public boolean isAdmitted() {
		return admitted;
	}
	public void setAdmitted(boolean admitted) {
		this.admitted = admitted;
	}
	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", score=" + score + ", chosenCourse="
				+ chosenCourse + ", admitted=" + admitted + "]";
	}
	
}
