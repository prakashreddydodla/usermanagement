package com.otsi.retail.authservice.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.otsi.retail.authservice.Entity.UserDetails;
import com.otsi.retail.authservice.requestModel.GetUserRequestModel;
import com.otsi.retail.authservice.requestModel.UpdateUserRequest;
import com.otsi.retail.authservice.requestModel.UserDetailsVO;
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
	Page<UserDetails> getUserFromDb(GetUserRequestModel userRequest, Long userId, Pageable pageable) throws Exception;

	Page<UserListResponse> getUserForClient(Long clientId, Pageable pageable) throws Exception;

	List<UserListResponse> getUsersForClientDomain(Long clientDomianId);

	GetCustomerResponce getCustomerbasedOnMobileNumber(String mobileNo, String mobileNo2);

	UserListResponse getUserbasedOnMobileNumber(String mobileNo) throws Exception;
	public String updateUser(UpdateUserRequest req);

	List<UserDetailsVO> getUserDetailsByIds(List<Long> userIds);
	
List<UserDetailsVO> getCustomersForGivenIds(List<Long> userIds);
	
	UserDetails getMobileNumber(String mobileNumber);

	String deleteUser(Long id);
	
}
