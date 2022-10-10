package com.otsi.retail.authservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otsi.retail.authservice.Entity.PlanTransactionDetails;


public interface PlanTransactionRepo extends JpaRepository<PlanTransactionDetails, Long> {

}
