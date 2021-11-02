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
import com.otsi.retail.authservice.services.CognitoAuthService;
import com.otsi.retail.authservice.utils.GateWayResponse;

@RestController
@RequestMapping("/client")
public class ClientAndDomianController {

	@Autowired
	private ClientAndDomianService clientAndDomianService;
	
	
	@PostMapping("/createMasterDomain")
	public GateWayResponse<?> creatChannel(@RequestBody MasterDomianVo domainVo) {
		String res;
		try {
			res = clientAndDomianService.createMasterDomain(domainVo);
			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}

	@GetMapping("/getMasterDomains")
	public GateWayResponse<?> getMasterDomians() {
		try {
			List<Domain_Master> res = clientAndDomianService.getMasterDomains();
			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}

	@PostMapping("/createClient")
	public GateWayResponse<?> creatclient(@RequestBody ClientDetailsVo client) {
		try {
			String res = clientAndDomianService.createClient(client);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@PostMapping("/assignDomianToClient")
	public GateWayResponse<?> assignDomianToClient(@RequestBody ClientDomianVo clientDomianVo) {
		try {
			String res = clientAndDomianService.assignDomianToClient(clientDomianVo);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping("/getDomiansForClient/{clientId}")
	public GateWayResponse<?> getDomiansForClient(@PathVariable String clientId) {
		try {
			List<ClientDomains> res = clientAndDomianService.getDomainsForClient(Long.parseLong(clientId));
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping("/getClient/{clientId}")
	public GateWayResponse<?> getClient(@PathVariable String clientId) {
		try {
			ClientDetails res = clientAndDomianService.getClient(Long.parseLong(clientId));
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping("/getAllClients")
	public GateWayResponse<?> getAllClients() {
		try {
			List<ClientDetails> res = clientAndDomianService.getAllClient();
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
}
