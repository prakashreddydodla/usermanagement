package com.otsi.retail.authservice.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Repository.UserRepository;
import com.otsi.retail.authservice.requestModel.ClientDomainVo;
import com.otsi.retail.authservice.requestModel.MasterDomianVo;
import com.otsi.retail.authservice.requestModel.ParentPrivilegesVO;
import com.otsi.retail.authservice.requestModel.RoleVO;
import com.otsi.retail.authservice.requestModel.SubPrivilegeVO;

@Component
public class RoleMapper {
	@Autowired
	private UserRepository userRepo;

	public RoleVO convertEntityToRoleVo(Role role) {

		RoleVO roleVO = new RoleVO();

		roleVO.setRoleName(role.getRoleName());
		roleVO.setCreatedDate(role.getCreatedDate());
		roleVO.setModifiedBy(role.getModifiedBy());
		roleVO.setDescription(role.getDescription());
		roleVO.setId(role.getId());
		roleVO.setLastModifyedDate(role.getLastModifiedDate());
		roleVO.setCreatedBy(role.getCreatedBy());
		roleVO.setActive(role.isActive());
		roleVO.setUsersCount(userRepo.countByRoleId(role.getId()));

		List<ParentPrivilegesVO> parentPrivileges = new ArrayList<>();

		role.getParentPrivileges().stream().forEach(privilege -> {
			ParentPrivilegesVO parentPrivilegeVO = new ParentPrivilegesVO();
			parentPrivilegeVO.setId(privilege.getId());
			parentPrivilegeVO.setName(privilege.getName());
			parentPrivilegeVO.setDescription(privilege.getDescription());
			parentPrivilegeVO.setPath(privilege.getPath());
			parentPrivilegeVO.setParentImage(privilege.getParentImage());
			parentPrivileges.add(parentPrivilegeVO);
		});

		List<SubPrivilegeVO> subPrivileges = new ArrayList<>();

		role.getSubPrivileges().stream().forEach(privilege -> {
			SubPrivilegeVO subPrivilegeVO = new SubPrivilegeVO();
			subPrivilegeVO.setId(privilege.getId());
			subPrivilegeVO.setName(privilege.getName());
			subPrivilegeVO.setDescription(privilege.getDescription());
			subPrivilegeVO.setChildPath(privilege.getChildPath());
			subPrivilegeVO.setChildImage(privilege.getChildImage());
			subPrivilegeVO.setParentPrivilegeId(privilege.getParentPrivilegeId());
			subPrivileges.add(subPrivilegeVO);
		});

		roleVO.setParentPrivilege(parentPrivileges);
		roleVO.setSubPrivilege(subPrivileges);

		ClientDomainVo cdVo = new ClientDomainVo();
		if (role.getClientDomian() != null) {
			cdVo.setId(role.getClientDomian().getId());
			cdVo.setCreatedBy(role.getClientDomian().getCreatedBy());
			cdVo.setCreatedDate(role.getClientDomian().getCreatedDate());
			cdVo.setDiscription(role.getClientDomian().getDiscription());
			cdVo.setDomaiName(role.getClientDomian().getDomaiName());
			cdVo.setLastModifyedDate(role.getClientDomian().getLastModifiedDate());
			cdVo.setModifiedBy(role.getClientDomian().getModifiedBy());
			cdVo.setActive(role.getClientDomian().isActive());
		}
		List<MasterDomianVo> mdVo = new ArrayList<>();
		if(role.getClientDomian() != null) {
		role.getClientDomian().getDomain().stream().forEach(m -> {
			MasterDomianVo mVo = new MasterDomianVo();
			mVo.setDomainName(m.getChannelName());
			mVo.setCreatedBy(m.getCreatedBy());
			mVo.setCreatedDate(m.getCreatedDate());
			mVo.setDiscription(m.getDiscription());
			mVo.setId(m.getId());
			mVo.setLastModifyedDate(m.getLastModifiedDate());
			mVo.setStatus(m.isStatus());
			mdVo.add(mVo);

		});
		}
		cdVo.setDomainMasterVo(mdVo);
		roleVO.setClientDomain(cdVo);

		return roleVO;

	}

}
