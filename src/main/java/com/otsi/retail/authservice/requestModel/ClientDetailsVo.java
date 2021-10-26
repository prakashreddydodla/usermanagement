package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class ClientDetailsVo {
	private long id;
	private String name;
	private String organizationName;
	private String address;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private long createdBy;
	//private List<ClientDomianVo> channelId;
}
