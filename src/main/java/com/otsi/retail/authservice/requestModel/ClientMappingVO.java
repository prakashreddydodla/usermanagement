package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;
import java.util.List;

import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.Entity.UserDetails;

import lombok.Data;


@Data
public class ClientMappingVO {
	
	private Long createdBy;
	
	private Long modifiedBy;
	
	private List<ClientDetails> clientIds;
	
	private List<UserDetails> userIds;
	
	private String clientName;
	
	private String supporterName;
	
	private String email;
	
	private LocalDate createdOn;
	
	 
	

}
	