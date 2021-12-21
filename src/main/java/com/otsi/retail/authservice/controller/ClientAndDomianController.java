package com.otsi.retail.authservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private Logger logger = LoggerFactory.getLogger(ClientAndDomianController.class);

//	
	@PostMapping(EndpointConstants.CREATE_MASTER_DOMIAN)
	public GateWayResponse<?> creatDomian(@RequestBody MasterDomianVo domainVo) {
		String res;
		try {
			logger.info("In CREATE_MASTER_DOMIAN request : "+domainVo);
			res = clientAndDomianService.createMasterDomain(domainVo);

			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}

	@GetMapping(EndpointConstants.GET_MASTER_DOMAINS)
	public GateWayResponse<?> getMasterDomians() {
		try {
			logger.info("In GET_MASTER_DOMAINS request : ");

			List<Domain_Master> res = clientAndDomianService.getMasterDomains();

			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}

	@PostMapping(EndpointConstants.CREATE_CLIENT)
	public GateWayResponse<?> creatclient(@RequestBody ClientDetailsVo client) {
		try {
			logger.info("In CREATE_CLIENT request : "+client);

			String res = clientAndDomianService.createClient(client);

			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@PostMapping(EndpointConstants.ASSIGN_DOMAIN_TO_CLIENT)
	public GateWayResponse<?> assignDomianToClient(@RequestBody ClientDomianVo clientDomianVo) {
		try {
			logger.info("In ASSIGN_DOMAIN_TO_CLIENT request : "+clientDomianVo);

			String res = clientAndDomianService.assignDomianToClient(clientDomianVo);

			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping(EndpointConstants.GET_DOMAINS_FOR_CLIENT)
	public GateWayResponse<?> getDomiansForClient(@PathVariable String clientId) {
		try {
			logger.info("In GET_DOMAINS_FOR_CLIENT request : "+clientId);

			List<ClientDomains> res = clientAndDomianService.getDomainsForClient(Long.parseLong(clientId));

			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping(EndpointConstants.GET_CLIENT)
	public GateWayResponse<?> getClient(@PathVariable String clientId) {
		try {
			logger.info("In GET_CLIENT request : "+clientId);

			ClientDetails res = clientAndDomianService.getClient(Long.parseLong(clientId));

			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping(EndpointConstants.GET_ALL_CLIENTS)
	public GateWayResponse<?> getAllClients() {
		try {
			logger.info("In GET_ALL_CLIENTS request : ");

			List<ClientDetails> res = clientAndDomianService.getAllClient();

			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	@GetMapping(EndpointConstants.GET_DOMIAN_BY_ID)
	public GateWayResponse<?> getDomianById(@PathVariable String clientDomianId){
		try {
			logger.info("In GET_DOMIAN_BY_ID request clientDomianId : "+clientDomianId);

		ClientDomains res = clientAndDomianService.getDomianById(Long.parseLong(clientDomianId));

		return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
		
	}
}
