package com.otsi.retail.authservice.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.otsi.retail.authservice.Entity.UserDeatils;
import com.otsi.retail.authservice.requestModel.GetUserRequestModel;
import com.otsi.retail.authservice.requestModel.UpdateUserRequest;
import com.otsi.retail.authservice.requestModel.UserDetailsVo;
import com.otsi.retail.authservice.responceModel.GetCustomerResponce;
import com.otsi.retail.authservice.responceModel.UserListResponse;

@Component
public interface UserService {
/**
 * 
 * @param userRequest
 * @param userId 
 * @param pageable 
 * @return
 * @throws Exception
 */
	Page<UserDeatils> getUserFromDb(GetUserRequestModel userRequest, Long userId, Pageable pageable) throws Exception;

	Page<UserListResponse> getUserForClient(int clientId, Pageable pageable) throws Exception;

	List<UserListResponse> getUsersForClientDomain(long clientDomianId);

	GetCustomerResponce getCustomerbasedOnMobileNumber(String mobileNo, String mobileNo2);

	UserListResponse getUserbasedOnMobileNumber(String mobileNo) throws Exception;
	public String updateUser(UpdateUserRequest req);

	List<UserDetailsVo> getUsersForGivenIds(List<Long> userIds);
	
	List<UserDetailsVo> getCustomersForGivenIds(List<Long> userIds);
	
	UserDeatils getMobileNumber(String mobileNumber);

	String deleteUser(Long id);
	
	
}
