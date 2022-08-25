package com.otsi.retail.authservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.PlanDetails;
import com.otsi.retail.authservice.requestModel.PlanDetailsVo;

@Repository
public interface PlandetailsRepo extends JpaRepository<PlanDetails, Long> {

	void save(PlanDetailsVo planDetailsVo);

	PlanDetails findByPlanNameAndTenureDetialsPlanTenure(String planName, String tenure);
	
	

}
