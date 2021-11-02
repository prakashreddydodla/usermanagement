package com.otsi.retail.authservice.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Manideep Thaninki
 * @timestamp 12-07-2021
 * @desicription this class is responsible for to give implenetation for CognitoAuthService interface
 * 
 */

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.amazonaws.services.cognitoidp.model.AdminAddUserToGroupResult;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserResult;
import com.amazonaws.services.cognitoidp.model.AdminGetUserResult;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeResult;
import com.amazonaws.services.cognitoidp.model.AdminUpdateUserAttributesResult;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.AuthenticationResultType;
import com.amazonaws.services.cognitoidp.model.ConfirmSignUpResult;
import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.amazonaws.services.cognitoidp.model.ListUsersResult;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.nimbusds.jwt.JWTClaimsSet;

import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.Entity.UserAv;
import com.otsi.retail.authservice.Entity.UserDeatils;
import com.otsi.retail.authservice.Repository.RoleRepository;
import com.otsi.retail.authservice.Repository.StoreRepo;
import com.otsi.retail.authservice.Repository.UserAvRepo;
import com.otsi.retail.authservice.Repository.UserRepo;
import com.otsi.retail.authservice.configuration.AwsCognitoTokenProcessor;
import com.otsi.retail.authservice.requestModel.AdminCreatUserRequest;
import com.otsi.retail.authservice.requestModel.NewPasswordChallengeRequest;
import com.otsi.retail.authservice.responceModel.Response;

@Service
public class CognitoAuthService {

	@Autowired
	private CognitoClient cognitoClient;
	@Autowired
	private AwsCognitoTokenProcessor awsCognitoTokenProcessor;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserAvRepo userAvRepo;
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private StoreRepo storeRepo;
	private Logger logger = LoggerFactory.getLogger(CognitoAuthService.class);

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
			throw new Exception("");

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

	public Response addRoleToUser(String groupName, String userName) throws InvalidParameterException, Exception {
		Response res = new Response();
		AdminAddUserToGroupResult result = cognitoClient.addRolesToUser(groupName, userName);
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

	/**
	 * 
	 * @param stores
	 * @param userName
	 * @return
	 * @throws Exception
	 * 
	 * 
	 */
	public Response assignStoreToUser(List<Store> stores, String userName) throws Exception {
		Response res = new Response();
		try {
			AdminUpdateUserAttributesResult result = cognitoClient.addStoreToUser(stores, userName);

			if (result != null) {
				if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
					logger.info("Succesfully assigned store to user in cognito");
					res.setBody("Sucessfully Assign stores to user role");
					res.setStatusCode(200);
					Optional<UserDeatils> dbUser = userRepo.findByUserName(userName);
					if (dbUser.isPresent()) {
						List<Store> assignedStores = dbUser.get().getStores();
						if (!CollectionUtils.isEmpty(stores)) {
							stores.stream().forEach(a -> {
								Optional<Store> storeFromDb = storeRepo.findById(a.getId());
								assignedStores.add(storeFromDb.get());
							});
							UserDeatils user = dbUser.get();
							user.setStores(assignedStores);
							userRepo.save(user);
						}
					} else {
						res.setErrorDescription("user not updated in Local db from cognito");
						logger.error("user not updated in Local db from cognito");
					}
					return res;
				} else {
					logger.error("failed to assign store to user in cognito");
					res.setBody("Falied to updated role");
					res.setStatusCode(result.getSdkHttpMetadata().getHttpStatusCode());
					return res;
				}
			} else

				throw new Exception();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * 
	 *                   Create user (Customer/employee) based on role
	 */
	public Response createUser(AdminCreatUserRequest request) throws Exception {
		Response res = new Response();

		if (null == request.getRole()) {
			throw new Exception("Role should not be null");
		}
		/**
		 * If the user is custmore we need to save user in our local DB not in Cognito
		 */
		if (request.getRole().getRoleName().equalsIgnoreCase("CUSTOMER")) {
			UserDeatils user = new UserDeatils();
			user.setUserName(request.getUsername());
			user.setPhoneNumber(request.getPhoneNumber());
			user.setGender(request.getGender());
			Optional<Role> roleFromDb = roleRepository.findByRoleName(request.getRole().getRoleName());
			if (!roleFromDb.isPresent()) {
				Role roleEntity = new Role();
				roleEntity.setRoleName("CUSTOMER");
				roleEntity.setDiscription("Customer for store");
				roleEntity.setParentPrivilages(null);
				Role savedRole = roleRepository.save(roleEntity);
				user.setRole(savedRole);
			} else {
				user.setRole(roleFromDb.get());
			}
			try {
				UserDeatils savedUser = userRepo.save(user);
				res.setBody("Saved Sucessfully");
				res.setStatusCode(200);
				return res;
			} catch (Exception e) {
				res.setBody("Not Saved");
				res.setStatusCode(400);
				return res;
			}
		} else {
			try {
				/**
				 * If it not customer then only save user in cognito userpool
				 */
				
				
				
				
				
				
				
				
				
				

				AdminCreateUserResult result = cognitoClient.adminCreateUser(request);
				if (result != null) {
					if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
						res.setStatusCode(200);

						/**
						 * Adding role to the saved user in cognito userpool
						 */

						Response roleResponse = addRoleToUser(request.getRole().getRoleName(), request.getUsername());
						res.setBody("with user " + result);
					} else {
						res.setStatusCode(result.getSdkHttpMetadata().getHttpStatusCode());
						res.setBody("something went wrong");
					}
				}
				return res;
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}

	}
/**
 * This API is used to get the assigned stores to the user from Cognito userpool
 * @param userName
 * @return
 * @throws Exception
 */
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
/**
 * This API is used to Enable or disable the user in cogntio
 * @param userName
 * @param actionType
 * @return
 * @throws Exception
 */
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

//********************************** SCHEDULER JOB  ****************************************************
	/***
	 * This Scheduler will executes every day 12:00 am to migrating the user data in
	 * Cognito userpool to Our application Database
	 */

	@Scheduled(cron = "0 0 0 * * ?")
	public void saveUsersDataIntoDataBase() {
		try {

			logger.info(
					"************Saving user details from cognito userpool to UserManagement DB----Sechdular Statred********");
			logger.info("Schedular starts time---->" + LocalDateTime.now());
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -2);
			ListUsersResult resultFromCognito = cognitoClient.getAllUsers();
			resultFromCognito.getUsers().stream().filter(user -> user.getUserStatus().equalsIgnoreCase("confirmed")
					&& user.getUserLastModifiedDate().after(calendar.getTime())).forEach(a -> {
						try {
							
							saveUsersIndataBase(a.getUserCreateDate(), a.getUserLastModifiedDate(), a.getAttributes(),
									0L,a.getUsername());
						} catch (Exception e) {
							logger.error(e.getMessage());
							System.out.println(e.getMessage());
						}
					});
			logger.info("********Schedular Ended*********");
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	private String saveUsersIndataBase(Date userCreateDate, Date userLastModifiedDate, List<AttributeType> attributes,
			long roleId,String userName) throws Exception {
/*
 * 1=long,2=string,3=date,4=boolean
 */
		try {
			UserDeatils user = new UserDeatils();
			UserDeatils savedUser = saveUser(attributes, roleId);
			List<UserAv> userAvList = new ArrayList<>();
			UserAv userAv1 = new UserAv();
			userAv1.setType(3);
			userAv1.setName("userCreateDate");
			userAv1.setDateValue(userCreateDate);
			userAv1.setUserData(savedUser);
			userAvRepo.save(userAv1);
			UserAv userAv2 = new UserAv();
			userAv2.setType(3);
			userAv2.setName("userLastModifiedDate");
			userAv2.setDateValue(userLastModifiedDate);
			userAv2.setUserData(savedUser);
			userAvRepo.save(userAv2);
			attributes.stream().forEach(a -> {

				if (a.getName().equalsIgnoreCase("custom:parentId")) {
					UserAv userAv = new UserAv();
					userAv.setType(1);
					userAv.setName("parentId");
					userAv.setIntegerValue(Integer.parseInt(a.getValue()));
					userAv.setUserData(savedUser);
					userAvRepo.save(userAv);
				}
				if (a.getName().equalsIgnoreCase("address")) {
					UserAv userAv = new UserAv();
					userAv.setType(2);
					userAv.setName("address");
					userAv.setStringValue(a.getValue());
					userAv.setUserData(savedUser);
					userAvRepo.save(userAv);
				}
				if (a.getName().equalsIgnoreCase("birthdate")) {
					UserAv userAv = new UserAv();
					userAv.setType(3);
					userAv.setName("birthdate");
					userAv.setStringValue(a.getValue());
					userAv.setUserData(savedUser);
					userAvRepo.save(userAv);
					userAvList.add(userAv);
				}
				if (a.getName().equalsIgnoreCase("custom:assignedStores")) {

					String[] storenames = a.getValue().split(",");
					Arrays.asList(storenames).stream().forEach(storeName -> {

						Optional<Store> dbStoreRecord = storeRepo.findByName(storeName);
						if (dbStoreRecord.isPresent()) {
							List<Store> storesOfUser = savedUser.getStores();
							if (!CollectionUtils.isEmpty(storesOfUser)) {
								storesOfUser.add(dbStoreRecord.get());
								savedUser.setStores(storesOfUser);
							} else {
								List<Store> newStores = new ArrayList<>();
								newStores.add(dbStoreRecord.get());
								savedUser.setStores(newStores);
							}
							userRepo.save(savedUser);
						}
					});
				}
				if (a.getName().equalsIgnoreCase("custom:domianId")) {

					UserAv userAv = new UserAv();
					userAv.setType(1);
					userAv.setName("domianId");
					userAv.setIntegerValue(Integer.parseInt(a.getValue()));
					userAv.setUserData(savedUser);
					userAvRepo.save(userAv);
				}
				if (a.getName().equalsIgnoreCase("email")) {

					UserAv userAv = new UserAv();
					userAv.setType(2);
					userAv.setName("email");
					userAv.setStringValue(a.getValue());
					userAv.setUserData(savedUser);
					userAvRepo.save(userAv);
				}

				if (a.getName().equalsIgnoreCase("enabled")) {

					UserAv userAv = new UserAv();
					userAv.setType(4);
					userAv.setName("enabled");
					userAv.setBooleanValue(Boolean.getBoolean(a.getValue()));
					userAv.setUserData(savedUser);
					userAvRepo.save(userAv);
				}
				if (a.getName().equalsIgnoreCase("userStatus")) {

					UserAv userAv = new UserAv();
					userAv.setType(2);
					userAv.setName("userStatus");
					userAv.setStringValue(a.getValue());
					userAv.setUserData(savedUser);
					userAvRepo.save(userAv);
				}
				if (a.getName().equalsIgnoreCase("custom:clientDomians")) {

					UserAv userAv = new UserAv();
					userAv.setType(2);
					userAv.setName("clientDomians");
					userAv.setStringValue(a.getValue());
					userAv.setUserData(savedUser);
					userAvRepo.save(userAv);
				}
				if (a.getName().equalsIgnoreCase("custom:clientId")) {

					UserAv userAv = new UserAv();
					userAv.setType(1);
					userAv.setName("clientId");
					userAv.setIntegerValue(Integer.parseInt(a.getValue()));
					userAv.setUserData(savedUser);
					userAvRepo.save(userAv);
				}
				if (a.getName().equalsIgnoreCase("custom:isConfigUser")) {

					UserAv userAv = new UserAv();
					userAv.setType(4);
					userAv.setName("isConfigUser");
					userAv.setBooleanValue(Boolean.getBoolean(a.getValue()));
					userAv.setUserData(savedUser);
					userAvRepo.save(userAv);
				}
			});
			return "success";

		} catch (Exception e) {
			return "fail";
		}
	}

	

	private UserDeatils saveUser(List<AttributeType> attributes, long roleId) throws Exception {
		UserDeatils user = new UserDeatils();
		user.setCreatedDate(LocalDate.now());
		user.setLastModifyedDate(LocalDate.now());
		attributes.stream().forEach(a -> {
			if (a.getName().equalsIgnoreCase("name")) {
				user.setUserName(a.getValue());
			}
			if (a.getName().equalsIgnoreCase("gender")) {
				user.setGender(a.getValue());
			}
			if (a.getName().equalsIgnoreCase("phone_number")) {
				user.setPhoneNumber(a.getValue());
			}
		});

		try {
			if (roleId != 0L) {
				Optional<Role> role = roleRepository.findById(roleId);
				if (role.isPresent()) {
					user.setRole(role.get());
				}
			}
			UserDeatils dbResponce = userRepo.save(user);
			return dbResponce;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public AdminRespondToAuthChallengeResult authResponceForNewUser(NewPasswordChallengeRequest req) throws Exception {
		try {
			AdminRespondToAuthChallengeResult responceFromCognito = cognitoClient.respondAuthChalleng(req);
			if (responceFromCognito.getSdkHttpMetadata().getHttpStatusCode() == 200) {
				AdminGetUserResult userFromCognito = cognitoClient.getUserFromUserpool(req.getUserName());
				String res = saveUsersIndataBase(userFromCognito.getUserCreateDate(),
						userFromCognito.getUserLastModifiedDate(), userFromCognito.getUserAttributes(),
						req.getRoleId(),req.getUserName());
				if (!res.equalsIgnoreCase("success")) {
					throw new Exception("User confirmed in Cognito userpool but not saved in Database");
				}
				return responceFromCognito;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return null;
	}

}
