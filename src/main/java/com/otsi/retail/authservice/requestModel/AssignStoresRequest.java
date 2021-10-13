package com.otsi.retail.authservice.requestModel;

import java.util.List;

import com.otsi.retail.authservice.Entity.Store;

import lombok.Data;
@Data
public class AssignStoresRequest {

	private String userName;
	private List<Store> stores;

	
}
