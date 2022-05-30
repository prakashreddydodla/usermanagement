package com.otsi.retail.authservice.requestModel;

import java.util.List;

import lombok.Data;
@Data
public class PrivilegeVO {
	
private List<ParentPrivilegeVO> webPrivileges;

private List<ParentPrivilegeVO> mobilePrivileges;

}
