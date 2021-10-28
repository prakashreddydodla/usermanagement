package com.otsi.retail.authservice.services;

import java.sql.SQLException;
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

import javax.transaction.Transactional;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.RuntimeCryptoException;
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
import com.amazonaws.services.cognitoidp.model.CreateGroupResult;
import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.amazonaws.services.cognitoidp.model.ListUsersResult;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.amazonaws.services.cognitoidp.model.UserType;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.nimbusds.jwt.JWTClaimsSet;
import com.otsi.retail.authservice.Entity.Domain_Master;
import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.Entity.Privilages;
import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.Entity.UserAv;
import com.otsi.retail.authservice.Entity.UserDeatils;
import com.otsi.retail.authservice.Repository.ChannelRepo;
import com.otsi.retail.authservice.Repository.ClientDetailsRepo;
import com.otsi.retail.authservice.Repository.Domian_MasterRepo;
import com.otsi.retail.authservice.Repository.PrivilageRepo;
import com.otsi.retail.authservice.Repository.RoleRepository;
import com.otsi.retail.authservice.Repository.StoreRepo;
import com.otsi.retail.authservice.Repository.UserAvRepo;
import com.otsi.retail.authservice.Repository.UserRepo;
import com.otsi.retail.authservice.configuration.AwsCognitoTokenProcessor;
import com.otsi.retail.authservice.requestModel.AdminCreatUserRequest;
import com.otsi.retail.authservice.requestModel.DomainVo;
import com.otsi.retail.authservice.requestModel.DomianStoresVo;
import com.otsi.retail.authservice.requestModel.GetStoresRequestVo;
import com.otsi.retail.authservice.requestModel.GetUserRequestModel;
import com.otsi.retail.authservice.requestModel.MasterDomianVo;
import com.otsi.retail.authservice.requestModel.NewPasswordChallengeRequest;
import com.otsi.retail.authservice.requestModel.StoreVo;
import com.otsi.retail.authservice.requestModel.ClientDetailsVo;
import com.otsi.retail.authservice.requestModel.ClientDomianVo;
import com.otsi.retail.authservice.requestModel.CreateRoleRequest;
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
	private PrivilageRepo privilageRepo;
	@Autowired
	private ChannelRepo clientChannelRepo;
	@Autowired
	private ClientDetailsRepo clientDetailsRepo;
	@Autowired
	private Domian_MasterRepo domian_MasterRepo;
	@Autowired
	private StoreRepo storeRepo;
	private Logger logger = LoggerFactory.getLogger(CognitoAuthService.class);

	private AmazonRekognition client;

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
				roleEntity.setPrivilages(null);
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
			System.out.println(calendar.getTime() + "------->current date");
			ListUsersResult resultFromCognito = cognitoClient.getAllUsers();
			resultFromCognito.getUsers().stream().filter(user -> user.getUserStatus().equalsIgnoreCase("confirmed")
					&& user.getUserLastModifiedDate().after(calendar.getTime())).forEach(a -> {
						try {
							saveUsersIndataBase(a.getUserCreateDate(), a.getUserLastModifiedDate(), a.getAttributes());
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

	private void saveUsersIndataBase(Date userCreateDate, Date userLastModifiedDate, List<AttributeType> attributes)
			throws Exception {
		UserDeatils user = new UserDeatils();

		UserDeatils savedUser = saveUser(attributes);
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

				/////////////////////////////
				String[] storenames = a.getValue().split(",");
				Arrays.asList(storenames).stream().forEach(storeName -> {

					Optional<Store> dbStoreRecord = storeRepo.findByName(storeName);
					if (dbStoreRecord.isPresent()) {
						List<Store> storesOfUser = savedUser.getStores();
						if(!CollectionUtils.isEmpty(storesOfUser)) {
							storesOfUser.add(dbStoreRecord.get());	
							savedUser.setStores(storesOfUser);

						}else {
							List<Store> newStores=new ArrayList<>();
							newStores.add(dbStoreRecord.get());
							savedUser.setStores(newStores);

						}
						userRepo.save(savedUser);
						/*
						 * Store storeEntity = dbStoreRecord.get(); List<UserDeatils> userList =
						 * storeEntity.getStoreUsers(); userList.add(savedUser);
						 * storeEntity.setStoreUsers(userList); storeRepo.save(storeEntity);
						 */
					}

				});

				////////////////////////////
				/*
				 * UserAv userAv = new UserAv(); userAv.setType(2);
				 * userAv.setName("assignedStores"); userAv.setStringValue(a.getValue());
				 * userAv.setUserData(savedUser); userAvRepo.save(userAv);
				 */
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
		});
	}

	private UserDeatils saveUser(List<AttributeType> attributes) throws Exception {
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
			UserDeatils dbResponce = userRepo.save(user);
			return dbResponce;
		} catch (Exception e) {
			logger.error(e.getMessage());

			throw new Exception(e.getMessage());
		}

	}

	public UserDeatils getUserFromDb(GetUserRequestModel userRequest) throws Exception {

		Optional<UserDeatils> user = Optional.empty();
		if (0l != userRequest.getId()) {
			user = userRepo.findById(userRequest.getId());
		}
		if (null != userRequest.getName()) {
			user = userRepo.findByUserName(userRequest.getName());
		}
		if (null != userRequest.getPhoneNo()) {
			user = userRepo.findByPhoneNumber(userRequest.getPhoneNo());
		}
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new Exception("No user found with this userName: " + userRequest);
		}

	}

	// *********************************** ROLES AND PRIVILAGES RELATED API'S
	// **************************************************************//

	public String savePrevilage(Privilages privilages) throws Exception {

		Privilages privilage = new Privilages();
		privilage.setDiscription(privilages.getDiscription());
		privilage.setRead(privilages.isRead());
		privilage.setWrite(privilages.isWrite());
		privilage.setCreatedDate(LocalDate.now());
		privilage.setLastModifyedDate(LocalDate.now());
		try {
			privilageRepo.save(privilage);
			return "Saved Successfully";
		} catch (Exception e) {
			throw new Exception();
		}
	}

	public List<Privilages> getAllPrivilages() {
		return privilageRepo.findAll();
	}

	public String createRole(CreateRoleRequest role) throws Exception {
		Role roleEntity = new Role();
		Role dbResult = null;
		roleEntity.setDiscription(role.getDescription());
		roleEntity.setRoleName(role.getRoleName());
		roleEntity.setCreatedDate(LocalDate.now());
		List<Privilages> privilages = new ArrayList<>();
		role.getPrivilages().forEach(a -> {
			Optional<Privilages> privilage = privilageRepo.findById(a.getId());
			if (privilage.isPresent()) {
				privilages.add(privilage.get());
			} else {
				throw new RuntimeException("Given privilage not found in master");
			}
		});
		roleEntity.setPrivilages(privilages);
		try {
			dbResult = roleRepository.save(roleEntity);
			if (!dbResult.equals(null)) {
				if (!role.getRoleName().equalsIgnoreCase("CUSTOMER")) {

					/**
					 * If the Role is not a Customer then it wil Create in Cognito also
					 */
					CreateGroupResult cognitoResult = cognitoClient.createRole(role);
					if (cognitoResult.getSdkHttpMetadata().getHttpStatusCode() == 200) {
						return "sucessfully saved";
					}
				}
			}
			throw new RuntimeException("Role not saved");
		} catch (Exception e) {
			roleRepository.delete(dbResult);
			throw new Exception(e.getMessage());
		}
	}

	public Role getPrivilages(long roleId) throws Exception {
		try {
			Optional<Role> role = roleRepository.findById(roleId);
			return role.get();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	// ********************************* DOMAIN RELATED APPI'S
	// **************************************************

	public String createMasterDomain(MasterDomianVo domainVo) throws Exception {
		Domain_Master domain = new Domain_Master();
		try {
			domain.setChannelName(domainVo.getDomainName());
			domain.setDiscription(domainVo.getDiscription());
			domain.setCreatedDate(LocalDate.now());
			domain.setLastModifyedDate(LocalDate.now());

			Domain_Master savedChannel = domian_MasterRepo.save(domain);
			return "Channel created";

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Domain_Master> getMasterDomains() {
		List<Domain_Master> domains = domian_MasterRepo.findAll();
		return domains;
	}

	// *************************************** CLIENT RELATED API'S
	// ************************************\\

	@Transactional(rollbackOn = { Exception.class })
	public String createClient(ClientDetailsVo clientVo) throws Exception {
		try {
			ClientDetails clientEntity = new ClientDetails();
			clientEntity.setName(clientVo.getName());
			clientEntity.setAddress(clientVo.getAddress());
			clientEntity.setCreatedDate(LocalDate.now());
			clientEntity.setLastModifyedDate(LocalDate.now());

			ClientDetails savedClient = clientDetailsRepo.save(clientEntity);
			/*
			 * if (clientVo.getChannelId() != null) {
			 * clientVo.getChannelId().stream().forEach(domainVo -> { ClientDomains
			 * clientDomians = new ClientDomains();
			 * clientDomians.setDomaiName(domainVo.getName());
			 * clientDomians.setDiscription(domainVo.getDiscription());
			 * clientDomians.setCreatedDate(LocalDate.now());
			 * clientDomians.setLastModifyedDate(LocalDate.now());
			 * clientDomians.setClient(savedClient); List<Domain_Master> masterDomains = new
			 * ArrayList<>(); if (domainVo.getChannel() != null) {
			 * domainVo.getChannel().stream().forEach(a -> { try {
			 * masterDomains.add(domian_MasterRepo.findById(a.getId()).get()); } catch
			 * (Exception e) { throw new RuntimeException("Domain not found in master"); }
			 * }); } clientDomians.setDomain(masterDomains);
			 * clientChannelRepo.save(clientDomians); }); }
			 */
			return "Client created successfully with ClientId :" + savedClient.getId();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
///////////

	public String assignDomianToClient(ClientDomianVo domianVo) {
		try {
			ClientDomains clientDomians = new ClientDomains();
			clientDomians.setDomaiName(domianVo.getName());
			clientDomians.setDiscription(domianVo.getDiscription());
			clientDomians.setCreatedDate(LocalDate.now());
			clientDomians.setLastModifyedDate(LocalDate.now());
			if (0L != domianVo.getClientId()) {
				Optional<ClientDetails> client_db = clientDetailsRepo.findById(domianVo.getClientId());
				if (client_db.isPresent()) {
					clientDomians.setClient(client_db.get());
				}
			}
			if (null != clientDomians.getDomain()) {
				List<Domain_Master> asssingedDomians = clientDomians.getDomain();
				asssingedDomians.add(domian_MasterRepo.findById(domianVo.getMasterDomianId()).get());
				clientDomians.setDomain(asssingedDomians);
			} else {
				List<Domain_Master> newAssignedDomians = new ArrayList<>();
				newAssignedDomians.add(domian_MasterRepo.findById(domianVo.getMasterDomianId()).get());
				clientDomians.setDomain(newAssignedDomians);
			}

			ClientDomains dbObject = clientChannelRepo.save(clientDomians);
			if (dbObject != null) {
				return "Domian assigned to client with domainId : " + dbObject.getClientDomainaId();
			} else {
				throw new RuntimeException("Domain not assinged to client");
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

////////////	
	public ClientDetails getClient(long clientId) throws Exception {

		Optional<ClientDetails> client = clientDetailsRepo.findById(clientId);
		if (client.isPresent()) {
			return client.get();
		} else
			throw new Exception("No Client found with this Name");
	}

	public List<ClientDetails> getAllClient() throws Exception {
		List<ClientDetails> clients = clientDetailsRepo.findAll();
		if (!CollectionUtils.isEmpty(clients))
			return clients;
		else
			throw new Exception("No clients found");
	}

//****************************************  STORE RELATED API'S  ****************************************************

	@Transactional(rollbackOn = { RuntimeException.class })
	public String createStore(StoreVo vo) throws Exception {

		Store storeEntity = new Store();
		try {
			storeEntity.setName(vo.getName());
			storeEntity.setAddress(vo.getAddress());
			storeEntity.setStateId(vo.getStateId());
			storeEntity.setDistrictId(vo.getDistrictId());
			storeEntity.setCityId(vo.getCityId());
			storeEntity.setArea(vo.getArea());
			storeEntity.setPhoneNumber(vo.getPhoneNumber());

			if (null != vo.getStoreOwner()) {
				Optional<UserDeatils> userfromDb = userRepo.findById(vo.getStoreOwner().getUserId());
				if (userfromDb.isPresent()) {
					storeEntity.setStoreOwner(userfromDb.get());
				}
			}
			if (0L != vo.getDomainId()) {
				Optional<ClientDomains> clientDomian = clientChannelRepo.findById(vo.getDomainId());
				if (clientDomian.isPresent()) {
					storeEntity.setClientDomianlId(clientDomian.get());
				} else {
					throw new RuntimeException("No client Domian found with this DomianId :" + vo.getDomainId());
				}
			}
			storeEntity.setCreatedDate(LocalDate.now());
			storeEntity.setLastModifyedDate(LocalDate.now());
			Store savedStore = storeRepo.save(storeEntity);

			return "Store created with storeId : " + savedStore.getId();
		} catch (RuntimeException re) {
			throw new Exception(re.getMessage());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Store> getStoresForClientDomian(long clientDomianId) throws Exception {
		try {

			List<Store> stores = storeRepo.findByClientDomianlId_ClientDomainaId(clientDomianId);
			if (!CollectionUtils.isEmpty(stores)) {
				return stores;
			} else
				throw new Exception("No stores found");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public String assignStoreToClientDomain(DomianStoresVo vo) throws Exception {

		try {
			Optional<ClientDomains> clientDetails = clientChannelRepo.findById(vo.getDomain().getClientChannelid());

			if (clientDetails.isPresent()) {
				List<Store> selectedStores = new ArrayList<>();
				ClientDomains clientDomain = clientDetails.get();
				vo.getStores().stream().forEach(a -> {
					selectedStores.add(storeRepo.findById(a.getId()).get());
				});
				clientDomain.setStore(selectedStores);

				clientChannelRepo.save(clientDomain);
				return "success";
			} else {
				throw new RuntimeException("Selected Domain not  found ");
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<ClientDomains> getDomainsForClient(long clientId) {

		List<ClientDomains> clientDomians = clientChannelRepo.findByClient_Id(clientId);
		if (!CollectionUtils.isEmpty(clientDomians)) {
			return clientDomians;
		} else {
			throw new RuntimeException("No domian found with this Client :" + clientId);
		}

	}

	public List<Role> getRolesFoeClientDomian(long clientId) {

		List<Role> roles = roleRepository.findByClientDomian_clientDomainaId(clientId);
		return null;
	}

	public List<Store> getStoresOnFilter(GetStoresRequestVo vo) {
		if (0L != vo.getStateId()) {
			List<Store> stores = storeRepo.findByStateId(vo.getStateId());
			if (!CollectionUtils.isEmpty(stores)) {
				return stores;
			} else {
				throw new RuntimeException("Stores not found with this StateId : " + vo.getDistrictId());
			}
		}
		if (0L != vo.getCityId()) {
			List<Store> stores = storeRepo.findByCityId(vo.getCityId());
			if (!CollectionUtils.isEmpty(stores)) {
				return stores;
			} else {
				throw new RuntimeException("Stores not found with this CityId : " + vo.getDistrictId());

			}
		}
		if (0L != vo.getDistrictId()) {
			List<Store> stores = storeRepo.findByDistrictId(vo.getCityId());
			if (!CollectionUtils.isEmpty(stores)) {
				return stores;
			} else {
				throw new RuntimeException("Stores not found with this DistrictId : " + vo.getDistrictId());

			}

		}
		if (null != vo.getStoreName()) {
			Optional<Store> storeOptional = storeRepo.findByName(vo.getStoreName());
			if (storeOptional.isPresent()) {
				List<Store> stores = new ArrayList<>();
				stores.add(storeOptional.get());
				return stores;
			} else {
				throw new RuntimeException("Stores not found with this StoreName : " + vo.getStoreName());
			}

		}
		throw new RuntimeException("Please provide valid information");
	}

	public AdminRespondToAuthChallengeResult authResponceForNewUser(NewPasswordChallengeRequest req) throws Exception {
		AdminRespondToAuthChallengeResult responceFromCognito = cognitoClient.respondAuthChalleng(req);
		if (responceFromCognito.getSdkHttpMetadata().getHttpStatusCode() == 200) {

			try {
				AdminGetUserResult userFromCognito = cognitoClient.getUserFromUserpool(req.getUserName());

				saveUsersIndataBase(userFromCognito.getUserCreateDate(), userFromCognito.getUserLastModifiedDate(),
						userFromCognito.getUserAttributes());
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}

		}

		return null;
	}

}
