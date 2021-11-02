package com.otsi.retail.authservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.otsi.retail.authservice.Entity.Districts;
import com.otsi.retail.authservice.Entity.States;
import com.otsi.retail.authservice.Repository.DistrictRepo;
import com.otsi.retail.authservice.Repository.StateRepo;
import com.otsi.retail.authservice.requestModel.SaveStatesAndDistrictsRequest;
import com.otsi.retail.authservice.requestModel.StateVo;

@Service
public class StatesAndDistrctsService {

	@Autowired
	private DistrictRepo districtRepo;

	@Autowired
	private StateRepo stateRepo;

	public String saveStatesAndDistricts(SaveStatesAndDistrictsRequest vo) throws Exception {
		try {
			vo.getStates().stream().forEach(sataeVo -> {
				States states = new States();
				states.setStateId(Long.parseLong(sataeVo.getId()));
				states.setStateName(sataeVo.getName());
				states.setStateCode(sataeVo.getCode());
				states.setCapital(sataeVo.getCapital());
				States savedState = stateRepo.save(states);
				sataeVo.getDistricts().stream().forEach(districtVo -> {
					Districts district = new Districts();
					district.setDistrictName(districtVo.getName());
					district.setStateCode(sataeVo.getCode());
					district.setDistrictId(Long.parseLong(districtVo.getId()));
					Districts savedDistrict = districtRepo.save(district);
				});

			});

			return "saved successfully";

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<States> getAllStates() {
		return stateRepo.findAll();
	}

	public List<Districts> getAllDistrctsOfState(String stateCode) throws Exception {
		try {
			List<Districts> district = districtRepo.findByStateCode(stateCode);
if(!CollectionUtils.isEmpty(district)) {
	return district;
}else {
	throw new Exception("No districts found with this State");

}
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
