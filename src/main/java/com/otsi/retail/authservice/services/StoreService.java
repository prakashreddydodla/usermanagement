package com.otsi.retail.authservice.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.otsi.retail.authservice.Entity.GstDetails;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.requestModel.DomianStoresVo;
import com.otsi.retail.authservice.requestModel.GetStoresRequestVo;
import com.otsi.retail.authservice.requestModel.StoreVO;

@Component
public interface StoreService {

	Store createStore(StoreVO storeVO);

	List<Store> getStoresForClientDomian(Long clientDomianId);

	List<StoreVO> getStoresByClient(Long clientId, Boolean isActive);

	String assignStoreToClientDomain(DomianStoresVo domainStoresVO) throws Exception;

	List<StoreVO> getStoresOnFilter(GetStoresRequestVo getStoresRequestVo, Long clientId);

	Store updateStore(StoreVO storeVO);

	List<Store> getStoresForGivenIds(List<Long> stateCodes);

	GstDetails getGstDetails(Long clientId, String stateCode);

	String deleteStore(Long id);

	Store getActiveStores(Long userId, Long clientId);

	List<StoreVO> getStores();

	List<StoreVO> getStoresByUser(long userId, Boolean isActive);

	Store getStore(Long id);

}
