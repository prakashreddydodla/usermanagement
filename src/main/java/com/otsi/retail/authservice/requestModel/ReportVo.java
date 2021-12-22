package com.otsi.retail.authservice.requestModel;

import lombok.Data;

@Data
public class ReportVo {

	private String name;
	private Long count;

	private ColorCodeVo colorCodeVo;

}
