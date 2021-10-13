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
public class ClientDomains {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long clientDomainaId;
	private String domaiName;
	private String discription;
	
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private long createdBy;
	
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name ="clientdomain_mastermaster",
	joinColumns = {@JoinColumn(name = "clientDomainaId")},
	inverseJoinColumns = {@JoinColumn(name = "id")})
	private List<Domain_Master> domain;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name ="clientdomain_stores",
	joinColumns = {@JoinColumn(name = "clientdomainid")},
	inverseJoinColumns = {@JoinColumn(name = "id")})
	private List<Store> store;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private ClientDetails client;
	
	
}
