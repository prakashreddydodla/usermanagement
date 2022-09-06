package com.otsi.retail.authservice.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.ClientDetails;

@Repository
public interface ClientDetailsRepo extends JpaRepository<ClientDetails, Long> {

	ClientDetails findByName(String clientName);
//
	boolean existsByName(String name);
	Page<ClientDetails> findByIdIn(List<Long> ids, Pageable pageable);
	ClientDetails findByIdAndCreatedDateBetween(Long clientId, LocalDateTime createdDatefrom,
			LocalDateTime createdDateTo);
	Page<ClientDetails> findByCreatedDateBetween(LocalDateTime createdDatefrom, LocalDateTime createdDateTo, Pageable pageable);
	Optional<ClientDetails> findById(ClientDetails clientId);
	Page<ClientDetails> findByIdInAndCreatedDateBetween(List<Long> clientIds, LocalDateTime createdDatefrom,
			LocalDateTime createdDateTo, Pageable pageable);
	Page<ClientDetails> findAllByOrderByCreatedDateDesc(Pageable pageable);
	List<ClientDetails> findByIdIn(List<Long> ids);
	
	@Query(value = "select * from client_details where name like %:clientName%  and created_date >=:createdDatefrom and created_date <=:createdDateTo", nativeQuery = true)
	Page<ClientDetails> findByNameAndCreatedDateBetween(String clientName, LocalDateTime createdDatefrom,
			LocalDateTime createdDateTo, Pageable pageable);
	@Query(value = "select * from client_details where name like %:clientName%", nativeQuery = true)
	Page<ClientDetails> findByName(String clientName, Pageable pageable);
	//List<ClientDetails> findAllByName(String clientName);
	ClientDetails findByPlanDetailsId(Long id);

 

}