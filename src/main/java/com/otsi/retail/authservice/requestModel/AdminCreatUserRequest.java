package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;

import lombok.Data;

@Data	
public class AdminCreatUserRequest {

	private String email;
	private String phoneNumber;
	private String birthDate;
	private String gender;
	private String name;
	private String username;
	private String tempPassword;
	private String assginedStores;
	private String parentId;
	private String domianId;
	private String address;
}
