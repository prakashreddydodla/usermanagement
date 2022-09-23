package com.otsi.retail.authservice.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "gst_details", indexes = {
		@Index(name = "clientid__statecode_index", columnList = "client_id,state_code") })
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GstDetails extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long gstId;

	private String gstNumber;

	@Column(name = "client_id")
	private Long clientId;

	@Column(name = "state_code")
	private String stateCode;

}
