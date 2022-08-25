package com.otsi.retail.authservice.Exceptions;

//we try  to save any duplicate record it will throw the exception 

public class DuplicateRecordException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public DuplicateRecordException(String msg,int statusCode) {
		super(msg, statusCode);
	}
}
