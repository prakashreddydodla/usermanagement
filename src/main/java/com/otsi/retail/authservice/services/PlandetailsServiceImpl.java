package com.otsi.retail.authservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otsi.retail.authservice.Entity.PlanDetails;
import com.otsi.retail.authservice.Repository.PlandetailsRepo;
import com.otsi.retail.authservice.requestModel.PlanDetailsVo;
@Service
public class PlandetailsServiceImpl implements PlandetailsService {
	
    @Autowired
	private PlandetailsRepo planDetailsRepo;
	
	@Override
	public String savePlanDetails(List<PlanDetailsVo> planDetailsVo) {
		planDetailsVo.stream().forEach(plans->{
			PlanDetails plandetails = new PlanDetails();
			plandetails.setPlanName(plans.getPlanName());
			plandetails.setPrice(plans.getPrice());
			plandetails.setDescription(plans.getDescription());
			plandetails.setMaxUsers(plans.getMaxUsers());
			plandetails.setCreatedBy(plans.getCreatedBy());
			plandetails.setModifiedBy(plans.getModifiedBy());
			/*
			 * plandetails.setPlanTenure(plans.getPlanTenure());
			 */			planDetailsRepo.save(plandetails);

		});
		
		
		return "planDetais Saved successfully";
	}

	@Override
	public List<PlanDetails> getPlanDetails() {
		
		List<PlanDetails> planDetails = planDetailsRepo.findAll();
		
		return planDetails;
	}

}
