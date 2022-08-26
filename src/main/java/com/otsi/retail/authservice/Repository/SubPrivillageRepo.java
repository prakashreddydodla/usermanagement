package com.otsi.retail.authservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.SubPrivilege;

@Repository
public interface SubPrivillageRepo  extends JpaRepository<SubPrivilege, Long>{

	List<SubPrivilege> findByParentPrivilegeId(Long id);

	List<SubPrivilege> findByParentPrivilegeIdAndRoleName(Long id, Object object);


	/*List<SubPrivilege> findByParentPrivilegeIdAndRoleNameNotIn(Long id, String[] roleName);


	List<SubPrivilege> findByParentPrivilegeIdAndRoleName(Long id, String str);*/


}