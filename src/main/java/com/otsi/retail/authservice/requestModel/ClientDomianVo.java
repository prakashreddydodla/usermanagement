package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;
import java.util.List;

import com.otsi.retail.authservice.Entity.Domain_Master;

import lombok.Data;

@Data
public class ClientDomianVo {

	private long clientChannelid;
	private String name;
	private String discription;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private long createdBy;
	private List<Domain_Master> channel;

}
