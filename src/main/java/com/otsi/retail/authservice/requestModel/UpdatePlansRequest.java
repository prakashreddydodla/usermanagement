package com.otsi.retail.authservice.requestModel;

import lombok.Data;

@Data
public class UpdatePlansRequest {
	
	private Long planId;
	private Long previlegeId;
	private String previlegeType;
	private Boolean isActive;

}
