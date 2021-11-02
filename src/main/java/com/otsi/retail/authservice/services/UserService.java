package com.otsi.retail.authservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.otsi.retail.authservice.Entity.UserDeatils;
import com.otsi.retail.authservice.Repository.UserAvRepo;
import com.otsi.retail.authservice.Repository.UserRepo;
import com.otsi.retail.authservice.requestModel.GetUserRequestModel;
@Service
public class UserService {

	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserAvRepo userAvRepo;
	
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
	
	public List<UserDeatils> getUserForClient(long clientId) throws Exception{
	
	List<UserDeatils>	users=userRepo.findByUserAv_IntegerValue(clientId);
	if(!CollectionUtils.isEmpty(users)) {
		return users;		
	}else {
		throw new Exception("No users found with this client");
	}
		
	}

	public List<UserDeatils> getUsersForClient(long parseLong) {
		return null;
	}
}
