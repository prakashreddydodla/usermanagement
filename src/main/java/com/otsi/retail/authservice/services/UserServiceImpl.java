package com.otsi.retail.authservice.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.amazonaws.services.cognitoidp.model.AdminUpdateUserAttributesResult;
import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.Entity.UserAv;
import com.otsi.retail.authservice.Entity.UserDetails;
import com.otsi.retail.authservice.Exceptions.UserNotFoundException;
import com.otsi.retail.authservice.Repository.ClientcDomianRepo;
import com.otsi.retail.authservice.Repository.RoleRepository;
import com.otsi.retail.authservice.Repository.StoreRepo;
import com.otsi.retail.authservice.Repository.UserAvRepo;
import com.otsi.retail.authservice.Repository.UserRepository;
import com.otsi.retail.authservice.requestModel.GetUserRequestModel;
import com.otsi.retail.authservice.requestModel.StoreVO;
//import com.otsi.retail.authservice.requestModel.PersonVo;
import com.otsi.retail.authservice.requestModel.UpdateUserRequest;
import com.otsi.retail.authservice.requestModel.UserDetailsVO;
import com.otsi.retail.authservice.responceModel.GetCustomerResponce;
import com.otsi.retail.authservice.responceModel.UserListResponse;
import com.otsi.retail.authservice.utils.CognitoAtributes;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

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

	private Logger logger = LogManager.getLogger(UserServiceImpl.class);

	public List<UserDetails> getUserFromDb(GetUserRequestModel userRequest, Long userId) throws Exception {
		logger.info(" ###############  getUserFromDb method starts  ##############3");
		List<UserDetails> users = new ArrayList<>();
		if (0l != userRequest.getId()) {
			Optional<UserDetails> user = userRepository.findById(userRequest.getId());
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
		if (null != userRequest.getName() && "" != userRequest.getName()) {
			Optional<UserDetails> user = userRepository.findByUserName(userRequest.getName());
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
		if (null != userRequest.getPhoneNo() && "" != userRequest.getPhoneNo()) {
			Optional<UserDetails> user = userRepository.findByPhoneNumber(userRequest.getPhoneNo());
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
		if (null != userRequest.getStoreName() && userRequest.isActive() && userRequest.getRoleName() != null) {
			users = userRepository.findByStores_NameAndRoleRoleNameAndIsActive(userRequest.getStoreName(),
					userRequest.getRoleName(), Boolean.TRUE);
			if (CollectionUtils.isEmpty(users)) {
				logger.debug("No users found with this given Details : " + userRequest.getRoleName());
				logger.error("No users found with this GivenDeatils : " + userRequest.getRoleName());
				throw new RuntimeException("No users found with this Given Details: " + userRequest.getRoleName());
			}
			logger.info(" ###############  getUserFromDb method ends  ##############3");
			return users;
		}
		if (null != userRequest.getStoreName() && userRequest.isInActive() && userRequest.getRoleName() != null) {
			users = userRepository.findByStores_NameAndRoleRoleNameAndIsActive(userRequest.getStoreName(),
					userRequest.getRoleName(), Boolean.FALSE);
			if (CollectionUtils.isEmpty(users)) {
				logger.debug("No users found with this given Details : " + userRequest.getRoleName());
				logger.error("No users found with this GivenDeatils : " + userRequest.getRoleName());
				throw new RuntimeException("No users found with this Given Details: " + userRequest.getRoleName());
			}
			logger.info(" ###############  getUserFromDb method ends  ##############3");
			return users;
		}
		if (null != userRequest.getStoreName() && !userRequest.isActive() && !userRequest.isInActive()
				&& userRequest.getRoleName() != null) {
			users = userRepository.findByStores_NameAndRoleRoleName(userRequest.getStoreName(),
					userRequest.getRoleName());
			if (CollectionUtils.isEmpty(users)) {
				logger.debug("No users found with this given Details : " + userRequest.getRoleName());
				logger.error("No users found with this GivenDeatils : " + userRequest.getRoleName());
				throw new RuntimeException("No users found with this Given Details: " + userRequest.getRoleName());
			}
			logger.info(" ###############  getUserFromDb method ends  ##############3");
			return users;
		}

		if (null != userRequest.getRoleName() && userRequest.isActive()) {
			users = userRepository.findByRoleRoleNameAndIsActive(userRequest.getRoleName(), Boolean.TRUE);
			if (CollectionUtils.isEmpty(users)) {
				logger.debug("No users found with this Role ID : " + userRequest.getRoleName());
				logger.error("No users found with this Role ID : " + userRequest.getRoleName());
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleName());
			}
			logger.info(" ###############  getUserFromDb method ends  ##############3");
			return users;
		}
		if ((null == userRequest.getRoleName() || "" == userRequest.getRoleName())
				&& (null == userRequest.getStoreName() || "" == userRequest.getStoreName()) && userRequest.isActive()) {
			users = userRepository.findByIsActiveAndUserAv_NameAndUserAv_IntegerValue(Boolean.TRUE,
					CognitoAtributes.CLIENT_ID, Math.toIntExact(userRequest.getClientDomainId()));
			if (CollectionUtils.isEmpty(users)) {
				logger.debug("No users found with this Role ID : " + userRequest.getRoleName());
				logger.error("No users found with this Role ID : " + userRequest.getRoleName());
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleName());
			}
			logger.info(" ###############  getUserFromDb method ends  ##############3");
			return users;
		}
		if ((null == userRequest.getRoleName() || "" == userRequest.getRoleName())
				&& (null == userRequest.getStoreName() || "" == userRequest.getStoreName())
				&& userRequest.isInActive()) {
			users = userRepository.findByIsActiveAndUserAv_NameAndUserAv_IntegerValue(Boolean.FALSE,
					CognitoAtributes.CLIENT_ID, Math.toIntExact(userRequest.getClientDomainId()));
			if (CollectionUtils.isEmpty(users)) {
				logger.debug("No users found with this Role ID : " + userRequest.getRoleName());
				logger.error("No users found with this Role ID : " + userRequest.getRoleName());
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleName());
			}
			logger.info(" ###############  getUserFromDb method ends  ##############3");
			return users;
		}

		if (null != userRequest.getRoleName() && userRequest.isInActive()) {
			users = userRepository.findByRoleRoleNameAndIsActive(userRequest.getRoleName(), Boolean.FALSE);
			if (CollectionUtils.isEmpty(users)) {
				logger.debug("No users found with this Role ID : " + userRequest.getRoleName());
				logger.error("No users found with this Role ID : " + userRequest.getRoleName());
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleName());
			}
			logger.info(" ###############  getUserFromDb method ends  ##############3");
			return users;
		}
		if (null != userRequest.getRoleName() && !userRequest.isActive() && !userRequest.isInActive()) {
			users = userRepository.findByRoleRoleNameAndId(userRequest.getRoleName(), userId);
			if (CollectionUtils.isEmpty(users)) {
				logger.debug("No users found with this Role ID : " + userRequest.getRoleName());
				logger.error("No users found with this Role ID : " + userRequest.getRoleName());
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleName());
			}
			logger.info(" ###############  getUserFromDb method ends  ##############3");
			return users;
		}

		if (null != userRequest.getStoreName() && userRequest.isActive()) {
			users = userRepository.findByStores_NameAndIsActiveAndId(userRequest.getStoreName(), Boolean.TRUE,
					userId);
			if (CollectionUtils.isEmpty(users)) {
				logger.debug("No users found with this Role ID : " + userRequest.getRoleName());
				logger.error("No users found with this Role ID : " + userRequest.getRoleName());
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleName());
			}
			logger.info(" ###############  getUserFromDb method ends  ##############3");
			return users;
		}
		if (null != userRequest.getStoreName() && userRequest.isInActive()) {
			users = userRepository.findByStores_NameAndIsActiveAndId(userRequest.getStoreName(), Boolean.FALSE,
					userId);
			if (CollectionUtils.isEmpty(users)) {
				logger.debug("No users found with this storeName : " + userRequest.getStoreName());
				logger.error("No users found with this storeName : " + userRequest.getStoreName());
				throw new RuntimeException("No users found with this storeName : " + userRequest.getStoreName());
			}
			logger.info(" ###############  getUserFromDb method ends  ##############3");
			return users;
		}
		if (null != userRequest.getStoreName() && !userRequest.isActive() && !userRequest.isInActive()) {
			users = userRepository.findByStores_NameAndId(userRequest.getStoreName(), userId);
			if (CollectionUtils.isEmpty(users)) {
				logger.debug("No users found with this storeName : " + userRequest.getStoreName());
				logger.error("No users found with this Role ID : " + userRequest.getStoreName());
				throw new RuntimeException("No users found with this storeName : " + userRequest.getStoreName());
			}
			return users;
		}

		logger.debug("Please select atleast one input");
		logger.error("Please select atleast one input");
		throw new RuntimeException("Please select atleast one input");

	}

	public List<UserListResponse> getUserForClient(Long clientId) throws Exception {
		List<UserDetails> users = userRepository.findByUserAv_NameAndUserAv_IntegerValue(CognitoAtributes.CLIENT_ID,
				clientId);
		if (!CollectionUtils.isEmpty(users)) {
			List<UserListResponse> userList = new ArrayList<>();
			users.stream().forEach(a -> {
				UserListResponse userVo = new UserListResponse();
				userVo.setId(a.getId());
				userVo.setUserName(a.getUserName());
				userVo.setCreatedBy(a.getCreatedBy());
				userVo.setCreatedDate(a.getCreatedDate());
				userVo.setIsSuperAdmin(a.getIsSuperAdmin());
				userVo.setIsActive(a.getIsActive());
				List<StoreVO> stores = new ArrayList<>();
				if (null != a.getStores()) {
					a.getStores().stream().forEach(str -> {
						StoreVO storeVo = new StoreVO();
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
			return userList;
		} else {
			logger.error("No users found with this client");
			throw new Exception("No users found with this client");
		}

	}

	public List<UserListResponse> getUsersForClientDomain(Long clientDomianId) {
		logger.info(" ###############  getUsersForClientDomain method starts  ##############3");

		List<UserDetails> users = userRepository.findByClientDomiansId(clientDomianId);

		if (!CollectionUtils.isEmpty(users)) {
			List<UserListResponse> userList = new ArrayList<>();
			users.stream().forEach(a -> {
				UserListResponse userVo = new UserListResponse();
				userVo.setId(a.getId());
				userVo.setUserName(a.getUserName());
				userVo.setCreatedBy(a.getCreatedBy());
				userVo.setCreatedDate(a.getCreatedDate());
				userVo.setDomian(clientDomianId);
				userVo.setIsActive(a.getIsActive());
				List<StoreVO> stores = new ArrayList<>();
				if (null != a.getStores()) {
					a.getStores().stream().forEach(str -> {
						StoreVO storeVo = new StoreVO();
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

		Optional<UserDetails> user = Optional.empty();
		if (null != type && type.equalsIgnoreCase("mobileNo")) {
			user = userRepository.findByPhoneNumber(value);
			if (!user.isPresent()) {
				logger.debug("No customer found with this MobileNo : " + value);
				logger.error("No customer found with this MobileNo : " + value);
				throw new RuntimeException("No customer found with this MobileNo : " + value);
			}
		}
		if (null != type && type.equalsIgnoreCase("id")) {
			user = userRepository.findById(Long.parseLong(value));
			if (!user.isPresent()) {
				logger.debug("No customer found with this Id : " + value);
				logger.error("No customer found with this Id : " + value);
				throw new RuntimeException("No customer found with this Id : " + value);
			}
		}

		GetCustomerResponce customer = new GetCustomerResponce();

		customer.setUserId(user.get().getId());
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
		if (null != user.get().getLastModifiedDate()) {

			customer.setLastModifyedDate(user.get().getLastModifiedDate());
		}
		if (true != user.get().getIsActive()) {

			customer.setIsActive(user.get().getIsActive());
		}
		logger.info(" ###############  getCustomerbasedOnMobileNumber method ends  ##############3");

		return customer;

	}

	@Override
	public String updateUser(UpdateUserRequest req) throws RuntimeException {
		try {
			Optional<UserDetails> userOptional = userRepository.findById(req.getUserId());
			if (userOptional.isPresent()) {

				UserDetails userDetails = userOptional.get();
				userDetails.setId(req.getUserId());
				userDetails.setUserName(req.getUsername());
				userDetails.setPhoneNumber(req.getPhoneNumber());
				userDetails.setGender(req.getGender());
				// userFromDb.setLastModifyedDate(LocalDate.now());
				if (null != req.getRole()) {
					Optional<Role> role = roleRepository.findByRoleName(req.getRole().getRoleName());
					if (role.isPresent()) {
						userDetails.setRole(role.get());
					} else {
						logger.debug("Role not found in DB with this Id : " + req.getRole().getId());
						logger.error("Role not found in DB with this Id : " + req.getRole().getId());
						throw new RuntimeException("Role not found in DB with this Id : " + req.getRole().getId());
					}
				}
				UserDetails savedUser = userRepository.save(userDetails);

				userDetails.getUserAv().stream().forEach(av -> {
					UserAv userAv = av;
					if (userAv.getName().equalsIgnoreCase(CognitoAtributes.EMAIL)) {
						if (null != req.getEmail()) {
							userAv.setStringValue(req.getEmail());
							// userAv.setLastModifyedDate(LocalDate.now());
							userAv.setUserData(savedUser);
							userAvRepo.save(userAv);
						}
					}
					if (av.getName().equalsIgnoreCase(CognitoAtributes.PARENTID)) {
						if (null != req.getParentId()) {
							userAv.setIntegerValue(Long.parseLong(req.getParentId()));
							// userAv.setLastModifyedDate(LocalDate.now());
							userAv.setUserData(savedUser);
							userAvRepo.save(userAv);
						}
					}
					if (av.getName().equalsIgnoreCase(CognitoAtributes.ADDRESS)) {
						if (null != req.getAddress()) {
							userAv.setStringValue(req.getAddress());
							// userAv.setLastModifyedDate(LocalDate.now());
							userAv.setUserData(savedUser);
							userAvRepo.save(userAv);
						}

					}

					if (av.getName().equalsIgnoreCase(CognitoAtributes.DOMAINID)) {
						if (null != req.getDomianId()) {
							userAv.setIntegerValue(Long.parseLong(req.getDomianId()));
							// userAv.setLastModifyedDate(LocalDate.now());
							userAv.setUserData(savedUser);
							userAvRepo.save(userAv);

						}

					}
					if (av.getName().equalsIgnoreCase(CognitoAtributes.CLIENT_ID)) {
						if (null != req.getClientId()) {
							userAv.setIntegerValue(Long.parseLong(req.getClientId()));
							// userAv.setLastModifyedDate(LocalDate.now());
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
					userRepository.save(savedUser);
				}

				if (!CollectionUtils.isEmpty(req.getStores())) {
					List<Store> stores = new ArrayList<>();
					req.getStores().stream().forEach(storeVo -> {
						List<Store> storeOptional = storeRepo.findByName(storeVo.getName());
						if (!storeOptional.isEmpty()) {
							storeOptional.stream().forEach(s -> {
								stores.add(s);
							});

						}
					});
					savedUser.setStores(stores);
					userRepository.save(savedUser);
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
		Optional<UserDetails> userOptional = userRepository.findByUserNameAndIsCustomer(mobileNo, Boolean.FALSE);
		if (!userOptional.isPresent()) {
			logger.debug("User details not found with this mobile number : " + mobileNo);
			logger.error("User details not found with this mobile number : " + mobileNo);
			throw new Exception("User details not found with this mobile number : " + mobileNo);
		}
		UserDetails user = userOptional.get();
		UserListResponse userVo = new UserListResponse();
		userVo.setId(user.getId());
		userVo.setUserName(user.getUserName());
		userVo.setCreatedBy(user.getCreatedBy());
		userVo.setCreatedDate(user.getCreatedDate());
		userVo.setIsSuperAdmin(user.getIsSuperAdmin());
		userVo.setGender(user.getGender());
		userVo.setIsActive(user.getIsActive());
		List<StoreVO> stores = new ArrayList<>();
		if (null != user.getStores()) {
			user.getStores().stream().forEach(str -> {
				StoreVO storeVo = new StoreVO();
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

	@Override
	public List<UserDetailsVO> getUserDetailsByIds(List<Long> userIds) {
		List<UserDetails> users = userRepository.findByIdInAndIsCustomer(userIds, Boolean.FALSE);
		List<UserDetailsVO> userDetailsList = new ArrayList<>();
		if (!users.isEmpty()) {
			users.stream().forEach(user -> {
				UserDetailsVO userDetailsVO = new UserDetailsVO();
				userDetailsVO.setId(user.getId());
				userDetailsVO.setUserName(user.getUserName());
				userDetailsVO.setPhoneNumber(user.getPhoneNumber());
				userDetailsList.add(userDetailsVO);
			});
		}
		return userDetailsList;
	}

	/*
	 * @RabbitListener(queues ="inventoryQueue") public void
	 * rabbitmqConsumer(PersonVo name) {
	 * 
	 * System.out.println("************************message recived from : "+name); }
	 */

}
