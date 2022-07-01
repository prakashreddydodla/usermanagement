package com.otsi.retail.authservice.requestModel;

import java.util.List;

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
	
	private List<SubPrivilegeVO> SubPrivileges;

}
