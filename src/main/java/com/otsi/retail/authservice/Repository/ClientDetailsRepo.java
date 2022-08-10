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
	ClientDetails findByIdAndCreatedDateBetween(Long clientId, LocalDateTime createdDatefrom,
			LocalDateTime createdDateTo);
	List<ClientDetails> findByCreatedDateBetween(LocalDateTime createdDatefrom, LocalDateTime createdDateTo);
	Optional<ClientDetails> findById(ClientDetails clientId);
	List<ClientDetails> findByIdInAndCreatedDateBetween(List<Long> clientIds, LocalDateTime createdDatefrom,
			LocalDateTime createdDateTo);
	List<ClientDetails> findAllByOrderByCreatedDateDesc();


}
