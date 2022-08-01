package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;

import lombok.Data;
@Data
public class ClientSearchVO {
	
	private String storeName;
	
	private LocalDate fromDate;
	
	private LocalDate toDate;

}
