package com.otsi.retail.authservice.requestModel;

import java.time.LocalDateTime;
import java.util.List;

import com.otsi.retail.authservice.Entity.ChildPrivilege;
import com.otsi.retail.authservice.Entity.SubPrivilege;
import com.otsi.retail.authservice.utils.PrevilegeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentPrivilegeVO {

	private Long id;
	
	private String name;
	
	private String description;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime lastModifyedDate;
	
	private Long createdBy;
	
	private List<SubPrivilege> subPrivilege;
	
	private List<ChildPrivilege> childPrivillages;
	
	private List<SubPrivilegeVO> subPrivileges;
	
	private String parentImage;
	
	private String path;
	
	private Long domain;
	
	private PrevilegeType previlegeType;
}
