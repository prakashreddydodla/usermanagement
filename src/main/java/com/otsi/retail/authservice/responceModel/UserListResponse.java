package com.otsi.retail.authservice.responceModel;

import java.time.LocalDate;
import java.util.List;

import com.otsi.retail.authservice.requestModel.StoreVo;

import lombok.Data;

@Data
public class UserListResponse {

	private long userId;
	private String userName;
	private String roleName;
	private String createdBy;
	private long domian;
	private String email;
	private LocalDate createdDate;
	private boolean isActive;
	private List<StoreVo> stores;
	private String address;
	private boolean isSuperAdmin;
	private  String dob;
	private String gender;
	
}
