package com.otsi.retail.authservice.Repository;

import java.util.List;
import java.util.Optional;

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

	List<ClientUsers> findByClientId_IdIn(List<Long> clientIds);

}
