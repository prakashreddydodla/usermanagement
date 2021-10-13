package com.otsi.retail.authservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.Store;

@Repository
public interface StoreRepo extends JpaRepository<Store, Long> {

}
