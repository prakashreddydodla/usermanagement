package com.otsi.retail.authservice.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.AdminAddUserToGroupRequest;
import com.amazonaws.services.cognitoidp.model.AdminAddUserToGroupResult;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserResult;
import com.amazonaws.services.cognitoidp.model.AdminDisableUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminDisableUserResult;
import com.amazonaws.services.cognitoidp.model.AdminEnableUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminEnableUserResult;
import com.amazonaws.services.cognitoidp.model.AdminGetUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminGetUserResult;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AdminResetUserPasswordRequest;
import com.amazonaws.services.cognitoidp.model.AdminResetUserPasswordResult;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeRequest;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeResult;
import com.amazonaws.services.cognitoidp.model.AdminUpdateUserAttributesRequest;
import com.amazonaws.services.cognitoidp.model.AdminUpdateUserAttributesResult;
import com.amazonaws.services.cognitoidp.model.AliasExistsException;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.amazonaws.services.cognitoidp.model.ChallengeNameType;
import com.amazonaws.services.cognitoidp.model.ConfirmForgotPasswordRequest;
import com.amazonaws.services.cognitoidp.model.ConfirmForgotPasswordResult;
import com.amazonaws.services.cognitoidp.model.ConfirmSignUpRequest;
import com.amazonaws.services.cognitoidp.model.ConfirmSignUpResult;
import com.amazonaws.services.cognitoidp.model.CreateGroupRequest;
import com.amazonaws.services.cognitoidp.model.CreateGroupResult;
import com.amazonaws.services.cognitoidp.model.ForgotPasswordRequest;
import com.amazonaws.services.cognitoidp.model.ForgotPasswordResult;
import com.amazonaws.services.cognitoidp.model.GroupExistsException;
import com.amazonaws.services.cognitoidp.model.InvalidEmailRoleAccessPolicyException;
import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.amazonaws.services.cognitoidp.model.LimitExceededException;
import com.amazonaws.services.cognitoidp.model.ListUsersRequest;
import com.amazonaws.services.cognitoidp.model.ListUsersResult;
import com.amazonaws.services.cognitoidp.model.PasswordResetRequiredException;
import com.amazonaws.services.cognitoidp.model.SignUpRequest;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.amazonaws.services.cognitoidp.model.UpdateGroupRequest;
import com.amazonaws.services.cognitoidp.model.UpdateGroupResult;
import com.amazonaws.services.cognitoidp.model.UserNotFoundException;
import com.amazonaws.services.cognitoidp.model.UsernameExistsException;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.requestModel.AdminCreatUserRequest;
import com.otsi.retail.authservice.requestModel.CreateRoleRequest;
import com.otsi.retail.authservice.requestModel.NewPasswordChallengeRequest;
import com.otsi.retail.authservice.requestModel.StoreVo;
import com.otsi.retail.authservice.requestModel.UpdateUserRequest;
import com.otsi.retail.authservice.utils.CognitoAtributes;

@Component
public class CognitoClient {

	private final AWSCognitoIdentityProvider client;
	private Logger logger = LoggerFactory.getLogger(CognitoClient.class);

	private String ACCESS_KEY;
	private String SECRET_ACCESS_KEY;
	private String CLIENT_ID;;
	private String USERPOOL_ID;
	private String REGION;

	public CognitoClient(

			@Value("${Cognito.aws.accesskey}") String ACCESS_KEY,

			@Value("${Cognito.aws.secret_access_key}") String SECRET_ACCESS_KEY,

			@Value("${Cognito.aws.client_id}") String CLIENT_ID,

			@Value("${Cognito.aws.userpool_id}") String USERPOOL_ID,

			@Value("${Cognito.aws.region}") String REGION) {

		this.ACCESS_KEY = ACCESS_KEY;
		this.SECRET_ACCESS_KEY = SECRET_ACCESS_KEY;
		this.CLIENT_ID = CLIENT_ID;
		this.USERPOOL_ID = USERPOOL_ID;
		this.REGION = REGION;
		client = createCognitoClient();
	}

	// To configure the cognito client. By using this cognito client we can
	// communicate AWS Cognito userpool
	private AWSCognitoIdentityProvider createCognitoClient() {

		AWSCredentials cred = new BasicAWSCredentials(ACCESS_KEY, SECRET_ACCESS_KEY);

		AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(cred);
		return AWSCognitoIdentityProviderClientBuilder.standard().withCredentials(credProvider).withRegion(REGION)
				.build();
	}

	// for User self signup
	public SignUpResult signUp(String userName, String email, String password, String givenName, String name,
			String phoneNo, String storeId) throws Exception {
		SignUpRequest request = new SignUpRequest().withClientId(CLIENT_ID).withUsername(userName)
				.withPassword(password)
				.withUserAttributes((new AttributeType().withName(CognitoAtributes.EMAIL).withValue(email)),
						(new AttributeType().withName(CognitoAtributes.GIVEN_NAME).withValue(givenName)),
						(new AttributeType().withName(CognitoAtributes.NAME).withValue(name)),
						(new AttributeType().withName(CognitoAtributes.PHONE_NUMBER).withValue(phoneNo)),
						(new AttributeType().withName(CognitoAtributes.GENDER).withValue("male")),
						(new AttributeType().withName(CognitoAtributes.USER_ASSIGNED_STORES).withValue(storeId)));

		SignUpResult result = client.signUp(request);
		return result;
	}

	// After SIGNUP user need to confirmSignUp by calling this API
	public ConfirmSignUpResult confirmSignUp(String userName, String confirmationCode) throws Exception {

		ConfirmSignUpRequest confirmSignUpRequest = new ConfirmSignUpRequest().withClientId(CLIENT_ID)
				.withUsername(userName).withConfirmationCode(confirmationCode);

		ConfirmSignUpResult result = client.confirmSignUp(confirmSignUpRequest);
		return result;
	}

	// If the user self signup.Then for login we need to use this API
	public AdminInitiateAuthResult login(String email, String password) throws Exception {
		Map<String, String> authParams = new LinkedHashMap<String, String>() {
			{
				put("USERNAME", email);
				put("PASSWORD", password);
			}
		};
		AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
				.withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH).withUserPoolId(USERPOOL_ID).withClientId(CLIENT_ID)
				.withAuthParameters(authParams);
		AdminInitiateAuthResult authResult = client.adminInitiateAuth(authRequest);
		return authResult;
	}

	// This API is used assign role(group) to user
	public AdminAddUserToGroupResult addRolesToUser(String groupName, String userName)
			throws InvalidParameterException, Exception {
		logger.info("##############  addRolesToUser method starts  ##############");
		List<String> errors = new ArrayList<>();
		if (!StringUtils.hasLength(groupName)) {
			errors.add("GroupName missing");
		}
		if (!StringUtils.hasLength(userName)) {
			errors.add("UserName missing");
		}

		if (errors.isEmpty() && errors.size() == 0) {
			AdminAddUserToGroupRequest adminAddUserToGroupRequest = new AdminAddUserToGroupRequest();
			adminAddUserToGroupRequest.setGroupName(groupName);
			adminAddUserToGroupRequest.setUsername(userName);
			adminAddUserToGroupRequest.setUserPoolId(USERPOOL_ID);
			AdminAddUserToGroupResult result = client.adminAddUserToGroup(adminAddUserToGroupRequest);
			logger.info("##############  addRolesToUser method ends  ##############");

			return result;
		} else {
			String error = errors.stream().collect(Collectors.joining(", "));
			throw new InvalidParameterException(error);
		}
	}

	// this API is used to assgin store to user in userpool
	public AdminUpdateUserAttributesResult addStoreToUser(List<Store> stores, String userName) throws Exception {
		logger.info("##############  addStoreToUser method starts  ##############");

		AdminUpdateUserAttributesRequest updateUserAttributesRequest = new AdminUpdateUserAttributesRequest();
		List<AttributeType> attributes = new ArrayList<>();
		AttributeType attributeType = null;
		AdminGetUserResult userAttributes = getUserFromUserpool(userName);
		if (userAttributes != null) {
			attributeType = userAttributes.getUserAttributes().stream()
					.filter(a -> a.getName().equals(CognitoAtributes.ASSIGNED_STORES)).findFirst().get();
			StringBuilder assignedStores = new StringBuilder(attributeType.getValue());
			stores.stream().forEach(a -> assignedStores.append("," + a.getName()));
			attributes.add(new AttributeType().withName(CognitoAtributes.ASSIGNED_STORES)
					.withValue(assignedStores.toString()));
			updateUserAttributesRequest.setUsername(userName);
			updateUserAttributesRequest.setUserPoolId(USERPOOL_ID);
			updateUserAttributesRequest.setUserAttributes(attributes);
			AdminUpdateUserAttributesResult result = client.adminUpdateUserAttributes(updateUserAttributesRequest);
			logger.info("##############  addStoreToUser method ends  ##############");

			return result;
		} else
			logger.debug("No user found with this username in userpool");
		logger.error("No user found with this username in userpool");
		throw new Exception("No user found with this username in userpool");
	}

	// This API is used to get user details from userpool for given userName
	public AdminGetUserResult getUserFromUserpool(String userName) throws Exception {
		logger.info("##############  getUserFromUserpool method starts  ##############");

		AdminGetUserRequest getUserRequest = new AdminGetUserRequest();
		getUserRequest.setUsername(userName);
		getUserRequest.setUserPoolId(USERPOOL_ID);
		try {
			AdminGetUserResult result = client.adminGetUser(getUserRequest);
			if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
				logger.info("##############  getUserFromUserpool method ends  ##############");

				return result;
			} else {
				logger.debug("No user found" + result);
				logger.error("No user found" + result);
				throw new Exception("No user found" + result);

			}
		} catch (UserNotFoundException une) {
			logger.debug(une.getErrorMessage());
			logger.error(une.getErrorMessage());
			throw new Exception(une.getErrorMessage());
		}

		catch (InvalidParameterException ie) {
			logger.debug(ie.getErrorMessage());
			logger.error(ie.getErrorMessage());
			throw new Exception(ie.getErrorMessage());
		} catch (Exception e) {
			logger.debug(e.getMessage());
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

//If we configure user creation by ADMIN. Then we need to use this API for create the user
	public AdminCreateUserResult adminCreateUser(AdminCreatUserRequest request) throws Exception {
		logger.info("##############  adminCreateUser method starts  ##############");

		AdminCreateUserRequest createUserRequest = new AdminCreateUserRequest();
		createUserRequest.setDesiredDeliveryMediums(Arrays.asList("EMAIL"));
		createUserRequest.setUserPoolId(USERPOOL_ID);
		createUserRequest.setUsername(request.getUsername());
		createUserRequest.setTemporaryPassword(generateTempPassword());
		createUserRequest.setForceAliasCreation(Boolean.FALSE);
		List<AttributeType> userAtributes = new ArrayList<>();
		userAtributes.add(new AttributeType().withName("email_verified").withValue("true"));
		if (null != request.getEmail()) {
			userAtributes.add(new AttributeType().withName(CognitoAtributes.EMAIL).withValue(request.getEmail()));
		}
		if (null != request.getPhoneNumber()) {
			userAtributes.add(
					new AttributeType().withName(CognitoAtributes.PHONE_NUMBER).withValue(request.getPhoneNumber()));
		}

		if (null != request.getName()) {
			userAtributes.add(new AttributeType().withName(CognitoAtributes.NAME).withValue(request.getName()));

		}

		if (null != request.getBirthDate()) {
			userAtributes
					.add(new AttributeType().withName(CognitoAtributes.BIRTHDATE).withValue(request.getBirthDate()));
		}

		if (null != request.getAddress()) {
			userAtributes.add(new AttributeType().withName(CognitoAtributes.ADDRESS).withValue(request.getAddress()));
		}

		if (null != request.getGender()) {
			userAtributes.add(new AttributeType().withName(CognitoAtributes.GENDER).withValue(request.getGender()));

		}

		if (null != request.getParentId()) {
			userAtributes.add(new AttributeType().withName(CognitoAtributes.PARENTID).withValue(request.getParentId()));
		}

		if (null != request.getDomianId()) {
			userAtributes.add(new AttributeType().withName(CognitoAtributes.DOMAINID).withValue(request.getDomianId()));
		}

		if (null != request.getStores()) {
			userAtributes.add(new AttributeType().withName(CognitoAtributes.ASSIGNED_STORES)
					.withValue(setStores(request.getStores())));
		}

		if (null != request.getIsConfigUser()) {

			userAtributes.add(
					new AttributeType().withName(CognitoAtributes.IS_CONFIGUSER).withValue(request.getIsConfigUser()));
		}
		if (null != request.getIsSuperAdmin()) {

			userAtributes.add(
					new AttributeType().withName(CognitoAtributes.IS_SUPER_ADMIN).withValue(request.getIsSuperAdmin()));
		}
		if (null != request.getClientId()) {
			userAtributes
					.add(new AttributeType().withName(CognitoAtributes.CLIENT_ID).withValue(request.getClientId()));
		}

		if (null != request.getRoleName()) {
			userAtributes
					.add(new AttributeType().withName(CognitoAtributes.ROLE_NAME).withValue(request.getRoleName()));
		}

		if (null != request.getClientDomain()) {
			userAtributes.add(new AttributeType().withName(CognitoAtributes.CLIENTDOMIANS)
					.withValue(clientDomiansConvertTostring(request.getClientDomain())));
		}

		try {
			createUserRequest.setUserAttributes(userAtributes);
			AdminCreateUserResult result = client.adminCreateUser(createUserRequest);
			logger.info("##############  adminCreateUser method ends  ##############");

			return result;

		} catch (UsernameExistsException uee) {
			logger.debug("UserName already exits");
			logger.error("UserName already exits");
			throw new Exception("UserName already exits");

		} catch (AliasExistsException ae) {
			logger.debug("Email already exits");
			logger.error("Email already exits");
			throw new Exception("Email already exits");
		}

		catch (InvalidParameterException ie) {
			logger.debug(ie.getErrorMessage());
			logger.error(ie.getErrorMessage());
			throw new Exception(ie.getErrorMessage());
		}

	}

	private String generateTempPassword() {
		int length = 10;
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		return generatedString + "0$";
	}

	private String clientDomiansConvertTostring(int[] clientDomain) {
		if (clientDomain.length != 0) {

			String domians = "";
			for (int i : clientDomain) {
				domians = domians + i + ",";
			}
			return domians;
		} else {
			return "";
		}
	}

	private String setStores(List<StoreVo> stores) {
		StringBuffer storesString = new StringBuffer();
		stores.stream().forEach(a -> storesString.append(a.getName() + ","));
		return storesString.toString();
	}

	// This API is used for the login only when the user was created by ADMIN
	public AdminInitiateAuthResult loginWithTempPassword(String email, String password) throws Exception {
		logger.info("##############  loginWithTempPassword method starts  ##############");

		Map<String, String> authParams = new LinkedHashMap<String, String>() {
			{
				put("USERNAME", email);
				put("PASSWORD", password);
			}
		};
		try {
			AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
					.withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH).withUserPoolId(USERPOOL_ID).withClientId(CLIENT_ID)
					.withAuthParameters(authParams);
			AdminInitiateAuthResult authResult = client.adminInitiateAuth(authRequest);
			logger.info("##############  loginWithTempPassword method ends  ##############");
			return authResult;
		} catch (InvalidParameterException e) {
			logger.debug(e.getErrorMessage());
			logger.error(e.getErrorMessage());
			throw new Exception(e.getErrorMessage());

		} catch (PasswordResetRequiredException pre) {
			logger.debug(pre.getErrorMessage());
			logger.error(pre.getErrorMessage());
			throw new Exception(pre.getErrorMessage());
		}

	}

	// When admin create the user. Then user will create with temporary password.
	// So that user sholud set the new password by using thi api
	public AdminRespondToAuthChallengeResult respondAuthChalleng(NewPasswordChallengeRequest request) {
		logger.info("##############  respondAuthChalleng method starts  ##############");

		AdminRespondToAuthChallengeRequest challengRequest = new AdminRespondToAuthChallengeRequest();
		Map<String, String> challengeResponses = new HashMap<>();
		challengeResponses.put("USERNAME", request.getUserName());
		challengeResponses.put("PASSWORD", request.getPassword());
		challengeResponses.put("NEW_PASSWORD", request.getNewPassword());
		challengRequest.setChallengeResponses(challengeResponses);
		challengRequest.setChallengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED);
		challengRequest.setClientId(CLIENT_ID);
		challengRequest.setUserPoolId(USERPOOL_ID);
		challengRequest.setSession(request.getSession());
		AdminRespondToAuthChallengeResult result = client.adminRespondToAuthChallenge(challengRequest);
		logger.info("##############  respondAuthChalleng method ends  ##############");
		return result;
	}

	public ForgotPasswordResult forgetPassword(String userName) throws Exception {
		logger.info("##############  forgetPassword method starts  ##############");

		ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
		forgotPasswordRequest.setUsername(userName);
		forgotPasswordRequest.setClientId(CLIENT_ID);
		try {
			ForgotPasswordResult result = client.forgotPassword(forgotPasswordRequest);
			if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
				logger.info("##############  forgetPassword method ends  ##############");
				return result;
			} else {
				logger.debug("failed");
				logger.error("failed");
				throw new Exception("failed");
			}

		} catch (Exception e) {
			logger.debug(e.getMessage());
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public ConfirmForgotPasswordResult confirmForgetPassword(String userName, String confirmationCode,
			String newPassword) throws Exception {
		logger.info("##############  confirmForgetPassword method starts  ##############");

		ConfirmForgotPasswordRequest confirmforgotPasswordRequest = new ConfirmForgotPasswordRequest();
		confirmforgotPasswordRequest.setUsername(userName);
		confirmforgotPasswordRequest.setClientId(CLIENT_ID);
		confirmforgotPasswordRequest.setConfirmationCode(confirmationCode);
		confirmforgotPasswordRequest.setPassword(newPassword);
		;
		try {
			ConfirmForgotPasswordResult result = client.confirmForgotPassword(confirmforgotPasswordRequest);
			if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
				logger.info("##############  confirmForgetPassword method ends  ##############");

				return result;
			} else {
				logger.debug("failed");
				logger.error("failed");
				throw new Exception("failed");
			}

		} catch (Exception e) {
			logger.debug(e.getMessage());
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}

	}

	// To create Group(role) in the cognito userpool
	public CreateGroupResult createRole(CreateRoleRequest input) throws Exception {
		logger.info("##############  confirmForgetPassword method starts  ##############");

		CreateGroupRequest request = new CreateGroupRequest();
		if (input.getRoleName() == null) {
			logger.debug("Role name should not be null");
			logger.error("Role name should not be null");
			throw new Exception("Role name should not be null");
		}
		request.setGroupName(input.getRoleName());
		request.setDescription(input.getDescription());
		// request.setPrecedence(input.getPrecedence());
		request.setUserPoolId(USERPOOL_ID);
		try {
			CreateGroupResult result = client.createGroup(request);
			if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
				logger.info("##############  confirmForgetPassword method ends  ##############");
				return result;
			} else {
				logger.debug("failed");
				logger.error("failed");
				throw new Exception("failed");
			}
		} catch (GroupExistsException e) {
			logger.debug(e.getErrorMessage());
			logger.error(e.getErrorMessage());
			throw new Exception(e.getErrorMessage());
		} catch (InvalidParameterException ie) {
			logger.debug(ie.getErrorMessage());
			logger.error(ie.getErrorMessage());
			throw new Exception(ie.getErrorMessage());
		} catch (LimitExceededException lee) {
			logger.debug(lee.getErrorMessage());
			logger.error(lee.getErrorMessage());
			throw new Exception(lee.getErrorMessage());
		} catch (Exception e) {
			logger.debug(e.getMessage());
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	// To update Group(role) in the cognito userpool
	public UpdateGroupResult updateRole(CreateRoleRequest input) throws Exception {
		logger.info("##############  updateRole method starts  ##############");

		UpdateGroupRequest request = new UpdateGroupRequest();
		request.setGroupName(input.getRoleName());
		request.setDescription(input.getDescription());
		request.setUserPoolId(USERPOOL_ID);
		try {
			UpdateGroupResult response = client.updateGroup(request);
			logger.info("##############  updateRole method ends  ##############");

			return response;
		} catch (InvalidParameterException ipe) {
			logger.debug(ipe.getErrorMessage());
			logger.error(ipe.getErrorMessage());
			throw new RuntimeException(ipe.getErrorMessage());
		} catch (Exception e) {
			logger.debug(e.getMessage());
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	// To Enable the user in userpool for given username
	public AdminEnableUserResult userEnabled(String userName) throws Exception {
		logger.info("##############  userEnabled method starts  ##############");

		AdminEnableUserRequest request = new AdminEnableUserRequest();
		request.setUsername(userName);
		request.setUserPoolId(USERPOOL_ID);
		try {
			AdminEnableUserResult result = client.adminEnableUser(request);
			if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
				logger.info("##############  userEnabled method ends  ##############");
				return result;
			} else
				logger.debug("failed to update");
			logger.error("failed to update");
			throw new Exception("failed to update");
		} catch (InvalidParameterException ie) {
			logger.debug(ie.getErrorMessage());
			logger.error(ie.getErrorMessage());
			throw new Exception(ie.getErrorMessage());
		} catch (Exception e) {
			logger.debug(e.getMessage());
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	// To Enable the user in userpool for given username
	public AdminDisableUserResult userDisabled(String userName) throws Exception {
		logger.info("##############  userDisabled method starts  ##############");

		AdminDisableUserRequest request = new AdminDisableUserRequest();
		request.setUsername(userName);
		request.setUserPoolId(USERPOOL_ID);

		try {
			AdminDisableUserResult result = client.adminDisableUser(request);
			if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
				logger.info("##############  userDisabled method ends  ##############");
				return result;
			} else
				logger.debug("failed to update");
			logger.error("failed to update");
			throw new Exception("failed to update");
		} catch (InvalidParameterException ie) {

			logger.debug(ie.getErrorMessage());
			logger.error(ie.getErrorMessage());
			throw new Exception(ie.getErrorMessage());
		} catch (Exception e) {
			logger.debug(e.getMessage());
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	// To get all users present in the userpool
	public ListUsersResult getAllUsers() throws Exception {
		logger.info("##############  getAllUsers method starts  ##############");

		ListUsersRequest request = new ListUsersRequest();
		request.setUserPoolId(USERPOOL_ID);
		try {
			ListUsersResult result = client.listUsers(request);
			if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
				logger.info("##############  getAllUsers method ends  ##############");

				return result;
			} else {
				logger.debug("No users found");
				logger.error("No users found");
				throw new Exception("No users found");
			}
		} catch (Exception e) {
			logger.debug(e.getMessage());
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	// Update the user in userpool. We need to set the update values to user
	public AdminUpdateUserAttributesResult updateUserInCognito(UpdateUserRequest request) {
		logger.info("##############  updateUserInCognito method starts  ##############");

		try {
			AdminUpdateUserAttributesRequest adminUpdateUserAttributesRequest = new AdminUpdateUserAttributesRequest();
			adminUpdateUserAttributesRequest.setUserPoolId(USERPOOL_ID);
			adminUpdateUserAttributesRequest.setUsername(request.getUsername());

			List<AttributeType> userAtributes = new ArrayList<>();
			if (null != request.getEmail()) {
				userAtributes.add(new AttributeType().withName(CognitoAtributes.EMAIL).withValue(request.getEmail()));
			}
			if (null != request.getPhoneNumber()) {
				userAtributes.add(new AttributeType().withName(CognitoAtributes.PHONE_NUMBER)
						.withValue(request.getPhoneNumber()));
			}

			if (null != request.getName()) {
				userAtributes.add(new AttributeType().withName(CognitoAtributes.NAME).withValue(request.getName()));

			}

			if (null != request.getBirthDate()) {
				userAtributes.add(
						new AttributeType().withName(CognitoAtributes.BIRTHDATE).withValue(request.getBirthDate()));
			}

			if (null != request.getAddress()) {
				userAtributes
						.add(new AttributeType().withName(CognitoAtributes.ADDRESS).withValue(request.getAddress()));
			}

			if (null != request.getGender()) {
				userAtributes.add(new AttributeType().withName(CognitoAtributes.GENDER).withValue(request.getGender()));

			}

			if (null != request.getParentId()) {
				userAtributes
						.add(new AttributeType().withName(CognitoAtributes.PARENTID).withValue(request.getParentId()));
			}

			if (null != request.getDomianId()) {
				userAtributes
						.add(new AttributeType().withName(CognitoAtributes.DOMAINID).withValue(request.getDomianId()));
			}

			if (null != request.getStores()) {
				userAtributes.add(new AttributeType().withName(CognitoAtributes.ASSIGNED_STORES)
						.withValue(setStores(request.getStores())));
			}

			if (null != request.getIsConfigUser()) {

				userAtributes.add(new AttributeType().withName(CognitoAtributes.IS_CONFIGUSER)
						.withValue(request.getIsConfigUser()));
			}
			if (null != request.getIsSuperAdmin()) {

				userAtributes.add(new AttributeType().withName(CognitoAtributes.IS_SUPER_ADMIN)
						.withValue(request.getIsSuperAdmin()));
			}
			if (null != request.getClientId()) {
				userAtributes
						.add(new AttributeType().withName(CognitoAtributes.CLIENT_ID).withValue(request.getClientId()));
			}

			if (null != request.getRoleName()) {
				userAtributes
						.add(new AttributeType().withName(CognitoAtributes.ROLE_NAME).withValue(request.getRoleName()));
			}

			if (null != request.getClientDomain()) {
				userAtributes.add(new AttributeType().withName(CognitoAtributes.CLIENTDOMIANS)
						.withValue(clientDomiansConvertTostring(request.getClientDomain())));
			}

			adminUpdateUserAttributesRequest.setUserAttributes(userAtributes);

			AdminUpdateUserAttributesResult result = client.adminUpdateUserAttributes(adminUpdateUserAttributesRequest);
			logger.info("##############  updateUserInCognito method ends  ##############");

			return result;
		} catch (InvalidParameterException ipe) {
			logger.debug(ipe.getErrorMessage());
			logger.error(ipe.getErrorMessage());
			throw new RuntimeException(ipe.getErrorMessage());
		} catch (RuntimeException re) {
			logger.debug(re.getMessage());
			logger.error(re.getMessage());
			throw new RuntimeException(re.getMessage());
		} catch (Exception e) {
			logger.debug(e.getMessage());
			logger.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

	}

	public AdminResetUserPasswordResult adminresetPassword(String userName) throws Exception {
		logger.info("##############  adminresetPassword method starts  ##############");
		try {
			AdminResetUserPasswordRequest request = new AdminResetUserPasswordRequest();
			request.setUsername(userName);
			request.setUserPoolId(USERPOOL_ID);
			AdminResetUserPasswordResult result = client.adminResetUserPassword(request);
			logger.info("##############  adminresetPassword method ends  ##############");
			return result;
		} catch (InvalidEmailRoleAccessPolicyException ierae) {
			logger.debug(ierae.getErrorMessage());
			logger.error(ierae.getErrorMessage());
			throw new Exception(ierae.getErrorMessage());
		}

	}
}