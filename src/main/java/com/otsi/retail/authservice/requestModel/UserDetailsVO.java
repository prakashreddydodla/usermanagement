package com.otsi.retail.authservice.requestModel;

import java.time.LocalDateTime;
import java.util.List;

import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.UserAv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsVO {
	private Long id;
	private String userName;
	private String phoneNumber;
	private String gender;
	private LocalDateTime createdDate;
	private LocalDateTime lastModifiedDate;
	private String createdBy;
	private Boolean isActive;
	private Boolean isSuperAdmin;
	private Boolean isCustomer;
	private Role role;
	private List<UserAv> userAv;
	private List<StoreVO> stores;
	private StoreVO ownerOf;
	private Long modifiedBy;
	private String email;
	private Long empId;
	private String address;
	private String dob;
	


}
