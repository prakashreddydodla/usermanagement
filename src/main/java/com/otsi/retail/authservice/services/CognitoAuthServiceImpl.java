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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.amazonaws.services.cognitoidp.model.AdminAddUserToGroupResult;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserResult;
import com.amazonaws.services.cognitoidp.model.AdminDisableUserResult;
import com.amazonaws.services.cognitoidp.model.AdminEnableUserResult;
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
import com.nimbusds.jwt.JWTClaimsSet;
import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.Entity.UserAv;
import com.otsi.retail.authservice.Entity.UserDeatils;
import com.otsi.retail.authservice.Exceptions.UserAlreadyExistsException;
import com.otsi.retail.authservice.Repository.ClientcDomianRepo;
import com.otsi.retail.authservice.Repository.RoleRepository;
import com.otsi.retail.authservice.Repository.StoreRepo;
import com.otsi.retail.authservice.Repository.UserAvRepo;
import com.otsi.retail.authservice.Repository.UserRepo;
import com.otsi.retail.authservice.configuration.AwsCognitoTokenProcessor;
import com.otsi.retail.authservice.requestModel.AdminCreatUserRequest;
import com.otsi.retail.authservice.requestModel.ClientDomianVo;
import com.otsi.retail.authservice.requestModel.MasterDomianVo;
import com.otsi.retail.authservice.requestModel.NewPasswordChallengeRequest;
import com.otsi.retail.authservice.responceModel.Response;
import com.otsi.retail.authservice.utils.CognitoAtributes;
import com.otsi.retail.authservice.utils.DataTypesEnum;

@Service
public class CognitoAuthServiceImpl implements CognitoAuthService {

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
	private ClientcDomianRepo clientcDomianRepo;

	@Autowired
	private StoreRepo storeRepo;
	private Logger logger = LoggerFactory.getLogger(CognitoAuthServiceImpl.class);

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

	@Override
	public Response addRoleToUser(String groupName, String userName) throws InvalidParameterException, Exception {
		logger.info("assing role to user method starts");

		Response res = new Response();
		Optional<UserDeatils> userOptional = userRepo.findByUserName(userName);
		Optional<Role> roleOptional = roleRepository.findByRoleName(groupName);
		if (userOptional.isPresent() && roleOptional.isPresent()) {
			try {
				UserDeatils user = userOptional.get();
				Role role = roleOptional.get();
				user.setRole(role);
				UserDeatils savedUser = userRepo.save(user);
				logger.info("Assign role to user in Local DB is Sucess");

			} catch (Exception e) {
				logger.error(
						"Error occurs while assigning role to user in Local Database. Error is : " + e.getMessage());
				throw new RuntimeException("Role not assing to User. Please try again.");
			}
		}
		AdminAddUserToGroupResult result = cognitoClient.addRolesToUser(groupName, userName);
		if (result != null) {
			if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
				logger.info("Assign role to user in Cognito sucess");
				res.setBody("Sucessfully updated role");
				res.setStatusCode(200);
				logger.info("assing role to user method ends");
				return res;
			} else {
				res.setBody("Falied to updated role");
				res.setStatusCode(result.getSdkHttpMetadata().getHttpStatusCode());
				logger.error("Assign role to user in Cognito Falied");
				return res;
			}
		} else
			throw new Exception();

	}

	@Override
	public AdminGetUserResult getUserInfo(String username) throws Exception {
		try {
			return cognitoClient.getUserFromUserpool(username);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * This API is responsible for assgin stores for to user in userpool and local
	 * db
	 * 
	 * @param stores
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	@Override
	public Response assignStoreToUser(List<Store> stores, String userName) throws Exception {
		Response res = new Response();
		try {
			logger.info("assignStore to User method starts");
			Optional<UserDeatils> dbUser = userRepo.findByUserName(userName);
			if (dbUser.isPresent()) {
				List<Store> assignedStores = dbUser.get().getStores();
				if (!CollectionUtils.isEmpty(stores)) {
					stores.stream().forEach(a -> {
						Optional<Store> storeFromDb = storeRepo.findById(a.getId());
						if (!storeFromDb.isPresent()) {
							logger.error("Store details not found in Database");
							throw new RuntimeException("Store details not found in Database");
						}
						assignedStores.add(storeFromDb.get());
					});
					UserDeatils user = dbUser.get();
					user.setStores(assignedStores);
					userRepo.save(user);
					logger.info("Assign store to user in local DB--> Success");
				}
			} else {
				logger.error("UserDeatils not found in local DB");
				throw new RuntimeException("UserDeatils not found in local DB");

			}
			AdminUpdateUserAttributesResult result = cognitoClient.addStoreToUser(stores, userName);
			if (null != result) {
				if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
					logger.info("Succesfully assigned store to user in cognito");
					res.setBody("Sucessfully Assign stores to user role");
					res.setStatusCode(200);
					logger.info("AssignStore to User method Ends");
					return res;
				} else {
					logger.error("failed to assign store to user in Cognito");
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
	@Override
	public Response createUser(AdminCreatUserRequest request) throws Exception {
		Response res = new Response();

		/*
		 * if (null == request.getRole()) { throw new
		 * Exception("Role should not be null"); }
		 */
		/**
		 * If the user is custmore we need to save user in our local DB not in Cognito
		 */
		try {
		boolean usernameExists=	userRepo.existsByUserNameAndIsCustomer(request.getUsername(),Boolean.FALSE);
		if(usernameExists) {
			throw new RuntimeException("UserName already exists");
		}
		boolean userphoneNoExists=	userRepo.existsByPhoneNumberAndIsCustomer(request.getPhoneNumber(),Boolean.FALSE);
		if(userphoneNoExists) {
			throw new RuntimeException("Mobile Number already exists");
		}
		/*
		 * boolean customerNameExists=
		 * userRepo.existsByUserNameAndIsCustomer(request.getUsername(),Boolean.TRUE);
		 * if(customerNameExists) { throw new
		 * RuntimeException("Customer Name already exists");
		 * 
		 * }
		 */
		boolean csutomerPhoneNoExists=	userRepo.existsByPhoneNumberAndIsCustomer(request.getPhoneNumber(),Boolean.TRUE);
		if(csutomerPhoneNoExists) {
			throw new RuntimeException("Customer Phone number already exists");
	
		}
		
				if (null != request.getIsCustomer() && request.getIsCustomer().equalsIgnoreCase("true")) {
					UserDeatils user = new UserDeatils();
					user.setUserName(request.getUsername());
					user.setPhoneNumber(request.getPhoneNumber());
					user.setGender(request.getGender());
					user.setCreatedBy(request.getCreatedBy());
					
					 user.setCustomer(Boolean.TRUE);
					
						UserDeatils savedUser = userRepo.save(user);
						res.setBody("Saved Sucessfully");
						res.setStatusCode(200);
						return res;
					
				} else {
				
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
								if (null != request.getRole().getRoleName() && null != request.getUsername()) {
									Response roleResponse = addRoleToUser(request.getRole().getRoleName(),
											request.getUsername());
									res.setBody("with user " + result);
								}
							} else {
								res.setStatusCode(result.getSdkHttpMetadata().getHttpStatusCode());
								res.setBody("something went wrong");
							}
						}
						return res;
					
				}
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * This API is used to get the assigned stores to the user from Cognito userpool
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	@Override
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
	 * 
	 * @param userName
	 * @param actionType
	 * @return
	 * @throws Exception
	 */
	@Override
	public String enableOrDisableUser(String userName, String actionType) throws Exception {
		try {
			if (actionType.equals("enable")) {
				AdminEnableUserResult res = cognitoClient.userEnabled(userName);
				if (res.getSdkHttpMetadata().getHttpStatusCode() == 200) {
					Optional<UserDeatils> userOptional = userRepo.findByUserName(userName);
					UserDeatils user = userOptional.get();
					user.setActive(Boolean.TRUE);
					user.setLastModifyedDate(LocalDate.now());
					userRepo.save(user);
				}
			}
			if (actionType.equals("disable")) {
				AdminDisableUserResult res = cognitoClient.userDisabled(userName);
				if (res.getSdkHttpMetadata().getHttpStatusCode() == 200) {
					Optional<UserDeatils> userOptional = userRepo.findByUserName(userName);
					UserDeatils user = userOptional.get();
					user.setActive(Boolean.FALSE);
					user.setLastModifyedDate(LocalDate.now());
					userRepo.save(user);
				}
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

	// @Scheduled(cron = "0 0 0 * * ?")
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
									0L, a.getUsername());
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

	/**
	 * This API is used for get the user from Cognito userpool and save it into our
	 * local DB after user performs change temp password
	 * 
	 * @param userCreateDate
	 * @param userLastModifiedDate
	 * @param attributes
	 * @param roleId
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	private String saveUsersIndataBase(Date userCreateDate, Date userLastModifiedDate, List<AttributeType> attributes,
			long roleId, String userName) throws Exception {
		/*
		 * 1=long,2=string,3=date,4=boolean
		 */
		try {
			UserDeatils user = new UserDeatils();
			/**
			 * Save the User first along with role
			 */
			UserDeatils savedUser = saveUser(attributes, roleId);
			List<UserAv> userAvList = new ArrayList<>();
			UserAv userAv1 = new UserAv();
			userAv1.setType(DataTypesEnum.DATE.getValue());
			userAv1.setName(CognitoAtributes.USER_CREATE_DATE);
			userAv1.setDateValue(userCreateDate);
			userAv1.setUserData(savedUser);
			userAvRepo.save(userAv1);

			UserAv userAv2 = new UserAv();
			userAv2.setType(DataTypesEnum.DATE.getValue());
			userAv2.setName(CognitoAtributes.USER_LAST_MODIFIEDDATE);
			userAv2.setDateValue(userLastModifiedDate);
			userAv2.setUserData(savedUser);
			userAvRepo.save(userAv2);
			attributes.stream().forEach(a -> {

				if (a.getName().equalsIgnoreCase(CognitoAtributes.PARENTID)) {
					UserAv userAv = new UserAv();
					userAv.setType(DataTypesEnum.INTEGER.getValue());
					userAv.setName(CognitoAtributes.PARENTID);
					userAv.setIntegerValue(Integer.parseInt(a.getValue()));
					userAv.setUserData(savedUser);
					userAvRepo.save(userAv);
				}
				if (a.getName().equalsIgnoreCase(CognitoAtributes.ADDRESS

				)) {
					UserAv userAv = new UserAv();
					userAv.setType(DataTypesEnum.STRING.getValue());
					userAv.setName(CognitoAtributes.ADDRESS);
					userAv.setStringValue(a.getValue());
					userAv.setUserData(savedUser);
					userAvRepo.save(userAv);
				}
				if (a.getName().equalsIgnoreCase(CognitoAtributes.BIRTHDATE)) {
					UserAv userAv = new UserAv();
					userAv.setType(DataTypesEnum.DATE.getValue());
					userAv.setName(CognitoAtributes.BIRTHDATE);
					userAv.setStringValue(a.getValue());
					userAv.setUserData(savedUser);
					userAvRepo.save(userAv);
					userAvList.add(userAv);
				}
				if (a.getName().equalsIgnoreCase(CognitoAtributes.ASSIGNED_STORES)) {

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
				if (a.getName().equalsIgnoreCase(CognitoAtributes.DOMAINID)) {

					UserAv userAv = new UserAv();
					userAv.setType(DataTypesEnum.INTEGER.getValue());
					userAv.setName(CognitoAtributes.DOMAINID);
					userAv.setIntegerValue(Integer.parseInt(a.getValue()));
					userAv.setUserData(savedUser);
					userAvRepo.save(userAv);
				}
				if (a.getName().equalsIgnoreCase(CognitoAtributes.EMAIL)) {

					UserAv userAv = new UserAv();
					userAv.setType(DataTypesEnum.STRING.getValue());
					userAv.setName(CognitoAtributes.EMAIL);
					userAv.setStringValue(a.getValue());
					userAv.setUserData(savedUser);
					userAvRepo.save(userAv);
				}

				if (a.getName().equalsIgnoreCase(CognitoAtributes.ENABLED)) {

					UserAv userAv = new UserAv();
					userAv.setType(DataTypesEnum.BOOLEAN.getValue());
					userAv.setName(CognitoAtributes.IS_ACTIVE);
					userAv.setBooleanValue(Boolean.getBoolean(a.getValue()));
					userAv.setUserData(savedUser);
					userAvRepo.save(userAv);
				}
				if (a.getName().equalsIgnoreCase(CognitoAtributes.USER_STATUS)) {

					UserAv userAv = new UserAv();
					userAv.setType(DataTypesEnum.STRING.getValue());
					userAv.setName(CognitoAtributes.USER_STATUS);
					userAv.setStringValue(a.getValue());
					userAv.setUserData(savedUser);
					userAvRepo.save(userAv);
				}
				if (a.getName().equalsIgnoreCase(CognitoAtributes.CLIENTDOMIANS)) {

					String[] clientDomianIds = a.getValue().split(",");
					Arrays.asList(clientDomianIds).stream().forEach(clientDomianId -> {

						Optional<ClientDomains> dbClientDomainRecord = clientcDomianRepo
								.findById(Long.parseLong(clientDomianId));
						if (dbClientDomainRecord.isPresent()) {
							List<ClientDomains> clientDomiansOfUser = savedUser.getClientDomians();
							if (!CollectionUtils.isEmpty(clientDomiansOfUser)) {
								clientDomiansOfUser.add(dbClientDomainRecord.get());
								savedUser.setClientDomians(clientDomiansOfUser);
							} else {
								List<ClientDomains> clientDomains = new ArrayList<>();
								clientDomains.add(dbClientDomainRecord.get());
								savedUser.setClientDomians(clientDomains);
							}
							userRepo.save(savedUser);

						} else {
							logger.error("No client domians found in DB");
						}
					});

					UserAv userAv = new UserAv();
					userAv.setType(DataTypesEnum.STRING.getValue());
					userAv.setName(CognitoAtributes.CLIENTDOMIANS);
					userAv.setStringValue(a.getValue());
					userAv.setUserData(savedUser);
					userAvRepo.save(userAv);
				}
				if (a.getName().equalsIgnoreCase(CognitoAtributes.CLIENT_ID)) {

					UserAv userAv = new UserAv();
					userAv.setType(DataTypesEnum.INTEGER.getValue());
					userAv.setName(CognitoAtributes.CLIENT_ID);
					userAv.setIntegerValue(Integer.parseInt(a.getValue()));
					userAv.setUserData(savedUser);
					userAvRepo.save(userAv);
				}
				if (a.getName().equalsIgnoreCase(CognitoAtributes.IS_CONFIGUSER)) {

					UserAv userAv = new UserAv();
					userAv.setType(DataTypesEnum.BOOLEAN.getValue());
					userAv.setName(CognitoAtributes.IS_CONFIGUSER);
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
			if (a.getName().equalsIgnoreCase(CognitoAtributes.USER_NAME)) {
				user.setUserName(a.getValue());
			}
			if (a.getName().equalsIgnoreCase(CognitoAtributes.GENDER)) {
				user.setGender(a.getValue());
			}
			if (a.getName().equalsIgnoreCase(CognitoAtributes.PHONE_NUMBER)) {
				user.setPhoneNumber(a.getValue());
			}
			if (a.getName().equalsIgnoreCase(CognitoAtributes.CREATED_BY)) {
				user.setCreatedBy(a.getValue());
			}
		});
		try {
			UserDeatils userSaved = userRepo.save(user);
			if (roleId != 0L) {
				Optional<Role> role = roleRepository.findById(roleId);
				if (role.isPresent()) {
					userSaved.setRole(role.get());
				}
			}
			UserDeatils dbResponce = userRepo.save(userSaved);
			return dbResponce;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * This API is responsible for confirm the user in userpool based on newPassword
	 * and session. If it is success we will get 200 responce.
	 * 
	 * @param req
	 * @return AdminRespondToAuthChallengeResult
	 * @throws Exception
	 * 
	 */
	@Override
	public AdminRespondToAuthChallengeResult authResponceForNewUser(NewPasswordChallengeRequest req) throws Exception {
		try {
			AdminRespondToAuthChallengeResult responceFromCognito = cognitoClient.respondAuthChalleng(req);
			if (responceFromCognito.getSdkHttpMetadata().getHttpStatusCode() == 200) {
				/**
				 * Get confirmed user details from cognito userpool
				 */

				AdminGetUserResult userFromCognito = cognitoClient.getUserFromUserpool(req.getUserName());

				/**
				 * Save the confirmed user into Userdetails table in Usermangement DB
				 */

				Optional<Role> role = roleRepository.findByRoleName(req.getRoleName());
				long roleId = 0L;
				if (role.isPresent()) {
					roleId = role.get().getRoleId();
				}
				String res = saveUsersIndataBase(userFromCognito.getUserCreateDate(),
						userFromCognito.getUserLastModifiedDate(), userFromCognito.getUserAttributes(), roleId,
						req.getUserName());
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
