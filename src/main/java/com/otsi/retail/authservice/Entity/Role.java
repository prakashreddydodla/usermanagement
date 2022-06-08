<<<<<<< HEAD
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
public class Role extends BaseEntity  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique=true)
	private String roleName;
	private String discription;
	/*private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private String createdBy;
	private String modifiedBy;*/
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
=======
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
public class Role  extends BaseEntity implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private String roleName;
	
	private String description;
	
	private boolean isActive;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade =CascadeType.ALL)
	@JoinTable(name = "role_parentPrivilages",
	joinColumns= { @JoinColumn(name = "roleId")},
	inverseJoinColumns = { @JoinColumn(name  = "id")})
	private List<ParentPrivilege> parentPrivileges;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade =CascadeType.ALL)
	@JoinTable(name = "role_subPrivilages",
	joinColumns= { @JoinColumn(name = "roleId")},
	inverseJoinColumns = { @JoinColumn(name  = "id")})
	private List<SubPrivilege> subPrivileges;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade =CascadeType.ALL)
	@JoinTable(name = "role_childPrivilages",
	joinColumns= { @JoinColumn(name = "roleId")},
	inverseJoinColumns = { @JoinColumn(name  = "id")})
	private List<ChildPrivilege> childPrivilages;
	
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	private List<UserDetails> user;
	
	@ManyToOne
	@JoinColumn(name = "clientDomian")
	private ClientDomains clientDomian;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private ClientDetails client;
}
>>>>>>> alpha-release
