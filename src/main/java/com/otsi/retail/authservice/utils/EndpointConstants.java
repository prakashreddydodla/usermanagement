package com.otsi.retail.authservice.utils;

public class EndpointConstants {
// #########################################     AUTH CONTROLLER END_POINTS   ###############################################
	public static final String SIGNUP = "/signup";
	public static final String LOGIN = "/login";
	public static final String AUTH = "/auth";
	public static final String CONFIR_EMAIL = "/confirmEmail";
	public static final String ADD_ROLE = "/addRole";
	public static final String GET_USER_INFO = "/getUserInfo/{username}";
	public static final String ASSIGN_STORES = "/assignStores";
	public static final String CREATE_USER = "/createUser";
	public static final String AUTH_RESPONCE = "/authResponce";
	public static final String LOGIN_WITH_TEMP_PASS = "/loginWithTempPass";
	public static final String GET_USER_STORES = "/getUserStores/{userName}";
	public static final String FORGET_PASSWORD = "/forgetPassword";
	public static final String CONFIRM_FORGET_PASSWORD = "/confirmforgetPassword";
	public static final String ENABLE_OR_DISABLE_USER = "/enabledOrdisabledUser/{user}/{action}";
	public static final String RESET_USER_PASSWORD = "/resetUserPassword/{userName}";
	public static final String GET_USER_PROFILE = "/get_user_profile/{mobileNo}";


	
//#############################################    CLIENT CONTROLLER  END_POINTS   ###########################################	
	public static final String CLIENT = "/client";
	public static final String CREATE_MASTER_DOMIAN = "/createMasterDomain";
	public static final String GET_MASTER_DOMAINS = "/getMasterDomains";
	public static final String CREATE_CLIENT = "/createClient";
	public static final String ASSIGN_DOMAIN_TO_CLIENT = "/assignDomianToClient";
	public static final String GET_DOMAINS_FOR_CLIENT = "/getDomiansForClient/{clientId}";
	public static final String GET_CLIENT = "/getClient/{clientId}";
	public static final String GET_ALL_CLIENTS = "/getAllClients";
	public static final String GET_DOMIAN_BY_ID ="domian/{clientDomianId}";


	
//############################################     ROLES CONTROLLER	END_POINTS   #############################################
	public static final String ROLES = "/roles";
	public static final String CREATE_ROLE = "/createRole";
	public static final String UPDATE_ROLE = "/updateRole";
	public static final String GET_ROLES_FOR_DOMIAN = "/getRolesForDomian/{domianId}";
	public static final String GET_ROLES_FOR_CLIENT = "/getRolesForClient/{clientId}";
	public static final String ADD_PREVILAGE = "/addPrevilage";
	public static final String GET_PRIVILAGES = "/getPrivilages/{roleId}";
	public static final String PRIVILAGES_BY_NAME = "/privilagesByName/{roleName}";
	public static final String SUB_PRIVILAGES = "/subPrivilages/{parentId}";
	public static final String GET_ALL_PRIVILAGES = "/getAllPrivilages";
	public static final String ROLES_WITH_FILTER = "/rolesWithFilter";
	public static final String GET_PRIVILLAGES_BY_DOMIAN = "/privillagesForDomian/{domian}";




	
//########################################       STORE CONTROLLER END_POINTS      ##############################################
	public static final String STORE = "/store";
	public static final String CREATE_STORE = "/createStore";
	public static final String GET_CLIENT_DOMIAN_STORES = "/getClientDomianStores";
	public static final String GET_CLIENT_STORES = "/getClientStores";
	public static final String ASSIGN_STORES_TO_DOMIAN = "/assignStoresToDomain";
	public static final String GET_STORES_WITH_FILTER = "/getStoresWithFilter";
	public static final String SAVE_STATES = "/saveStates";
	public static final String ALL_STATES = "/allStates";
	public static final String GET_DISTRICT = "/getDistrict";
	public static final String UPDATE_STORE = "/store";
	public static final String GET_STORELIST = "/storeList";
	public static final String GET_GSTDETAILS = "/getgstDetails";

	
	
// ##########################################     USER CONTROLLER  END_POINTS     #########################################
	
	public static final String USER = "/user";
	public static final String GET_USER = "/getUser";
	public static final String GET_ALL_USERS = "/getallUsers";
	public static final String GET_ALL_USERS_BY_CLIENT_ID = "/getallUsers/{clientId}";
	public static final String GET_ALL_USERS_BY_CLIENT_DOMIAN = "/usersByClientDomianId/{clientDomianId}";
	public static final String GET_CUSTOMER = "/customer/{feild}/{mobileNo}";


	
//######################################    AWS REKOGNIBITION CONTROLLER END_POINTS      ####################################
	
	public static final String IMAGE = "/image";
	public static final String IMAGE_SCANNING = "/imageScanning";

}
