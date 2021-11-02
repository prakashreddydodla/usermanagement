package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;
import java.util.List;

import com.otsi.retail.authservice.Entity.SubPrivillage;

import lombok.Data;

@Data
public class ParentPrivilageVo {

	private long id;
	private String name;
	private String description;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private long createdBy;
	private List<SubPrivillage> subPrivillages;
}
