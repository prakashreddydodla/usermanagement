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
import com.otsi.retail.authservice.services.CognitoAuthService;
import com.otsi.retail.authservice.services.RolesAndPrivillagesService;
import com.otsi.retail.authservice.utils.GateWayResponse;

@RestController
@RequestMapping("/roles")
public class RolesAndPrivillagesController {
	@Autowired
	private RolesAndPrivillagesService rolesAndPrivillagesService;
	
	@PostMapping(path = "/createRole")
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
	
	
	@GetMapping("/getRolesForDomian/{domianId}")
	public GateWayResponse<?> getRolesForDomian(@PathVariable String domianId) {
		try {
			List<Role> res = rolesAndPrivillagesService.getRolesForClientDomian(Long.parseLong(domianId));
			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	
	
	@GetMapping("/getRolesForClient/{clientId}")
	public GateWayResponse<?> getRolesForClient(@PathVariable String clientId) {
		try {
			List<Role> res = rolesAndPrivillagesService.getRolesForClient(Long.parseLong(clientId));
			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	
	@PostMapping("/addPrevilage")
	public GateWayResponse<?> savePrevilageToMaster(@RequestBody CreatePrivillagesRequest privilages) {
		try {
			String res = rolesAndPrivillagesService.savePrevilage(privilages);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}

	}
	
	@GetMapping("/getPrivilages/{roleId}")
	public GateWayResponse<?> getPrivilagesOfRole(@PathVariable String roleId) {
		try {
			Role res = rolesAndPrivillagesService.getPrivilages(Long.parseLong(roleId));
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	@GetMapping("/getPrivilages/{roleName}")
	public GateWayResponse<?> getPrivilagesOfRoleByRoleName(@PathVariable String roleName) {
		try {
			Role res = rolesAndPrivillagesService.getPrivilagesByRoleName(roleName);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	
	@GetMapping("/subPrivilages/{parentId}")
	public GateWayResponse<?> getsubPrivilagesForParent(@PathVariable String parentId) {
		try {
			List<SubPrivillage> res = rolesAndPrivillagesService.getSubPrivillages(Long.parseLong(parentId));
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	
	@GetMapping("/getAllPrivilages")
	public GateWayResponse<?> getAllPrivilages() {
		try {
			List<ParentPrivilageVo> res = rolesAndPrivillagesService.getAllPrivilages();
			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
}
