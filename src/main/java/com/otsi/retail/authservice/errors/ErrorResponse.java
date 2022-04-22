package com.otsi.retail.authservice.errors;



import java.util.Date;

public class ErrorResponse<T> {

	private String isSuccess;
	private Integer status;
	private String message;
	private T result;
	

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(int status,String message) {
		super();
		this.isSuccess = "false";
		this.status =status;
		this.message = message;
		this.result=null;
	}
	

	@Override
	public String toString() {
		return "ErrorResponse [isSuccess=" + isSuccess + ", status=" + status + ", message=" + message + ", result="
				+ result + "]";
	}

	

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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

