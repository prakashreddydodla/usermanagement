package com.otsi.retail.authservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping(path = EndpointConstants.CREATE_ROLE)
	public GateWayResponse<?> createRole(@RequestBody CreateRoleRequest request) {

		try {
			String res = rolesAndPrivillagesService.createRole(request);
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
			List<Role> res = rolesAndPrivillagesService.getRolesForClientDomian(Long.parseLong(domianId));
			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping(EndpointConstants.GET_ROLES_FOR_CLIENT)
	public GateWayResponse<?> getRolesForClient(@PathVariable String clientId) {
		try {
			List<Role> res = rolesAndPrivillagesService.getRolesForClient(Long.parseLong(clientId));
			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@PostMapping(EndpointConstants.ADD_PREVILAGE)
	public GateWayResponse<?> savePrevilageToMaster(@RequestBody CreatePrivillagesRequest privilages) {
		try {
			String res = rolesAndPrivillagesService.savePrevilage(privilages);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}

	}

	@GetMapping(EndpointConstants.GET_PRIVILAGES)
	public GateWayResponse<?> getPrivilagesOfRole(@PathVariable String roleId) {
		try {
			Role res = rolesAndPrivillagesService.getPrivilages(Long.parseLong(roleId));
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping(EndpointConstants.PRIVILAGES_BY_NAME)
	public GateWayResponse<?> getPrivilagesOfRoleByRoleName(@PathVariable String roleName) {
		try {
			Role res = rolesAndPrivillagesService.getPrivilagesByRoleName(roleName);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping(EndpointConstants.SUB_PRIVILAGES)
	public GateWayResponse<?> getsubPrivilagesForParent(@PathVariable String parentId) {
		try {
			List<SubPrivillage> res = rolesAndPrivillagesService.getSubPrivillages(Long.parseLong(parentId));
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping(EndpointConstants.GET_ALL_PRIVILAGES)
	public GateWayResponse<?> getAllPrivilages() {
		try {
			List<ParentPrivilageVo> res = rolesAndPrivillagesService.getAllPrivilages();
			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@PostMapping(EndpointConstants.ROLES_WITH_FILTER)
	public GateWayResponse<?> getRolesWithFilter(@RequestBody RolesFilterRequest req) {
		try {
			List<Role> res = rolesAndPrivillagesService.getRolesWithFilter(req);
			return new GateWayResponse<>(200, res, "", "true");

		} catch (RuntimeException re) {
			return new GateWayResponse<>(400, null, re.getMessage(), "false");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

}
