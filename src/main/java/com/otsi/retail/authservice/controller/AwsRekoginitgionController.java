package com.otsi.retail.authservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.rekognition.model.Label;
import com.otsi.retail.authservice.services.AmazonRekoginitionService;
import com.otsi.retail.authservice.services.CognitoAuthServiceImpl;
import com.otsi.retail.authservice.utils.EndpointConstants;
import com.otsi.retail.authservice.utils.GateWayResponse;

@RestController
@RequestMapping(EndpointConstants.IMAGE)

public class AwsRekoginitgionController {
	
	@Autowired
	private AmazonRekoginitionService amazonRekoginitionService;
	@PostMapping(EndpointConstants.IMAGE_SCANNING)
	public GateWayResponse<?> imageScannin(@RequestParam("image") MultipartFile image) {
		try {
			List<Label> res = amazonRekoginitionService.detectLables(image);
			return new GateWayResponse<>(200, res, "", "true");
		} catch (Exception e) {
			return new GateWayResponse<>(400, null, e.getMessage(), "false");
		}
	}
	
}
