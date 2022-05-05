package com.otsi.retail.authservice.requestModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentPrivilegesVo {
	
	private Long id;
	private String name;
	private String discription;
	
	private String path;
	private String parentImage;

}
