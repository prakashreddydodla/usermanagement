package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDetailsVo {
	private long id;
	private String name;
	private String organizationName;
	private String address;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private String createdBy;
	//private List<ClientDomianVo> channelId;
}
