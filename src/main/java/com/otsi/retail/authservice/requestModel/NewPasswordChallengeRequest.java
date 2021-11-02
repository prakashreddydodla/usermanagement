package com.otsi.retail.authservice.requestModel;

import lombok.Data;

@Data
public class NewPasswordChallengeRequest {

	private String userName;
	private String password;
	private String newPassword;
	private String session;
	private long roleId;
}
