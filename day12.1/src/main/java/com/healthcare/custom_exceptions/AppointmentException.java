package com.healthcare.custom_exceptions;

public class AppointmentException extends RuntimeException {
	public AppointmentException(String mesg) {
		super(mesg);
	}
}
