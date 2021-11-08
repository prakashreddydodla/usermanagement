package com.otsi.retail.authservice.Exceptions;

public class UserNotFoundException extends RuntimeException {

	
	public UserNotFoundException(String message) {
		super(message);
	}
}
