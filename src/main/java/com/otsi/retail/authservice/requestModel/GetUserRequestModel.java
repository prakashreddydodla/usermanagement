package com.otsi.retail.authservice.requestModel;

import lombok.Data;

@Data
public class GetUserRequestModel {

	private long id;
	private String phoneNo;
	private String name;
	
}
