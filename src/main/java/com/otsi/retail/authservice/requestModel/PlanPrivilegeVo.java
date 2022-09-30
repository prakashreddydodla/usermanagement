package com.otsi.retail.authservice.requestModel;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class PlanPrivilegeVo {

	private String planType;
	
	private List<ParentPrivilegeVO> parentPrivileges;

	

	
	
	
}
