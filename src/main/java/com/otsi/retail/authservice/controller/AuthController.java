package com.otsi.retail.authservice.controller;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.cognitoidp.model.AdminCreateUserResult;
import com.amazonaws.services.cognitoidp.model.AdminGetUserResult;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeResult;
import com.amazonaws.services.cognitoidp.model.ConfirmForgotPasswordResult;
import com.amazonaws.services.cognitoidp.model.CreateGroupResult;
import com.amazonaws.services.cognitoidp.model.ForgotPasswordResult;
import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import com.otsi.retail.authservice.gatewayresponse.GateWayResponse;
import com.otsi.retail.authservice.requestModel.AddRoleRequest;
import com.otsi.retail.authservice.requestModel.AdminCreatUserRequest;
import com.otsi.retail.authservice.requestModel.AssignStoresRequest;
import com.otsi.retail.authservice.requestModel.ConfirmSignupRequest;
import com.otsi.retail.authservice.requestModel.LoginRequest;
import com.otsi.retail.authservice.requestModel.NewPasswordChallengeRequest;
import com.otsi.retail.authservice.requestModel.SignupRequest;
import com.otsi.retail.authservice.responceModel.Response;
import com.otsi.retail.authservice.services.CognitoAuthService;
import com.otsi.retail.authservice.services.CognitoClient;

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
			return new GateWayResponse<Response>("login successfully", res);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			res.setStatusCode(400);
			res.setErrorDescription(e.getMessage());
			return new GateWayResponse<>(res,"please give valid details");
		}

	}

	@PostMapping(path = "/confirmEmail")
	public ResponseEntity<?> confirmEmail(@RequestBody ConfirmSignupRequest request) {
		Response res = null;
		try {
			res = cognitoAuthService.confirmSignUp(request.getUserName(), request.getConfimationCode());
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(path = "/addRole")
	public ResponseEntity<?> addRole(@RequestBody AddRoleRequest req) {
		Response res = null;
		try {
			res = cognitoAuthService.AddRoleToUser(req.getGroupName(), req.getUserName(), req.getUserpoolId());
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		} catch (InvalidParameterException ie) {
			return new ResponseEntity<>(ie.getMessage(), HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(path = "/getUserInfo/{username}")
	public ResponseEntity<?> getUserInfo(@PathVariable String username) {

		System.out.println("-------------------");
		try {
			AdminGetUserResult user = cognitoAuthService.getUserInfo(username);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (BadJOSEException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (JOSEException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path = "/assignStores")
	public ResponseEntity<?> assignStoresToUser(@RequestBody AssignStoresRequest req) throws Exception {
		Response res = null;
		try {
			res = cognitoAuthService.assignStoreToUser(req.getStores(), req.getUserName());
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path = "/createUser")
	public ResponseEntity<?> createUser(@RequestBody AdminCreatUserRequest request) {
		Response res = new Response();
		try {
			AdminCreateUserResult result = cognitoClient.adminCreateUser(request);
			if (result != null) {
				if (result.getSdkHttpMetadata().getHttpStatusCode() == 200) {
					res.setStatusCode(200);
					res.setBody("with user " + result);
				} else {
					res.setStatusCode(result.getSdkHttpMetadata().getHttpStatusCode());
					res.setBody("something went wrong");
				}
			}
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path = "/authResponce")
	public ResponseEntity<?> newPasswordChallenge(@RequestBody NewPasswordChallengeRequest req) {

		try {
			AdminRespondToAuthChallengeResult res = cognitoClient.respondAuthChalleng(req);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(path = "/loginWithTempPass")
	public ResponseEntity<?> loginwithTempPassword(@RequestBody LoginRequest request) {
		Response res = new Response();

		try {
			AdminInitiateAuthResult result = cognitoClient.loginWithTempPassword(request.getEmail(),
					request.getPassword());

			// res.setAuthResponce(result);
			// res.setStatusCode(200);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			res.setStatusCode(400);
			res.setErrorDescription(e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.OK);
		}

	}

	@GetMapping(path = "/getUserStores/{userName}")
	public ResponseEntity<?> getUserStores(@PathVariable String userName) {
		String[] stores;
		try {
			stores = cognitoAuthService.getStoresForUser(userName);
			return new ResponseEntity<>(stores, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(path = "/forgetPassword")
	public ResponseEntity<?> forgetPassword(@RequestParam String username) {
		try {
			ForgotPasswordResult res = cognitoClient.forgetPassword(username);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

		}
	}

	@PostMapping(path = "/confirmforgetPassword")
	public ResponseEntity<?> confirmForgetPassword(@RequestParam String username, @RequestParam String confirmarionCode,
			String newPassword) {
		try {
			ConfirmForgotPasswordResult res = cognitoClient.confirmForgetPassword(username, confirmarionCode,
					newPassword);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

		}
	}

	@PostMapping(path = "/createRole")
	public ResponseEntity<?> createRole(@RequestParam String roleName, @RequestParam String discreption) {

		try {
			CreateGroupResult res = cognitoClient.createRole(roleName, discreption);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

		}
	}

}
