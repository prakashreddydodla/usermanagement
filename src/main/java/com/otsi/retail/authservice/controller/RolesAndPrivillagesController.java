package com.otsi.retail.authservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.otsi.retail.authservice.Entity.ParentPrivilages;
import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.SubPrivillage;
import com.otsi.retail.authservice.requestModel.CreatePrivillagesRequest;
import com.otsi.retail.authservice.requestModel.CreateRoleRequest;
import com.otsi.retail.authservice.requestModel.ParentPrivilageVo;
import com.otsi.retail.authservice.requestModel.RolesFilterRequest;
import com.otsi.retail.authservice.services.CognitoAuthServiceImpl;
import com.otsi.retail.authservice.services.RolesAndPrivillagesServiceImpl;
import com.otsi.retail.authservice.utils.EndpointConstants;
import com.otsi.retail.authservice.utils.GateWayResponse;

@RestController
@RequestMapping(EndpointConstants.ROLES)
public class RolesAndPrivillagesController {
	@Autowired
	private RolesAndPrivillagesServiceImpl rolesAndPrivillagesService;

	private Logger logger = LoggerFactory.getLogger(RolesAndPrivillagesController.class);
//
	@PostMapping(path = EndpointConstants.CREATE_ROLE)
	public GateWayResponse<?> createRole(@RequestBody CreateRoleRequest request) {

		try {
			logger.info("In CREATE_ROLE request : " + request);
			String res = rolesAndPrivillagesService.createRole(request);
			return new GateWayResponse<>(200, res, "", "true");

		} catch (RuntimeException re) {

			return new GateWayResponse<>(400, null, re.getMessage(), "false");
		}

		catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}

	@PutMapping(path = EndpointConstants.UPDATE_ROLE)
	public GateWayResponse<?> updateRole(@RequestBody CreateRoleRequest request) {

		try {
			logger.info("In UPDATE_ROLE request : " + request);
			String res = rolesAndPrivillagesService.updateRole(request);

			return new GateWayResponse<>(200, res, "", "true");

		} catch (RuntimeException re) {

			return new GateWayResponse<>(400, null, re.getMessage(), "false");
		}

		catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}

	@GetMapping(EndpointConstants.GET_ROLES_FOR_DOMIAN)
	public GateWayResponse<?> getRolesForDomian(@PathVariable String domianId) {
		try {
			logger.info("In GET_ROLES_FOR_DOMIAN request domianId : " + domianId);
			List<Role> res = rolesAndPrivillagesService.getRolesForClientDomian(Long.parseLong(domianId));
			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping(EndpointConstants.GET_ROLES_FOR_CLIENT)
	public GateWayResponse<?> getRolesForClient(@PathVariable String clientId) {
		try {
			logger.info("In GET_ROLES_FOR_CLIENT request clientId : " + clientId);
			List<Role> res = rolesAndPrivillagesService.getRolesForClient(Long.parseLong(clientId));
			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@PostMapping(EndpointConstants.ADD_PREVILAGE)
	public GateWayResponse<?> savePrevilageToMaster(@RequestBody List<CreatePrivillagesRequest> privilages) {
		try {
			logger.info("In ADD_PREVILAGE request  : " + privilages);
			String res = rolesAndPrivillagesService.savePrevilage(privilages);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}

	}

	@GetMapping(EndpointConstants.GET_PRIVILAGES)
	public GateWayResponse<?> getPrivilagesOfRole(@PathVariable String roleId) {
		try {
			logger.info("In GET_PRIVILAGES request roleId : " + roleId);
			Role res = rolesAndPrivillagesService.getPrivilages(Long.parseLong(roleId));
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping(EndpointConstants.PRIVILAGES_BY_NAME)
	public GateWayResponse<?> getPrivilagesOfRoleByRoleName(@PathVariable String roleName) {
		try {
			logger.info("In PRIVILAGES_BY_NAME request roleName : " + roleName);
			Role res = rolesAndPrivillagesService.getPrivilagesByRoleName(roleName);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping(EndpointConstants.SUB_PRIVILAGES)
	public GateWayResponse<?> getsubPrivilagesForParent(@PathVariable String parentId) {
		try {
			logger.info("In SUB_PRIVILAGES request parentId : " + parentId);
			List<SubPrivillage> res = rolesAndPrivillagesService.getSubPrivillages(Long.parseLong(parentId));
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping(EndpointConstants.GET_ALL_PRIVILAGES)
	public GateWayResponse<?> getAllPrivilages() {
		try {
			logger.info("In GET_ALL_PRIVILAGES request  ");
			List<ParentPrivilageVo> res = rolesAndPrivillagesService.getAllPrivilages();
			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@PostMapping(EndpointConstants.ROLES_WITH_FILTER)
	public GateWayResponse<?> getRolesWithFilter(@RequestBody RolesFilterRequest req) {
		try {
			logger.info("In ROLES_WITH_FILTER request  : " + req);
			List<Role> res = rolesAndPrivillagesService.getRolesWithFilter(req);
			return new GateWayResponse<>(200, res, "", "true");

		} catch (RuntimeException re) {
			return new GateWayResponse<>(400, null, re.getMessage(), "false");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping(EndpointConstants.GET_PRIVILLAGES_BY_DOMIAN)
	public GateWayResponse<?> getPrivillagesForDomian(@PathVariable String domian) {

		try {
			logger.info("In GET_PRIVILLAGES_BY_DOMIAN request  domian : " + domian);
			List<ParentPrivilageVo> res = rolesAndPrivillagesService
					.getAllPrivilagesForDomian(Integer.parseInt(domian));
			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

}
