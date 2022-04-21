package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;


@Data
public class ClientDomainVo {

	private long clientDomainaId;
	private String domaiName;
	private String discription;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private String createdBy;
	private String modifiedBy;
	private boolean isActive;
	
	private List<MasterDomianVo> domainMasterVo;

}
