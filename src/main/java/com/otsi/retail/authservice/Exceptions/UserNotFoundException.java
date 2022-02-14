package com.otsi.retail.authservice.Exceptions;

public class UserNotFoundException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8542216653844851272L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
