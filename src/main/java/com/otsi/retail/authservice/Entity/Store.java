package com.otsi.retail.authservice.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "store", indexes = { @Index(name = "clientid__index", columnList = "client_id"),
		@Index(name = "clientid_isactive_index", columnList = "client_id, is_active") })
@Data
public class Store extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private Long stateId;

	private String stateCode;

	private Long districtId;

	private String cityId;

	private String area;

	private String address;

	private String phoneNumber;

	@Column(name = "is_active")
	private Boolean isActive = Boolean.FALSE;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private ClientDetails client;

	@OneToOne
	@JoinColumn(name = "store_owner")
	private UserDetails storeOwner;

	@ManyToOne
	@JoinColumn(name = "domianId")
	private ClientDomains clientDomianlId;

	@JsonIgnore
	@ManyToMany(mappedBy = "stores", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<UserDetails> storeUsers;

}
