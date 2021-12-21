package com.otsi.retail.authservice.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.GstDetails;
@Repository
public interface GstRepository extends JpaRepository<GstDetails, Long> {

	

	Optional<GstDetails> findByGstNumber(String gstNumber);

	GstDetails findByClientIdAndStateCode(long clientId, String stateCode);

}
