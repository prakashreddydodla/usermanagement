package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

	private Long id;
	private String name;
	private String description;
	private LocalDateTime createdDate;
	private LocalDateTime lastModifyedDate;
	private Long createdBy;
	private List<SubPrivillage> subPrivillages;
	private String parentImage;
	private String path;
	private int domian;
}
