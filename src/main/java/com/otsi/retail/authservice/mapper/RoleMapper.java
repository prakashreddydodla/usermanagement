package com.otsi.retail.authservice.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.otsi.retail.authservice.Entity.ChildPrivilege;
import com.otsi.retail.authservice.Entity.ParentPrivilege;
import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.SubPrivilege;
import com.otsi.retail.authservice.Repository.ChildPrivilegeRepo;
import com.otsi.retail.authservice.Repository.PrivilageRepo;
import com.otsi.retail.authservice.Repository.SubPrivillageRepo;
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
	
	@Autowired
	private PrivilageRepo privilageRepository;
	
	@Autowired
	private SubPrivillageRepo subPrivillageRepo;
	
	@Autowired
	private ChildPrivilegeRepo childPrivilegeRepo;

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
			parentPrivilegeVO.setPrevilegeType(privilege.getPrevilegeType());
			parentPrivileges.add(parentPrivilegeVO);
		});

		List<SubPrivilegeVO> subPrivileges = new ArrayList<>();

		role.getSubPrivileges().stream().forEach(privilege -> {
			SubPrivilegeVO subPrivilegeVO = new SubPrivilegeVO();
			List<ChildPrivilege> childPrivillages = role.getChildPrivilages();

			subPrivilegeVO.setId(privilege.getId());
			subPrivilegeVO.setName(privilege.getName());
			subPrivilegeVO.setDescription(privilege.getDescription());
			subPrivilegeVO.setChildPath(privilege.getChildPath());
			subPrivilegeVO.setChildImage(privilege.getChildImage());
			subPrivilegeVO.setParentPrivilegeId(privilege.getParentPrivilegeId());
			subPrivilegeVO.setChildPrivileges(childPrivillages);
			subPrivilegeVO.setPrevilegeType(privilege.getPrevilegeType());

			subPrivileges.add(subPrivilegeVO);
		});

		roleVO.setParentPrivileges(parentPrivileges);
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
	public RoleVO convertRoleEntityToRoleVo(Role role) {

		RoleVO roleVO = new RoleVO();

		roleVO.setRoleName(role.getRoleName());
		roleVO.setCreatedDate(role.getCreatedDate());
		roleVO.setModifiedBy(role.getModifiedBy());
		roleVO.setDescription(role.getDescription());
		roleVO.setId(role.getId());
		roleVO.setLastModifyedDate(role.getLastModifiedDate());
		roleVO.setCreatedBy(role.getCreatedBy());
		roleVO.setActive(role.isActive());
		//roleVO.setUsersCount(userRepo.countByRoleId(role.getId()));

		List<ParentPrivilegesVO> parentPrivileges = new ArrayList<>();
		
		List<SubPrivilege> subPrivilege = new ArrayList<>();


		/*
		 * role.getParentPrivileges().stream().forEach(privilege -> { ParentPrivilegesVO
		 * parentPrivilegeVO = new ParentPrivilegesVO(); List<SubPrivilegeVO>
		 * subPrivilegesList = new ArrayList<>();
		 * 
		 * List<SubPrivilege> subPrivileges = role.getSubPrivileges();
		 * 
		 * subPrivileges.stream().forEach(subprivilege -> { List<ChildPrivilege>
		 * ChildPrivilege = new ArrayList<>();
		 * 
		 * if(subprivilege.getParentPrivilegeId().equals(privilege.getId())) {
		 * List<ChildPrivilege> childPrivileges = role.getChildPrivilages();
		 * List<ChildPrivilege> masterchildPrivileges = childPrivilegeRepo.findAll();
		 * 
		 * if(childPrivileges!=null) {
		 * masterchildPrivileges.stream().forEach(masterchildPrivilege->{
		 * 
		 * if(subprivilege.getId().equals(masterchildPrivilege.getSubPrivillageId())){
		 * childPrivileges.stream().forEach(childPrivillage->{
		 * 
		 * if(subprivilege.getId().equals(childPrivillage.getSubPrivillageId())){
		 * 
		 * if(masterchildPrivilege.getId().equals(childPrivillage.getId())) {
		 * childPrivillage.setIsEnabeld(Boolean.TRUE);
		 * ChildPrivilege.add(childPrivillage);
		 * 
		 * } else ChildPrivilege.add(masterchildPrivilege); } }); } }); }
		 * List<ChildPrivilege> childs =
		 * ChildPrivilege.stream().map(c->c).distinct().collect(Collectors.toList());
		 * SubPrivilegeVO subPrivilegeVO = new SubPrivilegeVO();
		 * subPrivilegeVO.setId(subprivilege.getId());
		 * subPrivilegeVO.setName(subprivilege.getName());
		 * subPrivilegeVO.setDescription(subprivilege.getDescription());
		 * subPrivilegeVO.setChildPath(subprivilege.getChildPath());
		 * subPrivilegeVO.setChildImage(subprivilege.getChildImage());
		 * subPrivilegeVO.setParentPrivilegeId(subprivilege.getParentPrivilegeId());
		 * subPrivilegeVO.setPrevilegeType(subprivilege.getPrevilegeType());
		 * subPrivilegeVO.setChildPrivileges(childs);
		 * subPrivilegesList.add(subPrivilegeVO); subPrivilege.add(subprivilege); }
		 * 
		 * }); parentPrivilegeVO.setId(privilege.getId());
		 * parentPrivilegeVO.setName(privilege.getName());
		 * parentPrivilegeVO.setDescription(privilege.getDescription());
		 * parentPrivilegeVO.setPath(privilege.getPath());
		 * parentPrivilegeVO.setParentImage(privilege.getParentImage());
		 * parentPrivilegeVO.setPrevilegeType(privilege.getPrevilegeType());
		 * 
		 * parentPrivilegeVO.setSubPrivileges(subPrivilegesList);
		 * parentPrivileges.add(parentPrivilegeVO); });
		 * 
		 * //roleVO.setChildPrivilages(ChildPrivilege);
		 * roleVO.setSubPrivileges(subPrivilege);
		 * roleVO.setParentPrivileges(parentPrivileges);
		 * 
		 * 
		 * ClientDomainVo cdVo = new ClientDomainVo(); if (role.getClientDomian() !=
		 * null) { cdVo.setId(role.getClientDomian().getId());
		 * cdVo.setCreatedBy(role.getClientDomian().getCreatedBy());
		 * cdVo.setCreatedDate(role.getClientDomian().getCreatedDate());
		 * cdVo.setDiscription(role.getClientDomian().getDiscription());
		 * cdVo.setDomaiName(role.getClientDomian().getDomaiName());
		 * cdVo.setLastModifyedDate(role.getClientDomian().getLastModifiedDate());
		 * cdVo.setModifiedBy(role.getClientDomian().getModifiedBy());
		 * cdVo.setActive(role.getClientDomian().isActive()); } List<MasterDomianVo>
		 * mdVo = new ArrayList<>(); if(role.getClientDomian() != null) {
		 * role.getClientDomian().getDomain().stream().forEach(m -> { MasterDomianVo mVo
		 * = new MasterDomianVo(); mVo.setDomainName(m.getChannelName());
		 * mVo.setCreatedBy(m.getCreatedBy()); mVo.setCreatedDate(m.getCreatedDate());
		 * mVo.setDiscription(m.getDiscription()); mVo.setId(m.getId());
		 * mVo.setLastModifyedDate(m.getLastModifiedDate());
		 * mVo.setStatus(m.isStatus()); mdVo.add(mVo);
		 * 
		 * }); } if(role.getClient()!=null) { roleVO.setClient(role.getClient()); }
		 * cdVo.setDomainMasterVo(mdVo); roleVO.setClientDomain(cdVo);
		 */
		  List<ChildPrivilege> masterchildPrivileges = childPrivilegeRepo.findAll();
		  
		  List<Long> masterPrivilegeIds = masterchildPrivileges.stream().map(parentPrivilege -> parentPrivilege.getId()).collect(Collectors.toList());

		List<ParentPrivilege> parentPrivilegesList = role.getParentPrivileges();
		List<SubPrivilege> subPrivileges = role.getSubPrivileges();
		/*
		 * List<Long> parentPrivilegeIds =
		 * parentPrivilegesList.stream().map(parentPrivilege -> parentPrivilege.getId())
		 * .collect(Collectors.toList());
		 * 
		 * List<Long> subPrivilegeIds = subPrivileges.stream().map(parentPrivilege ->
		 * parentPrivilege.getId()) .collect(Collectors.toList());
		 */

		//subPrivileges = subPrivileges.stream()
			//	.filter(subPrivil -> parentPrivilegeIds.contains(subPrivil.getParentPrivilegeId()))
				//.collect(Collectors.toList());

		List<ChildPrivilege> chilePrivilegesList = role.getChildPrivilages();
		

		parentPrivilegesList.forEach(parentPrivilege -> {
			List<SubPrivilegeVO> subPrivilegesVOList = new ArrayList<SubPrivilegeVO>();
			ParentPrivilegesVO parentPrivilegeVO = new ParentPrivilegesVO();
			parentPrivilegeVO.setId(parentPrivilege.getId());
			parentPrivilegeVO.setName(parentPrivilege.getName());
			parentPrivilegeVO.setDescription(parentPrivilege.getDescription());
			parentPrivilegeVO.setPath(parentPrivilege.getPath());
			parentPrivilegeVO.setParentImage(parentPrivilege.getParentImage());
			parentPrivilegeVO.setPrevilegeType(parentPrivilege.getPrevilegeType());
			List<SubPrivilege> filteredSubPrivileges = subPrivileges.stream()
					.filter(subPrivil -> subPrivil.getParentPrivilegeId().equals(parentPrivilege.getId()))
					.collect(Collectors.toList());

			filteredSubPrivileges.stream().forEach(sub -> {
				List<ChildPrivilege> masterchildPrivilege = childPrivilegeRepo.findBySubPrivillageId(sub.getId());
				
				

				List<ChildPrivilege> childPrivilegesData = chilePrivilegesList.stream()
						.filter(childId -> childId.getSubPrivillageId().equals(sub.getId()))
						.collect(Collectors.toList());
				masterchildPrivilege.stream().forEach(masterChild->{
					childPrivilegesData.stream().forEach(childprivilege->{
						
						if(masterChild.getId().equals(childprivilege.getId())) {
							masterChild.setIsEnabeld(Boolean.TRUE);
						}
						
					});
					
					
				});
				
				

				SubPrivilegeVO subPrivilegeVO = entityToSubVo(sub);
				subPrivilegeVO.setChildPrivileges(masterchildPrivilege);
				subPrivilegesVOList.add(subPrivilegeVO);
			});

			parentPrivilegeVO.setSubPrivileges(subPrivilegesVOList);
			parentPrivileges.add(parentPrivilegeVO);
		});
		roleVO.setParentPrivileges(parentPrivileges);
		return roleVO;

	}
	
private SubPrivilegeVO entityToSubVo(SubPrivilege subprivilege) {
		
		SubPrivilegeVO subPrivilegeVO = new SubPrivilegeVO();
		subPrivilegeVO.setId(subprivilege.getId());
		subPrivilegeVO.setName(subprivilege.getName());
		subPrivilegeVO.setDescription(subprivilege.getDescription());
		subPrivilegeVO.setChildPath(subprivilege.getChildPath());
		subPrivilegeVO.setChildImage(subprivilege.getChildImage());
		subPrivilegeVO.setParentPrivilegeId(subprivilege.getParentPrivilegeId());
		subPrivilegeVO.setPrevilegeType(subprivilege.getPrevilegeType());
		return subPrivilegeVO;
	}

}
