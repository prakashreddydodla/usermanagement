package com.otsi.retail.authservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.ClientDomains;

@Repository
public interface ClientcDomianRepo extends JpaRepository<ClientDomains, Long> {

}
