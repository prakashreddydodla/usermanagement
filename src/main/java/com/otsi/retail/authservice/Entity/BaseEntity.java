package com.otsi.retail.authservice.Entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private long createdBy;
}
