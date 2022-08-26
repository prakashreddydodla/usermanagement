package com.otsi.retail.authservice.Entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
@Entity
public class TenureDetails extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String planTenure;
	
	private Long amount;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "plan_id")
	private PlanDetails planid;
	
	
	
	

}
