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
public class MasterDomianVo {
	private long id;
	private String domainName;
	private String discription;
	private boolean status;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private String createdBy;
}


