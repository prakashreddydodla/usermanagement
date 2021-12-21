package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.SubPrivillage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubPrivillageVo {
	
	private long id;
	private String name;
	private String description;
	private String childPath;
	private String childImage;
	private long parentPrivillageId;

}
