package com.otsi.retail.authservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otsi.retail.authservice.Entity.PlanDetails;
import com.otsi.retail.authservice.Entity.TenureDetails;
import com.otsi.retail.authservice.Repository.PlandetailsRepo;
import com.otsi.retail.authservice.mapper.PlanDetailsMapper;
import com.otsi.retail.authservice.requestModel.PlanDetailsVo;
@Service
public class PlandetailsServiceImpl implements PlandetailsService {
	
    @Autowired
	private PlandetailsRepo planDetailsRepo;
    
    @Autowired
    private PlanDetailsMapper planMapper;
	
	@Override
	public String savePlanDetails(List<PlanDetailsVo> planDetailsVo) {
		planDetailsVo.stream().forEach(plans->{
			/*PlanDetails plandetails = new PlanDetails();
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
			plandetails.setTenureDetials(tenureList);*/
			
			/*
			 * plandetails.setPlanTenure(plans.getPlanTenure());
			 */	
			PlanDetails plandetails = planMapper.convertVoToEntity(plans);
			planDetailsRepo.save(plandetails);

		});
		
		
		return "planDetais Saved successfully";
	}

	@Override
	public List<PlanDetails> getPlanDetails() {
		
		List<PlanDetails> planDetails = planDetailsRepo.findAll();
		
		return planDetails;
	}

	@Override
	public PlanDetailsVo getPlanDetailsByTenure(String planName, String tenure) {
		
		PlanDetails planDetails = planDetailsRepo.findByPlanNameAndTenureDetialsPlanTenure(planName,tenure);
            PlanDetailsVo planVO = new PlanDetailsVo();
            planVO.setPlanId(planDetails.getId());
            planVO.setPlanName(planDetails.getPlanName());
            planVO.setTenureDetails(planDetails.getTenureDetials());
            

		
		return planVO;
	}

}
