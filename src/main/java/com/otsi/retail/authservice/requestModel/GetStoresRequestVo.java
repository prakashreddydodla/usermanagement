package com.otsi.retail.authservice.requestModel;

import lombok.Data;

@Data
public class GetStoresRequestVo {

	private long id;
	private long stateId;
	private String cityId;
	private long districtId;
	private String storeName;
	
}
