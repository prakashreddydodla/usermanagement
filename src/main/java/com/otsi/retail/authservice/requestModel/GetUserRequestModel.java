package com.otsi.retail.authservice.requestModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserRequestModel {

	private long id;
	private String phoneNo;
	private String name;
	private boolean active;
	private boolean inActive;
	private String roleName;
	private String storeName;
	private long clientDomainId;
	
}
