package com.otsi.retail.authservice.requestModel;

import java.util.List;

import com.otsi.retail.authservice.Entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
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
	private Long createdBy;
	private String roleName;
}
