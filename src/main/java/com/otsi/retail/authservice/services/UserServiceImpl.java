package com.otsi.retail.authservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.RuntimeCryptoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.otsi.retail.authservice.Entity.UserDeatils;
import com.otsi.retail.authservice.Exceptions.UserNotFoundException;
import com.otsi.retail.authservice.Repository.UserAvRepo;
import com.otsi.retail.authservice.Repository.UserRepo;
import com.otsi.retail.authservice.requestModel.GetUserRequestModel;
import com.otsi.retail.authservice.utils.CognitoAtributes;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserAvRepo userAvRepo;

	public List<UserDeatils> getUserFromDb(GetUserRequestModel userRequest) throws Exception {

		List<UserDeatils> users = new ArrayList<>();
		if (0l != userRequest.getId()) {
			Optional<UserDeatils> user = userRepo.findById(userRequest.getId());
			if (user.isPresent()) {
				users.add(user.get());

			} else {
				throw new RuntimeException("User not found with this Id : " + userRequest.getId());
			}
		}
		if (null != userRequest.getName()) {
			Optional<UserDeatils> user = userRepo.findByUserName(userRequest.getName());
			if (user.isPresent()) {
				users.add(user.get());

			} else {
				throw new RuntimeException("User not found with this UserName : " + userRequest.getName());
			}
		}
		if (null != userRequest.getPhoneNo()) {
			Optional<UserDeatils> user = userRepo.findByPhoneNumber(userRequest.getPhoneNo());
			if (user.isPresent()) {
				users.add(user.get());
			} else {
				throw new Exception("No user found with this userName: " + userRequest.getPhoneNo());
			}
		}
         return users;
	}

	public List<UserDeatils> getUserForClient(int clientId) throws Exception {

		List<UserDeatils> users = userRepo.findByUserAv_NameAndUserAv_IntegerValue(CognitoAtributes.CLIENT_ID,clientId);
		if (!CollectionUtils.isEmpty(users)) {
			return users;
		} else {
			throw new Exception("No users found with this client");
		}

	}

	public List<UserDeatils> getUsersForClientDomain(long clientDomianId) {
	List<UserDeatils> users=userRepo.findByClientDomians_ClientDomainaId(clientDomianId);
	if(!CollectionUtils.isEmpty(users)) {
		return users;
	}else {
		throw new UserNotFoundException("User not found with this Domian Id : "+clientDomianId );
	}
		
		
	}
}
