package com.otsi.retail.authservice.Entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class PlanTransactionDetails extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long clientId;
	
	private String planName;
	
	private String planTenure;
	
	private Long amount;
	
    private LocalDateTime planActivationDate;
	
	private LocalDateTime planExpiryDate;

}
