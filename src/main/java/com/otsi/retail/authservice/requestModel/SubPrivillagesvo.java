package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubPrivillagesvo {

	private long id;
	private String name;
	private String description;
	private long parentId;
	private LocalDate createdDate;
	private LocalDate modifyDate;
	private String childPath;
	private String childImage;
	private int domian;

}
