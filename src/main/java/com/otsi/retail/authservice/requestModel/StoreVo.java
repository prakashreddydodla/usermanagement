package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreVo {

	private Long id;
	private String name;
	private Long stateId;
	private String stateCode;
	private Long districtId;
	private String cityId;
	private String area;
	private String address;
	private String phoneNumber;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private Long createdBy;
	private UserDetailsVo storeOwner;
	private Long domainId;
	private String gstNumber;
	private Long clientId;
	//private List<UserDetailsVo> storeUsers;
	
}
