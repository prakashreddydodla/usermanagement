package com.otsi.retail.authservice.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.RuntimeCryptoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.amazonaws.services.cognitoidp.model.AdminUpdateUserAttributesResult;
import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.Entity.UserAv;
import com.otsi.retail.authservice.Entity.UserDeatils;
import com.otsi.retail.authservice.Exceptions.UserNotFoundException;
import com.otsi.retail.authservice.Repository.ClientcDomianRepo;
import com.otsi.retail.authservice.Repository.RoleRepository;
import com.otsi.retail.authservice.Repository.StoreRepo;
import com.otsi.retail.authservice.Repository.UserAvRepo;
import com.otsi.retail.authservice.Repository.UserRepo;
import com.otsi.retail.authservice.requestModel.GetUserRequestModel;
import com.otsi.retail.authservice.requestModel.StoreVo;
//import com.otsi.retail.authservice.requestModel.PersonVo;
import com.otsi.retail.authservice.requestModel.UpdateUserRequest;
import com.otsi.retail.authservice.responceModel.GetCustomerResponce;
import com.otsi.retail.authservice.responceModel.UserListResponse;
import com.otsi.retail.authservice.utils.CognitoAtributes;

@Service
public class UserServiceImpl implements UserService {
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
	@Autowired
	private CognitoClient cognitoClient;
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	public List<UserDeatils> getUserFromDb(GetUserRequestModel userRequest) throws Exception {
		logger.info(" ###############  getUserFromDb method starts  ##############3");
		List<UserDeatils> users = new ArrayList<>();
		if (0l != userRequest.getId()) {
			Optional<UserDeatils> user = userRepo.findByUserId(userRequest.getId());
			if (user.isPresent()) {
				users.add(user.get());
				logger.info(" ###############  getUserFromDb method ends  ##############3");
				return users;
			} else {
				logger.debug("User not found with this Id : " + userRequest.getId());
				logger.error("User not found with this Id : " + userRequest.getId());
				throw new RuntimeException("User not found with this Id : " + userRequest.getId());
			}

		}
		if (null != userRequest.getName()) {
			Optional<UserDeatils> user = userRepo.findByUserName(userRequest.getName());
			if (user.isPresent()) {
				users.add(user.get());
				logger.info(" ###############  getUserFromDb method ends  ##############3");
				return users;

			} else {
				logger.debug("User not found with this UserName : " + userRequest.getName());
				logger.error("User not found with this UserName : " + userRequest.getName());
				throw new RuntimeException("User not found with this UserName : " + userRequest.getName());
			}
		}
		if (null != userRequest.getPhoneNo()) {
			Optional<UserDeatils> user = userRepo.findByPhoneNumber(userRequest.getPhoneNo());
			if (user.isPresent()) {
				users.add(user.get());
				logger.info(" ###############  getUserFromDb method ends  ##############3");
				return users;

			} else {
				logger.debug("No user found with this userName: " + userRequest.getPhoneNo());
				logger.error("No user found with this userName: " + userRequest.getPhoneNo());
				throw new Exception("No user found with this userName: " + userRequest.getPhoneNo());
			}
		}
		if (null != userRequest.getStoreName() && userRequest.isActive() && userRequest.getRoleName()!=null) {
			users = userRepo.findByStores_NameAndRoleRoleNameAndIsActive(userRequest.getStoreName(),userRequest.getRoleName(),Boolean.TRUE);
			if (CollectionUtils.isEmpty(users)) {
				logger.debug("No users found with this given Details : " + userRequest.getRoleName());
				logger.error("No users found with this GivenDeatils : " + userRequest.getRoleName());
				throw new RuntimeException("No users found with this Given Details: " + userRequest.getRoleName());
			}
			logger.info(" ###############  getUserFromDb method ends  ##############3");
			return users;
		}
		if (null != userRequest.getStoreName() && userRequest.isActive() && userRequest.getRoleName()!=null) {
			users = userRepo.findByStores_NameAndRoleRoleNameAndIsActive(userRequest.getStoreName(),userRequest.getRoleName(),Boolean.FALSE);
			if (CollectionUtils.isEmpty(users)) {
				logger.debug("No users found with this given Details : " + userRequest.getRoleName());
				logger.error("No users found with this GivenDeatils : " + userRequest.getRoleName());
				throw new RuntimeException("No users found with this Given Details: " + userRequest.getRoleName());
			}
			logger.info(" ###############  getUserFromDb method ends  ##############3");
			return users;
		}
		if (null != userRequest.getStoreName() && !userRequest.isActive()&&!userRequest.isInActive()&& userRequest.getRoleName()!=null) {
			users = userRepo.findByStores_NameAndRoleRoleName(userRequest.getStoreName(),userRequest.getRoleName());
			if (CollectionUtils.isEmpty(users)) {
				logger.debug("No users found with this given Details : " + userRequest.getRoleName());
				logger.error("No users found with this GivenDeatils : " + userRequest.getRoleName());
				throw new RuntimeException("No users found with this Given Details: " + userRequest.getRoleName());
			}
			logger.info(" ###############  getUserFromDb method ends  ##############3");
			return users;
		}

		if (null != userRequest.getRoleName() && userRequest.isActive()) {
			users = userRepo.findByRoleRoleNameAndIsActive(userRequest.getRoleName(), Boolean.TRUE);
			if (CollectionUtils.isEmpty(users)) {
				logger.debug("No users found with this Role ID : " + userRequest.getRoleName());
				logger.error("No users found with this Role ID : " + userRequest.getRoleName());
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleName());
			}
			logger.info(" ###############  getUserFromDb method ends  ##############3");
			return users;
		}
		if (null != userRequest.getRoleName() && userRequest.isInActive()) {
			users = userRepo.findByRoleRoleNameAndIsActive(userRequest.getRoleName(), Boolean.FALSE);
			if (CollectionUtils.isEmpty(users)) {
				logger.debug("No users found with this Role ID : " + userRequest.getRoleName());
				logger.error("No users found with this Role ID : " + userRequest.getRoleName());
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleName());
			}
			logger.info(" ###############  getUserFromDb method ends  ##############3");
			return users;
		}
		if (null != userRequest.getRoleName() && !userRequest.isActive() && !userRequest.isInActive()) {
			users = userRepo.findByRoleRoleName(userRequest.getRoleName());
			if (CollectionUtils.isEmpty(users)) {
				logger.debug("No users found with this Role ID : " + userRequest.getRoleName());
				logger.error("No users found with this Role ID : " + userRequest.getRoleName());
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleName());
			}
			logger.info(" ###############  getUserFromDb method ends  ##############3");
			return users;
		}

		if (null != userRequest.getStoreName() && userRequest.isActive()) {
			users = userRepo.findByStores_NameAndIsActive(userRequest.getStoreName(), Boolean.TRUE);
			if (CollectionUtils.isEmpty(users)) {
				logger.debug("No users found with this Role ID : " + userRequest.getRoleName());
				logger.error("No users found with this Role ID : " + userRequest.getRoleName());
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleName());
			}
			logger.info(" ###############  getUserFromDb method ends  ##############3");
			return users;
		}
		if (null != userRequest.getStoreName() && userRequest.isInActive()) {
			users = userRepo.findByStores_NameAndIsActive(userRequest.getStoreName(), Boolean.FALSE);
			if (CollectionUtils.isEmpty(users)) {
				logger.debug("No users found with this Role ID : " + userRequest.getRoleName());
				logger.error("No users found with this Role ID : " + userRequest.getRoleName());
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleName());
			}
			logger.info(" ###############  getUserFromDb method ends  ##############3");
			return users;
		}
		if (null != userRequest.getStoreName() && !userRequest.isActive() && !userRequest.isInActive()) {
			users = userRepo.findByStores_Name(userRequest.getStoreName());
			if (CollectionUtils.isEmpty(users)) {
				logger.debug("No users found with this Role ID : " + userRequest.getRoleName());
				logger.error("No users found with this Role ID : " + userRequest.getRoleName());
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleName());
			}
			logger.info(" ###############  getUserFromDb method ends  ##############3");
			return users;
		}
		
		
		logger.debug("Please select atleast one input");
		logger.error("Please select atleast one input");
		throw new RuntimeException("Please select atleast one input");

	}

	public List<UserListResponse> getUserForClient(int clientId) throws Exception {
		logger.info(" ###############  getUserForClient method starts  ##############3");

		List<UserDeatils> users = userRepo.findByUserAv_NameAndUserAv_IntegerValue(CognitoAtributes.CLIENT_ID,
				clientId);
		if (!CollectionUtils.isEmpty(users)) {
			List<UserListResponse> userList = new ArrayList<>();
			users.stream().forEach(a -> {
				UserListResponse userVo = new UserListResponse();
				userVo.setUserId(a.getUserId());
				userVo.setUserName(a.getUserName());
				userVo.setCreatedBy(a.getCreatedBy());
				userVo.setCreatedDate(a.getCreatedDate());
				userVo.setSuperAdmin(a.isSuperAdmin());
				userVo.setActive(a.isActive());
				List<StoreVo> stores = new ArrayList<>();
				if (null != a.getStores()) {
					a.getStores().stream().forEach(str -> {
						StoreVo storeVo = new StoreVo();
						storeVo.setId(str.getStateId());
						storeVo.setName(str.getName());
						stores.add(storeVo);

					});
					userVo.setStores(stores);
				}
				if (null != a.getRole()) {
					userVo.setRoleName(a.getRole().getRoleName());
				}
				a.getUserAv().stream().forEach(b -> {
					if (b.getName().equalsIgnoreCase(CognitoAtributes.EMAIL)) {
						userVo.setEmail(b.getStringValue());
					}
					if (b.getName().equalsIgnoreCase(CognitoAtributes.DOMAINID)) {
						userVo.setDomian(b.getIntegerValue());
					}
					if (b.getName().equalsIgnoreCase(CognitoAtributes.BIRTHDATE)) {
						userVo.setDob(b.getStringValue());
					}
					if (b.getName().equalsIgnoreCase(CognitoAtributes.ADDRESS)) {
						userVo.setAddress(b.getStringValue());

					}
				});
				userList.add(userVo);
			});
			logger.info(" ###############  getUserForClient method ends  ##############3");
			return userList;
		} else {
			logger.debug("No users found with this client");
			logger.error("No users found with this client");
			throw new Exception("No users found with this client");
		}

	}

	public List<UserListResponse> getUsersForClientDomain(long clientDomianId) {
		logger.info(" ###############  getUsersForClientDomain method starts  ##############3");

		List<UserDeatils> users = userRepo.findByClientDomians_ClientDomainaId(clientDomianId);

		if (!CollectionUtils.isEmpty(users)) {
			List<UserListResponse> userList = new ArrayList<>();
			users.stream().forEach(a -> {
				UserListResponse userVo = new UserListResponse();
				userVo.setUserId(a.getUserId());
				userVo.setUserName(a.getUserName());
				userVo.setCreatedBy(a.getCreatedBy());
				userVo.setCreatedDate(a.getCreatedDate());
				userVo.setDomian(clientDomianId);
				userVo.setActive(a.isActive());
				List<StoreVo> stores = new ArrayList<>();
				if (null != a.getStores()) {
					a.getStores().stream().forEach(str -> {
						StoreVo storeVo = new StoreVo();
						storeVo.setId(str.getStateId());
						storeVo.setName(str.getName());
						stores.add(storeVo);

					});
					userVo.setStores(stores);
				}
				if (null != a.getRole()) {
					userVo.setRoleName(a.getRole().getRoleName());
				}
				a.getUserAv().stream().forEach(b -> {
					if (b.getName().equalsIgnoreCase(CognitoAtributes.EMAIL)) {
						userVo.setEmail(b.getStringValue());
					}
				});
				userList.add(userVo);
			});
			logger.info(" ###############  getUsersForClientDomain method ends  ##############3");

			return userList;
		} else {
			throw new UserNotFoundException("User not found with this Domian Id : " + clientDomianId);
		}

	}

	@Override
	public GetCustomerResponce getCustomerbasedOnMobileNumber(String type, String value) {
		logger.info(" ###############  getCustomerbasedOnMobileNumber method starts  ##############3");

		Optional<UserDeatils> user = Optional.empty();
		if (null != type && type.equalsIgnoreCase("mobileNo")) {
			user = userRepo.findByPhoneNumber(value);
			if (!user.isPresent()) {
				logger.debug("No customer found with this MobileNo : " + value);
				logger.error("No customer found with this MobileNo : " + value);
				throw new RuntimeException("No customer found with this MobileNo : " + value);
			}
		}
		if (null != type && type.equalsIgnoreCase("id")) {
			user = userRepo.findByUserId(Long.parseLong(value));
			if (!user.isPresent()) {
				logger.debug("No customer found with this Id : " + value);
				logger.error("No customer found with this Id : " + value);
				throw new RuntimeException("No customer found with this Id : " + value);
			}
		}

		GetCustomerResponce customer = new GetCustomerResponce();

		customer.setUserId(user.get().getUserId());
		if (null != user.get().getPhoneNumber()) {

			customer.setPhoneNumber(user.get().getPhoneNumber());
		}
		if (null != user.get().getUserName()) {

			customer.setUserName(user.get().getUserName());
		}
		if (null != user.get().getCreatedDate()) {

			customer.setCreatedDate(user.get().getCreatedDate());
		}
		if (null != user.get().getGender()) {

			customer.setGender(user.get().getGender());
		}
		if (null != user.get().getLastModifyedDate()) {

			customer.setLastModifyedDate(user.get().getLastModifyedDate());
		}
		if (true != user.get().isActive()) {

			customer.setActive(user.get().isActive());
		}
		logger.info(" ###############  getCustomerbasedOnMobileNumber method ends  ##############3");

		return customer;

	}

	@Override
	public String updateUser(UpdateUserRequest req) throws RuntimeException {
		logger.info(" ###############  updateUser method starts  ##############3");

		try {
			Optional<UserDeatils> userOptional = userRepo.findById(req.getUserId());
			if (userOptional.isPresent()) {

				UserDeatils userFromDb = userOptional.get();
				userFromDb.setUserId(req.getUserId());
				userFromDb.setUserName(req.getUsername());
				userFromDb.setPhoneNumber(req.getPhoneNumber());
				userFromDb.setGender(req.getGender());
				userFromDb.setLastModifyedDate(LocalDate.now());
				if (null != req.getRole()) {
					Optional<Role> role = roleRepository.findByRoleName(req.getRole().getRoleName());
					if (role.isPresent()) {
						userFromDb.setRole(role.get());
					} else {
						logger.debug("Role not found in DB with this Id : " + req.getRole().getRoleId());
						logger.error("Role not found in DB with this Id : " + req.getRole().getRoleId());
						throw new RuntimeException("Role not found in DB with this Id : " + req.getRole().getRoleId());
					}
				}
				UserDeatils savedUser = userRepo.save(userFromDb);

				userFromDb.getUserAv().stream().forEach(av -> {
					UserAv userAv = av;
					if (userAv.getName().equalsIgnoreCase(CognitoAtributes.EMAIL)) {
						if(null!=req.getEmail()) {
						userAv.setStringValue(req.getEmail());
						userAv.setLastModifyedDate(LocalDate.now());
						userAv.setUserData(savedUser);
						userAvRepo.save(userAv);
						}
					}
					if (av.getName().equalsIgnoreCase(CognitoAtributes.PARENTID)) {
						if(null!=req.getParentId()) {
						userAv.setIntegerValue(Integer.parseInt(req.getParentId()));
						userAv.setLastModifyedDate(LocalDate.now());
						userAv.setUserData(savedUser);
						userAvRepo.save(userAv);
						}
					}
					if (av.getName().equalsIgnoreCase(CognitoAtributes.ADDRESS)) {
						if(null!=req.getAddress()) {
							userAv.setStringValue(req.getAddress());
							userAv.setLastModifyedDate(LocalDate.now());
							userAv.setUserData(savedUser);
							userAvRepo.save(userAv);
						}
						
					}

					if (av.getName().equalsIgnoreCase(CognitoAtributes.DOMAINID)) {
						if(null!=req.getDomianId()) {
							userAv.setIntegerValue(Integer.parseInt(req.getDomianId()));
							userAv.setLastModifyedDate(LocalDate.now());
							userAv.setUserData(savedUser);
							userAvRepo.save(userAv);

						}
						
					}
					if (av.getName().equalsIgnoreCase(CognitoAtributes.CLIENT_ID)) {
						if(null!=req.getClientId()) {
							userAv.setIntegerValue(Integer.parseInt(req.getClientId()));
							userAv.setLastModifyedDate(LocalDate.now());
							userAv.setUserData(savedUser);
							userAvRepo.save(userAv);
						}
						

					}
				});

				if (null != req.getChannelId()) {
					List<ClientDomains> clientDomains = new ArrayList<>();
					Arrays.asList(req.getClientDomain()).stream().forEach(clientDomianId -> {
						Optional<ClientDomains> dbClientDomainRecord = clientcDomianRepo
								.findById(Long.parseLong(clientDomianId.toString()));

						if (dbClientDomainRecord.isPresent()) {
							clientDomains.add(dbClientDomainRecord.get());
						} else {
							logger.debug("Client Domian not found with this Id : " + clientDomianId);
							logger.error("Client Domian not found with this Id : " + clientDomianId);
							throw new RuntimeException("Client Domian not found with this Id : " + clientDomianId);
						}
					});
					savedUser.setClientDomians(clientDomains);
					userRepo.save(savedUser);
				}

				if (!CollectionUtils.isEmpty(req.getStores())) {
					List<Store> stores = new ArrayList<>();
					req.getStores().stream().forEach(storeVo -> {
						Optional<Store> storeOptional = storeRepo.findByName(storeVo.getName());
						if (storeOptional.isPresent()) {
							stores.add(storeOptional.get());
						}
					});
					savedUser.setStores(stores);
					userRepo.save(savedUser);
					logger.info(" ###############  updated user in DB ##############3");
				}
				logger.info(" ###############  updating user in cognito userpool ##############3");
				AdminUpdateUserAttributesResult result = cognitoClient.updateUserInCognito(req);
				if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
					logger.info(" ###############  updated user in cognito userpool ##############3");
					logger.info(" ###############  updateUser method ends  ##############3");
					return "SucessFully updated";
				} else {
					logger.debug("Failed to update");
					logger.error("Failed to update");
					throw new RuntimeException("Failed to update");
				}
			} else {
				logger.debug("User not found with this Id :" + req.getUserId());
				logger.error("User not found with this Id :" + req.getUserId());
				throw new RuntimeException("User not found with this Id :" + req.getUserId());
			}

		} catch (RuntimeException re) {
			logger.debug(re.getMessage());
			logger.error(re.getMessage());
			throw new RuntimeException(re.getMessage());
		}
	}

	@Override
	public UserListResponse getUserbasedOnMobileNumber(String mobileNo) throws Exception {

		logger.info("################  getUserbasedOnMobileNumber method starts  ############");
		Optional<UserDeatils> userOptional = userRepo.findByUserNameAndIsCustomer(mobileNo, Boolean.FALSE);
		if (!userOptional.isPresent()) {
			logger.debug("User details not found with this mobile number : " + mobileNo);
			logger.error("User details not found with this mobile number : " + mobileNo);
			throw new Exception("User details not found with this mobile number : " + mobileNo);
		}
		UserDeatils user = userOptional.get();
		UserListResponse userVo = new UserListResponse();
		userVo.setUserId(user.getUserId());
		userVo.setUserName(user.getUserName());
		userVo.setCreatedBy(user.getCreatedBy());
		userVo.setCreatedDate(user.getCreatedDate());
		userVo.setSuperAdmin(user.isSuperAdmin());
		userVo.setGender(user.getGender());
		userVo.setActive(user.isActive());
		List<StoreVo> stores = new ArrayList<>();
		if (null != user.getStores()) {
			user.getStores().stream().forEach(str -> {
				StoreVo storeVo = new StoreVo();
				storeVo.setId(str.getStateId());
				storeVo.setName(str.getName());
				stores.add(storeVo);

			});
			userVo.setStores(stores);
		}
		if (null != user.getRole()) {
			userVo.setRoleName(user.getRole().getRoleName());
		}
		user.getUserAv().stream().forEach(b -> {
			if (b.getName().equalsIgnoreCase(CognitoAtributes.EMAIL)) {
				userVo.setEmail(b.getStringValue());
			}
			if (b.getName().equalsIgnoreCase(CognitoAtributes.DOMAINID)) {
				userVo.setDomian(b.getIntegerValue());
			}
			if (b.getName().equalsIgnoreCase(CognitoAtributes.BIRTHDATE)) {
				userVo.setDob(b.getStringValue());
			}
			if (b.getName().equalsIgnoreCase(CognitoAtributes.ADDRESS)) {
				userVo.setAddress(b.getStringValue());
			}
		});
		logger.info("################  getUserbasedOnMobileNumber method ends  ############");
		return userVo;
	}

	/*
	 * @RabbitListener(queues ="inventoryQueue") public void
	 * rabbitmqConsumer(PersonVo name) {
	 * 
	 * System.out.println("************************message recived from : "+name); }
	 */

}
