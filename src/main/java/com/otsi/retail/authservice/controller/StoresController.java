package com.otsi.retail.authservice.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.otsi.retail.authservice.Entity.Districts;
import com.otsi.retail.authservice.Entity.GstDetails;
import com.otsi.retail.authservice.Entity.States;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.requestModel.DomianStoresVo;
import com.otsi.retail.authservice.requestModel.GetStoresRequestVo;
import com.otsi.retail.authservice.requestModel.SaveStatesAndDistrictsRequest;
import com.otsi.retail.authservice.requestModel.StoreVo;
import com.otsi.retail.authservice.services.StatesAndDistrctsService;
import com.otsi.retail.authservice.services.StoreService;
import com.otsi.retail.authservice.utils.EndpointConstants;
import com.otsi.retail.authservice.utils.GateWayResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(EndpointConstants.STORE)
public class StoresController {
	@Autowired
	private StoreService storeService;
	@Autowired
	private StatesAndDistrctsService statesAndDistrctsService;
	private Logger logger = LogManager.getLogger(StoresController.class);

	@ApiOperation(value = "createStore", notes = "create store")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = String.class, responseContainer = "String") })
	@PostMapping(EndpointConstants.CREATE_STORE)
	public GateWayResponse<?> createStore(@RequestBody StoreVo vo) {
		try {
			logger.info("In CREATE_STORE request : " + vo);

			String res = storeService.createStore(vo);

			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	
//
	@ApiOperation(value = "updateStore", notes = "update store record")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = String.class, responseContainer = "List") })
	@PutMapping(EndpointConstants.UPDATE_STORE)
	public GateWayResponse<?> updateStore(@RequestBody StoreVo vo) {
		try {
			logger.info("In UPDATE_STORE request : " + vo);

			String res = storeService.updateStore(vo);

			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	@ApiOperation(value = "getClientDomainStores", notes = "get store details using clientDomainId")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = Store.class, responseContainer = "List") })
	@GetMapping(EndpointConstants.GET_CLIENT_DOMIAN_STORES)
	public GateWayResponse<?> getClientDomianStores(@RequestParam("clientDomianId") long clientDomianId) {
		try {
			logger.info("In GET_CLIENT_DOMIAN_STORES request clientDomianId : " + clientDomianId);

			List<Store> res = storeService.getStoresForClientDomian(clientDomianId);

			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	
	@ApiOperation(value = "getClientStores", notes = "get store details using clientId")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = Store.class, responseContainer = "List") })
	@GetMapping(EndpointConstants.GET_CLIENT_STORES)
	public GateWayResponse<?> getClientStores(@RequestParam("clientId") long clientId) {
		try {
			logger.info("In GET_CLIENT_STORES request clientId : " + clientId);

			List<StoreVo> res = storeService.getStoresForClient(clientId);

			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	@ApiOperation(value = "assignStoresToDomain", notes = "assign stores to clientDomain")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = String.class, responseContainer = "string") })
	@PutMapping(EndpointConstants.ASSIGN_STORES_TO_DOMIAN)
	public GateWayResponse<?> assignStoresToDomain(@RequestBody DomianStoresVo vo) {
		try {
			logger.info("In ASSIGN_STORES_TO_DOMIAN request  : " + vo);

			String res = storeService.assignStoreToClientDomain(vo);

			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	@ApiOperation(value = "getStoresWithFilter", notes = "get store details using search criteria")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = Store.class, responseContainer = "List") })
	@PostMapping(EndpointConstants.GET_STORES_WITH_FILTER)
	public GateWayResponse<?> getStoresWithFilter(@RequestBody GetStoresRequestVo vo,@RequestHeader("clientId") Long clientId ){
		try {
			logger.info("In GET_STORES_WITH_FILTER request  : " + vo,clientId);

		List<Store> res=storeService.getStoresOnFilter(vo,clientId);

		return new GateWayResponse<>(200, res, "", "true");
		}catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	@ApiOperation(value = "saveStates", notes = "saving states and districts Details")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = String.class, responseContainer = "string") })
	@PostMapping(EndpointConstants.SAVE_STATES)
	public GateWayResponse<?> saveStates(@RequestBody SaveStatesAndDistrictsRequest vo ){
		try {
			logger.info("In SAVE_STATES request  : " + vo);

		String res=statesAndDistrctsService.saveStatesAndDistricts(vo);

		return new GateWayResponse<>(200, res, "", "true");
		}catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	@ApiOperation(value = "allStates", notes = "get all states  Details")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = States.class, responseContainer = "List") })
	@GetMapping(EndpointConstants.ALL_STATES)
	public GateWayResponse<?> getAllStates( ){
		try {
			logger.info("In ALL_STATES request  : " );

		List<States> res=statesAndDistrctsService.getAllStates();

		return new GateWayResponse<>(200, res, "", "true");
		}catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	@ApiOperation(value = "getDistrict", notes = "get Districts for particular state")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = Districts.class, responseContainer = "List") })
	@GetMapping(EndpointConstants.GET_DISTRICT)
	public GateWayResponse<?> getDistrictsOfState(@RequestParam("stateCode") String stateCode){
		try {
			logger.info("In GET_DISTRICT request stateCode : "+stateCode );

		List<Districts> res=statesAndDistrctsService.getAllDistrctsOfState(stateCode);

		return new GateWayResponse<>(200, res, "", "true");
		}catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	@ApiOperation(value = "storeList", notes = "getStoresForGivenIds")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = Store.class, responseContainer = "List") })
	@PostMapping(EndpointConstants.GET_STORELIST)
	public GateWayResponse<?> getStoresForGivenIds(@RequestBody List<Long> storeIds){
		try {
			logger.info("In GET_STORELIST request storeIds : "+storeIds );

		List<Store> res=storeService.getStoresForGivenIds(storeIds);

		return new GateWayResponse<>(200, res, "", "true");
		}catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	@ApiOperation(value = "getgstDetails", notes = "getgstDetails")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", 
			response = GstDetails.class, responseContainer = "object") })
	@GetMapping(EndpointConstants.GET_GSTDETAILS)
	public GateWayResponse<?> getGstDetails(@RequestParam("clientId") long clientId,@RequestParam("stateCode") String stateCode) {
		try {
			logger.info("In GET_GSTDETAILS request clientId : " + clientId);

			GstDetails res = storeService.getGstDetails(clientId,stateCode);

			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	
}
