package com.otsi.retail.authservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.States;

@Repository
public interface StateRepo extends JpaRepository<States, Long>{

	
}
