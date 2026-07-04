package com.hms.exceptions;

@SuppressWarnings("serial")
public class AuthenticationException extends RuntimeException {
	public AuthenticationException(String mesg) {
		super(mesg);
	}
}
