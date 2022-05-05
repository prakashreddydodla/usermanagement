package com.otsi.retail.authservice.requestModel;

import java.util.List;

import com.otsi.retail.authservice.Entity.SubPrivillage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubPrivillageVo {

	private Long id;
	private String name;
	private String description;
	private String childPath;
	private String childImage;
	private Long parentPrivillageId;
	private List<ChildPrivilegeVo> childPrivillages;

}
