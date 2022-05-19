package com.otsi.retail.authservice.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.cognitoidp.model.ListUsersResult;
import com.otsi.retail.authservice.Entity.UserDeatils;
import com.otsi.retail.authservice.requestModel.GetUserRequestModel;
import com.otsi.retail.authservice.requestModel.UpdateUserRequest;
import com.otsi.retail.authservice.requestModel.UserDetailsVo;
import com.otsi.retail.authservice.responceModel.GetCustomerResponce;
import com.otsi.retail.authservice.responceModel.UserListResponse;
import com.otsi.retail.authservice.services.CognitoClient;
import com.otsi.retail.authservice.services.UserService;
import com.otsi.retail.authservice.utils.EndpointConstants;
import com.otsi.retail.authservice.utils.GateWayResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(value = "UserController", description = "REST APIs related to HsnDetails Entity !!!!")
@RestController
@RequestMapping(EndpointConstants.USER)
public class UserController {


	@Autowired
	private UserService userService;

	@Autowired
	private CognitoClient cognitoClient;
	private Logger logger = LogManager.getLogger(UserController.class);
	@ApiOperation(value = "getUser", notes = "get user details from db", response = UserDeatils.class)
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = UserDeatils.class, responseContainer = "List") })
	@PostMapping(EndpointConstants.GET_USER)
	public GateWayResponse<?> getUserFromDB(@RequestBody GetUserRequestModel userRequest,@RequestHeader("clientId") Long clientId) {
		try {
			logger.info("In GET_USER request : "+userRequest.toString());
			List<UserDeatils> res = userService.getUserFromDb(userRequest,clientId);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	@ApiOperation(value = "getUsersForGivenIds", notes = "get user details for given ids", response = UserDetailsVo.class)
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = UserDetailsVo.class, responseContainer = "List") })
	@PostMapping(EndpointConstants.GET_USERSFOR_GIVENIDS)
	public GateWayResponse<?> getUsersForGivenIds(@RequestBody List<Long> userIds) {
		try {
			logger.info("In GET_USER request : "+userIds.toString());
			List<UserDetailsVo> res = userService.getUsersForGivenIds(userIds);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	
	@ApiOperation(value = "", notes = "getMobileNumber", response = UserDeatils.class)
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = UserDeatils.class, responseContainer = "List") })
	@GetMapping(EndpointConstants.GET_MOBILENUMBER)
	public GateWayResponse<?> getMobileNumber(@RequestParam String mobileNumber) {
		try {
			logger.info("In GET_MOBILENUMBER request : "+mobileNumber);
			UserDeatils res = userService.getMobileNumber(mobileNumber);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	@ApiOperation(value = "getCustomersForGivenIds", notes = "get customer details for given ids", response = UserDetailsVo.class)
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = UserDetailsVo.class, responseContainer = "List") })
	@PostMapping(EndpointConstants.GET_CUSTOMERSFOR_GIVENIDS)
	public GateWayResponse<?> getCustomersForGivenIds(@RequestBody List<Long> userIds) {
		try {
			logger.info("In GET_CUSTOMER request : "+userIds.toString());
			List<UserDetailsVo> res = userService.getCustomersForGivenIds(userIds);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	
	
	@ApiOperation(value = "getAllUsers", notes = "get  all the user details", response = ListUsersResult.class)
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = ListUsersResult.class, responseContainer = "List") })
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
	@ApiOperation(value = "getAllUsersByClientId", notes = "get  all the user detailsusing clientId", response = UserListResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = UserListResponse.class, responseContainer = "List") })
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
	@ApiOperation(value = "getAllUsersByClientDomainId", notes = "get  all the user detailsusing clientId and domainId", response = UserListResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = UserListResponse.class, responseContainer = "List") })
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
	@ApiOperation(value = "getUserProfile", notes = "get   users based on MobileNumber", response = UserListResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = UserListResponse.class, responseContainer = "List") })
	
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
	@ApiOperation(value = "getCustomer", notes = "get customer based on mobile number", response = GetCustomerResponce.class)
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = GetCustomerResponce.class, responseContainer = "List") })
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
	@ApiOperation(value = "updateUser", notes = "update user record")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = String.class, responseContainer = "List") })
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
