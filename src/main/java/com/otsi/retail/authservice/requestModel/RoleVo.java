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
public class RoleVo {
	
	
	private long id;

	private String roleName;
	
	private String discription;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime lastModifyedDate;
	
	private Long createdBy;
	
	private Long modifiedBy;
	
	private boolean isActive;
	
	private Long usersCount;
	
	private List<ParentPrivilegesVo> parentPrivilageVo;
	
	private List<SubPrivillageVo> subPrivilageVo;
	
    private ClientDomainVo clientDomainVo;
	
	
	

}
