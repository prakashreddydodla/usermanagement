package com.otsi.retail.authservice.requestModel;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;


@Data
public class ClientDomainVo {

	private Long id;
	private String domaiName;
	private String discription;
	private LocalDateTime createdDate;
	private LocalDateTime lastModifyedDate;
	private Long createdBy;
	private Long modifiedBy;
	private boolean isActive;
	
	private List<MasterDomianVo> domainMasterVo;

}
