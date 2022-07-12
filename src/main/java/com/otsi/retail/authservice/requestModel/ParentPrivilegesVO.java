package com.otsi.retail.authservice.requestModel;

import java.util.List;

import com.otsi.retail.authservice.utils.PrevilegeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentPrivilegesVO {
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private String path;
	
	private String parentImage;
	
	private PrevilegeType previlegeType;
	
	private List<SubPrivilegeVO> SubPrivileges;

}
