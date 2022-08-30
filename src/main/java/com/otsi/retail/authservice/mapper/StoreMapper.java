package com.otsi.retail.authservice.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.Entity.UserDetails;
import com.otsi.retail.authservice.requestModel.StoreVO;
import com.otsi.retail.authservice.requestModel.UserDetailsVO;

@Component
public class StoreMapper {
	
	public List<StoreVO> convertStoresToVO(List<Store> stores) {
		List<StoreVO> storeList = new ArrayList<>();
		stores.stream().forEach(store -> {
			
			StoreVO storesVo=	converstoreToVO(store);
			storeList.add(storesVo);
		});
		return storeList;
		
		
	}

	private StoreVO converstoreToVO(Store store) {
		
		StoreVO storeVo = new StoreVO();
		storeVo.setAddress(store.getAddress());
		storeVo.setArea(store.getArea());
		storeVo.setCityId(store.getCityId());
		storeVo.setCreatedBy(store.getCreatedBy());
		storeVo.setDistrictId(store.getDistrictId());
		storeVo.setName(store.getName());
		storeVo.setIsActive(store.getIsActive());
		storeVo.setPhoneNumber(store.getPhoneNumber());
		// storeVo.setDomainId(store.getClientDomianlId().getId());
		storeVo.setStateCode(store.getStateCode());
		storeVo.setCreatedDate(store.getCreatedDate());
		storeVo.setStateId(store.getStateId());
		storeVo.setId(store.getId());
		// storeVo.setDomainName(store.getClientDomianlId().getDomaiName());
		return storeVo;
	}

}
