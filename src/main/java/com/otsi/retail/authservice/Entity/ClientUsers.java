package com.otsi.retail.authservice.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class ClientUsers extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	

	@ManyToOne
	@JoinColumn(name = "client_id")
	private ClientDetails clientId;
    
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserDetails userId;
	
	private Boolean status;

}
