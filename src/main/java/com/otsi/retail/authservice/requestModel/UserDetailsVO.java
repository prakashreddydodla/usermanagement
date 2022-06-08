<<<<<<< HEAD:src/main/java/com/otsi/retail/authservice/requestModel/UserDetailsVo.java
package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;
import java.util.List;

import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.UserAv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsVo {
	private Long userId;
	private String userName;
	private String phoneNumber;
	private String gender;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private String createdBy;
	private Role role;
	private List<UserAv> userAv;
	private List<StoreVo> stores;
	private StoreVo ownerOf;


}
=======
package com.otsi.retail.authservice.requestModel;

import java.time.LocalDateTime;
import java.util.List;
import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.UserAv;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsVO {
	private Long id;
	private String userName;
	private String phoneNumber;
	private String gender;
	private LocalDateTime createdDate;
	private LocalDateTime lastModifiedDate;
	private String createdBy;
	private Boolean isActive;
	private Boolean isSuperAdmin;
	private Boolean isCustomer;
	private Role role;
	//private List<ClientDomains> clientDomians;
	private List<UserAv> userAv;
	private List<StoreVO> stores;
	private StoreVO ownerOf;
	private Long modifiedBy;


}
>>>>>>> alpha-release:src/main/java/com/otsi/retail/authservice/requestModel/UserDetailsVO.java
