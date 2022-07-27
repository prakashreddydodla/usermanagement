package com.otsi.retail.authservice.mapper;

import java.util.List;
import java.util.Optional;

import javax.xml.bind.ParseConversionEvent;

import org.springframework.beans.factory.annotation.Autowired;
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

	public List<UserDetailsVO> convertUsersDetailsToVO(List<UserDetails> usersDetails) {
		
		usersDetails.stream().forEach(userDetails -> {
			
			convertUserDetailsToVO(userDetails);
			
		});
		return null;
		
		
	}

	private void convertUserDetailsToVO(UserDetails userDetails) {
		UserDetailsVO userVO = new UserDetailsVO();
		
		userVO.setUserName(userDetails.getUserName());
		Optional<UserDetails> userDetail = userRepository.findById(userDetails.getCreatedBy());
		userVO.setCreatedBy(userDetail.get().getUserName());
		userVO.setCreatedDate(userDetails.getCreatedDate());

		List<UserAv> users = userAvRepo.findByuserData_Id(userDetails.getId());
		users.stream().forEach(user->{
			if (user.getName().equalsIgnoreCase(CognitoAtributes.EMAIL)) {
				userVO.setEmail(user.getStringValue());
			}
			if (user.getName().equalsIgnoreCase(CognitoAtributes.GENDER)) {
				userVO.setGender(user.getStringValue());
			}
			
			
		});
		
		
	}

}
