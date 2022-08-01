package com.otsi.retail.authservice.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.ClientDetails;

@Repository
public interface ClientDetailsRepo extends JpaRepository<ClientDetails, Long> {

	ClientDetails findByName(String clientName);
//
	boolean existsByName(String name);
	List<ClientDetails> findByIdIn(List<Long> ids);
	ClientDetails findByIdAndCreatedDateDateBetween(Long clientId, LocalDateTime createdDatefrom,
			LocalDateTime createdDateTo);
	ClientDetails findByCreatedDateDateBetween(LocalDateTime createdDatefrom, LocalDateTime createdDateTo);


}
