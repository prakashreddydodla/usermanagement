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
		
		StoreVO vo = new StoreVO();
		vo.setName(store.getName());
		vo.setId(store.getId());
		return vo;
	}

}
