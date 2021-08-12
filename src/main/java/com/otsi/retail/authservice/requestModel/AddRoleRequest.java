package com.otsi.retail.authservice.requestModel;


public class AddRoleRequest {

	private String groupName;
	private String userpoolId;
	private String userName;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getUserpoolId() {
		return userpoolId;
	}
	public void setUserpoolId(String userpoolId) {
		this.userpoolId = userpoolId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
