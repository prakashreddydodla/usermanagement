package com.otsi.retail.authservice.requestModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPasswordChallengeRequest {

	private String userName;
	private String password;
	private String newPassword;
	private String session;
	private String roleName;
}
