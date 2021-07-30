package com.otsi.rpd.authservice.requestModel;


public class LoginRequest {

	private String email;
	private String password;
	private String storeName;
	
	public String getStoreName() {
		return storeName;
	}
	public void setString(String storeName) {
		this.storeName = storeName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
