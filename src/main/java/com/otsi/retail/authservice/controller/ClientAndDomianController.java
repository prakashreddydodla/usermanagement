package com.otsi.retail.authservice.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.Domain_Master;
import com.otsi.retail.authservice.requestModel.ClientDetailsVO;
import com.otsi.retail.authservice.requestModel.ClientDomianVo;
import com.otsi.retail.authservice.requestModel.MasterDomianVo;
import com.otsi.retail.authservice.requestModel.ReportVo;
import com.otsi.retail.authservice.services.ClientAndDomianService;
import com.otsi.retail.authservice.utils.EndpointConstants;
import com.otsi.retail.authservice.utils.ErrorCodes;
import com.otsi.retail.authservice.utils.GateWayResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(EndpointConstants.CLIENT)
public class ClientAndDomianController {

	@Autowired
	private ClientAndDomianService clientAndDomianService;
	private Logger logger = LogManager.getLogger(ClientAndDomianService.class);

//	
	@ApiOperation(value = "createMasterDomain", notes = "creating master domains")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = String.class, responseContainer = "string") })
	@PostMapping(EndpointConstants.CREATE_MASTER_DOMIAN)
	public GateWayResponse<?> creatDomian(@RequestBody MasterDomianVo domainVo) {
		String res;
		try {
			logger.info("In CREATE_MASTER_DOMIAN request : " + domainVo);
			res = clientAndDomianService.createMasterDomain(domainVo);

			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}

	@ApiOperation(value = "getMasterDomains", notes = "get  master domains")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = Domain_Master.class, responseContainer = "List") })
	@GetMapping(EndpointConstants.GET_MASTER_DOMAINS)
	public GateWayResponse<?> getMasterDomians() {

		logger.info("In GET_MASTER_DOMAINS request : ");

		List<Domain_Master> res = clientAndDomianService.getMasterDomains();

		return new GateWayResponse<>(200, res, "", "true");

	}

	/**
	 * 
	 * @param clientDetailsVO
	 * @return
	 */
	@ApiOperation(value = EndpointConstants.CREATE_CLIENT, notes = "create clients")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = String.class, responseContainer = "string") })
	@PostMapping(EndpointConstants.CREATE_CLIENT)
	public ResponseEntity<?> creatclient(@RequestBody ClientDetailsVO clientDetailsVO) {
		ClientDetails response = clientAndDomianService.createClient(clientDetailsVO);
		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = EndpointConstants.ASSIGN_DOMAIN_TO_CLIENT, notes = "assign domains to client")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = String.class, responseContainer = "string") })

	@PostMapping(EndpointConstants.ASSIGN_DOMAIN_TO_CLIENT)
	public GateWayResponse<?> assignDomianToClient(@RequestBody ClientDomianVo clientDomianVo) {
		try {
			logger.info("In ASSIGN_DOMAIN_TO_CLIENT request : " + clientDomianVo);

			String res = clientAndDomianService.assignDomianToClient(clientDomianVo);

			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@ApiOperation(value = "getDomiansForClient/{clientId}", notes = "get domains for client")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = ClientDomains.class, responseContainer = "List") })
	@GetMapping(EndpointConstants.GET_DOMAINS_FOR_CLIENT)
	public GateWayResponse<?> getDomiansForClient(@PathVariable String clientId) {

		logger.info("In GET_DOMAINS_FOR_CLIENT request : " + clientId);

		List<ClientDomains> res = clientAndDomianService.getDomainsForClient(Long.parseLong(clientId));

		return new GateWayResponse<>(200, res, "", "true");

	}

	@ApiOperation(value = "getClient/{clientId}", notes = "get client Details using clientId")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = ClientDetails.class, responseContainer = "Object") })
	@GetMapping(EndpointConstants.GET_CLIENT)
	public GateWayResponse<?> getClient(@PathVariable String clientId) throws NumberFormatException, Exception {

		logger.info("In GET_CLIENT request : " + clientId);

		ClientDetails res = clientAndDomianService.getClient(Long.parseLong(clientId));

		return new GateWayResponse<>(200, res, "", "true");

	}

	@ApiOperation(value = "getAllClients", notes = "get All client Details ")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = ClientDetails.class, responseContainer = "List") })
	@GetMapping(EndpointConstants.GET_ALL_CLIENTS)
	public GateWayResponse<?> getAllClients() throws Exception {

		logger.info("In GET_ALL_CLIENTS request : ");

		List<ClientDetails> res = clientAndDomianService.getAllClient();

		return new GateWayResponse<>(200, res, "", "true");

	}

	@ApiOperation(value = "domian/{clientDomianId}", notes = "get domains By clientDomainId")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = ClientDomains.class, responseContainer = "Object") })
	@GetMapping(EndpointConstants.GET_DOMIAN_BY_ID)
	public GateWayResponse<?> getDomianById(@PathVariable String clientDomianId) {

		logger.info("In GET_DOMIAN_BY_ID request clientDomianId : " + clientDomianId);

		ClientDomains res = clientAndDomianService.getDomianById(Long.parseLong(clientDomianId));

		return new GateWayResponse<>(200, res, "", "true");

	}
}
