package com.otsi.retail.authservice.Repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.SubPrivilege;

@Repository
public interface SubPrivillageRepo  extends JpaRepository<SubPrivilege, Long>{

	LinkedList<SubPrivilege> findByParentPrivilegeId(Long id);


}
