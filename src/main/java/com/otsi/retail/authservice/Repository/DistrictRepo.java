package com.otsi.retail.authservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.Districts;

@Repository
public interface DistrictRepo extends JpaRepository<Districts, Long>{

	List<Districts> findByStateCode(String stateCode);

}
