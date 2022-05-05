package com.otsi.retail.authservice.Entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class ClientDomains extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String domaiName;
	private String discription;
	/*private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private String createdBy;
	private String modifiedBy;*/
	private boolean isActive;

	
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name ="clientdomain_domainmaster",
	joinColumns = {@JoinColumn(name = "clientDomainaId")},
	inverseJoinColumns = {@JoinColumn(name = "id")})
	private List<Domain_Master> domain;
	
	@JsonIgnore
	@OneToMany(mappedBy = "clientDomianlId",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Store> store;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private ClientDetails client;
	
	@JsonIgnore
	@OneToMany(mappedBy = "clientDomian",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Role> roles;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "clientDomians",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<UserDeatils> users;

	
}
