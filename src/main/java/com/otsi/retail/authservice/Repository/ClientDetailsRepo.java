package com.otsi.retail.authservice.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.Entity.Domain_Master;

@Repository
public interface ClientDetailsRepo extends JpaRepository<ClientDetails, Long> {

	ClientDetails findByName(String clientName);

	boolean existsByName(String name);


}
