package com.hms.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -4782002225958027257L;

	public ResourceNotFoundException(String errMesg) {
		super(errMesg);
	}
}
