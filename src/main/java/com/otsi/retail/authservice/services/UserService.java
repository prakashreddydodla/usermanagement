package com.otsi.retail.authservice.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.otsi.retail.authservice.Entity.UserDeatils;
import com.otsi.retail.authservice.requestModel.GetUserRequestModel;
import com.otsi.retail.authservice.requestModel.UpdateUserRequest;
import com.otsi.retail.authservice.responceModel.GetCustomerResponce;
import com.otsi.retail.authservice.responceModel.UserListResponse;

@Component
public interface UserService {
/**
 * 
 * @param userRequest
 * @return
 * @throws Exception
 */
	List<UserDeatils> getUserFromDb(GetUserRequestModel userRequest) throws Exception;

	List<UserListResponse> getUserForClient(int clientId) throws Exception;

	List<UserListResponse> getUsersForClientDomain(long clientDomianId);

	GetCustomerResponce getCustomerbasedOnMobileNumber(String mobileNo, String mobileNo2);

	UserListResponse getUserbasedOnMobileNumber(String mobileNo) throws Exception;
	public String updateUser(UpdateUserRequest req);
	
	
	
	
}
