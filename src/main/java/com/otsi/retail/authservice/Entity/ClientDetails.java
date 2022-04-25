package com.otsi.retail.authservice.Entity;


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
public class ClientDetails extends BaseEntity  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private String name;
	private String organizationName;
	private String address;
	/*private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private String createdBy;
	private String modifiedBy;*/
	private boolean isActive;

	
	@OneToMany(mappedBy = "client")
	private List<ClientDomains> channelId;
}
