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
	public static final String CREATE_USER = "/create-user";
	public static final String AUTH_RESPONSE = "/auth-challenge";
	public static final String LOGIN_WITH_TEMP_PASS = "/temporary-login";
	public static final String GET_USER_STORES = "/getUserStores/{userName}";
	public static final String FORGET_PASSWORD = "/forgetPassword";
	public static final String CONFIRM_FORGET_PASSWORD = "/confirmforgetPassword";
	public static final String ENABLE_OR_DISABLE_USER = "/enabledOrdisabledUser/{user}/{action}";
	public static final String RESET_USER_PASSWORD = "/resetUserPassword";
	public static final String GET_USER_PROFILE = "/get_user_profile/{mobileNo}";


	
//#############################################    CLIENT CONTROLLER  END_POINTS   ###########################################	
	public static final String CLIENT = "/client";
	public static final String CREATE_MASTER_DOMIAN = "/createMasterDomain";
	public static final String GET_MASTER_DOMAINS = "/getMasterDomains";
	public static final String CREATE_CLIENT = "/create-client";
	public static final String ASSIGN_DOMAIN_TO_CLIENT = "/assignDomianToClient";
	public static final String GET_DOMAINS_FOR_CLIENT = "/getDomiansForClient/{clientId}";
	public static final String GET_CLIENT = "/getClient/{clientId}";
	public static final String GET_ALL_CLIENTS = "/getAllClients";
	public static final String GET_DOMIAN_BY_ID ="domian/{clientDomianId}";


	
//############################################     ROLES CONTROLLER	END_POINTS   #############################################
	public static final String ROLES = "/roles";
	public static final String CREATE_ROLE = "/create-role";
	public static final String UPDATE_ROLE = "/updateRole";
	public static final String GET_ROLES_FOR_DOMIAN = "/getRolesForDomian/{domianId}";
	public static final String GET_ROLES_FOR_CLIENT = "/client/{clientId}";
	public static final String ADD_PREVILAGE = "/addPrevilage";
	public static final String GET_PRIVILAGES = "/getPrivilages/{roleId}";
	public static final String PRIVILAGES_BY_NAME = "/privilagesByName/{roleName}";
	public static final String SUB_PRIVILAGES = "/subPrivilages/{parentId}";
	public static final String CHILD_PRIVILAGES = "/childPrivileges/{subPrivilegeId}";
	public static final String GET_ALL_PRIVILAGES = "/getAllPrivilages";
	public static final String ROLES_WITH_FILTER = "/rolesWithFilter";
	public static final String GET_PRIVILLAGES = "/privileges";




	
//########################################       STORE CONTROLLER END_POINTS      ##############################################
	public static final String STORE = "/store";
	public static final String CREATE_STORE = "/create-store";
	public static final String GET_CLIENT_DOMIAN_STORES = "/getClientDomianStores";
	public static final String GET_CLIENT_STORES = "/client/stores";
	public static final String ASSIGN_STORES_TO_DOMIAN = "/assignStoresToDomain";
	public static final String GET_STORES_WITH_FILTER = "/getStoresWithFilter";
	public static final String SAVE_STATES = "/saveStates";
	public static final String ALL_STATES = "/allStates";
	public static final String GET_DISTRICT = "/getDistrict";
	public static final String UPDATE_STORE = "/store";
	public static final String GET_STORELIST = "/storeList";
	public static final String GET_GSTDETAILS = "/getgstDetails";
	public static final String DELETE_STORE = "/deleteStore";


	
	
// ##########################################     USER CONTROLLER  END_POINTS     #########################################
	
	public static final String USER = "/user";
	public static final String GET_USERSFOR_GIVENIDS = "/getUsersForGivenIds";
	
	public static final String GET_MOBILENUMBER = "/mobilenumber";
	public static final String GET_CUSTOMERSFOR_GIVENIDS = "/getCustomersForGivenIds";
	public static final String GET_USER = "/getUser";
    public static final String GET_ALL_USERS = "/users";
	public static final String GET_ALL_USERS_BY_CLIENT_ID = "/users/{clientId}";
	public static final String GET_ALL_USERS_BY_CLIENT_DOMIAN = "/usersByClientDomianId/{clientDomianId}";
	public static final String GET_CUSTOMER = "/customer/{feild}/{mobileNo}";


	
//######################################    AWS REKOGNIBITION CONTROLLER END_POINTS      ####################################
	
	public static final String IMAGE = "/image";
	public static final String IMAGE_SCANNING = "/imageScanning";
	
	
//#########################################  ReportsController Endpoints  ########################################
	public static final String REPORTS = "/reports";

	public static final String USERS_BY_ROLE = "/usersByRole";
	public static final String ACTIVE_VS_INACTIVE_USERS = "/activeVsInactiveUsers";
	public static final String STORES_VS_EMPLOYEES = "/storesVsEmployees";
	public static final String COLOR_CODES = "/savetcolorCodes";
	public static final String GETCOLOR_CODES = "/gettcolorCodes";
    public static final String DELETE_PRIVILLAGES = "/deletePrivileges";


	

}
