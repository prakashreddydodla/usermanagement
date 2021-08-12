package com.otsi.retail.authservice.requestModel;

import java.util.List;

public class AssignStoresRequest {

	private String userName;
	private List<String> stores;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<String> getStores() {
		return stores;
	}
	public void setStores(List<String> stores) {
		this.stores = stores;
	}
	
}
