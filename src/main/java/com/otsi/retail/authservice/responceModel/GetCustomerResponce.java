package com.otsi.retail.authservice.responceModel;

import java.time.LocalDate;

import lombok.Data;

@Data
public class GetCustomerResponce {

	private long userId;
	private String userName;
	private String phoneNumber;
	private String gender;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private boolean isActive;
	private String createdBy;
}
