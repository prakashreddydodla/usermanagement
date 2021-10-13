package com.otsi.retail.authservice.requestModel;

import java.util.List;

import lombok.Data;

@Data
public class DomianStoresVo {
	private ClientDomianVo domain;
	private List<StoreVo> stores;
}

