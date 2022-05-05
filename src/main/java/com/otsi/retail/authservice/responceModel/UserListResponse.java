package com.otsi.retail.authservice.responceModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.otsi.retail.authservice.requestModel.StoreVo;

import lombok.Data;

@Data
public class UserListResponse {

	private Long userId;
	private String userName;
	private String roleName;
	private Long createdBy;
	private long domian;
	private String email;
	private LocalDateTime createdDate;
	private Boolean isActive;
	private List<StoreVo> stores;
	private String address;
	private Boolean isSuperAdmin;
	private  String dob;
	private String gender;
	
}
