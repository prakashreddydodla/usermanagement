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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Store  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private long stateId;
	private long districtId;
	private String cityId;
	private String area;
	private String address;
	private String phoneNumber;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private String createdBy;
	
	@OneToOne
	@JoinColumn(name = "store_owner")
	private UserDeatils storeOwner;
	
//	@ManyToMany(mappedBy = "store",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name = "domianId")
	private ClientDomains clientDomianlId;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "stores",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<UserDeatils> storeUsers;

}
