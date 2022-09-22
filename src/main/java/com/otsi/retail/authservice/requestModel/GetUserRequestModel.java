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

	private Long id;
	private String phoneNo;
	private String name;
	private boolean active;
	private boolean inActive;
	private String roleName;
	private String storeName;
	private Long clientDomainId;
	private Long clientId;
	
}
