package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;
import java.util.List;

import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.Entity.UserAv;

import lombok.Data;


@Data
public class UserDetailsVo {
	private Long userId;
	private String userName;
	private String phoneNumber;
	private String gender;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private long createdBy;
	private Role role;
	private List<UserAv> userAv;
	private List<StoreVo> stores;
	private StoreVo ownerOf;


}
