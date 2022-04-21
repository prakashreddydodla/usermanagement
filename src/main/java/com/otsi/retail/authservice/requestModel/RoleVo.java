package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleVo {
	
	
	private long roleId;

	private String roleName;
	private String discription;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private String createdBy;
	private String modifiedBy;
	private boolean isActive;
	private long usersCount;
	private List<ParentPrivilegesVo> parentPrivilageVo;
	private List<SubPrivillageVo> subPrivilageVo;
    private ClientDomainVo clientDomainVo;
	
	
	

}
