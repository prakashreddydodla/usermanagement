package com.otsi.retail.authservice.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class ClientDetails extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String name;

	private String organizationName;

	private String address;

	private boolean isActive;

	@OneToMany(mappedBy = "client")
	private List<ClientDomains> channelId;
	
	private String description;
	@OneToOne
	@JoinColumn(name = "planId")
	private PlanDetails planDetails;

	private String mobile;

	private String email;

	private Boolean isEsSlipEnabled;

	private Boolean isTaxIncluded;
	
}
