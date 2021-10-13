package com.otsi.retail.authservice.Entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class ClientDetails extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(unique = true)
	private String name;
	private String organizationName;
	private String address;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private long createdBy;

	
	@OneToMany(mappedBy = "client")
	private List<ClientDomains> channelId;
}
