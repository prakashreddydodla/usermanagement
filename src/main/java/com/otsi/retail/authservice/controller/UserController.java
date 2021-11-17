package com.otsi.retail.authservice.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
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

	@PostMapping(EndpointConstants.GET_USER)
	public GateWayResponse<?> getUserFromDB(@RequestBody GetUserRequestModel userRequest) {
		try {
			List<UserDeatils> res = userService.getUserFromDb(userRequest);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping(EndpointConstants.GET_ALL_USERS)
	public GateWayResponse<?> getAllUsers() {
		try {
			ListUsersResult res = cognitoClient.getAllUsers();
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}

	@GetMapping(EndpointConstants.GET_ALL_USERS_BY_CLIENT_ID)
	public GateWayResponse<?> getUsersForClient(@PathVariable String clientId) {
		try {
			List<UserDeatils> res = userService.getUserForClient(Integer.parseInt(clientId));
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}

	@GetMapping(EndpointConstants.GET_ALL_USERS_BY_CLIENT_DOMIAN)
	public GateWayResponse<?> getUsersForClientDomianId(@PathVariable String clientDomianId) {
		try {
			List<UserListResponse> res = userService.getUsersForClientDomain(Long.parseLong(clientDomianId));
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}

	@GetMapping(EndpointConstants.GET_CUSTOMER)
	public GateWayResponse<?> getCustomer(@PathVariable String feild,@PathVariable String mobileNo) {
		try {
			GetCustomerResponce res = userService.getCustomerbasedOnMobileNumber(feild,mobileNo);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}

	@PutMapping("/updateUser")
	public GateWayResponse<?> updateUser(@RequestBody UpdateUserRequest req) {
		try {
			AdminUpdateUserAttributesResult res = cognitoClient.updateUserInCognito(req);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}
	
}
