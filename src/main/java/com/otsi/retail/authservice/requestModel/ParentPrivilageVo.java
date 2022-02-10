package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;
import java.util.List;

import com.otsi.retail.authservice.Entity.SubPrivillage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentPrivilageVo {

	private long id;
	private String name;
	private String description;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private long createdBy;
	private List<SubPrivillage> subPrivillages;
	private String parentImage;
	private String path;
	private int domian;
}
