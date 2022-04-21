/**
 * gateway response for controller and classes
 */
package com.otsi.retail.authservice.utils;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;

/**
 * @author vasavi
 *
 */
public class GateWayResponse<T> {

	private String isSuccess;
	private int statusCode;
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
		this.statusCode = 200;
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
		this.statusCode = status;
		this.isSuccess ="false";
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

		this.isSuccess = "false";
	}

	/**
	 * @param status
	 * @param httpStatus
	 * @param message
	 * @param error
	 */

	
	public GateWayResponse(final int statusCode, final String message, final String error) {
		super();
		// this.status = status;
		this.statusCode = statusCode;
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

	public GateWayResponse(final int statusCode, final T result, String message) {
		super();
		this.result = result;
		this.statusCode = statusCode;
		this.message = message;
		//this.isSuccess = "true";
	}
	
	
	public GateWayResponse(final int statusCode, final T result, String message,String status) {
		super();
		this.result = result;
		this.statusCode = statusCode;
		this.message = message;
		this.isSuccess = status;
	}
	public GateWayResponse(final HttpStatus status, String message) {
		super();
		//this.result = result;
		// this.httpStatus = httpStatus;
		this.message = message;
		this.isSuccess = "true";
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
		this.isSuccess = "true";
		this.statusCode = 200;
		this.message = message;
		this.result = result;
	}

	public int getStatus() {
		return statusCode;
	}

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public void setStatus(int statusCode) {
		this.statusCode = statusCode;
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


