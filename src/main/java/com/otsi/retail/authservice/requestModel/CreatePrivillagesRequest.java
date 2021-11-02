package com.otsi.retail.authservice.requestModel;

import java.util.List;

import lombok.Data;

@Data
public class CreatePrivillagesRequest {

	private ParentPrivilageVo parentPrivillage;
	private List<SubPrivillagesvo> subPrivillages;
}
