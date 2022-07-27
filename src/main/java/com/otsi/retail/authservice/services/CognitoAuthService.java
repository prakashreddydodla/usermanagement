package com.otsi.retail.authservice.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.amazonaws.services.cognitoidp.model.AdminGetUserResult;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeResult;
import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.requestModel.AdminCreatUserRequest;
import com.otsi.retail.authservice.requestModel.NewPasswordChallengeRequest;
import com.otsi.retail.authservice.responceModel.Response;

@Component
public interface CognitoAuthService {

	//Response addRoleToUser(String groupName, String userName) throws InvalidParameterException, Exception;

	AdminGetUserResult getUserInfo(String username) throws Exception;

	Response assignStoreToUser(List<Store> stores, String userName) throws Exception;

	ResponseEntity<?> createUser(AdminCreatUserRequest request, Long clientId);

	String[] getStoresForUser(String userName) throws Exception;

	String enableOrDisableUser(String userName, String actionType) throws Exception;

	AdminRespondToAuthChallengeResult authChallenge(NewPasswordChallengeRequest req) throws Exception;

	Response addRoleToUser(String groupName, String userName, Long createdBy)
			throws InvalidParameterException, Exception;
}
