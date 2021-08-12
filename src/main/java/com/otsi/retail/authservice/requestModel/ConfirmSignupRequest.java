package com.otsi.retail.authservice.requestModel;

public class ConfirmSignupRequest {

	private String confimationCode;
	
	private String userName;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getConfimationCode() {
		return confimationCode;
	}
	public void setConfimationCode(String confimationCode) {
		this.confimationCode = confimationCode;
	}
	
}
