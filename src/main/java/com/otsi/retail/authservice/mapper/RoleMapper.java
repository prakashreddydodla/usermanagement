package com.otsi.retail.authservice.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.SubPrivillage;
import com.otsi.retail.authservice.Repository.UserRepo;
import com.otsi.retail.authservice.requestModel.ClientDomainVo;
import com.otsi.retail.authservice.requestModel.MasterDomianVo;
import com.otsi.retail.authservice.requestModel.ParentPrivilegesVo;
import com.otsi.retail.authservice.requestModel.RoleVo;
import com.otsi.retail.authservice.requestModel.SubPrivillageVo;
@Component
public class RoleMapper {
	@Autowired
	private UserRepo userRepo;

	public RoleVo convertEntityToRoleVo(Role role) {

		RoleVo vo = new RoleVo();

		vo.setRoleName(role.getRoleName());
		vo.setCreatedDate(role.getCreatedDate());
		vo.setModifiedBy(role.getModifiedBy());
		vo.setDiscription(role.getDiscription());
		vo.setRoleId(role.getRoleId());
		vo.setLastModifyedDate(role.getLastModifyedDate());
		vo.setCreatedBy(role.getCreatedBy());
		vo.setActive(role.isActive());
		vo.setUsersCount(userRepo.countByRoleRoleId(role.getRoleId()));
		List<ParentPrivilegesVo> plvo = new ArrayList<>();
		
		role.getParentPrivilages().stream().forEach(p -> {
			ParentPrivilegesVo pvo = new ParentPrivilegesVo();
			

			pvo.setId(p.getId());
			pvo.setName(p.getName());
			pvo.setDiscription(p.getDiscription());
			pvo.setPath(p.getPath());
			pvo.setParentImage(p.getParentImage());
			plvo.add(pvo);
			
		});
		List<SubPrivillageVo> slvo = new ArrayList<>();

		role.getSubPrivilages().stream().forEach(s -> {
			SubPrivillageVo svo = new SubPrivillageVo();
			
			svo.setId(s.getId());
			svo.setName(s.getName());
			svo.setDescription(s.getDescription());
			svo.setChildPath(s.getChildPath());
			svo.setChildImage(s.getChildImage());
			svo.setParentPrivillageId(s.getParentPrivillageId());
			slvo.add(svo);
			

		});
		 vo.setParentPrivilageVo(plvo);

		vo.setSubPrivilageVo(slvo);
		ClientDomainVo cdVo= new ClientDomainVo();
		cdVo.setClientDomainaId(role.getClientDomian().getClientDomainaId());
		cdVo.setCreatedBy(role.getClientDomian().getCreatedBy());
		cdVo.setCreatedDate(role.getClientDomian().getCreatedDate());
		cdVo.setDiscription(role.getClientDomian().getDiscription());
		cdVo.setDomaiName(role.getClientDomian().getDomaiName());
		cdVo.setLastModifyedDate(role.getClientDomian().getLastModifyedDate());
		cdVo.setModifiedBy(role.getClientDomian().getModifiedBy());
		cdVo.setActive(role.getClientDomian().isActive());
		
		
		List<MasterDomianVo> mdVo = new ArrayList<>();
		role.getClientDomian().getDomain().stream().forEach(m->{
			MasterDomianVo mVo= new MasterDomianVo();
			mVo.setDomainName(m.getChannelName());
			mVo.setCreatedBy(m.getCreatedBy());
			mVo.setCreatedDate(m.getCreatedDate());
			mVo.setDiscription(m.getDiscription());
			mVo.setId(m.getId());
			mVo.setLastModifyedDate(m.getLastModifyedDate());
			mVo.setStatus(m.isStatus());
			mdVo.add(mVo);
			
		});
		cdVo.setDomainMasterVo(mdVo);
		 vo.setClientDomainVo(cdVo);
		
		
		
        
		return vo;

	}

}
