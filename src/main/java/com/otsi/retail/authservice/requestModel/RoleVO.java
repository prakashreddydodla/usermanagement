package com.otsi.retail.authservice.requestModel;

import java.time.LocalDateTime;
import java.util.List;

import com.otsi.retail.authservice.Entity.ChildPrivilege;
import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.Entity.SubPrivilege;

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
	
	private List<ParentPrivilegesVO> parentPrivileges;
	
	private List<SubPrivilegeVO> subPrivilege;
	
	private List<SubPrivilege> subPrivileges;
	private List<ChildPrivilege> childPrivilages;
	
    private ClientDomainVo clientDomain;
    
    private ClientDetailsVO clientDetailsVO;
    
    private ClientDetails client;
	
	
	

}
