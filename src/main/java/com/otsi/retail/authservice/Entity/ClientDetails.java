package com.otsi.retail.authservice.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class ClientDetails extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String name;

	private String organizationName;

	private String address;

	private boolean isActive;

	@OneToMany(mappedBy = "client")
	private List<ClientDomains> channelId;

	private String mobile;

	private String email;

	private Boolean isEsSlipEnabled;

	private Boolean isTaxIncluded;

}
