package com.otsi.retail.authservice.services;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.otsi.retail.authservice.Entity.PlanDetails;
import com.otsi.retail.authservice.requestModel.PlanDetailsVo;
@Component
public interface PlandetailsService {

	String savePlanDetails(List<PlanDetailsVo> planDetailsVo);

	List<PlanDetails> getPlanDetails();

	PlanDetailsVo getPlanDetailsByTenure(String planName, String tenure);

}
