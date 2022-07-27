package com.otsi.retail.authservice.requestModel;

import java.time.LocalDateTime;

import javax.persistence.Column;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
public class PlanDetailsVo {
private Long planId;
	
	private String  planName;
	
	private String description;
	
	private  Long price;
	
	private int maxUsers;
	
	private Long createdBy;

    
    private LocalDateTime createdDate ;

    private Long modifiedBy;

    
    private LocalDateTime lastModifiedDate;

	
	
}
