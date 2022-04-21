package com.otsi.retail.authservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.Domain_Master;

@Repository
public interface Domian_MasterRepo extends JpaRepository<Domain_Master, Long> {

}
