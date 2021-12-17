package com.otsi.retail.authservice.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.otsi.retail.authservice.requestModel.ReportVo;
import com.otsi.retail.authservice.services.ReportsService;
import com.otsi.retail.authservice.utils.EndpointConstants;
import com.otsi.retail.authservice.utils.GateWayResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(EndpointConstants.STORE)
public class ReportsController {

	private Logger logger = LoggerFactory.getLogger(ReportsController.class);
	@Autowired
	private ReportsService reportsService;

	@GetMapping(path = EndpointConstants.USERS_BY_ROLE)
	public GateWayResponse<?> UsersByRole(@RequestParam Long clientId) {
		try {
			logger.info("usersByRole method starts");
			List<ReportVo> result = reportsService.getUsersByRole(clientId);
			return new GateWayResponse<>(200, result, "", "true");

		} catch (Exception e) {

			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}

	}

	@GetMapping(path = EndpointConstants.ACTIVE_VS_INACTIVE_USERS)
	public GateWayResponse<?> ActiveUsers(@RequestParam Long clientId) {

		try {

			logger.info("usersByRole method starts");
			List<ReportVo> vo = reportsService.getActiveUsers(clientId);
			return new GateWayResponse<>(200, vo, "", "true");

		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");

		}

	}

	@GetMapping(path = EndpointConstants.STORES_VS_EMPLOYEES)
	public GateWayResponse<?> StoresVsEmployees(@RequestParam Long clientId) {

		try {

			logger.info("usersByRole method starts");
			List<ReportVo> count = reportsService.StoresVsEmployees(clientId);
			return new GateWayResponse<>(200, count, "", "true");

		} catch (Exception e) {

			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}

	}
	@PostMapping(path = EndpointConstants.COLOR_CODES)
	public GateWayResponse<?> saveColorCodes(@RequestBody List<String> colorCodes) {

		try {

			logger.info("usersByRole method starts");
			String msg = reportsService.SaveColorCodes(colorCodes );
			return new GateWayResponse<>(200, msg, "", "true");

		} catch (Exception e) {

			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}

	}

}
