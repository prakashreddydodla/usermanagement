package com.otsi.retail.authservice.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.cognitoidp.model.AdminUpdateUserAttributesResult;
import com.amazonaws.services.cognitoidp.model.ListUsersResult;
import com.otsi.retail.authservice.Entity.UserDeatils;
import com.otsi.retail.authservice.requestModel.GetUserRequestModel;
import com.otsi.retail.authservice.requestModel.UpdateUserRequest;
import com.otsi.retail.authservice.responceModel.GetCustomerResponce;
import com.otsi.retail.authservice.responceModel.UserListResponse;
import com.otsi.retail.authservice.services.CognitoAuthService;
import com.otsi.retail.authservice.services.CognitoAuthServiceImpl;
import com.otsi.retail.authservice.services.CognitoClient;
import com.otsi.retail.authservice.services.UserService;
import com.otsi.retail.authservice.services.UserServiceImpl;
import com.otsi.retail.authservice.utils.EndpointConstants;
import com.otsi.retail.authservice.utils.GateWayResponse;

@RestController
@RequestMapping(EndpointConstants.USER)
public class UserController {


	@Autowired
	private UserService userService;

	@Autowired
	private CognitoClient cognitoClient;
	private Logger logger = LogManager.getLogger(UserController.class);

	@PostMapping(EndpointConstants.GET_USER)
	public GateWayResponse<?> getUserFromDB(@RequestBody GetUserRequestModel userRequest) {
		try {
			logger.info("In GET_USER request : "+userRequest.toString());
			List<UserDeatils> res = userService.getUserFromDb(userRequest);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping(EndpointConstants.GET_ALL_USERS)
	public GateWayResponse<?> getAllUsers() {
		try {
			logger.info("In GET_ALL_USERS request : ");
			ListUsersResult res = cognitoClient.getAllUsers();
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}

	@GetMapping(EndpointConstants.GET_ALL_USERS_BY_CLIENT_ID)
	public GateWayResponse<?> getUsersForClient(@PathVariable String clientId) {
		try {
			logger.info("In GET_ALL_USERS_BY_CLIENT_ID request clientId : "+clientId);
			List<UserListResponse> res = userService.getUserForClient(Integer.parseInt(clientId));
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}

	@GetMapping(EndpointConstants.GET_ALL_USERS_BY_CLIENT_DOMIAN)
	public GateWayResponse<?> getUsersForClientDomianId(@PathVariable String clientDomianId) {
		try {
			logger.info("In GET_ALL_USERS_BY_CLIENT_DOMIAN request clientDomianId : "+clientDomianId);
			List<UserListResponse> res = userService.getUsersForClientDomain(Long.parseLong(clientDomianId));
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}
	
	@GetMapping(EndpointConstants.GET_USER_PROFILE)
	public GateWayResponse<?> getCustomer(@PathVariable String mobileNo) {
		try {
			logger.info("In GET_USER_PROFILE request mobileNo : "+mobileNo);
			UserListResponse res = userService.getUserbasedOnMobileNumber(mobileNo);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}

	@GetMapping(EndpointConstants.GET_CUSTOMER)
	public GateWayResponse<?> getCustomer(@PathVariable String feild,@PathVariable String mobileNo) {
		try {
			logger.info("In GET_CUSTOMER request mobileNo : "+mobileNo);
			GetCustomerResponce res = userService.getCustomerbasedOnMobileNumber(feild,mobileNo);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}
//
	@PutMapping("/updateUser")
	public GateWayResponse<?> updateUser(@RequestBody UpdateUserRequest req) {
		try {
			logger.info("In updateUser request mobileNo : "+req);
			String res = userService.updateUser(req);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}
	
}
