package com.otsi.retail.authservice.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.RuntimeCryptoException;
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

	public List<UserDeatils> getUserFromDb(GetUserRequestModel userRequest) throws Exception {

		List<UserDeatils> users = new ArrayList<>();
		if (0l != userRequest.getId()) {
			Optional<UserDeatils> user = userRepo.findByUserId(userRequest.getId());
			if (user.isPresent()) {
				users.add(user.get());
				return users;
			} else {
				throw new RuntimeException("User not found with this Id : " + userRequest.getId());
			}

		}
		if (null != userRequest.getName()) {
			Optional<UserDeatils> user = userRepo.findByUserName(userRequest.getName());
			if (user.isPresent()) {
				users.add(user.get());
				return users;

			} else {
				throw new RuntimeException("User not found with this UserName : " + userRequest.getName());
			}
		}
		if (null != userRequest.getPhoneNo()) {
			Optional<UserDeatils> user = userRepo.findByPhoneNumber(userRequest.getPhoneNo());
			if (user.isPresent()) {
				users.add(user.get());
				return users;

			} else {
				throw new Exception("No user found with this userName: " + userRequest.getPhoneNo());
			}
		}

		if (0L != userRequest.getRoleId() && userRequest.isActive()) {
			users = userRepo.findByRoleRoleIdAndIsActive(userRequest.getRoleId(), Boolean.TRUE);
			if (CollectionUtils.isEmpty(users)) {
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleId());
			}
			return users;
		}
		if (0L != userRequest.getRoleId() && userRequest.isInActive()) {
			users = userRepo.findByRoleRoleIdAndIsActive(userRequest.getRoleId(), Boolean.FALSE);
			if (CollectionUtils.isEmpty(users)) {
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleId());
			}
			return users;
		}
		if (0L != userRequest.getRoleId() && !userRequest.isActive() && !userRequest.isInActive()) {
			users = userRepo.findByRoleRoleId(userRequest.getRoleId());
			if (CollectionUtils.isEmpty(users)) {
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleId());
			}
			return users;
		}

		if (0L != userRequest.getStoreId() && userRequest.isActive()) {
			users = userRepo.findByStores_IdAndIsActive(userRequest.getStoreId(), Boolean.TRUE);
			if (CollectionUtils.isEmpty(users)) {
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleId());
			}
			return users;
		}
		if (0L != userRequest.getStoreId() && userRequest.isInActive()) {
			users = userRepo.findByStores_IdAndIsActive(userRequest.getStoreId(), Boolean.FALSE);
			if (CollectionUtils.isEmpty(users)) {
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleId());
			}
			return users;
		}
		if (0L != userRequest.getStoreId() && !userRequest.isActive() && !userRequest.isInActive()) {
			users = userRepo.findByStores_Id(userRequest.getStoreId());
			if (CollectionUtils.isEmpty(users)) {
				throw new RuntimeException("No users found with this Role ID : " + userRequest.getRoleId());
			}
			return users;
		}
		throw new RuntimeException("Please select atleast one input");

	}

	public List<UserListResponse> getUserForClient(int clientId) throws Exception {

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
				});
				userList.add(userVo);
			});

			return userList;
		} else {
			throw new Exception("No users found with this client");
		}

	}

	public List<UserListResponse> getUsersForClientDomain(long clientDomianId) {
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

			return userList;
		} else {
			throw new UserNotFoundException("User not found with this Domian Id : " + clientDomianId);
		}

	}

	@Override
	public GetCustomerResponce getCustomerbasedOnMobileNumber(String type, String value) {
		Optional<UserDeatils> user = Optional.empty();
		if (null != type && type.equalsIgnoreCase("mobileNo")) {
			user = userRepo.findByPhoneNumber(value);
			if (!user.isPresent()) {
				throw new RuntimeException("No customer found with this MobileNo : " + value);
			}
		}
		if (null != type && type.equalsIgnoreCase("id")) {
			user = userRepo.findByUserId(Long.parseLong(value));
			if (!user.isPresent()) {
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
		return customer;

	}

	public String updateUser(UpdateUserRequest req) {
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
					Optional<Role> role = roleRepository.findById(req.getRole().getRoleId());
					if (role.isPresent()) {
						userFromDb.setRole(role.get());
					} else {
						throw new RuntimeException(
								"Role not d=found in DB with this Id : " + req.getRole().getRoleId());
					}
				}
				UserDeatils savedUser = userRepo.save(userFromDb);

				userFromDb.getUserAv().stream().forEach(av -> {
					UserAv userAv = av;
					if (userAv.getName().equalsIgnoreCase(CognitoAtributes.EMAIL)) {
						userAv.setStringValue(req.getEmail());
						userAv.setLastModifyedDate(LocalDate.now());
						userAv.setUserData(savedUser);
						userAvRepo.save(userAv);
					}
					if (av.getName().equalsIgnoreCase(CognitoAtributes.PARENTID)) {
						userAv.setIntegerValue(Integer.parseInt(req.getParentId()));
						userAv.setLastModifyedDate(LocalDate.now());
						userAv.setUserData(savedUser);
						userAvRepo.save(userAv);
					}
					if (av.getName().equalsIgnoreCase(CognitoAtributes.ADDRESS)) {
						userAv.setStringValue(req.getAddress());
						userAv.setLastModifyedDate(LocalDate.now());
						userAv.setUserData(savedUser);
						userAvRepo.save(userAv);
					}

					if (av.getName().equalsIgnoreCase(CognitoAtributes.DOMAINID)) {
						userAv.setIntegerValue(Integer.parseInt(req.getDomianId()));
						userAv.setLastModifyedDate(LocalDate.now());
						userAv.setUserData(savedUser);
						userAvRepo.save(userAv);

					}
					if (av.getName().equalsIgnoreCase(CognitoAtributes.CLIENT_ID)) {
						userAv.setIntegerValue(Integer.parseInt(req.getClientId()));
						userAv.setLastModifyedDate(LocalDate.now());
						userAv.setUserData(savedUser);
						userAvRepo.save(userAv);

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
				}
				AdminUpdateUserAttributesResult result = cognitoClient.updateUserInCognito(req);
				if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
					return "SucessFully updated";
				} else {
					throw new RuntimeException("Failed to update");
				}
			} else {
				throw new RuntimeException("User not found with this Id :" + req.getUserId());
			}

		} catch (RuntimeException re) {
			throw new RuntimeException(re.getMessage());
		}
	}

	/*
	 * @RabbitListener(queues ="inventoryQueue") public void
	 * rabbitmqConsumer(PersonVo name) {
	 * 
	 * System.out.println("************************message recived from : "+name); }
	 */

}
