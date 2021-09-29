package com.otsi.retail.authservice.services;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

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
import org.springframework.scheduling.annotation.Scheduled;
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
import com.amazonaws.services.cognitoidp.model.ListUsersResult;
import com.amazonaws.services.cognitoidp.model.SignUpRequest;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.amazonaws.services.cognitoidp.model.UpdateUserAttributesResult;
import com.amazonaws.services.cognitoidp.model.UserType;
import com.nimbusds.jwt.JWTClaimsSet;
import com.otsi.retail.authservice.Entity.UserDeatils;
import com.otsi.retail.authservice.Entity.UserAv;
import com.otsi.retail.authservice.Repository.UserRepo;
import com.otsi.retail.authservice.configuration.AwsCognitoTokenProcessor;
import com.otsi.retail.authservice.requestModel.AdminCreatUserRequest;
import com.otsi.retail.authservice.responceModel.Response;
import com.amazonaws.services.cognitoidp.model.AdminAddUserToGroupResult;
import com.amazonaws.services.cognitoidp.model.AdminGetUserResult;

@Service
public class CognitoAuthService {

	@Autowired
	private CognitoClient cognitoClient;
	@Autowired
	private AwsCognitoTokenProcessor awsCognitoTokenProcessor;
	@Autowired
	private UserRepo userRepo;

	public Response signUp(String userName, String email, String password, String givenName, String name,
			String phoneNo, String storeId) throws Exception {
		Response res = new Response();
		SignUpResult result = cognitoClient.signUp(userName, email, password, givenName, name, phoneNo, storeId);
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

		try {
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
					put("expiresIn", resultType.getExpiresIn().toString());
				}
			};
		} catch (Exception e) {
			System.out.println(e.getMessage());
			String[] authError = e.getMessage().split("\\(");
			String error = authError[0];
			System.out.println("**********" + authError[0]);
			throw new Exception(error);
		}
	}

	/**
	 * @apiNote Confirm the signUp by entering confirmation code which is sent to
	 *          email
	 *
	 * @param String userName, String confirmationCode (username,password should not
	 *               be null)
	 * 
	 * @return Http status 200 for positive case & other than 200 for negitive cases
	 */

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
		} else
			throw new Exception();

	}

	public AdminGetUserResult getUserInfo(String username) throws Exception {
		try {
			return cognitoClient.getUserFromUserpool(username);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Response assignStoreToUser(List<String> stores, String userName) throws Exception {
		Response res = new Response();
		AdminUpdateUserAttributesResult result = cognitoClient.addStoreToUser(stores, userName);
		if (result != null) {
			if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
				res.setBody("Sucessfully Assign stores to user role");
				res.setStatusCode(200);
				return res;
			} else {
				res.setBody("Falied to updated role");
				res.setStatusCode(result.getSdkHttpMetadata().getHttpStatusCode());
				return res;
			}
		} else
			throw new Exception();

	}

	public Response createUser(AdminCreatUserRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getStoresForUser(String userName) throws Exception {

		AdminGetUserResult userDetails;
		try {
			userDetails = cognitoClient.getUserFromUserpool(userName);
			return userDetails.getUserAttributes().stream().filter(a -> a.getName().equals("custom:assignedStores"))
					.findFirst().get().getValue().split(",");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public String enableOrDisableUser(String userName, String actionType) throws Exception {
		try {
			if (actionType.equals("enable")) {
				cognitoClient.userEnabled(userName);
			}
			if (actionType.equals("disable")) {
				cognitoClient.userDisabled(userName);
			}

			return "sucessfully updated";
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	//@Scheduled(cron = "0 0 * * *")
	public void saveUsersDataIntoDataBase() {
		try {

			System.out.println("Scheduled job starts");
			Calendar calendar = Calendar.getInstance();
			System.out.println(calendar.getTime() + "------->current date");
			calendar.add(Calendar.DATE, -1);

			LocalDateTime currentdate = LocalDateTime.now();
			LocalDateTime yesterdayDate = currentdate.minusDays(1);

			ListUsersResult resultFromCognito = cognitoClient.getAllUsers();
			resultFromCognito.getUsers().stream()
					.forEach(a -> System.out.println(a.getUserLastModifiedDate() + "-------------->"));
			resultFromCognito.getUsers().stream()
					.filter(user -> user.getUserStatus().equalsIgnoreCase("confirmed")
							&& user.getUserLastModifiedDate().after(calendar.getTime()))
					.forEach(a -> saveUsersIndataBase(a));
			// userRepo.save(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void saveUsersIndataBase(UserType cognitoUser) {
		UserDeatils user = new UserDeatils();
		List<UserAv> userAvList = new ArrayList<>();
		cognitoUser.getAttributes().stream().forEach(a -> {
			if (a.getName().equalsIgnoreCase("name")) {
				user.setUserName(a.getValue());
			}
			if (a.getName().equalsIgnoreCase("gender")) {
				user.setGender(a.getValue());
			}
			if (a.getName().equalsIgnoreCase("phone_number")) {
				user.setPhoneNumber(a.getValue());
			}

			// for userAv table

			if (a.getName().equalsIgnoreCase("custom:parentId")) {
				UserAv userAv = new UserAv();
				userAv.setType(1);
				userAv.setName("parentId");
				userAv.setIntegerValue(Integer.parseInt(a.getValue()));
				userAvList.add(userAv);
			}
			if (a.getName().equalsIgnoreCase("address")) {

				UserAv userAv = new UserAv();
				userAv.setType(2);
				userAv.setName("address");
				userAv.setStringValue(a.getValue());
				userAvList.add(userAv);
			}
			if (a.getName().equalsIgnoreCase("birthdate")) {

				UserAv userAv = new UserAv();
				userAv.setType(3);
				userAv.setName("birthdate");
				userAv.setStringValue(a.getValue());
				userAvList.add(userAv);
			}
			if (a.getName().equalsIgnoreCase("custom:assignedStores")) {

				UserAv userAv = new UserAv();
				userAv.setType(2);
				userAv.setName("assignedStores");
				userAv.setStringValue(a.getValue());
				userAvList.add(userAv);
			}
			if (a.getName().equalsIgnoreCase("custom:domianId")) {

				UserAv userAv = new UserAv();
				userAv.setType(1);
				userAv.setName("domianId");
				userAv.setIntegerValue(Integer.parseInt(a.getValue()));
				userAvList.add(userAv);
			}
			if (a.getName().equalsIgnoreCase("email")) {

				UserAv userAv = new UserAv();
				userAv.setType(2);
				userAv.setName("email");
				userAv.setStringValue(a.getValue());
				userAvList.add(userAv);
			}
			if (a.getName().equalsIgnoreCase("userCreateDate")) {
				System.out.println("custom:parentId  ---->" + a.getValue());

				UserAv userAv = new UserAv();
				userAv.setType(3);
				userAv.setName("userCreateDate");
				userAv.setDateValue(LocalDateTime.parse(a.getValue()));
				// userAvList.add(userAv);
			}
			if (a.getName().equalsIgnoreCase("userLastModifiedDate")) {
				System.out.println("custom:parentId  ---->" + a.getValue());

				UserAv userAv = new UserAv();
				userAv.setType(3);
				userAv.setName("userLastModifiedDate");
				userAv.setDateValue(LocalDateTime.parse(a.getValue()));
				// userAvList.add(userAv);
			}
			if (a.getName().equalsIgnoreCase("enabled")) {

				UserAv userAv = new UserAv();
				userAv.setType(1);
				userAv.setName("enabled");
				userAv.setBooleanValue(Boolean.getBoolean(a.getValue()));
				userAvList.add(userAv);
			}
			if (a.getName().equalsIgnoreCase("userStatus")) {

				UserAv userAv = new UserAv();
				userAv.setType(1);
				userAv.setName("userStatus");
				userAv.setStringValue(a.getValue());
				userAvList.add(userAv);
			}
			user.setUserAv(userAvList);
			try {
				UserDeatils savedUser = userRepo.save(user);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		});

	}
}