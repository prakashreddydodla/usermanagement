package com.otsi.retail.authservice.responceModel;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserListResponse {

	private long userId;
	private String userName;
	private String roleName;
	private String createdBy;
	private String domian;
	private String email;
	private LocalDate createdDate;
}
