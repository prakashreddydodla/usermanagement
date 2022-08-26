package com.otsi.retail.authservice.requestModel;

import java.time.LocalDateTime;
import java.util.List;



import com.otsi.retail.authservice.Entity.TenureDetails;

import lombok.Data;

@Data
public class PlanDetailsVo {
private Long planId;
	
	private String  planName;
	
	private String description;
	
	
	private int maxUsers;
	
	private Long createdBy;
	
	
	private List<TenureDetails> tenureDetails;

    
    private LocalDateTime createdDate ;

    private Long modifiedBy;

    
    private LocalDateTime lastModifiedDate;

	
	
}
