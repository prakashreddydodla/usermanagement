package com.otsi.retail.authservice.services;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.SubPrivillage;
import com.otsi.retail.authservice.requestModel.CreatePrivillagesRequest;
import com.otsi.retail.authservice.requestModel.CreateRoleRequest;
import com.otsi.retail.authservice.requestModel.ParentPrivilageVo;
import com.otsi.retail.authservice.requestModel.RolesFilterRequest;
import com.otsi.retail.authservice.requestModel.SubPrivillagesvo;

@Component
public interface RolesAndPrivillagesService {

	String savePrevilage(List<CreatePrivillagesRequest> privilages) throws Exception;

	String saveSubPrivillages(SubPrivillagesvo vo) throws Exception;

	List<ParentPrivilageVo> getAllPrivilages();

	List<SubPrivillage> getSubPrivillages(long parentId) throws Exception;

	String createRole(CreateRoleRequest role) throws Exception;

	Role getPrivilages(long roleId) throws Exception;

	List<Role> getRolesForClientDomian(long clientId) throws Exception;

	List<Role> getRolesForClient(long clientId) throws Exception;

	Role getPrivilagesByRoleName(String roleName) throws Exception;

	List<Role> getRolesWithFilter(RolesFilterRequest req) throws RuntimeException;

	String updateRole(CreateRoleRequest request) throws Exception;
}
