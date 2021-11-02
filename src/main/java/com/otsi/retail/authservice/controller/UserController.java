package com.otsi.retail.authservice.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.cognitoidp.model.ListUsersResult;
import com.otsi.retail.authservice.Entity.UserDeatils;
import com.otsi.retail.authservice.requestModel.GetUserRequestModel;
import com.otsi.retail.authservice.services.CognitoAuthService;
import com.otsi.retail.authservice.services.CognitoClient;
import com.otsi.retail.authservice.services.UserService;
import com.otsi.retail.authservice.utils.GateWayResponse;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private CognitoClient cognitoClient;
	
	@PostMapping("/getUser")
	public GateWayResponse<?> getUserFromDB(@RequestBody GetUserRequestModel userRequest) {
		try {
			UserDeatils res = userService.getUserFromDb(userRequest);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping("/getallUsers")
	public GateWayResponse<?> getAllUsers() {
		try {
			ListUsersResult res = cognitoClient.getAllUsers();
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}
	
	
	@GetMapping("/getallUsers/{clientId}")
	public GateWayResponse<?> getUsersForClient(@PathVariable String clientId) {
		try {
			List<UserDeatils> res = userService.getUsersForClient(Long.parseLong(clientId));
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}
}
