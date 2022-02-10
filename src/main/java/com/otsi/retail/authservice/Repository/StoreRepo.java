package com.otsi.retail.authservice.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.Store;

@Repository
public interface StoreRepo extends JpaRepository<Store, Long> {

	List<Store> findByName(String storeName);


	List<Store> findByClientDomianlId_ClientDomainaId(long clientId);
	List<Store> findByClientDomianlId_Client_Id(long clientId);


	List<Store> findByStateId(long stateId);


	List<Store> findByCityId(String string);


	List<Store> findByDistrictId(long cityId);


	List<Store> findByStateIdAndDistrictId(long stateId, long districtId);


	List<Store> findByIdIn(List<Long> storeIds);


	List<Store> findByStateCode(String stateId);


	List<Store> findByStateCodeAndDistrictId(String stateId, long districtId);


	//List<Store> findByStateCodeAndDistrictIdAndCityId(String stateId, long districtId, String cityId);


	List<Store> findByStateCodeAndDistrictIdAndName(String stateId, long districtId, String storeName);


	List<Store> findByStateCodeAndName(String stateId, String storeName);


	

}
