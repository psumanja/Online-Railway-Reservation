package com.admin.exception;

@SuppressWarnings("serial")
public class PhoneNumberAlreadyExistingException extends RuntimeException {
	
	public PhoneNumberAlreadyExistingException(String message) {
		super(message);
	}

}
