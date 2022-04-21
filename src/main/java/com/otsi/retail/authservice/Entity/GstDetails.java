package com.otsi.retail.authservice.Entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GstDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long gstId;
	private String gstNumber;
	private long clientId;
	private String stateCode;
	private String createdBy;
	private LocalDate createdDate;
}
