package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.Entity.UserAv;

import lombok.Data;

@Data
public class CreateUserRequest {

	private Long userId;

	private String userName;

	private String phoneNumber;

	private String gender;
	private LocalDate dob;
	private long channelId;

	private RoleVo role;

	private List<UserAv> userAv;

	private List<StoreVo> stores;

}
