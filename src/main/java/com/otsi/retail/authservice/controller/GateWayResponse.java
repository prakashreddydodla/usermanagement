/**
 * gateway response for controller and classes
 */
package com.otsi.retail.authservice.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;

/**
 * @author vasavi
 *
 */
public class GateWayResponse<T> {

	private boolean isSuccess;
	private int status;
	private String message;
	private T result;
	/*
	 * private List<String> errors; private List<FieldError> fieldErrors;
	 */
	// public final int HttpStatus_OK = 200;
	
	public GateWayResponse() {
		super();
	}

	/**
	 * @param result
	 * @param status
	 * @param httpStatus
	 */
	public GateWayResponse(final T result) {
		this.result = result;
		this.status = status;
		// this.httpStatus = status;
	}

	/**
	 * @param result
	 * @param status
	 * @param message
	 * @param errors
	 */

	public GateWayResponse(int status, final String message, final List<String> errors) {
		super();
		this.status = status;
		this.isSuccess =false;
		this.message = message;

	}

	/**
	 * @param status
	 * @param httpStatus
	 * @param message
	 * @param fieldErrors
	 */

	public GateWayResponse(String message, final List<FieldError> fieldErrors) {
		super();
		// this.status = status;
		
		this.message = message;

		this.isSuccess = false;
	}

	/**
	 * @param status
	 * @param httpStatus
	 * @param message
	 * @param error
	 */

	public GateWayResponse(final HttpStatus httpStatus, final String message, final String error) {
		super();
		// this.status = status;
		this.status = status;
		this.message = message;
		if (!StringUtils.isEmpty(error)) {
			// errors = Arrays.asList(error);
		}
	}

	/**
	 * @param httpStatus
	 * @param result
	 * @param httpStatus
	 * @param message
	 */

	public GateWayResponse(final int status, final T result, String message) {
		super();
		this.result = result;
		this.status = status;
		this.message = message;
		//this.isSuccess = "true";
	}
	
	public GateWayResponse(final int httpStatus, final T result, String message,boolean status) {
		super();
		this.result = result;
		this.status = httpStatus;
		this.message = message;
		this.isSuccess = status;
	}
	public GateWayResponse(final HttpStatus status, String message) {
		super();
		//this.result = result;
		// this.httpStatus = httpStatus;
		this.message = message;
		this.isSuccess = true;
	}


	/**
	 * @param isSuccess
	 * @param status
	 * @param message
	 * @param result
	 */

	// our response for controller
	public GateWayResponse(String message, T result) {
		super();
		this.isSuccess = true;
		this.status = 200;
		this.message = message;
		this.result = result;
	}

	public int getStatus() {
		return status;
	}

	public boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

}


