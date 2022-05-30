package com.otsi.retail.authservice.requestModel;

import java.util.List;

import lombok.Data;
@Data
public class PrivilageVO {
	private List<ParentPrivilageVo> webPrivileges;

	private List<ParentPrivilageVo> mobilePrivileges;

}
