package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MasterDomianVo {
	private long id;
	private String channelName;
	private String discription;
	private boolean status;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private long createdBy;
}
