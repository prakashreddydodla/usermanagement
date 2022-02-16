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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long roleId;
	@Column(unique=true)
	private String roleName;
	private String discription;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private String createdBy;
	private String modifiedBy;
	private boolean isActive;
	
	

	@ManyToMany(fetch = FetchType.LAZY,cascade =CascadeType.ALL)
	@JoinTable(name = "role_parentPrivilages",
	joinColumns= { @JoinColumn(name = "roleId")},
	inverseJoinColumns = { @JoinColumn(name  = "id")})
	private List<ParentPrivilages> parentPrivilages;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade =CascadeType.ALL)
	@JoinTable(name = "role_subPrivilages",
	joinColumns= { @JoinColumn(name = "roleId")},
	inverseJoinColumns = { @JoinColumn(name  = "id")})
	private List<SubPrivillage> subPrivilages;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	private List<UserDeatils> user;
	
	@ManyToOne
	@JoinColumn(name = "clientDomian")
	private ClientDomains clientDomian;
}
