package com.otsi.retail.authservice.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeRequest;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeResult;
import com.amazonaws.services.cognitoidp.model.AdminUpdateUserAttributesRequest;
import com.amazonaws.services.cognitoidp.model.AdminUpdateUserAttributesResult;
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
import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.amazonaws.services.cognitoidp.model.ListUsersRequest;
import com.amazonaws.services.cognitoidp.model.ListUsersResult;
import com.amazonaws.services.cognitoidp.model.SignUpRequest;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.amazonaws.services.cognitoidp.model.UsernameExistsException;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.requestModel.AdminCreatUserRequest;
import com.otsi.retail.authservice.requestModel.CreateRoleRequest;
import com.otsi.retail.authservice.requestModel.NewPasswordChallengeRequest;
import com.otsi.retail.authservice.requestModel.StoreVo;
import com.otsi.retail.authservice.utils.CognitoAtributes;

@Component
public class CognitoClient {

	private final AWSCognitoIdentityProvider client;

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

	
	
	private AWSCognitoIdentityProvider createCognitoClient() {

		AWSCredentials cred = new BasicAWSCredentials(ACCESS_KEY, SECRET_ACCESS_KEY);

		AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(cred);
		return AWSCognitoIdentityProviderClientBuilder.standard().withCredentials(credProvider).withRegion(REGION)
				.build();
	}

	
	
	public SignUpResult signUp(String userName, String email, String password, String givenName, String name,
			String phoneNo, String storeId) throws Exception {
		SignUpRequest request = new SignUpRequest().withClientId(CLIENT_ID).withUsername(userName)
				.withPassword(password).withUserAttributes((new AttributeType().withName("email").withValue(email)),
						(new AttributeType().withName("given_name").withValue(givenName)),
						(new AttributeType().withName("name").withValue(name)),
						(new AttributeType().withName("phone_number").withValue(phoneNo)),
						(new AttributeType().withName("gender").withValue("male")),
						(new AttributeType().withName(CognitoAtributes.USER_ASSIGNED_STORES).withValue(storeId)));

		SignUpResult result = client.signUp(request);
		System.out.println("----------");
		return result;
	}

	
	
	public ConfirmSignUpResult confirmSignUp(String userName, String confirmationCode) throws Exception {

		ConfirmSignUpRequest confirmSignUpRequest = new ConfirmSignUpRequest().withClientId(CLIENT_ID)
				.withUsername(userName).withConfirmationCode(confirmationCode);

		ConfirmSignUpResult result = client.confirmSignUp(confirmSignUpRequest);
		return result;
	}

	
	
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
		// AuthenticationResultType resultType = authResult.getAuthenticationResult();
		return authResult;
	}

	
	
	public AdminAddUserToGroupResult addRolesToUser(String groupName, String userName)
			throws InvalidParameterException, Exception {

		List<String> errors = new ArrayList<>();
		if (!StringUtils.hasLength(groupName)) {
			errors.add("GroupName missing");
		}
		if (!StringUtils.hasLength(userName)) {
			errors.add("UserName missing");
		}
		/*
		 * if (!StringUtils.hasLength(userPoolId)) { errors.add("UerPoolId missing"); }
		 */

		if (errors.isEmpty() && errors.size() == 0) {
			AdminAddUserToGroupRequest adminAddUserToGroupRequest = new AdminAddUserToGroupRequest();
			adminAddUserToGroupRequest.setGroupName(groupName);
			adminAddUserToGroupRequest.setUsername(userName);
			adminAddUserToGroupRequest.setUserPoolId(USERPOOL_ID);
			AdminAddUserToGroupResult result = client.adminAddUserToGroup(adminAddUserToGroupRequest);
			return result;
		} else {
			String error = errors.stream().collect(Collectors.joining(", "));
			throw new InvalidParameterException(error);
		}
	}

	
	
	public AdminUpdateUserAttributesResult addStoreToUser(List<Store> stores, String userName) throws Exception {
		AdminUpdateUserAttributesRequest updateUserAttributesRequest = new AdminUpdateUserAttributesRequest();
		List<AttributeType> attributes = new ArrayList<>();
		AttributeType attributeType = null;
		AdminGetUserResult userAttributes = getUserFromUserpool(userName);
		if (userAttributes != null) {
			attributeType = userAttributes.getUserAttributes().stream()
					.filter(a -> a.getName().equals("custom:assignedStores")).findFirst().get();
			StringBuilder assignedStores = new StringBuilder(attributeType.getValue());
			stores.stream().forEach(a -> assignedStores.append("," + a.getName()));
			attributes.add(new AttributeType().withName("custom:assignedStores").withValue(assignedStores.toString()));
			updateUserAttributesRequest.setUsername(userName);
			updateUserAttributesRequest.setUserPoolId(USERPOOL_ID);
			updateUserAttributesRequest.setUserAttributes(attributes);
			AdminUpdateUserAttributesResult result = client.adminUpdateUserAttributes(updateUserAttributesRequest);
			return result;
		} else
			throw new Exception("No user found with this username in userpool");
	}


	
	
	public AdminGetUserResult getUserFromUserpool(String userName) throws Exception {
		System.out.println(userName);
		AdminGetUserRequest getUserRequest = new AdminGetUserRequest();
		getUserRequest.setUsername(userName);
		getUserRequest.setUserPoolId(USERPOOL_ID);
		try {
			AdminGetUserResult result = client.adminGetUser(getUserRequest);
			if (result.getSdkHttpMetadata().getHttpStatusCode() == 200)
				return result;
			else
				throw new Exception("No user found" + result);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	
	
	public AdminCreateUserResult adminCreateUser(AdminCreatUserRequest request) throws Exception {
		AdminCreateUserRequest createUserRequest = new AdminCreateUserRequest();
		createUserRequest.setDesiredDeliveryMediums(Arrays.asList("EMAIL"));
		createUserRequest.setUserPoolId(USERPOOL_ID);
		createUserRequest.setUsername(request.getUsername());
		createUserRequest.setTemporaryPassword(request.getTempPassword());
		createUserRequest.setUserAttributes(Arrays.asList(
				new AttributeType().withName(CognitoAtributes.EMAIL).withValue(request.getEmail()),
				new AttributeType().withName(CognitoAtributes.PHONE_NUMBER).withValue(request.getPhoneNumber()),
				new AttributeType().withName(CognitoAtributes.NAME).withValue(request.getName()),
				new AttributeType().withName(CognitoAtributes.BIRTHDATE).withValue(request.getBirthDate()),
				new AttributeType().withName(CognitoAtributes.ADDRESS).withValue(request.getAddress()),
				new AttributeType().withName(CognitoAtributes.GENDER).withValue(request.getGender()),
				new AttributeType().withName(CognitoAtributes.PARENTID).withValue(request.getParentId()),
				new AttributeType().withName(CognitoAtributes.DOMAINID).withValue(request.getDomianId()),
				new AttributeType().withName(CognitoAtributes.ASSIGNED_STORES).withValue(setStores(request.getStores()))
		// new AttributeType().withName("email_verified").withValue("true")

		// new
		// AttributeType().withName(CognitoAtributes.PREFFERED_USERNAME).withValue(request.getUsername())
		));
		try {
			AdminCreateUserResult result = client.adminCreateUser(createUserRequest);
			return result;
		} catch (UsernameExistsException uee) {
			throw new Exception(uee.getMessage());
		} catch (InvalidParameterException ie) {
			throw new Exception(ie.getMessage());
		}

	}

	private String setStores(List<StoreVo> stores) {
		StringBuffer storesString=new StringBuffer();
		stores.stream().forEach(a->storesString.append(a.getName()+","));
		return storesString.toString();
	}

	
	
	public AdminInitiateAuthResult loginWithTempPassword(String email, String password) throws Exception {
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
		// AuthenticationResultType resultType = authResult.getAuthenticationResult();
		return authResult;

	}

	
	
	public AdminRespondToAuthChallengeResult respondAuthChalleng(NewPasswordChallengeRequest request) {
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
		return result;
	}

	
	
	public ForgotPasswordResult forgetPassword(String userName) throws Exception {
		ForgotPasswordRequest forgotPasswordRequest = new ForgotPasswordRequest();
		forgotPasswordRequest.setUsername(userName);
		forgotPasswordRequest.setClientId(CLIENT_ID);
		try {
			ForgotPasswordResult result = client.forgotPassword(forgotPasswordRequest);
			if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
				return result;
			} else {
				throw new Exception("failed");
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	
	
	public ConfirmForgotPasswordResult confirmForgetPassword(String userName,String confirmationCode,String newPassword) throws Exception {
		ConfirmForgotPasswordRequest confirmforgotPasswordRequest = new ConfirmForgotPasswordRequest();
		confirmforgotPasswordRequest.setUsername(userName);
		confirmforgotPasswordRequest.setClientId(CLIENT_ID);
		confirmforgotPasswordRequest.setConfirmationCode(confirmationCode);
		confirmforgotPasswordRequest.setPassword(newPassword);;
		try {
			ConfirmForgotPasswordResult result = client.confirmForgotPassword(confirmforgotPasswordRequest);
			if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
				return result;
			} else {
				throw new Exception("failed");
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}


	
	public CreateGroupResult createRole(CreateRoleRequest input) throws Exception {
		CreateGroupRequest request=new CreateGroupRequest();
		if(input.getRoleName()==null) {
			throw new Exception("Role name should not be null");
		}
		request.setGroupName(input.getRoleName());
		request.setDescription(input.getDescription());
		request.setPrecedence(input.getPrecedence());
		request.setUserPoolId(USERPOOL_ID);
		try {
		CreateGroupResult result=client.createGroup(request);
		if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
			return result;
		} else {
			throw new Exception("failed");
		}
	} catch (Exception e) {
		throw new Exception(e.getMessage());
	}
	}

	
	
	public AdminEnableUserResult userEnabled(String userName) throws Exception {
		AdminEnableUserRequest request=new AdminEnableUserRequest();
		request.setUsername(userName);
		request.setUserPoolId(USERPOOL_ID);
		try {
		AdminEnableUserResult result=	client.adminEnableUser(request);
		if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
			return result;
			}
			else 
				throw new Exception("failed to update");
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}		
	}

	
	
	public AdminDisableUserResult userDisabled(String userName) throws Exception {
		AdminDisableUserRequest request=new AdminDisableUserRequest();
		request.setUsername(userName);
		request.setUserPoolId(USERPOOL_ID);
		
		try {
		AdminDisableUserResult result=	client.adminDisableUser(request);
		if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
		return result;
		}
		else 
			throw new Exception("failed to update");	
	}catch (Exception e) {
		throw new Exception(e.getMessage());
	}
	}
	
	
	
	public ListUsersResult getAllUsers() throws Exception {
		ListUsersRequest request=new ListUsersRequest();
		request.setUserPoolId(USERPOOL_ID);
		try {
		ListUsersResult result=	client.listUsers(request);
	//	result.getUsers().stream().forEach(a->a.getAttributes().stream().forEach(b->b.););
		if(result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
			return result;
		  }
		else {
			throw new Exception("No users found");
		  }
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
}