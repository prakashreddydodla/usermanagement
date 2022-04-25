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
public class ClientDomianVo {

	private Long clientChannelid;
	private String name;
	private String discription;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private Long createdBy;
	//private List<Domain_Master> channel;
	private Long masterDomianId;
	private Long clientId;

}
