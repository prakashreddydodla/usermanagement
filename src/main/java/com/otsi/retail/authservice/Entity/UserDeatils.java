package com.otsi.retail.authservice.Entity;

import java.time.LocalDate;
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
import org.hibernate.type.TrueFalseType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDeatils extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	@Column(unique = true)
	private String userName;
	@Column(unique = true)
	private String phoneNumber;
	private String gender;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private long createdBy;
	private boolean isActive;
	
	@ManyToOne
	@JoinColumn(name = "roleId")
	private Role role;

	@OneToMany(mappedBy = "userData", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserAv> userAv;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_store", joinColumns = { @JoinColumn(name = "userId") }, inverseJoinColumns = {
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