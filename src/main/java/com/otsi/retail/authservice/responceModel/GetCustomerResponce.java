package com.otsi.retail.authservice.responceModel;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class GetCustomerResponce {

	private Long userId;
	private String userName;
	private String phoneNumber;
	private String gender;
	private LocalDateTime createdDate;
	private LocalDateTime lastModifyedDate;
	private Boolean isActive;
	private Long createdBy;
}
