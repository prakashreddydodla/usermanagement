package com.otsi.retail.authservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.Domain_Master;
import com.otsi.retail.authservice.requestModel.ClientDetailsVo;
import com.otsi.retail.authservice.requestModel.ClientDomianVo;
import com.otsi.retail.authservice.requestModel.MasterDomianVo;
import com.otsi.retail.authservice.services.ClientAndDomianService;
import com.otsi.retail.authservice.services.ClientAndDomianServiceImpl;
import com.otsi.retail.authservice.services.CognitoAuthServiceImpl;
import com.otsi.retail.authservice.utils.EndpointConstants;
import com.otsi.retail.authservice.utils.GateWayResponse;

@RestController
@RequestMapping(EndpointConstants.CLIENT)
public class ClientAndDomianController {

	@Autowired
	private ClientAndDomianService clientAndDomianService;
	
	
	@PostMapping(EndpointConstants.CREATE_MASTER_DOMIAN)
	public GateWayResponse<?> creatDomian(@RequestBody MasterDomianVo domainVo) {
		String res;
		try {
			res = clientAndDomianService.createMasterDomain(domainVo);
			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}

	@GetMapping(EndpointConstants.GET_MASTER_DOMAINS)
	public GateWayResponse<?> getMasterDomians() {
		try {
			List<Domain_Master> res = clientAndDomianService.getMasterDomains();
			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}

	@PostMapping(EndpointConstants.CREATE_CLIENT)
	public GateWayResponse<?> creatclient(@RequestBody ClientDetailsVo client) {
		try {
			String res = clientAndDomianService.createClient(client);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@PostMapping(EndpointConstants.ASSIGN_DOMAIN_TO_CLIENT)
	public GateWayResponse<?> assignDomianToClient(@RequestBody ClientDomianVo clientDomianVo) {
		try {
			String res = clientAndDomianService.assignDomianToClient(clientDomianVo);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping(EndpointConstants.GET_DOMAINS_FOR_CLIENT)
	public GateWayResponse<?> getDomiansForClient(@PathVariable String clientId) {
		try {
			List<ClientDomains> res = clientAndDomianService.getDomainsForClient(Long.parseLong(clientId));
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping(EndpointConstants.GET_CLIENT)
	public GateWayResponse<?> getClient(@PathVariable String clientId) {
		try {
			ClientDetails res = clientAndDomianService.getClient(Long.parseLong(clientId));
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping(EndpointConstants.GET_ALL_CLIENTS)
	public GateWayResponse<?> getAllClients() {
		try {
			List<ClientDetails> res = clientAndDomianService.getAllClient();
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	@GetMapping(EndpointConstants.GET_DOMIAN_BY_ID)
	public GateWayResponse<?> getDomianById(@PathVariable String clientDomianId){
		try {
		ClientDomains res = clientAndDomianService.getDomianById(Long.parseLong(clientDomianId));
		return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
		
	}
}
