package com.otsi.retail.authservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.Privilages;

@Repository
public interface PrivilageRepo extends JpaRepository<Privilages, Long> {

}
