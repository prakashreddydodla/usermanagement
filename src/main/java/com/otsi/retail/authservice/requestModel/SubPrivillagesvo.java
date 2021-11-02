package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SubPrivillagesvo {

	private long id;
	private String name;
	private String description;
	private long parentId;
	private LocalDate createdDate;
	private LocalDate modifyDate;
}
