package com.otsi.rpd.authservice.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.cognitoidp.model.AdminGetUserResult;
import com.amazonaws.services.cognitoidp.model.AdminUpdateUserAttributesResult;
import com.amazonaws.services.cognitoidp.model.ConfirmSignUpResult;
import com.amazonaws.services.cognitoidp.model.GetUserResult;
import com.amazonaws.services.cognitoidp.model.InvalidParameterException;
import com.amazonaws.services.cognitoidp.model.SignUpResult;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.osti.rpd.authservice.responceModel.Response;
import com.otsi.rpd.authservice.configuration.AwsCognitoTokenProcessor;
import com.otsi.rpd.authservice.requestModel.AssignStoresRequest;
import com.otsi.rpd.authservice.requestModel.AddRoleRequest;
import com.otsi.rpd.authservice.requestModel.ConfirmSignupRequest;
import com.otsi.rpd.authservice.requestModel.LoginRequest;
import com.otsi.rpd.authservice.requestModel.SignupRequest;
import com.otsi.rpd.authservice.services.CognitoAuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

	@Autowired
	private CognitoAuthService cognitoAuthService;
	
	@Autowired
	private AwsCognitoTokenProcessor awsCognitoTokenProcessor;

	@PostMapping(path = "/signup")
	public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest) {
		
		try {
			Response res = cognitoAuthService.signUp(signupRequest.getUserName(), signupRequest.getEmail(),
					signupRequest.getPassword(),signupRequest.getGivenName(),signupRequest.getName(),signupRequest.getPhoneNo(),signupRequest.getStoreId());
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		} catch (Exception e) {
          	return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		Response res = new Response();

		try {
			Map<String, String> result = cognitoAuthService.login(request.getEmail(), request.getPassword(),request.getStoreName());
			
			res.setAuthResponce(result);
			res.setStatusCode(200);
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			res.setStatusCode(400);
			res.setErrorDescription(e.getMessage());
			return new ResponseEntity<>(res,HttpStatus.OK);
		}
		

	}

	@PostMapping(path = "/confirmEmail")
	public ResponseEntity<?> confirmEmail(@RequestBody ConfirmSignupRequest request) {
		Response res = null;
		try {
			res = cognitoAuthService.confirmSignUp(request.getUserName(), request.getConfimationCode());
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}

	}
	
	@PostMapping(path="/addRole")
	public ResponseEntity<?> addRole(@RequestBody AddRoleRequest req) {
		Response res = null;
		try {
			res = cognitoAuthService.AddRoleToUser(req.getGroupName(), req.getUserName(), req.getUserpoolId());
			return new ResponseEntity<Response>(res, HttpStatus.OK);
		}catch (InvalidParameterException ie)
		{
			return new ResponseEntity<>(ie.getMessage(),HttpStatus.BAD_REQUEST);

		}catch (Exception e) {
		 return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
			
	}
	
	@GetMapping(path="/getUserInfo/{username}")
		public AdminGetUserResult getUserInfo(@PathVariable String username) throws ParseException, BadJOSEException, JOSEException {
		
		Authentication auths=SecurityContextHolder.getContext().getAuthentication();
		System.out.println("-------------------");
		System.out.println(auths.toString());
		return	cognitoAuthService.getUserInfo(username);
		}
	
	@PostMapping(path="/assignStores")
	public ResponseEntity<?> assignStoresToUser(@RequestBody AssignStoresRequest req) throws Exception{
		Response res = null;
		try {
				res=cognitoAuthService.assignStoreToUser(req.getStores(), req.getUserName());
			return new ResponseEntity<>(res,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		
		
	}
	
}
