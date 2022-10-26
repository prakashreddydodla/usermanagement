package com.otsi.retail.authservice.controller;

import java.text.ParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.requestModel.AddRoleRequest;
import com.otsi.retail.authservice.requestModel.AdminCreatUserRequest;
import com.otsi.retail.authservice.requestModel.AssignStoresRequest;

import com.otsi.retail.authservice.requestModel.LoginRequest;
import com.otsi.retail.authservice.requestModel.NewPasswordChallengeRequest;
import com.otsi.retail.authservice.responceModel.Response;
import com.otsi.retail.authservice.services.CognitoAuthService;
import com.otsi.retail.authservice.services.CognitoClient;
import com.otsi.retail.authservice.utils.EndpointConstants;
import com.otsi.retail.authservice.utils.ErrorCodes;
import com.otsi.retail.authservice.utils.GateWayResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(EndpointConstants.AUTH)
public class AuthController {
	
	@Autowired
	private CognitoAuthService cognitoAuthService;
	
	@Autowired
	private CognitoClient cognitoClient;
	//
	//private Logger logger = LogManager.getLogger(AuthController.class);
	@ApiOperation(value = "addRole", notes = "adding Roles")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = Response.class, responseContainer = "Object") })
	@PostMapping(path = EndpointConstants.ADD_ROLE)
	public GateWayResponse<?> addRole(@RequestBody AddRoleRequest req) {
		Response res = null;
		try {
			//logger.info("add_role request : "+req);
			Long createdBy = 0L;
			res = cognitoAuthService.addRoleToUser(req.getGroupName(), req.getUserName(),createdBy);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (InvalidParameterException ie) {
			return new GateWayResponse<>(400, res, ie.getMessage(), "false");

		} catch (Exception e) {
			return new GateWayResponse<>(400, res, e.getMessage(), "false");
		}

	}
	@ApiOperation(value = "getUserInfo/{username}", notes = "getting user Details")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = Response.class, responseContainer = "Object") })

	@GetMapping(path = EndpointConstants.GET_USER_INFO)
	public GateWayResponse<?> getUserInfo(@PathVariable String username) {
		//logger.info("In getUserInfo request userName : "+username);
		try {
			AdminGetUserResult user = cognitoAuthService.getUserInfo(username);	
			//logger.info("In getUserInfo responce  : "+user);
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
	@ApiOperation(value = EndpointConstants.ASSIGN_STORES, notes = "assigns stores to users")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = Response.class, responseContainer = "Object") })
	@PostMapping(path = EndpointConstants.ASSIGN_STORES)
	public GateWayResponse<?> assignStoresToUser(@RequestBody AssignStoresRequest req) throws Exception {
		//logger.info("In ASSIGN_STORES request userName : "+req);
		Response res = null;
		try {
			res = cognitoAuthService.assignStoreToUser(req.getStores(), req.getUserName());
			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	
	/**
	 * API for creating user
	 * @param request
	 * @return
	 */
	@ApiOperation(value = EndpointConstants.CREATE_USER, notes = "creating users")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = Response.class, responseContainer = "Object") })
	@PostMapping(path = EndpointConstants.CREATE_USER)
	public ResponseEntity<?> createUser(@RequestBody AdminCreatUserRequest request,@RequestHeader(required=false) Long clientId) {
		return cognitoAuthService.createUser(request,clientId);
	}
	
	/*@PostMapping(path = EndpointConstants.CREATE_CLIENTSUPPORTS)
	public ResponseEntity<?> createClientSupports(@RequestBody AdminCreatUserRequest request,@RequestHeader(required=false) Long clientId) {
		return cognitoAuthService.createUser(request,clientId);
	}	*/
	

/**
 * 
 * @param req
 * @return
 * @throws Exception
 */
	@ApiOperation(value = EndpointConstants.AUTH_RESPONSE, notes = "newPassword challange")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = AdminRespondToAuthChallengeResult.class, responseContainer = "Object") })
	@PostMapping(path = EndpointConstants.AUTH_RESPONSE)
	public ResponseEntity<?> newPasswordChallenge(@RequestBody NewPasswordChallengeRequest req) throws Exception {
		//logger.info("In AUTH_RESPONCE request  : "+req);
			AdminRespondToAuthChallengeResult res = cognitoAuthService.authChallenge(req);
			return ResponseEntity.ok(res);
	}
	
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@ApiOperation(value =EndpointConstants.LOGIN_WITH_TEMP_PASS, notes = "loginWith TempPassword")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = AdminInitiateAuthResult.class, responseContainer = "Object") })
	@PostMapping(path = EndpointConstants.LOGIN_WITH_TEMP_PASS)
	public ResponseEntity<?> loginwithTempPassword(@RequestBody LoginRequest request,@RequestHeader(required=false)Long clientId) throws NumberFormatException, Exception {
		AdminInitiateAuthResult result = cognitoClient.loginWithTempPassword(request.getEmail(), request.getPassword());
		return ResponseEntity.ok(result);

	}
	
	
	@ApiOperation(value = "getUserStores/{userName}", notes = "getting stores for users using userName")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = String.class, responseContainer = "String[]") })

	@GetMapping(path = EndpointConstants.GET_USER_STORES)
	public GateWayResponse<?> getUserStores(@PathVariable String userName) {
		String[] stores;
		//logger.info("In LOGIN_WITH_TEMP_PASS request userName : "+userName);

		try {
			stores = cognitoAuthService.getStoresForUser(userName);
			return new GateWayResponse<>(200, stores, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}

	}
	@ApiOperation(value = "forgetPassword", notes = "ForgetPassword")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = ForgotPasswordResult.class, responseContainer = "Object") })

	@PostMapping(path = EndpointConstants.FORGET_PASSWORD)
	public GateWayResponse<?> forgetPassword(@RequestParam String username) {
		//logger.info("In FORGET_PASSWORD request userName : "+username);
		try {
			ForgotPasswordResult res = cognitoClient.forgetPassword(username);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	@ApiOperation(value = "confirmforgetPassword", notes = "password confirmation")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = ConfirmForgotPasswordResult.class, responseContainer = "Object") })
	@PostMapping(path = EndpointConstants.CONFIRM_FORGET_PASSWORD)
	public GateWayResponse<?> confirmForgetPassword(@RequestParam String username,
			@RequestParam String confirmarionCode,String newPassword) {
		//logger.info("In CONFIRM_FORGET_PASSWORD request userName : "+username);

		try {
			ConfirmForgotPasswordResult res = cognitoClient.confirmForgetPassword(username, confirmarionCode,newPassword);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	@ApiOperation(value = "enabledOrdisabledUser/{user}/{action}", notes = "enabledOrdisabledUser")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = String.class, responseContainer = "Object") })
	@PutMapping(EndpointConstants.ENABLE_OR_DISABLE_USER)
	public GateWayResponse<?> enabledOrdisabledUser(@PathVariable String user, @PathVariable String action) {
		try {
			//logger.info("In ENABLE_OR_DISABLE_USER request userName : "+user+" ,action : "+action);
			String res = cognitoAuthService.enableOrDisableUser(user, action);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}

		
		
	}
	@ApiOperation(value = "resetUserPassword", notes = "adminResetPasssword")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = AdminResetUserPasswordResult.class, responseContainer = "Object") })
	@GetMapping(path = EndpointConstants.RESET_USER_PASSWORD)
	public GateWayResponse<?> adminRestPasssword(@RequestParam String userName) {
		try {
			//logger.info("In RESET_USER_PASSWORD request userName : "+userName);
			AdminResetUserPasswordResult res = cognitoClient.adminresetPassword(userName);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}

	}
	
}
