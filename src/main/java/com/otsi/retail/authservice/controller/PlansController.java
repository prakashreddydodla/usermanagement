package com.otsi.retail.authservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.otsi.retail.authservice.Entity.PlanDetails;
import com.otsi.retail.authservice.requestModel.PlanDetailsVo;
import com.otsi.retail.authservice.services.PlandetailsService;
import com.otsi.retail.authservice.utils.EndpointConstants;
import com.otsi.retail.authservice.utils.GateWayResponse;

@RestController
@RequestMapping(EndpointConstants.PLAN)
public class PlansController {
	@Autowired
	private PlandetailsService plandetailsService;
	
	@PostMapping(path = EndpointConstants.SAVE_PLANDETAILS)
	public GateWayResponse<?>  savePlanDetails(@RequestBody List<PlanDetailsVo> planDetailsVo){
		
		
		String msg = plandetailsService.savePlanDetails(planDetailsVo);
		
		return  new GateWayResponse<>(200, null, msg, "true");
		
		
		
		
	}
	
	@GetMapping(path = EndpointConstants.GET_PLANDETAILS)
    public GateWayResponse<?> getPlanDetails(){
		
		
		List<PlanDetails> planDeatils = plandetailsService.getPlanDetails();
		
		return  new GateWayResponse<>(200, planDeatils, "", "true");
		
		
		
		
	}
	@GetMapping(path = EndpointConstants.GET_PLANDETAILSBYTENURE)
    public GateWayResponse<?> getPlanDetailsByTenure(@RequestParam String PlanName,@RequestParam String Tenure){
		
		
		PlanDetailsVo planDeatils = plandetailsService.getPlanDetailsByTenure(PlanName,Tenure);
		
		return  new GateWayResponse<>(200, planDeatils, "", "true");
		
		
		
		
	}
		
		
		
	

}
