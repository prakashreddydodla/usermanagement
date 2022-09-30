package com.otsi.retail.authservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.otsi.retail.authservice.Entity.ChildPrivilege;
import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.SubPrivilege;
import com.otsi.retail.authservice.requestModel.CreatePrivilegesRequest;
import com.otsi.retail.authservice.requestModel.CreateRoleRequest;
import com.otsi.retail.authservice.requestModel.ParentPrivilegeVO;
import com.otsi.retail.authservice.requestModel.PlanPrivilegeVo;
import com.otsi.retail.authservice.requestModel.PrivilegeVO;
import com.otsi.retail.authservice.requestModel.RoleVO;
import com.otsi.retail.authservice.requestModel.RolesFilterRequest;
import com.otsi.retail.authservice.requestModel.SubPrivilegesVO;

@Component
public interface RolesAndPrivillagesService {

	String savePrivilege(List<CreatePrivilegesRequest> privilages) throws Exception;

	String saveSubPrivillages(SubPrivilegesVO vo) throws Exception;

	PrivilegeVO getAllPrivilages();

	List<SubPrivilege> getSubPrivillages(long parentId) throws Exception;

	Role createRole(CreateRoleRequest role) throws Exception;

	Role getPrivilages(long roleId) throws Exception;

	List<RoleVO> getRolesForClientDomian(long clientId) throws Exception;

	List<RoleVO> getRolesByClient(Long clientId);

	RoleVO getPrivilagesByRoleName(String roleName) ;

	List<RoleVO> getRolesWithFilter(RolesFilterRequest req,Long clientId) throws RuntimeException;

	String updateRole(CreateRoleRequest request) throws Exception;

	String deletePrevileges(Long id);
	
	List<ChildPrivilege> getChildPrivileges(long subPrivillageId) throws Exception;
	
	List<PlanPrivilegeVo> getPrivilegeByPlan();
	
}
