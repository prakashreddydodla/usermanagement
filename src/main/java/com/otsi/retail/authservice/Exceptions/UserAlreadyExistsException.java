package com.otsi.retail.authservice.Exceptions;

public class UserAlreadyExistsException extends RuntimeException {

/**
	 * 
	 */
	private static final long serialVersionUID = 916371399228766916L;

public  UserAlreadyExistsException(String message) {
	super(message);
}
	
}
