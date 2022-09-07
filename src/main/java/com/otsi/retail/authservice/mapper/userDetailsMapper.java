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
import com.otsi.retail.authservice.requestModel.UserDetailsVO;
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
		
		return usersDetails.map(user -> convertUserDetailsToVO(user));

		
		
	}

	private UserDetailsVO convertUserDetailsToVO(UserDetails userDetails) {
		UserDetailsVO userVO = new UserDetailsVO();
		
		userVO.setUserName(userDetails.getUserName());
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
			
			
			
		});
		return userVO;
		
		
	}
}
