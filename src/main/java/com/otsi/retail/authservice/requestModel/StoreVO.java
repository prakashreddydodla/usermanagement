package com.otsi.retail.authservice.requestModel;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreVO {

	private Long id;
	
	private String name;
	
	private Long stateId;
	
	private String stateCode;
	
	private Long districtId;
	
	private String cityId;
	
	private String area;
	
	private String address;
	
	private String phoneNumber;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime lastModifyedDate;
	
	private Long createdBy;
	
	private String userName;
	
	private UserDetailsVO storeOwner;
	
	private Long domainId;
	
	private String domainName;
	
	private String gstNumber;
	
	private Boolean isActive;
	
	private Long clientId;
	
}
