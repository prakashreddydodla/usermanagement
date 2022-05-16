package com.otsi.retail.authservice.Entity;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String userName;
	
	@Column(unique = true)
	private String phoneNumber;
	
	private String gender;
	
	private Boolean isActive = Boolean.FALSE;
	
	private Boolean isSuperAdmin;
	
	private Boolean isCustomer = Boolean.FALSE;
	
	@ManyToOne
	@JoinColumn(name = "roleId")
	private Role role;

	@OneToMany(mappedBy = "userData", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserAv> userAv;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_store", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "id") })
	private List<Store> stores;
	
	@JsonIgnore
	@OneToOne(mappedBy = "storeOwner")
	private Store ownerOf;
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_clientDomians", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = {
			@JoinColumn(name = "clientDomainaId") })
	private List<ClientDomains> clientDomians;

}