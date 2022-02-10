package com.otsi.retail.authservice.Exceptions;

import lombok.NoArgsConstructor;

public class UserAlreadyExistsException extends RuntimeException {

public  UserAlreadyExistsException(String message) {
	super(message);
}
	
}
