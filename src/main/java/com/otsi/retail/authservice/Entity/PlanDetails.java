package com.otsi.retail.authservice.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.otsi.retail.authservice.utils.PlanTenure;

import lombok.Data;

@Entity
@Data
public class PlanDetails extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String  planName;
	
	private String description;
	
	private  Long price;
	
	private int maxUsers;
	
	private PlanTenure planTenure;

}
