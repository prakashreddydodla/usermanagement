package com.otsi.retail.authservice.controller;

import java.util.List;

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
	
	
	@PostMapping(EndpointConstants.CREATE_STORE)
	public GateWayResponse<?> createStore(@RequestBody StoreVo vo) {
		try {
			String res = storeService.createStore(vo);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	

	@PutMapping(EndpointConstants.UPDATE_STORE)
	public GateWayResponse<?> updateStore(@RequestBody StoreVo vo) {
		try {
			String res = storeService.updateStore(vo);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping(EndpointConstants.GET_CLIENT_DOMIAN_STORES)
	public GateWayResponse<?> getClientDomianStores(@RequestParam("clientDomianId") long clientDomianId) {
		try {
			List<Store> res = storeService.getStoresForClientDomian(clientDomianId);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	
	@GetMapping(EndpointConstants.GET_CLIENT_STORES)
	public GateWayResponse<?> getClientStores(@RequestParam("clientId") long clientId) {
		try {
			List<Store> res = storeService.getStoresForClient(clientId);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@PutMapping(EndpointConstants.ASSIGN_STORES_TO_DOMIAN)
	public GateWayResponse<?> assignStoresToDomain(@RequestBody DomianStoresVo vo) {
		try {
			String res = storeService.assignStoreToClientDomain(vo);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@PostMapping(EndpointConstants.GET_STORES_WITH_FILTER)
	public GateWayResponse<?> getStoresWithFilter(@RequestBody GetStoresRequestVo vo ){
		try {
		List<Store> res=storeService.getStoresOnFilter(vo);
		return new GateWayResponse<>(200, res, "", "true");
		}catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	
	@PostMapping(EndpointConstants.SAVE_STATES)
	public GateWayResponse<?> saveStates(@RequestBody SaveStatesAndDistrictsRequest vo ){
		try {
		String res=statesAndDistrctsService.saveStatesAndDistricts(vo);
		return new GateWayResponse<>(200, res, "", "true");
		}catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	
	@GetMapping(EndpointConstants.ALL_STATES)
	public GateWayResponse<?> getAllStates( ){
		try {
		List<States> res=statesAndDistrctsService.getAllStates();
		return new GateWayResponse<>(200, res, "", "true");
		}catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	
	@GetMapping(EndpointConstants.GET_DISTRICT)
	public GateWayResponse<?> getDistrictsOfState(@RequestParam("stateCode") String stateCode){
		try {
		List<Districts> res=statesAndDistrctsService.getAllDistrctsOfState(stateCode);
		return new GateWayResponse<>(200, res, "", "true");
		}catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
}
