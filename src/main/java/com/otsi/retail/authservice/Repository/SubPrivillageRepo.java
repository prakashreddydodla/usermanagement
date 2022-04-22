package com.otsi.retail.authservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.SubPrivillage;

@Repository
public interface SubPrivillageRepo  extends JpaRepository<SubPrivillage, Long>{

	List<SubPrivillage> findByParentPrivillageId(long id);

}
