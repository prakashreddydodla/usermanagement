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

	private long id;
	private String name;
	private long stateId;
	private String stateCode;
	private long districtId;
	private String cityId;
	private String area;
	private String address;
	private String phoneNumber;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private String createdBy;
	private UserDetailsVo storeOwner;
	private long domainId;
	private String gstNumber;
	private long clientId;
	//private List<UserDetailsVo> storeUsers;
	
}
