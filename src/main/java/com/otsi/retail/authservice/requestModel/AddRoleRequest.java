package com.otsi.retail.authservice.requestModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRoleRequest {

	private String groupName;
	private String userpoolId;
	private String userName;


	
}
