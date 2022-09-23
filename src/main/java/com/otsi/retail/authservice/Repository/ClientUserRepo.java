package com.otsi.retail.authservice.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.Entity.ClientUsers;
@Repository
public interface ClientUserRepo extends JpaRepository<ClientUsers, Long>{

	List<ClientUsers> findByClientId(ClientDetails clientId);

/*	List<ClientDetails> findByUserId(Long userId);
*/
	List<ClientUsers> findByUserId_Id(Long userId);

	List<ClientUsers> findByClientId_Id(Long long1);

	Page<ClientUsers> findByClientId_IdIn(List<Long> clientIds, Pageable pageable);

	Page<ClientUsers> findByClientId_IdInAndCreatedDateBetween(List<Long> clientIds, LocalDateTime createdDatefrom,
			LocalDateTime createdDateTo, Pageable pageable);

	Page<ClientUsers> findByCreatedDateBetween(LocalDateTime createdDatefrom, LocalDateTime createdDateTo, Pageable pageable);


	Page<ClientUsers> findAllByOrderByCreatedDateDesc(Pageable pageable);

	List<ClientUsers> findByClientId_IdIn(List<Long> clientIds);

	Page<ClientUsers> findByUserId_IdInAndCreatedDateBetween(List<Long> userIds, LocalDateTime createdDatefrom,
			LocalDateTime createdDateTo, Pageable pageable);

	Page<ClientUsers> findByUserId_IdIn(List<Long> userIds, Pageable pageable);

	List<ClientUsers> findByClientId_IdAndUserId_Id(Long id, Long id2);

	List<ClientUsers> findByUserId_IdAndStatus(Long userId, Boolean true1);

	Page<ClientUsers> findByStatusOrderByCreatedDateDesc(Boolean true1, Pageable pageable);

	Page<ClientUsers> findByClientId_IdInAndStatusAndCreatedDateBetween(List<Long> clientIds, Boolean true1,
			LocalDateTime createdDatefrom, LocalDateTime createdDateTo, Pageable pageable);

	Page<ClientUsers> findByUserId_IdInAndStatusAndCreatedDateBetween(List<Long> userIds, Boolean true1,
			LocalDateTime createdDatefrom, LocalDateTime createdDateTo, Pageable pageable);

	Page<ClientUsers> findByClientId_IdInAndStatus(List<Long> clientIds, Boolean true1, Pageable pageable);

	Page<ClientUsers> findByUserId_IdInAndStatus(List<Long> userIds, Boolean true1, Pageable pageable);

	Page<ClientUsers> findByCreatedDateBetweenAndStatus(LocalDateTime createdDatefrom, LocalDateTime createdDateTo,
			Boolean true1, Pageable pageable);

	List<ClientUsers> findByClientId_IdAndUserId_IdAndStatus(Long id, Long id2, Boolean true1);

	List<ClientUsers> findByClientId_IdAndStatus(Long clientId, Boolean true1);


}
