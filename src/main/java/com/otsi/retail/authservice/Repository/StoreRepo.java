package com.otsi.retail.authservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.Store;

@Repository
public interface StoreRepo extends JpaRepository<Store, Long> {

	List<Store> findByName(String storeName);

	List<Store> findByClientDomianlIdId(Long clientId);

	List<Store> findByClientDomianlId_Client_Id(Long clientId);

	List<Store> findByClientId(Long clientId);

	List<Store> findByStateId(Long stateId);

	List<Store> findByCityId(String string);

	List<Store> findByDistrictId(Long cityId);

	List<Store> findByStateIdAndDistrictId(Long stateId, Long districtId);

	List<Store> findByIdIn(List<Long> storeIds);

	List<Store> findByStateCode(String stateId);

	List<Store> findByStateCodeAndDistrictId(String stateId, Long districtId);

	// List<Store> findByStateCodeAndDistrictIdAndCityId(String stateId, long
	// districtId, String cityId);

	List<Store> findByStateCodeAndDistrictIdAndName(String stateId, Long districtId, String storeName);

	List<Store> findByStateCodeAndName(String stateId, String storeName);

	List<Store> findByStateCodeAndDistrictIdAndNameAndClient_Id(String stateId, Long districtId,
			String storeName, Long clientId);

	List<Store> findByStateCodeAndDistrictIdAndClient_Id(String stateId, Long districtId,
			Long clientId);

	List<Store> findByStateCodeAndNameAndClient_Id(String stateId, String storeName, Long clientId);

	List<Store> findByStateCodeAndClient_Id(String stateId, Long clientId);

	Store findByNameAndClient_Id(String name, Long clientId);

	List<Store> findByClientIdAndIsActive(Long clientId, Boolean true1);

	

}
