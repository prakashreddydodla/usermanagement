package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;
import java.util.List;

import com.otsi.retail.authservice.Entity.Domain_Master;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDomianVo {

	private long clientChannelid;
	private String name;
	private String discription;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private String createdBy;
	//private List<Domain_Master> channel;
	private long masterDomianId;
	private long clientId;

}
