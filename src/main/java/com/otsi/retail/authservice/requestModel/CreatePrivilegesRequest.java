<<<<<<< HEAD:src/main/java/com/otsi/retail/authservice/requestModel/CreatePrivillagesRequest.java
package com.otsi.retail.authservice.requestModel;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePrivillagesRequest {

	private ParentPrivilageVo parentPrivillage;
	private List<SubPrivillagesvo> subPrivillages;
}
=======
package com.otsi.retail.authservice.requestModel;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePrivilegesRequest {

	private ParentPrivilegeVO parentPrivilege;
	
	private List<SubPrivilegesVO> subPrivileges;
}
>>>>>>> alpha-release:src/main/java/com/otsi/retail/authservice/requestModel/CreatePrivilegesRequest.java
