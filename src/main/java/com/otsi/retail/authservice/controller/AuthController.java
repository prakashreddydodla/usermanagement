package com.otsi.retail.authservice.controller;

import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.amazonaws.services.cognitoidp.model.AdminGetUserResult;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AdminResetUserPasswordResult;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeResult;
import com.amazonaws.services.cognitoidp.model.ConfirmForgotPasswordResult;
import com.amazonaws.services.cognitoidp.model.ForgotPasswordResult;
import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import com.otsi.retail.authservice.requestModel.AddRoleRequest;
import com.otsi.retail.authservice.requestModel.AdminCreatUserRequest;
import com.otsi.retail.authservice.requestModel.AssignStoresRequest;

import com.otsi.retail.authservice.requestModel.LoginRequest;
import com.otsi.retail.authservice.requestModel.NewPasswordChallengeRequest;
import com.otsi.retail.authservice.responceModel.Response;
import com.otsi.retail.authservice.services.CognitoAuthService;
import com.otsi.retail.authservice.services.CognitoClient;
import com.otsi.retail.authservice.utils.EndpointConstants;
import com.otsi.retail.authservice.utils.GateWayResponse;

@RestController
@RequestMapping(EndpointConstants.AUTH)
public class AuthController {

	@Autowired
	private CognitoAuthService cognitoAuthService;
	
	@Autowired
	private CognitoClient cognitoClient;

	/*
	 * @PostMapping(path =EndpointConstants.SIGNUP) public ResponseEntity<?>
	 * signUp(@RequestBody SignupRequest signupRequest) {
	 * 
	 * try { Response res =
	 * cognitoAuthServiceImpl.signUp(signupRequest.getUserName(),
	 * signupRequest.getEmail(), signupRequest.getPassword(),
	 * signupRequest.getGivenName(), signupRequest.getName(),
	 * signupRequest.getPhoneNo(), signupRequest.getStoreId()); return new
	 * ResponseEntity<Response>(res, HttpStatus.OK); } catch (Exception e) { return
	 * new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); } }
	 * 
	 * @PostMapping(path = EndpointConstants.LOGIN) public GateWayResponse<?>
	 * login(@RequestBody LoginRequest request) { Response res = new Response();
	 * 
	 * try { Map<String, String> result =
	 * cognitoAuthServiceImpl.login(request.getEmail(), request.getPassword(),
	 * request.getStoreName()); res.setAuthResponce(result); res.setStatusCode(200);
	 * return new GateWayResponse<>(200, res, "", "true"); } catch (Exception e) {
	 * System.out.println(e.getMessage()); res.setStatusCode(400);
	 * res.setErrorDescription(e.getMessage()); return new GateWayResponse<>(400,
	 * res, "", "false"); }
	 * 
	 * }
	 * 
	 * @PostMapping(path = EndpointConstants.CONFIR_EMAIL) public GateWayResponse<?>
	 * confirmEmail(@RequestBody ConfirmSignupRequest request) { Response res =
	 * null; try { res = cognitoAuthServiceImpl.confirmSignUp(request.getUserName(),
	 * request.getConfimationCode()); return new GateWayResponse<>(200, res, "",
	 * "true"); } catch (Exception e) { return new GateWayResponse<>(400, res, "",
	 * "false"); }
	 * 
	 * }
	 */

	@PostMapping(path = EndpointConstants.ADD_ROLE)
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

	@GetMapping(path = EndpointConstants.GET_USER_INFO)
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

	@PostMapping(path = EndpointConstants.ASSIGN_STORES)
	public GateWayResponse<?> assignStoresToUser(@RequestBody AssignStoresRequest req) throws Exception {
		Response res = null;
		try {
			res = cognitoAuthService.assignStoreToUser(req.getStores(), req.getUserName());
			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@PostMapping(path =EndpointConstants.CREATE_USER)
	public GateWayResponse<?> createUser(@RequestBody AdminCreatUserRequest request) {
		Response res = new Response();
		try {
			res = cognitoAuthService.createUser(request);
			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@PostMapping(path = EndpointConstants.AUTH_RESPONCE)
	public GateWayResponse<?> newPasswordChallenge(@RequestBody NewPasswordChallengeRequest req) {

		try {
			AdminRespondToAuthChallengeResult res = cognitoAuthService.authResponceForNewUser(req);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}

	}

	@PostMapping(path = EndpointConstants.LOGIN_WITH_TEMP_PASS)
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

	@GetMapping(path = EndpointConstants.GET_USER_STORES)
	public GateWayResponse<?> getUserStores(@PathVariable String userName) {
		String[] stores;
		try {
			stores = cognitoAuthService.getStoresForUser(userName);
			return new GateWayResponse<>(200, stores, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}

	}

	@PostMapping(path = EndpointConstants.FORGET_PASSWORD)
	public GateWayResponse<?> forgetPassword(@RequestParam String username) {
		try {
			ForgotPasswordResult res = cognitoClient.forgetPassword(username);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@PostMapping(path = EndpointConstants.CONFIRM_FORGET_PASSWORD)
	public GateWayResponse<?> confirmForgetPassword(@RequestParam String username,
			@RequestParam String confirmarionCode,String newPassword) {
		try {
			ConfirmForgotPasswordResult res = cognitoClient.confirmForgetPassword(username, confirmarionCode,newPassword);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@PutMapping(EndpointConstants.ENABLE_OR_DISABLE_USER)
	public GateWayResponse<?> enabledOrdisabledUser(@PathVariable String user, @PathVariable String action) {
		try {
			String res = cognitoAuthService.enableOrDisableUser(user, action);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}

	}

	@GetMapping(path = EndpointConstants.RESET_USER_PASSWORD)
	public GateWayResponse<?> adminRestPasssword(@PathVariable String userName) {
		try {
			AdminResetUserPasswordResult		res = cognitoClient.adminresetPassword(userName);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}

	}
	
}
