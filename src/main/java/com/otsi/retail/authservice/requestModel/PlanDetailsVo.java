package com.otsi.retail.authservice.requestModel;

import java.time.LocalDateTime;

import javax.persistence.Column;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.otsi.retail.authservice.utils.PlanTenure;

import lombok.Data;

@Data
public class PlanDetailsVo {
private Long planId;
	
	private String  planName;
	
	private String description;
	
	private  Long price;
	
	private int maxUsers;
	
	private Long createdBy;
	
	private PlanTenure planTenure;

    
    private LocalDateTime createdDate ;

    private Long modifiedBy;

    
    private LocalDateTime lastModifiedDate;

	
	
}
