package com.otsi.retail.authservice.requestModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetStoresRequestVo {

	private long id;
	private long stateId;
	private String cityId;
	private long districtId;
	private String storeName;
	
}
