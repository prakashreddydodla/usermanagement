<<<<<<< HEAD:src/main/java/com/otsi/retail/authservice/requestModel/ClientDetailsVo.java
package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDetailsVo {
	private Long id;
	private String name;
	private String organizationName;
	private String address;
	private LocalDate createdDate;
	private LocalDate lastModifyedDate;
	private Long createdBy;
	//private List<ClientDomianVo> channelId;
}
=======
package com.otsi.retail.authservice.requestModel;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDetailsVO {
	
	private Long id;
	
	private String name;
	
	private String organizationName;
	
	private String address;
	
	private LocalDate createdDate;
	
	private LocalDate lastModifiedDate;
	
	private String mobile;
	
	private String email;
	
	private Long createdBy;
	
	private Boolean isEsSlipEnabled;
	
	private Boolean isTaxIncluded;
	
	
	
	
}
>>>>>>> alpha-release:src/main/java/com/otsi/retail/authservice/requestModel/ClientDetailsVO.java
