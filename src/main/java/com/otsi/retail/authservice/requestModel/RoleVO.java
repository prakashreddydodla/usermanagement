package com.otsi.retail.authservice.requestModel;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleVO {
	
	
	private long id;

	private String roleName;
	
	private String description;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime lastModifyedDate;
	
	private Long createdBy;
	
	private Long modifiedBy;
	
	private boolean isActive;
	
	private Long usersCount;
	
	private List<ParentPrivilegesVO> parentPrivilege;
	
	private List<SubPrivilegeVO> subPrivilege;
	
    private ClientDomainVo clientDomain;
	
	
	

}
