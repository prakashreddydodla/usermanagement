package com.otsi.retail.authservice.requestModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

	private String userName;
	private String email;
	private String password;
	private String givenName;
	private String name;
	private String storeId;
	private String phoneNo;
	
}
