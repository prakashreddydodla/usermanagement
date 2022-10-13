package com.otsi.retail.authservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.ParentPrivilege;
import com.otsi.retail.authservice.utils.PrevilegeType;

@Repository
public interface PrivilageRepo extends JpaRepository<ParentPrivilege, Long> {

	List<ParentPrivilege> findByDomain(Long domian);

	List<ParentPrivilege> findByIsActiveTrue();

	List<ParentPrivilege> findByPlanIdAndIsActiveTrue(Long planId);

	ParentPrivilege findByPlanId(Long id);

	List<ParentPrivilege> findByPrevilegeType(PrevilegeType web);

	List<ParentPrivilege> findByPrevilegeTypeAndPlanIdIsNotNull(PrevilegeType web);

	ParentPrivilege findByPlanIdAndId(Long planId, Long previlegeId);

	List<ParentPrivilege> findByPrevilegeTypeAndPlanIdIsNotNullOrderByCreatedDateDesc(PrevilegeType web);

	

}
