package com.otsi.retail.authservice.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.cognitoidp.model.AdminCreateUserResult;
import com.amazonaws.services.cognitoidp.model.AdminGetUserResult;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeResult;
import com.amazonaws.services.cognitoidp.model.ConfirmForgotPasswordResult;
import com.amazonaws.services.cognitoidp.model.CreateGroupResult;
import com.amazonaws.services.cognitoidp.model.ForgotPasswordResult;
import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.amazonaws.services.cognitoidp.model.ListUsersResult;
import com.amazonaws.services.rekognition.model.Label;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import com.otsi.retail.authservice.Entity.Domain_Master;
import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.Entity.ParentPrivilages;
import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.Entity.UserDeatils;
import com.otsi.retail.authservice.requestModel.AddRoleRequest;
import com.otsi.retail.authservice.requestModel.AdminCreatUserRequest;
import com.otsi.retail.authservice.requestModel.AssignStoresRequest;
import com.otsi.retail.authservice.requestModel.DomainVo;
import com.otsi.retail.authservice.requestModel.DomianStoresVo;
import com.otsi.retail.authservice.requestModel.GetStoresRequestVo;
import com.otsi.retail.authservice.requestModel.GetUserRequestModel;
import com.otsi.retail.authservice.requestModel.ClientDetailsVo;
import com.otsi.retail.authservice.requestModel.ClientDomianVo;
import com.otsi.retail.authservice.requestModel.ConfirmSignupRequest;
import com.otsi.retail.authservice.requestModel.CreateRoleRequest;
import com.otsi.retail.authservice.requestModel.LoginRequest;
import com.otsi.retail.authservice.requestModel.MasterDomianVo;
import com.otsi.retail.authservice.requestModel.NewPasswordChallengeRequest;
import com.otsi.retail.authservice.requestModel.SignupRequest;
import com.otsi.retail.authservice.requestModel.StoreVo;
import com.otsi.retail.authservice.responceModel.Response;
import com.otsi.retail.authservice.services.AmazonRekoginitionService;
import com.otsi.retail.authservice.services.CognitoAuthService;
import com.otsi.retail.authservice.services.CognitoClient;
import com.otsi.retail.authservice.utils.GateWayResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private CognitoAuthService cognitoAuthService;
	@Autowired
	private CognitoClient cognitoClient;

	@PostMapping(path = "/signup")
	public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest) {

		try {
			Response res = cognitoAuthService.signUp(signupRequest.getUserName(), signupRequest.getEmail(),
					signupRequest.getPassword(), signupRequest.getGivenName(), signupRequest.getName(),
					signupRequest.getPhoneNo(), signupRequest.getStoreId());
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path = "/login")
	public GateWayResponse<?> login(@RequestBody LoginRequest request) {
		Response res = new Response();

		try {
			Map<String, String> result = cognitoAuthService.login(request.getEmail(), request.getPassword(),
					request.getStoreName());
			res.setAuthResponce(result);
			res.setStatusCode(200);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			res.setStatusCode(400);
			res.setErrorDescription(e.getMessage());
			return new GateWayResponse<>(400, res, "", "false");
		}

	}

	@PostMapping(path = "/confirmEmail")
	public GateWayResponse<?> confirmEmail(@RequestBody ConfirmSignupRequest request) {
		Response res = null;
		try {
			res = cognitoAuthService.confirmSignUp(request.getUserName(), request.getConfimationCode());
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, res, "", "false");
		}

	}

	@PostMapping(path = "/addRole")
	public GateWayResponse<?> addRole(@RequestBody AddRoleRequest req) {
		Response res = null;
		try {
			res = cognitoAuthService.addRoleToUser(req.getGroupName(), req.getUserName());
			return new GateWayResponse<>(200, res, "", "true");
		} catch (InvalidParameterException ie) {
			return new GateWayResponse<>(400, res, "", "false");

		} catch (Exception e) {
			return new GateWayResponse<>(400, res, "", "false");
		}

	}

	@GetMapping(path = "/getUserInfo/{username}")
	public GateWayResponse<?> getUserInfo(@PathVariable String username) {

		try {
			AdminGetUserResult user = cognitoAuthService.getUserInfo(username);
			return new GateWayResponse<>(200, user, "", "true");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		} catch (BadJOSEException e) {
			// TODO Auto-generated catch block
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		} catch (JOSEException e) {
			// TODO Auto-generated catch block
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}

	@PostMapping(path = "/assignStores")
	public GateWayResponse<?> assignStoresToUser(@RequestBody AssignStoresRequest req) throws Exception {
		Response res = null;
		try {
			res = cognitoAuthService.assignStoreToUser(req.getStores(), req.getUserName());
			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@PostMapping(path = "/createUser")
	public GateWayResponse<?> createUser(@RequestBody AdminCreatUserRequest request) {
		Response res = new Response();
		try {
			Response result = cognitoAuthService.createUser(request);
			return new GateWayResponse<>(200, result, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@PostMapping(path = "/authResponce")
	public GateWayResponse<?> newPasswordChallenge(@RequestBody NewPasswordChallengeRequest req) {

		try {
			AdminRespondToAuthChallengeResult res = cognitoAuthService.authResponceForNewUser(req);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}

	}

	@PostMapping(path = "/loginWithTempPass")
	public GateWayResponse<?> loginwithTempPassword(@RequestBody LoginRequest request) {
		Response res = new Response();

		try {
			AdminInitiateAuthResult result = cognitoClient.loginWithTempPassword(request.getEmail(),
					request.getPassword());

			// res.setAuthResponce(result);
			// res.setStatusCode(200);
			return new GateWayResponse<>(200, result, "", "true");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			res.setStatusCode(400);
			res.setErrorDescription(e.getMessage());
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}

	}

	@GetMapping(path = "/getUserStores/{userName}")
	public GateWayResponse<?> getUserStores(@PathVariable String userName) {
		String[] stores;
		try {
			stores = cognitoAuthService.getStoresForUser(userName);
			return new GateWayResponse<>(200, stores, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}

	}

	@PostMapping(path = "/forgetPassword")
	public GateWayResponse<?> forgetPassword(@RequestParam String username) {
		try {
			ForgotPasswordResult res = cognitoClient.forgetPassword(username);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@PostMapping(path = "/confirmforgetPassword")
	public GateWayResponse<?> confirmForgetPassword(@RequestParam String username,
			@RequestParam String confirmarionCode,String newPassword) {
		try {
			ConfirmForgotPasswordResult res = cognitoClient.confirmForgetPassword(username, confirmarionCode,newPassword);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@PutMapping("/enabledOrdisabledUser/{user}/{action}")
	public GateWayResponse<?> enabledOrdisabledUser(@PathVariable String user, @PathVariable String action) {
		try {
			String res = cognitoAuthService.enableOrDisableUser(user, action);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}

	}

}
