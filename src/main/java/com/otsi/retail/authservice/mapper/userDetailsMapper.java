package com.otsi.retail.authservice.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.ParseConversionEvent;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.otsi.retail.authservice.Entity.UserAv;
import com.otsi.retail.authservice.Entity.UserDetails;
import com.otsi.retail.authservice.Repository.UserAvRepo;
import com.otsi.retail.authservice.Repository.UserRepository;
import com.otsi.retail.authservice.requestModel.StoreVO;
import com.otsi.retail.authservice.requestModel.UserDetailsVO;
import com.otsi.retail.authservice.responceModel.UserListResponse;
import com.otsi.retail.authservice.utils.CognitoAtributes;
@Component
public class userDetailsMapper {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserAvRepo userAvRepo;

	public Page<UserDetailsVO> convertUsersDetailsToVO(Page<UserDetails> usersDetails) {
		/*Page<UserDetailsVO> usersList = null;
		usersDetails.stream().forEach(userDetails -> {
			
			UserDetailsVO usersVo=	convertUserDetailsToVO(userDetails);
			usersList.add(usersVo);
		});
		return usersList;*/
		
		return usersDetails.map(user -> convertUserDetailToVO(user));

		
		
	}

	private UserDetailsVO convertUserDetailToVO(UserDetails userDetails) {
		UserDetailsVO userVO = new UserDetailsVO();
		
		userVO.setUserName(userDetails.getUserName());
		userVO.setPhoneNumber(userDetails.getPhoneNumber());
		if(ObjectUtils.isNotEmpty(userDetails.getCreatedBy())) {
		Optional<UserDetails> userDetail = userRepository.findById(userDetails.getCreatedBy());
		userVO.setCreatedBy(userDetail.get().getUserName());
		}
		userVO.setCreatedDate(userDetails.getCreatedDate());
		userVO.setId(userDetails.getId());
		userVO.setGender(userDetails.getGender());
		List<UserAv> users = userAvRepo.findByUserDataId(userDetails.getId());
		users.stream().forEach(user->{
			if (user.getName().equalsIgnoreCase(CognitoAtributes.EMAIL)) {
				userVO.setEmail(user.getStringValue());
			}
			if (user.getName().equalsIgnoreCase(CognitoAtributes.ADDRESS)) {
				userVO.setAddress(user.getStringValue());
			}
			if (user.getName().equalsIgnoreCase(CognitoAtributes.BIRTHDATE)) {
				userVO.setDob(user.getStringValue());
			}
			
			
			
		});
		return userVO;
		
		
	}
	
	public List<UserDetailsVO> convertListUsersDetailsToVO(List<UserDetails> usersDetails) {
		
		  List<UserDetailsVO> usersList = new ArrayList<>();
		  usersDetails.stream().forEach(userDetails -> {
		  
		  UserDetailsVO usersVo= convertUserDetailToVO(userDetails);
		  usersList.add(usersVo); 
		  }); 
		  return usersList;
		 
		
		//return usersDetails.map(user -> convertUserDetailsToVO(user));
		  

		
		
	}
	public UserListResponse getUserDeatils(UserDetails a) {
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
	
	
}
