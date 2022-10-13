package com.otsi.retail.authservice.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.otsi.retail.authservice.Entity.ChildPrivilege;
import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.SubPrivilege;
import com.otsi.retail.authservice.requestModel.CreatePrivilegesRequest;
import com.otsi.retail.authservice.requestModel.CreateRoleRequest;
import com.otsi.retail.authservice.requestModel.ParentPrivilegeVO;
import com.otsi.retail.authservice.requestModel.PlanPrivilegeVo;
import com.otsi.retail.authservice.requestModel.PrivilegeVO;
import com.otsi.retail.authservice.requestModel.RoleVO;
import com.otsi.retail.authservice.requestModel.RolesFilterRequest;
import com.otsi.retail.authservice.requestModel.UpdatePlansRequest;
import com.otsi.retail.authservice.services.RolesAndPrivillagesServiceImpl;
import com.otsi.retail.authservice.utils.EndpointConstants;
import com.otsi.retail.authservice.utils.GateWayResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(EndpointConstants.ROLES)
public class RolesAndPrivillagesController {

	@Autowired
	private RolesAndPrivillagesServiceImpl rolesAndPrivillagesService;

	// private Logger logger =
	// LogManager.getLogger(RolesAndPrivillagesController.class);

	@ApiOperation(value = EndpointConstants.CREATE_ROLE, notes = "CreatingRoles")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = String.class, responseContainer = "String") })
	@PostMapping(path = EndpointConstants.CREATE_ROLE)
	public ResponseEntity<?> createRole(@RequestBody CreateRoleRequest request) throws Exception {
		Role res = rolesAndPrivillagesService.createRole(request);
		return ResponseEntity.ok(res);
	}

	@ApiOperation(value = EndpointConstants.UPDATE_ROLE, notes = "UpdatingRoles")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = String.class, responseContainer = "String") })
	@PutMapping(path = EndpointConstants.UPDATE_ROLE)
	public GateWayResponse<?> updateRole(@RequestBody CreateRoleRequest request) {

		try {
			// logger.info("In UPDATE_ROLE request : " + request);
			String res = rolesAndPrivillagesService.updateRole(request);

			return new GateWayResponse<>(200, res, "", "true");

		} catch (RuntimeException re) {

			return new GateWayResponse<>(400, null, re.getMessage(), "false");
		}

		catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}
	}

	@ApiOperation(value = EndpointConstants.GET_ROLES_FOR_DOMIAN, notes = "getting Roles using domainId")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = RoleVO.class, responseContainer = "List") })
	@GetMapping(EndpointConstants.GET_ROLES_FOR_DOMIAN)
	public GateWayResponse<?> getRolesForDomian(@PathVariable String domianId) {
		try {
			// logger.info("In GET_ROLES_FOR_DOMIAN request domianId : " + domianId);
			List<RoleVO> res = rolesAndPrivillagesService.getRolesForClientDomian(Long.parseLong(domianId));
			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@ApiOperation(value = EndpointConstants.GET_ROLES_FOR_CLIENT, notes = "getting Roles using clientId")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = RoleVO.class, responseContainer = "List") })
	@GetMapping(EndpointConstants.GET_ROLES_FOR_CLIENT)
	public ResponseEntity<?> getRolesForClient(@PathVariable String clientId) {
		// logger.info("In GET_ROLES_FOR_CLIENT request clientId : " + clientId);
		List<RoleVO> roles = rolesAndPrivillagesService.getRolesByClient(Long.parseLong(clientId));
		return ResponseEntity.ok(roles);
	}

	@ApiOperation(value = EndpointConstants.ADD_PREVILAGE, notes = "adding privileges to master")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = String.class, responseContainer = "String") })
	@PostMapping(EndpointConstants.ADD_PREVILAGE)
	public GateWayResponse<?> savePrevilageToMaster(@RequestBody List<CreatePrivilegesRequest> privilages) {
		try {
			// logger.info("In ADD_PREVILAGE request : " + privilages);
			String res = rolesAndPrivillagesService.savePrivilege(privilages);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}

	}

	@ApiOperation(value = EndpointConstants.GET_PRIVILAGES, notes = "get privileges by roleId")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = Role.class, responseContainer = "object") })
	@GetMapping(EndpointConstants.GET_PRIVILAGES)
	public GateWayResponse<?> getPrivilagesOfRole(@PathVariable String roleId) {
		try {
			// logger.info("In GET_PRIVILAGES request roleId : " + roleId);
			Role res = rolesAndPrivillagesService.getPrivilages(Long.parseLong(roleId));
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@ApiOperation(value = EndpointConstants.PRIVILAGES_BY_NAME, notes = "getPrivileges By Rolename")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = Role.class, responseContainer = "object") })
	@GetMapping(EndpointConstants.PRIVILAGES_BY_NAME)
	public ResponseEntity<?> getPrivilagesOfRoleByRoleName(@PathVariable String roleName) {
		// logger.info("In PRIVILAGES_BY_NAME request roleName : " + roleName);
		RoleVO roleOptional = rolesAndPrivillagesService.getPrivilagesByRoleName(roleName);
		return ResponseEntity.ok(roleOptional);
	}

	@ApiOperation(value = EndpointConstants.SUB_PRIVILAGES, notes = "getSubPrivileges By parentId")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = SubPrivilege.class, responseContainer = "List") })
	@GetMapping(EndpointConstants.SUB_PRIVILAGES)
	public GateWayResponse<?> getsubPrivilagesForParent(@PathVariable String parentId) {
		try {
			// logger.info("In SUB_PRIVILAGES request parentId : " + parentId);
			List<SubPrivilege> res = rolesAndPrivillagesService.getSubPrivillages(Long.parseLong(parentId));
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@ApiOperation(value = EndpointConstants.GET_ALL_PRIVILAGES, notes = "get All Privileges ")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = ParentPrivilegeVO.class, responseContainer = "List") })
	@GetMapping(EndpointConstants.GET_ALL_PRIVILAGES)
	public GateWayResponse<?> getAllPrivilages() {
		try {
			// logger.info("In GET_ALL_PRIVILAGES request ");
			PrivilegeVO res = rolesAndPrivillagesService.getAllPrivilages();
			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@ApiOperation(value = EndpointConstants.ROLES_WITH_FILTER, notes = "get Roles based on conditions")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = RoleVO.class, responseContainer = "List") })
	@PostMapping(EndpointConstants.ROLES_WITH_FILTER)
	public GateWayResponse<?> getRolesWithFilter(@RequestBody RolesFilterRequest req,
			@RequestHeader("clientId") Long clientId) {
		try {
			// logger.info("In ROLES_WITH_FILTER request : " + req);
			List<RoleVO> res = rolesAndPrivillagesService.getRolesWithFilter(req, clientId);
			return new GateWayResponse<>(200, res, "", "true");

		} catch (RuntimeException re) {
			return new GateWayResponse<>(400, null, re.getMessage(), "false");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@ApiOperation(value = EndpointConstants.GET_PRIVILLAGES, notes = "get Privileges For Domain")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = ParentPrivilegeVO.class, responseContainer = "List") })
	@GetMapping(EndpointConstants.GET_PRIVILLAGES)
	public ResponseEntity<?> getPrivillagesForDomian(@RequestHeader(required = false) Boolean isEsSlipEnabled,
			@RequestHeader(required = false) Long clientId) {
		PrivilegeVO privileges = rolesAndPrivillagesService.getAllPrivilagesForDomian(isEsSlipEnabled, clientId);
		return ResponseEntity.ok(privileges);
	}

	@ApiOperation(value = "childPrivileges/{subPrivilegeId}", notes = "getChildPrivileges By subPrivillageId")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = ChildPrivilege.class, responseContainer = "List") })
	@GetMapping(EndpointConstants.CHILD_PRIVILAGES)
	public GateWayResponse<?> getChildPrivilegesForSubPrivilege(@PathVariable String subPrivilegeId) {
		try {
			// logger.info("In CHILD_PRIVILAGES request subPrivilegeId : " +
			// subPrivilegeId);
			List<ChildPrivilege> res = rolesAndPrivillagesService.getChildPrivileges(Long.parseLong(subPrivilegeId));
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@ApiOperation(value = "deletePrivileges", notes = "delete Privileges")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = ParentPrivilegeVO.class, responseContainer = "String") })
	@DeleteMapping(EndpointConstants.DELETE_PRIVILLAGES)
	public GateWayResponse<?> deletePrivillages(@RequestParam Long id) {

		try {

			String res = rolesAndPrivillagesService.deletePrevileges(id);
			return new GateWayResponse<>(200, res, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}

	@GetMapping("/view-plans")
	public ResponseEntity<?> viewPlans() {
		List<PlanPrivilegeVo> planPrev = rolesAndPrivillagesService.getPrivilegeByPlan();
		return ResponseEntity.ok(planPrev);
	}



	@PutMapping("/editPlanPrivileges")
	public ResponseEntity<?> updatePlanPrivileges(@RequestBody UpdatePlansRequest updatePlansRequest) {
		String updatePlanPrivileges = rolesAndPrivillagesService.updatePlanPrivileges(updatePlansRequest);
		return ResponseEntity.ok(updatePlanPrivileges);
	}

}
