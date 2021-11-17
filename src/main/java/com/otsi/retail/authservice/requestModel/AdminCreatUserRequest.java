package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;
import java.util.List;

import com.otsi.retail.authservice.Entity.Role;

import lombok.Data;

@Data	
public class AdminCreatUserRequest {

	private String email;
	private String phoneNumber;
	private String birthDate;
	private String gender;
	private String name;
	private String username;
	private String tempPassword;
	private String assginedStores;
	private String parentId;
	private String domianId;
	private String channelId;
	private String address;
	private Role role;
	private List<StoreVo> stores;
	private String clientId;
	private String isConfigUser;
	private int[] clientDomain;
	private String isCustomer;
	private String isSuperAdmin;
	private String createdBy;
}
