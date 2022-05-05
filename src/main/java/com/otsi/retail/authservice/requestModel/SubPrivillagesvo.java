package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;
import java.util.List;

import com.otsi.retail.authservice.Entity.ChildPrivilege;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubPrivillagesvo {

	private Long id;
	private String name;
	private String description;
	private Long parentId;
	private LocalDate createdDate;
	private LocalDate modifyDate;
	private String childPath;
	private String childImage;
	private int domian;
	private List<ChildPrivilegeVo> childPrivillages;

}
