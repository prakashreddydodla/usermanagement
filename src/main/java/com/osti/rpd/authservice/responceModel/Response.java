package com.osti.rpd.authservice.responceModel;

import java.util.Map;


public class Response {
	
	
	private int statusCode;
    private String body;
    private Map<String, String> headers;
    private  String errorDescription;
    private Map<String, String> authResponce;
	
	
	public Map<String, String> getAuthResponce() {
		return authResponce;
	}
	public void setAuthResponce(Map<String, String> authResponce) {
		this.authResponce = authResponce;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Map<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
	
}
