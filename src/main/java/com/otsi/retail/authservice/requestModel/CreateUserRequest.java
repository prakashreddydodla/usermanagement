package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;
import java.util.List;

import com.otsi.retail.authservice.Entity.UserAv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

	private Long userId;

	private String userName;

	private String phoneNumber;

	private String gender;
	private LocalDate dob;
	private Long channelId;
	private Long clientId;
	private String isConfigUser;
	private int[] clientDomain;
	private RoleVO role;
	

	private List<UserAv> userAv;

	private List<StoreVO> stores;

}
