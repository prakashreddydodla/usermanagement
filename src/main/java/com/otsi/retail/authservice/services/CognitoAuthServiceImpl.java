package com.otsi.retail.authservice.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Manideep Thaninki
 * @timestamp 12-07-2021
 * @desicription this class is responsible for to give implenetation for CognitoAuthService interface
 * 
 */

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.services.cognitoidp.model.AdminAddUserToGroupResult;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserResult;
import com.amazonaws.services.cognitoidp.model.AdminDisableUserResult;
import com.amazonaws.services.cognitoidp.model.AdminEnableUserResult;
import com.amazonaws.services.cognitoidp.model.AdminGetUserResult;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeResult;
import com.amazonaws.services.cognitoidp.model.AdminUpdateUserAttributesResult;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.Entity.UserAv;
import com.otsi.retail.authservice.Entity.UserDetails;
import com.otsi.retail.authservice.Repository.ClientDetailsRepo;
import com.otsi.retail.authservice.Repository.ClientcDomianRepo;
import com.otsi.retail.authservice.Repository.RoleRepository;
import com.otsi.retail.authservice.Repository.StoreRepo;
import com.otsi.retail.authservice.Repository.UserAvRepo;
import com.otsi.retail.authservice.Repository.UserRepository;
import com.otsi.retail.authservice.requestModel.AdminCreatUserRequest;
import com.otsi.retail.authservice.requestModel.NewPasswordChallengeRequest;
import com.otsi.retail.authservice.requestModel.UpdateUserAttribute;
import com.otsi.retail.authservice.responceModel.Response;
import com.otsi.retail.authservice.utils.CognitoAtributes;
import com.otsi.retail.authservice.utils.CommonUtilities;
import com.otsi.retail.authservice.utils.Constants;
import com.otsi.retail.authservice.utils.DataTypesEnum;
import com.otsi.retail.authservice.utils.ErrorCodes;

@Service
public class CognitoAuthServiceImpl implements CognitoAuthService {

	@Autowired
	private CognitoClient cognitoClient;
	// @Autowired
	// private AwsCognitoTokenProcessor awsCognitoTokenProcessor;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserAvRepo userAvRepo;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ClientcDomianRepo clientcDomianRepo;

	@Autowired
	private ClientDetailsRepo clientDetailsRepo;

	@Autowired
	private StoreRepo storeRepo;
	private Logger logger = LogManager.getLogger(CognitoAuthServiceImpl.class);

	@Override
	public Response addRoleToUser(String groupName, String userName) throws InvalidParameterException, Exception {
		logger.info("#############  assing role to user method starts  ###############");

		Response res = new Response();
		Optional<UserDetails> userOptional = userRepository.findByUserName(userName);
		Optional<Role> roleOptional = roleRepository.findByRoleName(groupName);
		if (userOptional.isPresent() && roleOptional.isPresent()) {
			try {
				UserDetails user = userOptional.get();
				Role role = roleOptional.get();
				user.setRole(role);
				userRepository.save(user);
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
			throw new Exception("no users found with this users");

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
			Optional<UserDetails> dbUser = userRepository.findByUserName(userName);
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
					UserDetails user = dbUser.get();
					user.setStores(assignedStores);
					userRepository.save(user);
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

				throw new Exception("No user found with this username in userpool");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 
	 * @param adminCreateUserRequest
	 * @return
	 * @throws Exception
	 * 
	 *                   Create user (Customer/employee) based on role
	 */
	@Override
	public ResponseEntity<?> createUser(AdminCreatUserRequest adminCreateUserRequest,Long clientId) {
		try {
			boolean usernameExists = userRepository.existsByUserNameAndIsCustomer(adminCreateUserRequest.getUsername(),
					Boolean.FALSE);
			if (usernameExists) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"username already exists " + adminCreateUserRequest.getUsername());
			}
			boolean userphoneNoExists = userRepository.existsByPhoneNumber(adminCreateUserRequest.getPhoneNumber());

			if (userphoneNoExists) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"mobile number already exists " + adminCreateUserRequest.getPhoneNumber());
			}
			List<String> missingFileds = new ArrayList<>();
			// create customer
			if (null != adminCreateUserRequest.getIsCustomer() && adminCreateUserRequest.getIsCustomer()) {
				UserDetails user = new UserDetails();
				user.setUserName(adminCreateUserRequest.getUsername());
				user.setPhoneNumber(adminCreateUserRequest.getPhoneNumber());
				user.setGender(adminCreateUserRequest.getGender());
				user.setCreatedBy(adminCreateUserRequest.getCreatedBy());
				user.setIsCustomer(Boolean.TRUE);
				if(ObjectUtils.isNotEmpty(clientId)) {
					List<Long> ids = new ArrayList<>();
					ids.add(clientId);		
					List<ClientDetails> clientDetail = clientDetailsRepo.findByIdIn(ids);
					if(!(CollectionUtils.isEmpty(clientDetail))) {
						user.setClient(clientDetail);
					}
				}
				user = userRepository.save(user);
				adminCreateUserRequest.setId(user.getId());
				return ResponseEntity.ok(CommonUtilities.buildSuccessResponse(Constants.SUCCESS, Constants.RESULT));

			}

			else if (adminCreateUserRequest.getIsConfigUser() != null && adminCreateUserRequest.getIsConfigUser()) {
				validateConfigUser(adminCreateUserRequest, missingFileds);
			}

			// If it not customer then only save user in cognito userpool
			else {
				validateOtherUser(adminCreateUserRequest, missingFileds);
			}
			if (missingFileds.size() > 0) {
				if (adminCreateUserRequest.getIsConfigUser()
						&& StringUtils.isNotEmpty(adminCreateUserRequest.getClientId())) {
					deleteClientWhileConfigUserNotCreated(adminCreateUserRequest.getClientId());
				}
				logger.error("missing required fields : " + missingFileds);
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "missing required fields : " + missingFileds);
			}

			AdminCreateUserResult result = cognitoClient.adminCreateUser(adminCreateUserRequest);
			if (result != null) {
				if (result.getSdkHttpMetadata().getHttpStatusCode() == HttpStatus.OK.value()) {
					if (null != adminCreateUserRequest.getRole().getRoleName()
							&& null != adminCreateUserRequest.getUsername()) {
						addRoleToUser(adminCreateUserRequest.getRole().getRoleName(),
								adminCreateUserRequest.getUsername());
					}
					return ResponseEntity.ok(result);

				} else {
					// When we create config user for client if config user not created,need to
					// delete client
					if (adminCreateUserRequest.getIsConfigUser()) {
						if (StringUtils.isNotEmpty(adminCreateUserRequest.getClientId())) {
							deleteClientWhileConfigUserNotCreated(adminCreateUserRequest.getClientId());
						}
					}
					logger.error("admin user creation failed {}", result);
					throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
							ErrorCodes.ADMIN_CREATE_USER_FAILED);
				}
			}

		} catch (Exception e) {
			if (adminCreateUserRequest.getIsConfigUser()) {
				if (StringUtils.isNotEmpty(adminCreateUserRequest.getClientId())) {
					deleteClientWhileConfigUserNotCreated(adminCreateUserRequest.getClientId());
				}
			}
			logger.error("client creation failed " + e);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"client creation failed: " + ErrorCodes.ADMIN_CREATE_USER_FAILED);
		}
		return ResponseEntity.ok().build();
	}

	private void validateConfigUser(AdminCreatUserRequest adminCreateUserRequest, List<String> missingFileds) {
		if (StringUtils.isEmpty(adminCreateUserRequest.getEmail())) {
			missingFileds.add("email");
		}
		if (StringUtils.isEmpty(adminCreateUserRequest.getName())) {
			missingFileds.add("name");
		}
		if (StringUtils.isEmpty(adminCreateUserRequest.getPhoneNumber())) {
			missingFileds.add("phoneNumber");
		}

		if (StringUtils.isEmpty(adminCreateUserRequest.getUsername())) {
			missingFileds.add("username");
		}
		if (StringUtils.isEmpty(adminCreateUserRequest.getClientId())) {
			missingFileds.add("clientId");
		}
	}

	private void validateOtherUser(AdminCreatUserRequest adminCreateUserRequest, List<String> missingFileds) {
		if (adminCreateUserRequest.getAddress() == null) {
			missingFileds.add("address");
		}
		if (adminCreateUserRequest.getBirthDate() == null) {
			missingFileds.add("birthDate");
		}

		if (null == adminCreateUserRequest.getClientDomain()) {
			missingFileds.add("clientDomain");
		}
		/*
		 * if (adminCreateUserRequest.getDomianId() != null) {
		 * missingFileds.add("domianId"); }
		 */
		if (StringUtils.isEmpty(adminCreateUserRequest.getClientId())) {
			missingFileds.add("clientId");
		}
		if (StringUtils.isEmpty(adminCreateUserRequest.getEmail())) {
			missingFileds.add("email");
		}
		if (adminCreateUserRequest.getGender() == null) {
			missingFileds.add("gender");
		}
		if (null == adminCreateUserRequest.getIsConfigUser()) {
			missingFileds.add("isConfigUser");
		}
		if (null == adminCreateUserRequest.getIsSuperAdmin()) {
			missingFileds.add("isSuperAdmin");
		}
		if (StringUtils.isEmpty(adminCreateUserRequest.getName())) {
			missingFileds.add("name");
		}
		/*
		 * if (null == adminCreateUserRequest.getParentId()) {
		 * missingFileds.add("parentId"); }
		 */
		if (StringUtils.isEmpty(adminCreateUserRequest.getPhoneNumber())) {
			missingFileds.add("phoneNumber");
		}
		if (null == adminCreateUserRequest.getStores()) {
			missingFileds.add("stores");
		}
		if (StringUtils.isEmpty(adminCreateUserRequest.getUsername())) {
			missingFileds.add("username");
		}
	}

	private void deleteClientWhileConfigUserNotCreated(String clientId) {
		clientDetailsRepo.deleteById(Long.parseLong(clientId));
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
			logger.debug(e.getMessage());
			logger.error(e.getMessage());
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
					Optional<UserDetails> userOptional = userRepository.findByUserName(userName);
					UserDetails user = userOptional.get();
					user.setIsActive(Boolean.TRUE);
					userRepository.save(user);
				}
			}
			if (actionType.equals("disable")) {
				AdminDisableUserResult res = cognitoClient.userDisabled(userName);
				if (res.getSdkHttpMetadata().getHttpStatusCode() == 200) {
					Optional<UserDetails> userOptional = userRepository.findByUserName(userName);
					UserDetails user = userOptional.get();
					user.setIsActive(Boolean.FALSE);
					userRepository.save(user);
				}
			}
			return "sucessfully updated";
		} catch (Exception e) {
			logger.debug(e.getMessage());
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
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
	 * @param enable
	 * @return
	 * @throws Exception
	 */
	private Long saveUser(Date userCreateDate, Date userLastModifiedDate, List<AttributeType> attributes, long roleId,
			String userName, Boolean enable, Long clientId) throws Exception {

		// save user along with role
		UserDetails user = saveUser(attributes, roleId, userName, enable, clientId);

		List<UserAv> userAvList = new ArrayList<>();
		UserAv userAv1 = new UserAv();
		userAv1.setType(DataTypesEnum.DATE.getValue());
		userAv1.setName(CognitoAtributes.USER_CREATE_DATE);
		userAv1.setDateValue(userCreateDate);
		userAv1.setUserData(user);
		userAvRepo.save(userAv1);

		UserAv userAv2 = new UserAv();
		userAv2.setType(DataTypesEnum.DATE.getValue());
		userAv2.setName(CognitoAtributes.USER_LAST_MODIFIEDDATE);
		userAv2.setDateValue(userLastModifiedDate);
		userAv2.setUserData(user);
		userAvRepo.save(userAv2);

		attributes.stream().forEach(a -> {

			if (a.getName().equalsIgnoreCase(CognitoAtributes.PARENTID)) {
				UserAv userAv = new UserAv();
				userAv.setType(DataTypesEnum.INTEGER.getValue());
				userAv.setName(CognitoAtributes.PARENTID);
				userAv.setIntegerValue(Long.parseLong(a.getValue()));
				userAv.setUserData(user);
				userAvRepo.save(userAv);
			}
			if (a.getName().equalsIgnoreCase(CognitoAtributes.ADDRESS)) {
				UserAv userAv = new UserAv();
				userAv.setType(DataTypesEnum.STRING.getValue());
				userAv.setName(CognitoAtributes.ADDRESS);
				userAv.setStringValue(a.getValue());
				userAv.setUserData(user);
				userAvRepo.save(userAv);
			}
			if (a.getName().equalsIgnoreCase(CognitoAtributes.BIRTHDATE)) {
				UserAv userAv = new UserAv();
				userAv.setType(DataTypesEnum.DATE.getValue());
				userAv.setName(CognitoAtributes.BIRTHDATE);
				userAv.setStringValue(a.getValue());
				userAv.setUserData(user);
				userAvRepo.save(userAv);
				userAvList.add(userAv);
			}
			if (a.getName().equalsIgnoreCase(CognitoAtributes.ASSIGNED_STORES)) {

				String[] storenames = a.getValue().split(",");
				
				List<String> list = Arrays.asList(storenames); 
			       
		        List<String> l = new ArrayList<>(list);
				
			
 				l.stream().forEach(storeName -> {
					String[] sName = storeName.split(":");
					

					Optional<Store> stores = storeRepo.findById(Long.parseLong(sName[1]));
					if (stores.isPresent()) {
						List<Store> userStores = user.getStores();

						if (!CollectionUtils.isEmpty(userStores)) {
							
								userStores.add(stores.get());
							
							user.setStores(userStores);
						}

						else {
							List<Store> newStores = new ArrayList<>();
							
								newStores.add(stores.get());
							

							user.setStores(newStores);
						}
					//	userRepository.save(user);

					}
				});
				userRepository.save(user);

			}
			if (a.getName().equalsIgnoreCase(CognitoAtributes.DOMAINID)) {

				UserAv userAv = new UserAv();
				userAv.setType(DataTypesEnum.INTEGER.getValue());
				userAv.setName(CognitoAtributes.DOMAINID);
				userAv.setIntegerValue(Long.parseLong(a.getValue()));
				userAv.setUserData(user);
				userAvRepo.save(userAv);
			}
			if (a.getName().equalsIgnoreCase(CognitoAtributes.EMAIL)) {
				UserAv userAv = new UserAv();
				userAv.setType(DataTypesEnum.STRING.getValue());
				userAv.setName(CognitoAtributes.EMAIL);
				userAv.setStringValue(a.getValue());
				userAv.setUserData(user);
				userAvRepo.save(userAv);
			}

			if (a.getName().equalsIgnoreCase(CognitoAtributes.ENABLED)) {

				UserAv userAv = new UserAv();
				userAv.setType(DataTypesEnum.BOOLEAN.getValue());
				userAv.setName(CognitoAtributes.IS_ACTIVE);
				userAv.setBooleanValue(Boolean.getBoolean(a.getValue()));
				userAv.setUserData(user);
				userAvRepo.save(userAv);
			}

			if (a.getName().equalsIgnoreCase(CognitoAtributes.USER_STATUS)) {

				UserAv userAv = new UserAv();
				userAv.setType(DataTypesEnum.STRING.getValue());
				userAv.setName(CognitoAtributes.USER_STATUS);
				userAv.setStringValue(a.getValue());
				userAv.setUserData(user);
				userAvRepo.save(userAv);
			}

			if (a.getName().equalsIgnoreCase(CognitoAtributes.CLIENTDOMIANS)) {

				String[] clientDomianIds = a.getValue().split(",");
				Arrays.asList(clientDomianIds).stream().forEach(clientDomianId -> {

					Optional<ClientDomains> dbClientDomainRecord = clientcDomianRepo
							.findById(Long.parseLong(clientDomianId));
					if (dbClientDomainRecord.isPresent()) {
						List<ClientDomains> clientDomiansOfUser = user.getClientDomians();
						if (!CollectionUtils.isEmpty(clientDomiansOfUser)) {
							clientDomiansOfUser.add(dbClientDomainRecord.get());
							user.setClientDomians(clientDomiansOfUser);
						} else {
							List<ClientDomains> clientDomains = new ArrayList<>();
							clientDomains.add(dbClientDomainRecord.get());
							user.setClientDomians(clientDomains);
						}
						userRepository.save(user);
					} else {
						logger.error("No client domians found in DB");
					}
				});

				UserAv userAv = new UserAv();
				userAv.setType(DataTypesEnum.STRING.getValue());
				userAv.setName(CognitoAtributes.CLIENTDOMIANS);
				userAv.setStringValue(a.getValue());
				userAv.setUserData(user);
				userAvRepo.save(userAv);
			}
			if (a.getName().equalsIgnoreCase(CognitoAtributes.CLIENT_ID)) {

				UserAv userAv = new UserAv();
				userAv.setType(DataTypesEnum.INTEGER.getValue());
				userAv.setName(CognitoAtributes.CLIENT_ID);
				userAv.setIntegerValue(Long.parseLong(a.getValue()));
				userAv.setUserData(user);
				userAvRepo.save(userAv);
			}
			if (a.getName().equalsIgnoreCase(CognitoAtributes.IS_CONFIGUSER)) {

				UserAv userAv = new UserAv();
				userAv.setType(DataTypesEnum.BOOLEAN.getValue());
				userAv.setName(CognitoAtributes.IS_CONFIGUSER);
				userAv.setBooleanValue(Boolean.getBoolean(a.getValue()));
				userAv.setUserData(user);
				userAvRepo.save(userAv);
			}
		});
		return user.getId();
	}

	private UserDetails saveUser(List<AttributeType> attributes, long roleId, String userName, Boolean enable,
			Long clientId) throws Exception {
		UserDetails user = new UserDetails();
		user.setUserName(userName);
		user.setIsActive(enable);
		attributes.stream().forEach(attribute -> {
			if (attribute.getName().equalsIgnoreCase(CognitoAtributes.GENDER)) {
				user.setGender(attribute.getValue());
			}
			if (attribute.getName().equalsIgnoreCase(CognitoAtributes.CLIENT_ID)) {
				
			
			if (attribute.getValue() != null) {
				Optional<ClientDetails> clientDetailsOptional = clientDetailsRepo.findById(Long.parseLong(attribute.getValue()));
				if (clientDetailsOptional.isPresent()) {
					List<ClientDetails> clients = new ArrayList<ClientDetails>();
					clients.add(clientDetailsOptional.get());
					user.setClient(clients);
				}
			}
			}
			if (attribute.getName().equalsIgnoreCase(CognitoAtributes.PHONE_NUMBER)) {
				user.setPhoneNumber(attribute.getValue());
			}
			if (attribute.getName().equalsIgnoreCase(CognitoAtributes.CREATED_BY)) {
				user.setCreatedBy(Long.valueOf(attribute.getValue()));
			}
			if (attribute.getName().equalsIgnoreCase(CognitoAtributes.IS_SUPER_ADMIN)) {
				user.setIsSuperAdmin(Boolean.valueOf(attribute.getValue()));
			}
		});
		try {
			// UserDetails userSaved = userRepository.save(user);
			if (roleId != 0L) {
				Optional<Role> role = roleRepository.findById(roleId);
				if (role.isPresent()) {
					user.setRole(role.get());
				} else {
					Role specialRole = new Role();
					attributes.stream().forEach(b -> {
						if (b.getName().equalsIgnoreCase(CognitoAtributes.IS_SUPER_ADMIN)) {
							if (b.getValue().equalsIgnoreCase("true")) {
								Optional<Role> roleSuperAdmin = roleRepository.findByRoleName("super_admin");
								if (roleSuperAdmin.isPresent()) {
									user.setRole(roleSuperAdmin.get());
								}

							}
						}
						if (b.getName().equalsIgnoreCase(CognitoAtributes.IS_CONFIGUSER)) {
							if (b.getValue().equalsIgnoreCase("true")) {
								Optional<Role> roleCognifuser = roleRepository.findByRoleName("config_user");
								if (roleCognifuser.isPresent()) {
									user.setRole(roleCognifuser.get());
								}

							}
						}
					});
					user.setRole(specialRole);
				}
			}
			/*
			 * if (clientId != null) { Optional<ClientDetails> clientDetailsOptional =
			 * clientDetailsRepo.findById(clientId); if (clientDetailsOptional.isPresent())
			 * { user.setClient(Arrays.asList(clientDetailsOptional.get())); } } if(clientId
			 * == null) { String[] splitted = userName.split("_"); String name =
			 * splitted[0]; ClientDetails clientDetail = clientDetailsRepo.findByName(name);
			 * if (clientDetail!=null) { user.setClient(Arrays.asList(clientDetail)); } }
			 */
			return userRepository.save(user);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * This API is responsible for confirm the user in userpool based on newPassword
	 * and session. If it is success we will get 200 responce.
	 * 
	 * @param newPasswordChallengeRequest
	 * @return AdminRespondToAuthChallengeResult
	 * @throws Exception
	 * 
	 */
	@Override
	public AdminRespondToAuthChallengeResult authChallenge(NewPasswordChallengeRequest newPasswordChallengeRequest)
			throws Exception {
		try {
			AdminRespondToAuthChallengeResult authChallengeResponse = cognitoClient
					.respondAuthChalleng(newPasswordChallengeRequest);
			if (authChallengeResponse.getSdkHttpMetadata().getHttpStatusCode() == HttpStatus.OK.value()) {
				AdminGetUserResult userDetails = cognitoClient
						.getUserFromUserpool(newPasswordChallengeRequest.getUserName());
				Optional<Role> role = roleRepository.findByRoleName(newPasswordChallengeRequest.getRoleName());
				long roleId = 0L;
				if (role.isPresent()) {
					roleId = role.get().getId();
				}
				/*
				 * userDetails.getUserAttributes().stream().forEach(attribute->{ if
				 * (attribute.getName().equalsIgnoreCase(CognitoAtributes.CLIENT_ID)) {
				 * newPasswordChallengeRequest.setClientId(Long.parseLong(attribute.getValue()))
				 * ; } });
				 */
				
				Long userId = saveUser(userDetails.getUserCreateDate(), userDetails.getUserLastModifiedDate(),
						userDetails.getUserAttributes(), roleId, newPasswordChallengeRequest.getUserName(),
						userDetails.getEnabled(), newPasswordChallengeRequest.getClientId());
				UpdateUserAttribute request = new UpdateUserAttribute();
				request.setUserName(newPasswordChallengeRequest.getUserName());
				request.setAttributeName(CognitoAtributes.USER_ID);
				request.setAttributeValue(String.valueOf(userId));
				cognitoClient.updateSingleUserAttributeInUserpool(request);
				return authChallengeResponse;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return null;
	}

}
