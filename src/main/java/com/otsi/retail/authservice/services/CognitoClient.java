package com.otsi.retail.authservice.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.util.Asserts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.AdminAddUserToGroupRequest;
import com.amazonaws.services.cognitoidp.model.AdminAddUserToGroupResult;
import com.amazonaws.services.cognitoidp.model.AdminGetUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminGetUserResult;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AdminUpdateUserAttributesRequest;
import com.amazonaws.services.cognitoidp.model.AdminUpdateUserAttributesResult;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.amazonaws.services.cognitoidp.model.AuthenticationResultType;
import com.amazonaws.services.cognitoidp.model.ConfirmSignUpRequest;
import com.amazonaws.services.cognitoidp.model.ConfirmSignUpResult;
import com.amazonaws.services.cognitoidp.model.GetUserRequest;
import com.amazonaws.services.cognitoidp.model.GetUserResult;
import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.amazonaws.services.cognitoidp.model.SignUpRequest;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.amazonaws.services.cognitoidp.model.UpdateUserAttributesRequest;
import com.amazonaws.services.cognitoidp.model.UpdateUserAttributesResult;
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
		
		this.ACCESS_KEY=ACCESS_KEY;
		this.SECRET_ACCESS_KEY=SECRET_ACCESS_KEY;
		this.CLIENT_ID=CLIENT_ID;
		this.USERPOOL_ID=USERPOOL_ID;
		this.REGION=REGION;
		client = createCognitoClient();
	}
	
	private AWSCognitoIdentityProvider createCognitoClient() {

		AWSCredentials cred = new BasicAWSCredentials(ACCESS_KEY, SECRET_ACCESS_KEY);

		AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(cred);
		return AWSCognitoIdentityProviderClientBuilder.standard().withCredentials(credProvider)
				.withRegion(REGION).build();
	}

	public SignUpResult signUp(String userName, String email, String password,String givenName, String name,String phoneNo,String storeId) throws Exception {
		SignUpRequest request = new SignUpRequest().withClientId(CLIENT_ID).withUsername(userName).withPassword(password)
				.withUserAttributes(
						(new AttributeType().withName("email").withValue(email)),
						(new AttributeType().withName("given_name").withValue(givenName)),
						(new AttributeType().withName("name").withValue(name)),
						(new AttributeType().withName("phone_number").withValue(phoneNo)),
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
			//AuthenticationResultType resultType = authResult.getAuthenticationResult();
			return authResult;
			
	}

	
	public AdminAddUserToGroupResult addRolesToUser(String groupName,String userName,String userPoolId)
			throws InvalidParameterException,Exception {
		
		List<String> errors=new ArrayList<>();
		if(!StringUtils.hasLength(groupName)) {
			errors.add("GroupName missing");
		}
		if(!StringUtils.hasLength(userName)) {
			errors.add("UserName missing");
		}
		if(!StringUtils.hasLength(userPoolId)) {
			errors.add("UerPoolId missing");
		}
	
		if(errors.isEmpty()&&errors.size()==0) {
			AdminAddUserToGroupRequest adminAddUserToGroupRequest=new AdminAddUserToGroupRequest();
			adminAddUserToGroupRequest.setGroupName(groupName);
			adminAddUserToGroupRequest.setUsername(userName);
			adminAddUserToGroupRequest.setUserPoolId(userPoolId);
			AdminAddUserToGroupResult result=	client.adminAddUserToGroup(adminAddUserToGroupRequest);	
			return result;
		}else {
			String error=errors.stream().collect(Collectors.joining(", "));
			throw new InvalidParameterException(error);	
	}
	
}
	
	public AdminUpdateUserAttributesResult addStoreToUser(List<String> stores, String userName) throws Exception {
		AdminUpdateUserAttributesRequest updateUserAttributesRequest=new AdminUpdateUserAttributesRequest();
		List<AttributeType> attributes=new ArrayList<>();
		AttributeType attributeType=null;
AdminGetUserResult userAttributes =	getUserFromUserpool(userName);
		if(userAttributes!=null) {
		attributeType = userAttributes.getUserAttributes().stream().filter(a-> a.getName().equals(CognitoAtributes.USER_ASSIGNED_STORES)).findFirst().get();
		StringBuilder assignedStores= new StringBuilder(attributeType.getValue());
		
		
		stores.stream().forEach(a->assignedStores.append(a+","));
System.out.println(assignedStores+"___________________________");
		
		attributes.add(new AttributeType().withName(CognitoAtributes.USER_ASSIGNED_STORES).withValue(assignedStores.toString()));
		updateUserAttributesRequest.setUsername(userName);
		updateUserAttributesRequest.setUserPoolId(USERPOOL_ID);
		updateUserAttributesRequest.setUserAttributes(attributes);
		AdminUpdateUserAttributesResult result= client.adminUpdateUserAttributes(updateUserAttributesRequest);
		return result; 
		}else throw new Exception("No user found with this username in userpool");
	}
	
	public AdminGetUserResult getUserFromUserpool(String userName) {
		System.out.println(userName);
		AdminGetUserRequest getUserRequest=new AdminGetUserRequest();
		getUserRequest.setUsername(userName);
		getUserRequest.setUserPoolId(USERPOOL_ID);
		AdminGetUserResult result=client.adminGetUser(getUserRequest);
		return  result;
	}
	
	
	
}