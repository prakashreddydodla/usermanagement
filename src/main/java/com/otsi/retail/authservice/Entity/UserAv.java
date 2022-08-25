package com.otsi.retail.authservice.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAv  extends BaseEntity{

	@Id
	@GeneratedValue
	private Long id;

	private int type;
	
	private Long integerValue;
	
	private String name;

	private String stringValue;

	private Date dateValue;
	
	private boolean booleanValue;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserDetails userData;
}
