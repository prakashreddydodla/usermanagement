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
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long roleId;
	private String roleName;
	private String discription;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private long createdBy;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade =CascadeType.ALL)
	@JoinTable(name = "role_privilages",
	joinColumns= { @JoinColumn(name = "roleId")},
	inverseJoinColumns = { @JoinColumn(name  = "id")})
	private List<Privilages> privilages;
	
	@OneToMany(mappedBy = "role")
	private List<UserDeatils> user;
	
	@ManyToOne
	@JoinColumn(name = "clientDomian")
	private ClientDomains clientDomian;
}
