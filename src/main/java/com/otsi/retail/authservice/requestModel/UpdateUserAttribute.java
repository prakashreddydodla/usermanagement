package com.otsi.retail.authservice.requestModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateUserAttribute {

	private String attributeName;
	private String attributeValue;
	private String userName;
	
}
