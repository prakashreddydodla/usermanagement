package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolesFilterRequest {

	private String roleName;
	private LocalDate createdDate;
	private Long createdBy;
	
}

