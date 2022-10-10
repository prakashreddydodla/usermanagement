package com.otsi.retail.authservice.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.otsi.retail.authservice.Entity.PlanDetails;
import com.otsi.retail.authservice.Entity.TenureDetails;
import com.otsi.retail.authservice.requestModel.PlanDetailsVo;

@Component
public class PlanDetailsMapper {
	
	public PlanDetails convertVoToEntity(PlanDetailsVo plans) {
		
			PlanDetails plandetails = new PlanDetails();
			List<TenureDetails> tenureList = new ArrayList();
			plandetails.setPlanName(plans.getPlanName());
			//plandetails.setPrice(plans.getPrice());
			plandetails.setDescription(plans.getDescription());
			plandetails.setMaxUsers(plans.getMaxUsers());
			plandetails.setCreatedBy(plans.getCreatedBy());
			plandetails.setModifiedBy(plans.getModifiedBy());
			plans.getTenureDetails().stream().forEach(tenures->{
				TenureDetails tenureDetails = new TenureDetails();
				tenureDetails.setAmount(tenures.getAmount());
				tenureDetails.setCreatedBy(tenures.getCreatedBy());
				tenureDetails.setCreatedDate(tenures.getCreatedDate());
				tenureDetails.setLastModifiedDate(tenures.getLastModifiedDate());
				tenureDetails.setModifiedBy(tenures.getModifiedBy());
				tenureDetails.setPlanTenure(tenures.getPlanTenure());
				tenureDetails.setPlanid(plandetails);
				
				tenureList.add(tenureDetails);
			});
			plandetails.setTenureDetials(tenureList);		
		
	
return plandetails;
}
}
