package com.otsi.retail.authservice.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.Store;

@Repository
public interface StoreRepo extends JpaRepository<Store, Long> {

	List<Store> findByName(String storeName);


	List<Store> findByClientDomianlIdId(long clientId);
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


	List<Store> findByStateCodeAndDistrictIdAndNameAndClientDomianlId_Client_Id(String stateId, Long districtId,
			String storeName, Long clientId);


	List<Store> findByStateCodeAndDistrictIdAndClientDomianlId_Client_Id(String stateId, Long districtId,
			Long clientId);


	List<Store> findByStateCodeAndNameAndClientDomianlId_Client_Id(String stateId, String storeName, Long clientId);


	List<Store> findByStateCodeAndClientDomianlId_Client_Id(String stateId, Long clientId);


	Store findByNameAndClientDomianlId_Client_Id(String name, Long clientId);




	

}
