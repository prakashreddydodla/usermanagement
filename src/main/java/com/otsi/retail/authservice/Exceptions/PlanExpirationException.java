package com.otsi.retail.authservice.Exceptions;

public class PlanExpirationException extends BusinessException {
	
	public PlanExpirationException(String message, int statusCode) {
		super(message, statusCode);
	}

}
