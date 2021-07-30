package com.otsi.rpd.authservice.services;

/**
 * @author Manideep Thaninki
 * @timestamp 12-07-2021
 * @desicription this class is responsible for to give implenetation for CognitoAuthService interface
 * 
 */

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AdminUpdateUserAttributesResult;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.amazonaws.services.cognitoidp.model.AuthenticationResultType;
import com.amazonaws.services.cognitoidp.model.ConfirmSignUpRequest;
import com.amazonaws.services.cognitoidp.model.ConfirmSignUpResult;
import com.amazonaws.services.cognitoidp.model.GetUserResult;
import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.amazonaws.services.cognitoidp.model.SignUpRequest;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.amazonaws.services.cognitoidp.model.UpdateUserAttributesResult;
import com.nimbusds.jwt.JWTClaimsSet;
import com.osti.rpd.authservice.responceModel.Response;
import com.otsi.rpd.authservice.configuration.AwsCognitoTokenProcessor;
import com.amazonaws.services.cognitoidp.model.AdminAddUserToGroupResult;
import com.amazonaws.services.cognitoidp.model.AdminGetUserResult;

@Service
public class CognitoAuthService {

	@Autowired
	private CognitoClient cognitoClient;
	@Autowired
	private AwsCognitoTokenProcessor awsCognitoTokenProcessor;

	public Response signUp(String userName, String email, String password, String givenName, String name,
			String phoneNo,String storeId) throws Exception {
		Response res = new Response();
		SignUpResult result = cognitoClient.signUp(userName, email, password, givenName, name,phoneNo, storeId);
		System.out.println(result.toString());
		if (result != null) {
			if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
				res.setStatusCode(200);
				res.setBody("Confirmation Code is sent to " + result.getCodeDeliveryDetails().getDestination());
			} else {
				res.setStatusCode(result.getSdkHttpMetadata().getHttpStatusCode());
				res.setBody("something went wrong");
			}
			return res;
		} else {
			throw new Exception();

		}
	}

	/**
	 * @apiNote Login using AWS Cognito service for authentication
	 *
	 * @param String userName, String password (username,password should not be
	 *               null)
	 * 
	 * @return key-value pairs for auth tokens(idToken,accessToken,refreshToken)
	 */

	public Map<String, String> login(String email, String password, String selectedStore) throws Exception {
		
		
try{
	AdminInitiateAuthResult authResult = cognitoClient.login(email, password);
	AuthenticationResultType resultType = authResult.getAuthenticationResult();

		String idToken = authResult.getAuthenticationResult().getIdToken();
		JWTClaimsSet claims = awsCognitoTokenProcessor.getCliamsFromToken(idToken);
		String assignedStores = (String) claims.getClaims().get("custom:userAssignedStores");
		String[] listOfStores = assignedStores.split(",");
		boolean isStoreFound = Boolean.FALSE;
		for (String store : listOfStores) {
			if (store.equals(selectedStore)) {
				isStoreFound = Boolean.TRUE;
			}
		}
		if (isStoreFound != Boolean.TRUE) {
			throw new Exception("User don't have access to this store");
		}
		 return new LinkedHashMap<String, String>() {
			{
				put("idToken", resultType.getIdToken());
				put("accessToken", resultType.getAccessToken());
				put("refreshToken", resultType.getRefreshToken());
				put("message", "Successfully login");
				put("expiresIn",resultType.getExpiresIn().toString());
			}
		};
	}catch(Exception e) {
		System.out.println(e.getMessage());
		String[] authError=e.getMessage().split("\\(");
		String error=authError[0];
		System.out.println("**********"+authError[0]);
		throw new Exception(error);
	}
	}
	
	public Response confirmSignUp(String userName, String confirmationCode) throws Exception {
		Response res = null;
		ConfirmSignUpResult result = cognitoClient.confirmSignUp(userName, confirmationCode);
		if (result != null) {
			if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
				res.setBody("Sucessfully confirmed your email");
				res.setStatusCode(200);
				return res;
			} else {
				res.setErrorDescription("Internal server error");
				res.setStatusCode(result.getSdkHttpMetadata().getHttpStatusCode());
				return res;

			}

		}
		return null;

	}

	public Response AddRoleToUser(String groupName, String userName, String userPoolId)
			throws InvalidParameterException, Exception {
		Response res = new Response();
		AdminAddUserToGroupResult result = cognitoClient.addRolesToUser(groupName, userName, userPoolId);
		if (result != null) {
			if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
				res.setBody("Sucessfully updated role");
				res.setStatusCode(200);
				return res;
			} else {
				res.setBody("Falied to updated role");
				res.setStatusCode(result.getSdkHttpMetadata().getHttpStatusCode());
				return res;
			}
		}else
		throw new Exception();

	}

	public AdminGetUserResult getUserInfo(String username) {
		return cognitoClient.getUserFromUserpool(username);
	}

	public Response assignStoreToUser(List<String> stores,String userName) throws Exception {
		Response res = new Response();
		AdminUpdateUserAttributesResult result=cognitoClient.addStoreToUser(stores, userName);
		if(result !=null) {
		if(result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
			res.setBody("Sucessfully Assign stores to user role");
			res.setStatusCode(200);
			return res;
		}else {
			res.setBody("Falied to updated role");
			res.setStatusCode(result.getSdkHttpMetadata().getHttpStatusCode());
			return res;
		}
		}else throw new Exception();
		
	}
	
}
