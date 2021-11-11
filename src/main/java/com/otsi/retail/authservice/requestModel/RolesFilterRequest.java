package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RolesFilterRequest {

	private String roleName;
	private LocalDate createdDate;
	private long createdBy;
}
