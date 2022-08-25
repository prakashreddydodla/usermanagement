/**
 * 
 */
package com.otsi.retail.authservice.requestModel;

import com.otsi.retail.authservice.utils.PrevilegeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Sudheer.Swamy
 *
 */
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildPrivilegeVo {
	
	private Long id;
	private String name;
	private String description;
	private String subChildPath;
	private String subChildImage;
	private int domian;
	private Long subPrivillageId;
	private PrevilegeType previlegeType;

}
