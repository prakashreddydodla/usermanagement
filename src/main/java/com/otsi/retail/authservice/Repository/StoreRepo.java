package com.otsi.retail.authservice.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.Store;

@Repository
public interface StoreRepo extends JpaRepository<Store, Long> {

	Optional<Store> findByName(String storeName);


	List<Store> findByClientDomianlId_ClientDomainaId(long clientId);


	List<Store> findByStateId(long stateId);


	List<Store> findByCityId(long cityId);


	List<Store> findByDistrictId(long cityId);

}
