package com.otsi.retail.authservice.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.amazonaws.services.cognitoidp.model.AdminUpdateUserAttributesResult;
import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.Entity.ClientUsers;
import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.Entity.UserAv;
import com.otsi.retail.authservice.Entity.UserDetails;
import com.otsi.retail.authservice.Exceptions.RecordNotFoundException;
import com.otsi.retail.authservice.Exceptions.UserNotFoundException;
import com.otsi.retail.authservice.Repository.ClientDetailsRepo;
import com.otsi.retail.authservice.Repository.ClientUserRepo;
import com.otsi.retail.authservice.Repository.ClientcDomianRepo;
import com.otsi.retail.authservice.Repository.RoleRepository;
import com.otsi.retail.authservice.Repository.StoreRepo;
import com.otsi.retail.authservice.Repository.UserAvRepo;
import com.otsi.retail.authservice.Repository.UserRepository;
import com.otsi.retail.authservice.mapper.userDetailsMapper;
import com.otsi.retail.authservice.requestModel.GetUserRequestModel;
import com.otsi.retail.authservice.requestModel.StoreVO;
//import com.otsi.retail.authservice.requestModel.PersonVo;
import com.otsi.retail.authservice.requestModel.UpdateUserRequest;
import com.otsi.retail.authservice.requestModel.UserDetailsVO;
import com.otsi.retail.authservice.requestModel.UsersSearchVO;
import com.otsi.retail.authservice.responceModel.GetCustomerResponce;
import com.otsi.retail.authservice.responceModel.UserListResponse;
import com.otsi.retail.authservice.utils.CognitoAtributes;
import com.otsi.retail.authservice.utils.DateConverters;

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
	private userDetailsMapper userMapper;

	@Autowired
	private CognitoClient cognitoClient;

	@Autowired
	private ClientDetailsRepo clientRepo;

	@Autowired
	private ClientUserRepo clientUserRepo;

	// private Logger logger = LogManager.getLogger(UserServiceImpl.class);

	public Page<UserListResponse> getUserFromDb(GetUserRequestModel userRequest, Long clientId, Pageable pageable)
			throws Exception {
		// logger.info(" ############### getUserFromDb method starts ##############3");
		Page<UserDetails> users = null;
		if (0l != userRequest.getId()) {
			users = userRepository.findById(userRequest.getId(), pageable);
			if (users.hasContent()) {
				return users.map(user -> getUserDeatils(user));
			} else {
				// logger.error("User not found with this Id : " + userRequest.getId());
				throw new RuntimeException("User not found with this Id : " + userRequest.getId());
			}

		}
		if (null != userRequest.getName() && "" != userRequest.getName()) {
			users = userRepository.findByUserName(userRequest.getName(), pageable);
			if (users.hasContent()) {
				return users.map(user -> getUserDeatils(user));

			} else {
				// logger.error("User not found with this UserName : " + userRequest.getName());
				throw new RuntimeException("User not found with this UserName : " + userRequest.getName());
			}
		}
		if (null != userRequest.getPhoneNo() && "" != userRequest.getPhoneNo()) {
			users = userRepository.findByPhoneNumber(userRequest.getPhoneNo(), pageable);
			if (users.hasContent()) {
				return users.map(user -> getUserDeatils(user));

			} else {
				// logger.debug("No user found with this userName: " +
				// userRequest.getPhoneNo());
				// logger.error("No user found with this userName: " +
				// userRequest.getPhoneNo());
				throw new Exception("No user found with this userName: " + userRequest.getPhoneNo());
			}
		}
		if (null != userRequest.getStoreName() && userRequest.isActive() && userRequest.getRoleName() != null) {
			users = userRepository.findByStores_NameAndRoleRoleNameAndIsActive(userRequest.getStoreName(),
					userRequest.getRoleName(), Boolean.TRUE, pageable);
			if (users.isEmpty()) {
				// logger.debug("No users found with this given Details : " +
				// userRequest.getRoleName());
				// logger.error("No users found with this GivenDeatils : " +
				// userRequest.getRoleName());
				throw new RuntimeException("No users found with this Given Details: " + userRequest.getRoleName());
			}
			// logger.info(" ############### getUserFromDb method ends ##############3");
			return users.map(user -> getUserDeatils(user));
		}
		if (null != userRequest.getStoreName() && userRequest.isInActive() && userRequest.getRoleName() != null) {
			users = userRepository.findByStores_NameAndRoleRoleNameAndIsActive(userRequest.getStoreName(),
					userRequest.getRoleName(), Boolean.FALSE, pageable);
			if (users.isEmpty()) {
				// logger.debug("No users found with this given Details : " +
				// userRequest.getRoleName());
				// logger.error("No users found with this GivenDeatils : " +
				// userRequest.getRoleName());
				throw new RuntimeException("No users found with this Given Details: " + userRequest.getRoleName());
			}
			// logger.info(" ############### getUserFromDb method ends ##############3");
			return users.map(user -> getUserDeatils(user));
		}
		if (null != userRequest.getStoreName() && !userRequest.isActive() && !userRequest.isInActive()
				&& userRequest.getRoleName() != null) {
			users = userRepository.findByStores_NameAndRoleRoleNameAndClient_Id(userRequest.getStoreName(),
					userRequest.getRoleName(), clientId, pageable);
			if (users.isEmpty()) {
				// logger.debug("No users found with this given Details : " +
				// userRequest.getRoleName());
				// logger.error("No users found with this GivenDeatils : " +
				// userRequest.getRoleName());
				throw new RuntimeException("No users found with this Given Details: " + userRequest.getRoleName());
			}
			// logger.info(" ############### getUserFromDb method ends ##############3");
			return users.map(user -> getUserDeatils(user));
		}

		if (null != userRequest.getRoleName() && userRequest.isActive()) {
			users = userRepository.findByRoleRoleNameAndIsActive(userRequest.getRoleName(), Boolean.TRUE, pageable);
			if (users.isEmpty()) {
				// logger.debug("No users found with this Role ID : " +
				// userRequest.getRoleName());
				// logger.error("No users found with this Role ID : " +
				// userRequest.getRoleName());
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleName());
			}
			// logger.info(" ############### getUserFromDb method ends ##############3");
			return users.map(user -> getUserDeatils(user));
		}
		if ((null == userRequest.getRoleName() || "" == userRequest.getRoleName())
				&& (null == userRequest.getStoreName() || "" == userRequest.getStoreName()) && userRequest.isActive()) {
			users = userRepository.findByIsActiveAndUserAv_NameAndUserAv_IntegerValue(Boolean.TRUE,
					CognitoAtributes.CLIENT_ID, userRequest.getClientDomainId(), pageable);
			if (users.isEmpty()) {
				// logger.debug("No users found with this Role ID : " +
				// userRequest.getRoleName());
				// logger.error("No users found with this Role ID : " +
				// userRequest.getRoleName());
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleName());
			}
			// logger.info(" ############### getUserFromDb method ends ##############3");
			return users.map(user -> getUserDeatils(user));
		}
		if ((null == userRequest.getRoleName() || "" == userRequest.getRoleName())
				&& (null == userRequest.getStoreName() || "" == userRequest.getStoreName())
				&& userRequest.isInActive()) {
			users = userRepository.findByIsActiveAndUserAv_NameAndUserAv_IntegerValue(Boolean.FALSE,
					CognitoAtributes.CLIENT_ID, userRequest.getClientDomainId(), pageable);
			if (users.isEmpty()) {
				// logger.debug("No users found with this Role ID : " +
				// userRequest.getRoleName());
				// logger.error("No users found with this Role ID : " +
				// userRequest.getRoleName());
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleName());
			}
			// logger.info(" ############### getUserFromDb method ends ##############3");
			return users.map(user -> getUserDeatils(user));
		}

		if (null != userRequest.getRoleName() && userRequest.isInActive()) {
			users = userRepository.findByRoleRoleNameAndIsActiveAndClient_Id(userRequest.getRoleName(), Boolean.FALSE,
					clientId, pageable);
			if (users.isEmpty()) {
				// logger.debug("No users found with this Role ID : " +
				// userRequest.getRoleName());
				// logger.error("No users found with this Role ID : " +
				// userRequest.getRoleName());
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleName());
			}
			// logger.info(" ############### getUserFromDb method ends ##############3");
			return users.map(user -> getUserDeatils(user));
		}
		if (null != userRequest.getRoleName() && !userRequest.isActive() && !userRequest.isInActive()) {
			users = userRepository.findByRoleRoleNameAndClientId(userRequest.getRoleName(), clientId, pageable);
			if (users.isEmpty()) {
				// logger.debug("No users found with this Role ID : " +
				// userRequest.getRoleName());
				// logger.error("No users found with this Role ID : " +
				// userRequest.getRoleName());
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleName());
			}
			// logger.info(" ############### getUserFromDb method ends ##############3");
			return users.map(user -> getUserDeatils(user));
		}

		if (null != userRequest.getStoreName() && userRequest.isActive()) {
			users = userRepository.findByStores_NameAndIsActiveAndClient_Id(userRequest.getStoreName(), Boolean.TRUE,
					clientId, pageable);
			if (users.isEmpty()) {
				// logger.debug("No users found with this Role ID : " +
				// userRequest.getRoleName());
				// logger.error("No users found with this Role ID : " +
				// userRequest.getRoleName());
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleName());
			}
			// logger.info(" ############### getUserFromDb method ends ##############3");
			return users.map(user -> getUserDeatils(user));
		}
		if (null != userRequest.getStoreName() && userRequest.isInActive()) {
			users = userRepository.findByStores_NameAndIsActiveAndClient_Id(userRequest.getStoreName(), Boolean.FALSE,
					clientId, pageable);
			if (users.isEmpty()) {
				// logger.debug("No users found with this storeName : " +
				// userRequest.getStoreName());
				// logger.error("No users found with this storeName : " +
				// userRequest.getStoreName());
				throw new RuntimeException("No users found with this storeName : " + userRequest.getStoreName());
			}
			// logger.info(" ############### getUserFromDb method ends ##############3");
			return users.map(user -> getUserDeatils(user));
		}
		if (null != userRequest.getStoreName() && !userRequest.isActive() && !userRequest.isInActive()) {
			users = userRepository.findByStores_NameAndClient_Id(userRequest.getStoreName(), clientId, pageable);
			if (users.isEmpty()) {
				// logger.debug("No users found with this storeName : " +
				// userRequest.getStoreName());
				// logger.error("No users found with this Role ID : " +
				// userRequest.getStoreName());
				throw new RuntimeException("No users found with this storeName : " + userRequest.getStoreName());
			}
			return users.map(user -> getUserDeatils(user));
		}

		// logger.debug("Please select atleast one input");
		// logger.error("Please select atleast one input");
		throw new RuntimeException("Please select atleast one input");

	}

	public Page<UserListResponse> getUserForClient(Long clientId, Pageable pageable) throws Exception {
		Page<UserDetails> users = null;
		users = userRepository.findByUserAv_NameAndUserAv_IntegerValue(CognitoAtributes.CLIENT_ID, clientId, pageable);
		if (users != null) {

			return users.map(user -> getUserDeatils(user));

		} else {
			// logger.debug("No users found with this client");
			// logger.error("No users found with this client");
			throw new Exception("No users found with this client");
		}

	}

	private UserListResponse getUserDeatils(UserDetails a) {
		UserListResponse userVo = new UserListResponse();
		userVo.setId(a.getId());
		userVo.setUserName(a.getUserName());
		userVo.setCreatedBy(a.getCreatedBy());
		userVo.setCreatedDate(a.getCreatedDate());
		userVo.setIsSuperAdmin(a.getIsSuperAdmin());
		userVo.setIsActive(a.getIsActive());
		userVo.setPhoneNumber(a.getPhoneNumber());
		List<StoreVO> stores = new ArrayList<>();
		if (null != a.getStores()) {
			a.getStores().stream().forEach(str -> {
				StoreVO storeVo = new StoreVO();
				storeVo.setId(str.getId());
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
		return userVo;

	}

	public List<UserListResponse> getUsersForClientDomain(Long clientDomianId) {
		// logger.info(" ############### getUsersForClientDomain method starts
		// ##############3");

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
			// logger.info(" ############### getUsersForClientDomain method ends
			// ##############3");

			return userList;
		} else {
			throw new UserNotFoundException("User not found with this Domian Id : " + clientDomianId);
		}

	}

	@Override
	public GetCustomerResponce getCustomerbasedOnMobileNumber(String type, String value, Long clientId) {
		// logger.info(" ############### getCustomerbasedOnMobileNumber method starts
		// ##############3");

		Optional<UserDetails> user = Optional.empty();
		if (null != type && type.equalsIgnoreCase("mobileNo")) {
			user = userRepository.findByPhoneNumber(value);
			if (!user.isPresent()) {
				// logger.debug("No customer found with this MobileNo : " + value);
				// logger.error("No customer found with this MobileNo : " + value);
				throw new RuntimeException("No customer found with this MobileNo : " + value);
			}
		}
		if (null != type && type.equalsIgnoreCase("id")) {
			user = userRepository.findById(Long.parseLong(value));
			if (!user.isPresent()) {
				// logger.debug("No customer found with this Id : " + value);
				// logger.error("No customer found with this Id : " + value);
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
		// logger.info(" ############### getCustomerbasedOnMobileNumber method ends
		// ##############3");

		return customer;

	}

	@Override
	public String updateUser(UpdateUserRequest req) throws RuntimeException {
		try {
			Optional<UserDetails> userOptional = userRepository.findById(req.getId());
			if (userOptional.isPresent()) {

				UserDetails userDetails = userOptional.get();
				userDetails.setId(req.getId());
				userDetails.setUserName(req.getUsername());
				userDetails.setPhoneNumber(req.getPhoneNumber());
				userDetails.setGender(req.getGender());
				userDetails.setIsActive(req.getIsActive());
				// userFromDb.setLastModifyedDate(LocalDate.now());
				if (null != req.getRole()) {
					Optional<Role> role = roleRepository.findByRoleName(req.getRole().getRoleName());
					if (role.isPresent()) {
						userDetails.setRole(role.get());
					} else {
						// logger.debug("Role not found in DB with this Id : " + req.getRole().getId());
						// logger.error("Role not found in DB with this Id : " + req.getRole().getId());
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

				if (null != req.getClientId()) {
					List<ClientDetails> clients = new ArrayList<>();
					Arrays.asList(req.getClientId()).stream().forEach(clientId -> {
						Optional<ClientDetails> dbClientRecord = clientRepo
								.findById(Long.parseLong(clientId.toString()));

						if (dbClientRecord.isPresent()) {
							clients.add(dbClientRecord.get());
						} else {
							// logger.debug("Client not found with this Id : " + clientId);
							// logger.error("Client not found with this Id : " + clientId);
							throw new RuntimeException("Client not found with this Id : " + clientId);
						}
					});
					savedUser.setClient(clients);
					userRepository.save(savedUser);
				}

				if (!CollectionUtils.isEmpty(req.getStores())) {
					List<Store> stores = new ArrayList<>();
					req.getStores().stream().forEach(storeVo -> {
						Optional<Store> storeOptional = storeRepo.findById(storeVo.getId());
						if (storeOptional.isPresent()) {

							stores.add(storeOptional.get());

						}
					});
					savedUser.setStores(stores);
					userRepository.save(savedUser);
					// logger.info(" ############### updated user in DB ##############3");
				}
				// logger.info(" ############### updating user in cognito userpool
				// ##############3");
				AdminUpdateUserAttributesResult result = cognitoClient.updateUserInCognito(req);
				if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
					// logger.info(" ############### updated user in cognito userpool
					// ##############3");
					// logger.info(" ############### updateUser method ends ##############3");
					return "SucessFully updated";
				} else {
					// logger.debug("Failed to update");
					// logger.error("Failed to update");
					throw new RuntimeException("Failed to update");
				}
			} else {
				// logger.debug("User not found with this Id :" + req.getId());
				// logger.error("User not found with this Id :" + req.getId());
				throw new RuntimeException("User not found with this Id :" + req.getId());
			}

		} catch (RuntimeException re) {
			// logger.debug(re.getMessage());
			// logger.error(re.getMessage());
			throw new RuntimeException(re.getMessage());
		}
	}

	@Override
	public UserListResponse getUserbasedOnMobileNumber(String mobileNo) throws Exception {

		// logger.info("################ getUserbasedOnMobileNumber method starts
		// ############");
		Optional<UserDetails> userOptional = userRepository.findByUserNameAndIsCustomer(mobileNo, Boolean.FALSE);
		if (!userOptional.isPresent()) {
			// logger.debug("User details not found with this mobile number : " + mobileNo);
			// logger.error("User details not found with this mobile number : " + mobileNo);
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
		// logger.info("################ getUserbasedOnMobileNumber method ends
		// ############");
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

	@Override
	public List<UserDetailsVO> getCustomersForGivenIds(List<Long> userIds) {
		List<UserDetails> customers = userRepository.findByIdInAndIsCustomer(userIds, Boolean.TRUE);
		if (!customers.isEmpty()) {

			List<UserDetailsVO> userDetailsVos = new ArrayList<>();
			customers.stream().forEach(customer -> {
				UserDetailsVO userDetailsVo = new UserDetailsVO();
				userDetailsVo.setId(customer.getId());
				userDetailsVo.setUserName(customer.getUserName());
				userDetailsVo.setPhoneNumber(customer.getPhoneNumber());

				userDetailsVos.add(userDetailsVo);
			});

			return userDetailsVos;

		} else {
			// logger.debug("No customers found with these customerId's");
			// logger.error("No customers found with these customerId's");
			throw new RuntimeException("No customers found with these customerId's");
		}
	}

	@Override
	public UserDetailsVO getMobileNumber(String mobileNumber) {
		String phoneNumber = "+" + mobileNumber.trim();

		Optional<UserDetails> user = userRepository.findByPhoneNumber(phoneNumber);
		if (user == null) {
			throw new RecordNotFoundException("No user found with this userName: " + mobileNumber, 400);
		}
		UserDetailsVO userDetailsVo = new UserDetailsVO();
		userDetailsVo.setId(user.get().getId());
		userDetailsVo.setUserName(user.get().getUserName());
		userDetailsVo.setPhoneNumber(user.get().getPhoneNumber());

		return userDetailsVo;
	}


	@Override
	public String deleteUser(Long id) {
		Optional<UserDetails> userDetail = userRepository.findById(id);
		if (userDetail.isPresent()) {
			UserDetails userDetails = userDetail.get();
			userDetails.setIsActive(Boolean.FALSE);
			userRepository.save(userDetails);

			// AdminDisableProviderForUserRequest adminDisableProviderForUser =
			// cognitoClient.
			return "user record deleted with the given id" + id;

		} else {

			throw new RuntimeException("No user found with these Id" + id);
		}
	}

	@Override
	public Page<UserDetailsVO> getUsersByRoleName(String roleName, UsersSearchVO userSearchVo, Pageable pageable) {
		

		try {
			Long roleId = 0L;
			Optional<Role> role = roleRepository.findByRoleName(roleName);
			if(role.isPresent()) {
			 roleId = role.get().getId();
			}

			if (StringUtils.isNotEmpty(roleName) && roleName != null && userSearchVo.getSupporterName() == null
					&& userSearchVo.getFromDate() == null && userSearchVo.getToDate() == null ) {
				Page<UserDetails> userDetails = userRepository.findByRole_RoleName(roleName, pageable);
				if (userDetails.hasContent()) {
					Page<UserDetailsVO> userDetailsVO = userMapper.convertUsersDetailsToVO(userDetails);
					return userDetailsVO;
				}

			} else if (userSearchVo.getSupporterName() != null && userSearchVo.getSupporterName().length() >= 3
					&& userSearchVo.getFromDate() != null && userSearchVo.getToDate() != null &&  roleName != null) {

				LocalDateTime createdDatefrom = DateConverters
						.convertLocalDateToLocalDateTime(userSearchVo.getFromDate());
				LocalDateTime createdDateTo = DateConverters.convertToLocalDateTimeMax(userSearchVo.getToDate());
				

				Page<UserDetails> userDetail = userRepository.findByUserNameAndRole_IdAndCreatedDateBetween(
						userSearchVo.getSupporterName(),roleId, createdDatefrom, createdDateTo, pageable);

				if (userDetail.hasContent()) {

					Page<UserDetailsVO> userDetailsVO = userMapper.convertUsersDetailsToVO(userDetail);
					return userDetailsVO;
				}

			} else if (userSearchVo.getSupporterName() != null && roleName!=null && userSearchVo.getSupporterName().length() >= 3
					&& userSearchVo.getFromDate() != null) {

				LocalDateTime createdDatefrom = DateConverters
						.convertLocalDateToLocalDateTime(userSearchVo.getFromDate());
				LocalDateTime createdDateTo = DateConverters.convertToLocalDateTimeMax(userSearchVo.getFromDate());

				Page<UserDetails> userDetail = userRepository.findByUserNameAndRole_IdAndCreatedDateBetween(
						userSearchVo.getSupporterName(), roleId, createdDatefrom, createdDateTo, pageable);
				if (userDetail.hasContent()) {

					Page<UserDetailsVO> userDetailsVO = userMapper.convertUsersDetailsToVO(userDetail);
					return userDetailsVO;
				}

			} else if (userSearchVo.getSupporterName() == null && roleName!=null && userSearchVo.getToDate() != null
					&& userSearchVo.getFromDate() != null) {

				LocalDateTime createdDatefrom = DateConverters
						.convertLocalDateToLocalDateTime(userSearchVo.getFromDate());
				LocalDateTime createdDateTo = DateConverters.convertToLocalDateTimeMax(userSearchVo.getToDate());

				Page<UserDetails> userDetail = userRepository.findByRole_RoleNameAndCreatedDateBetween(roleName,createdDatefrom, createdDateTo,
						pageable);
				if (userDetail.hasContent()) {

					Page<UserDetailsVO> userDetailsVO = userMapper.convertUsersDetailsToVO(userDetail);
					return userDetailsVO;
				}

			} else if (userSearchVo.getSupporterName() != null && userSearchVo.getSupporterName().length() >= 3 && roleName!=null) {

				Page<UserDetails> users = userRepository.findByUserNameAndRole_Id(userSearchVo.getSupporterName(),roleId, pageable);

				if (users.hasContent()) {
					return userMapper.convertUsersDetailsToVO(users);
				}
			}

			return Page.empty();

		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());

		}

	}

	/*
	 * @RabbitListener(queues ="inventoryQueue") public void
	 * rabbitmqConsumer(PersonVo name) {
	 * 
	 * System.out.println("************************message recived from : "+name); }
	 */

}