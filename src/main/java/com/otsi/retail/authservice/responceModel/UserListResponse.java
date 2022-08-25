package com.otsi.retail.authservice.responceModel;

import java.time.LocalDateTime;
import java.util.List;

import com.otsi.retail.authservice.requestModel.StoreVO;

import lombok.Data;

@Data
public class UserListResponse {

	private Long id;
	
	private String userName;
	
	private String roleName;
	
	private Long createdBy;
	
	private long domian;
	
	private String email;
	
	private LocalDateTime createdDate;
	
	private Boolean isActive;
	
	private List<StoreVO> stores;
	
	private String address;
	
	private Boolean isSuperAdmin;
	
	private String dob;
	
	private String phoneNumber;
	
	private String gender;

}
