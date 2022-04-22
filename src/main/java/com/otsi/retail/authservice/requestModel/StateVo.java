package com.otsi.retail.authservice.requestModel;

import java.util.List;

import lombok.Data;

@Data
public class StateVo {

	private String id;
	private String type;
	private String capital;
	private String code;
	private String name;
	private List<DistrictsVo> districts;
	private List<String> coordinates;
  
}
