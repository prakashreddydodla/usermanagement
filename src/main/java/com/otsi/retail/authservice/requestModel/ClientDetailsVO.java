package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;

import com.otsi.retail.authservice.Entity.PlanDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDetailsVO {
	
	private Long id;
	
	private String name;
	
	private String organizationName;
	
	private String address;
	
	private LocalDate createdDate;
	
	private LocalDate lastModifiedDate;
	
	private String mobile;
	
	private String email;
	
	private boolean isActive;

	
	private Long createdBy;
	
	private Boolean isEsSlipEnabled;
	
	private Boolean isTaxIncluded;
	
	private String planName;
	
	private Long planId;
	
	private String description;
	
	private String  planTenure;
	
	private String rayzorPayPaymentId;
	
	private Long amount;
	
	private PlanDetails plandetails;
	
	
	
	
}
