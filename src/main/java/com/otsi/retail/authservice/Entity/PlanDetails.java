package com.otsi.retail.authservice.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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
	
	
	private int maxUsers;
	
	@OneToMany(mappedBy = "planid", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<TenureDetails> tenureDetials;
	
	
	/*
	 * @Enumerated(EnumType.STRING) private PlanTenure planTenure;
	 */

}
