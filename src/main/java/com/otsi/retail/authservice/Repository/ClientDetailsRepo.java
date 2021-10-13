package com.otsi.retail.authservice.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.ClientDetails;

@Repository
public interface ClientDetailsRepo extends JpaRepository<ClientDetails, Long> {

	ClientDetails findByName(String clientName);

}
