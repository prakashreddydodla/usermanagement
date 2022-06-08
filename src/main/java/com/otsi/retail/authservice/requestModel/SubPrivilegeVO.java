<<<<<<< HEAD:src/main/java/com/otsi/retail/authservice/requestModel/SubPrivillageVo.java
package com.otsi.retail.authservice.requestModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubPrivillageVo {
	
	private Long id;
	private String name;
	private String description;
	private String childPath;
	private String childImage;
	private Long parentPrivillageId;

}
=======
package com.otsi.retail.authservice.requestModel;

import java.util.List;

import com.otsi.retail.authservice.utils.PrevilegeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubPrivilegeVO {

	private Long id;

	private String name;

	private String description;

	private String childPath;

	private String childImage;

	private Long parentPrivilegeId;
	private List<ChildPrivilegeVo> childPrivillages;
	private PrevilegeType previlegeType;


}
>>>>>>> alpha-release:src/main/java/com/otsi/retail/authservice/requestModel/SubPrivilegeVO.java
