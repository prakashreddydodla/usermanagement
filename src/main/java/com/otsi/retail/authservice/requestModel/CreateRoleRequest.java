package com.otsi.retail.authservice.requestModel;

import java.util.List;

import com.otsi.retail.authservice.utils.PrevilegeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoleRequest {
	
	private Long roleId;

	private String roleName;

	private String description;

	private Long clientDomianId;

	private Long createdBy;
	
	private PrevilegeType previligeType;

	private List<ParentPrivilegeVO> parentPrivileges;

	private List<SubPrivilegesVO> subPrivileges;

	private Long clientId;

}
