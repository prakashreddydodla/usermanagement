package com.otsi.retail.authservice.requestModel;

import lombok.Data;

@Data
public class GetStoresRequestVo {

	private long stateId;
	private long cityId;
	private long districtId;
	private String storeName;
	
}
