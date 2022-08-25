package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterDomianVo {
	private Long id;
	private String domainName;
	private String discription;
	private boolean status;
	private LocalDateTime createdDate;
	private LocalDateTime lastModifyedDate;
	private Long createdBy;
}


