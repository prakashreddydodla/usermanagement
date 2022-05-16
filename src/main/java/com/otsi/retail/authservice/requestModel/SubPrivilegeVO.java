package com.otsi.retail.authservice.requestModel;

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

}
