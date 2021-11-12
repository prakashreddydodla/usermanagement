package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;
import java.util.List;

import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.UserDeatils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreVo {

	private long id;
	private String name;
	private long stateId;
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
	//private List<UserDetailsVo> storeUsers;
	
}
