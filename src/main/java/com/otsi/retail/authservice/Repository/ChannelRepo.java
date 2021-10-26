package com.otsi.retail.authservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.ClientDomains;

@Repository
public interface ChannelRepo extends JpaRepository<ClientDomains, Long> {


	List<ClientDomains> findByClient_Id(long clientId);

}
