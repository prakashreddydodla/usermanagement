package com.otsi.retail.authservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.otsi.retail.authservice.Entity.Districts;
import com.otsi.retail.authservice.Entity.States;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.requestModel.DomianStoresVo;
import com.otsi.retail.authservice.requestModel.GetStoresRequestVo;
import com.otsi.retail.authservice.requestModel.SaveStatesAndDistrictsRequest;
import com.otsi.retail.authservice.requestModel.StateVo;
import com.otsi.retail.authservice.requestModel.StoreVo;
import com.otsi.retail.authservice.services.CognitoAuthServiceImpl;
import com.otsi.retail.authservice.services.StatesAndDistrctsService;
import com.otsi.retail.authservice.services.StoreService;
import com.otsi.retail.authservice.services.StoreServiceImpl;
import com.otsi.retail.authservice.utils.EndpointConstants;
import com.otsi.retail.authservice.utils.GateWayResponse;

@RestController
@RequestMapping(EndpointConstants.STORE)
public class StoresController {
	@Autowired
	private StoreService storeService;
	@Autowired
	private StatesAndDistrctsService statesAndDistrctsService;
	private Logger logger = LoggerFactory.getLogger(StoresController.class);

	
	@PostMapping(EndpointConstants.CREATE_STORE)
	public GateWayResponse<?> createStore(@RequestBody StoreVo vo) {
		try {
			logger.info("In CREATE_STORE request : " + vo);

			String res = storeService.createStore(vo);
			logger.info("In CREATE_STORE responce  : "+res );

			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	

	@PutMapping(EndpointConstants.UPDATE_STORE)
	public GateWayResponse<?> updateStore(@RequestBody StoreVo vo) {
		try {
			logger.info("In UPDATE_STORE request : " + vo);

			String res = storeService.updateStore(vo);
			logger.info("In UPDATE_STORE responce  : "+res );

			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping(EndpointConstants.GET_CLIENT_DOMIAN_STORES)
	public GateWayResponse<?> getClientDomianStores(@RequestParam("clientDomianId") long clientDomianId) {
		try {
			logger.info("In GET_CLIENT_DOMIAN_STORES request clientDomianId : " + clientDomianId);

			List<Store> res = storeService.getStoresForClientDomian(clientDomianId);
			logger.info("In GET_CLIENT_DOMIAN_STORES responce  : "+res );

			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	
	@GetMapping(EndpointConstants.GET_CLIENT_STORES)
	public GateWayResponse<?> getClientStores(@RequestParam("clientId") long clientId) {
		try {
			logger.info("In GET_CLIENT_STORES request clientId : " + clientId);

			List<Store> res = storeService.getStoresForClient(clientId);
			logger.info("In GET_CLIENT_STORES responce  : "+res );

			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@PutMapping(EndpointConstants.ASSIGN_STORES_TO_DOMIAN)
	public GateWayResponse<?> assignStoresToDomain(@RequestBody DomianStoresVo vo) {
		try {
			logger.info("In ASSIGN_STORES_TO_DOMIAN request  : " + vo);

			String res = storeService.assignStoreToClientDomain(vo);
			logger.info("In ASSIGN_STORES_TO_DOMIAN responce  : "+res );

			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@PostMapping(EndpointConstants.GET_STORES_WITH_FILTER)
	public GateWayResponse<?> getStoresWithFilter(@RequestBody GetStoresRequestVo vo ){
		try {
			logger.info("In GET_STORES_WITH_FILTER request  : " + vo);

		List<Store> res=storeService.getStoresOnFilter(vo);
		logger.info("In GET_STORES_WITH_FILTER responce  : "+res );

		return new GateWayResponse<>(200, res, "", "true");
		}catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	
	@PostMapping(EndpointConstants.SAVE_STATES)
	public GateWayResponse<?> saveStates(@RequestBody SaveStatesAndDistrictsRequest vo ){
		try {
			logger.info("In SAVE_STATES request  : " + vo);

		String res=statesAndDistrctsService.saveStatesAndDistricts(vo);
		logger.info("In SAVE_STATES responce  : "+res );

		return new GateWayResponse<>(200, res, "", "true");
		}catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	
	@GetMapping(EndpointConstants.ALL_STATES)
	public GateWayResponse<?> getAllStates( ){
		try {
			logger.info("In ALL_STATES request  : " );

		List<States> res=statesAndDistrctsService.getAllStates();
		logger.info("In ALL_STATES responce  : "+res );

		return new GateWayResponse<>(200, res, "", "true");
		}catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	
	@GetMapping(EndpointConstants.GET_DISTRICT)
	public GateWayResponse<?> getDistrictsOfState(@RequestParam("stateCode") String stateCode){
		try {
			logger.info("In GET_DISTRICT request stateCode : "+stateCode );

		List<Districts> res=statesAndDistrctsService.getAllDistrctsOfState(stateCode);
		logger.info("In GET_DISTRICT responce  : "+res );

		return new GateWayResponse<>(200, res, "", "true");
		}catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	
	@PostMapping(EndpointConstants.GET_STORELIST)
	public GateWayResponse<?> getStoresForGivenIds(@RequestBody List<Long> storeIds){
		try {
			logger.info("In GET_STORELIST request storeIds : "+storeIds );

		List<Store> res=storeService.getStoresForGivenIds(storeIds);
		logger.info("In GET_STORELIST responce  : "+res );

		return new GateWayResponse<>(200, res, "", "true");
		}catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
}
