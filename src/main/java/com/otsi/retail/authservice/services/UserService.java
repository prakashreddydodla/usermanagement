package com.otsi.retail.authservice.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.otsi.retail.authservice.Entity.UserDeatils;
import com.otsi.retail.authservice.requestModel.GetUserRequestModel;
import com.otsi.retail.authservice.responceModel.GetCustomerResponce;

@Component
public interface UserService {
/**
 * 
 * @param userRequest
 * @return
 * @throws Exception
 */
	List<UserDeatils> getUserFromDb(GetUserRequestModel userRequest) throws Exception;

	List<UserDeatils> getUserForClient(int clientId) throws Exception;

	List<UserDeatils> getUsersForClientDomain(long clientDomianId);

	GetCustomerResponce getCustomerbasedOnMobileNumber(String mobileNo);
	
	
	
	
}
