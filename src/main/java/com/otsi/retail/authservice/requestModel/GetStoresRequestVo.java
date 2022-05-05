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

	private Long id;
	private String stateId;
	private String cityId;
	private Long districtId;
	private String storeName;
	
}
