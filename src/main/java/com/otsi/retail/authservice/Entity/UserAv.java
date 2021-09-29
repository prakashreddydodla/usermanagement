package com.otsi.retail.authservice.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAv {

	@Id
	@GeneratedValue
	private Long userAvId;

	private int type;
	
	private int integerValue;
	
	private String name;

	private String stringValue;

	private LocalDateTime dateValue;

	private LocalDateTime lastModified;
	
	private boolean booleanValue;

	@ManyToOne
	@JoinColumn(name = "userId")
	private UserDeatils userData;
}
