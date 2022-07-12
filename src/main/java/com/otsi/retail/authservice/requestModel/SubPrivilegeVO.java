package com.otsi.retail.authservice.requestModel;

import java.util.List;

import com.otsi.retail.authservice.Entity.ChildPrivilege;
import com.otsi.retail.authservice.utils.PrevilegeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubPrivilegeVO {

	private Long id;

	private String name;

	private String description;

	private String childPath;

	private String childImage;

	private Long parentPrivilegeId;
	
	private List<ChildPrivilege> childPrivillages;
	
	private PrevilegeType privilegeType;


}
