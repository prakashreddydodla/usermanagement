package com.otsi.retail.authservice.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.amazonaws.services.cognitoidp.model.CreateGroupResult;
import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.ParentPrivilages;
import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.SubPrivillage;
import com.otsi.retail.authservice.Repository.ChannelRepo;
import com.otsi.retail.authservice.Repository.PrivilageRepo;
import com.otsi.retail.authservice.Repository.RoleRepository;
import com.otsi.retail.authservice.Repository.SubPrivillageRepo;
import com.otsi.retail.authservice.requestModel.CreatePrivillagesRequest;
import com.otsi.retail.authservice.requestModel.CreateRoleRequest;
import com.otsi.retail.authservice.requestModel.ParentPrivilageVo;
import com.otsi.retail.authservice.requestModel.SubPrivillagesvo;

@Service
public class RolesAndPrivillagesService {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PrivilageRepo privilageRepo;
	@Autowired
	private CognitoClient cognitoClient;
	@Autowired
	private SubPrivillageRepo subPrivillageRepo;
	@Autowired
	private ChannelRepo channelRepo;

	public String savePrevilage(CreatePrivillagesRequest privilages) throws Exception {

		ParentPrivilages privilage = new ParentPrivilages();
		privilage.setName(privilages.getParentPrivillage().getName());
		privilage.setDiscription(privilages.getParentPrivillage().getDescription());
		privilage.setRead(Boolean.TRUE);
		privilage.setWrite(Boolean.TRUE);
		privilage.setCreatedDate(LocalDate.now());
		privilage.setLastModifyedDate(LocalDate.now());
		try {
			ParentPrivilages parentPrivillage = privilageRepo.save(privilage);
			if (!CollectionUtils.isEmpty(privilages.getSubPrivillages())) {
				privilages.getSubPrivillages().stream().forEach(a -> {
					SubPrivillage subPrivillage = new SubPrivillage();
					subPrivillage.setName(a.getName());
					subPrivillage.setDescription(a.getDescription());
					subPrivillage.setCreatedDate(LocalDate.now());
					subPrivillage.setModifyDate(LocalDate.now());
					subPrivillage.setParentPrivillageId(parentPrivillage.getId());
					subPrivillageRepo.save(subPrivillage);

				});
			}

			return "Saved Successfully";
		} catch (Exception e) {
			throw new Exception();
		}
	}

	public String saveSubPrivillages(SubPrivillagesvo vo) throws Exception {
		try {
			SubPrivillage subPrivillage = new SubPrivillage();
			subPrivillage.setName(vo.getName());
			subPrivillage.setDescription(vo.getDescription());
			subPrivillage.setParentPrivillageId(vo.getParentId());
			subPrivillage.setCreatedDate(LocalDate.now());
			subPrivillage.setModifyDate(LocalDate.now());
			subPrivillageRepo.save(subPrivillage);
			return "Sub privillege is created";
		} catch (Exception e) {
			throw new Exception();
		}
	}

	public List<ParentPrivilageVo> getAllPrivilages() {
		List<ParentPrivilageVo> listOfParentPrivillages = new ArrayList<>();
		List<ParentPrivilages> entity = privilageRepo.findAll();
		entity.stream().forEach(p -> {
			ParentPrivilageVo parentPrivillagesVo = new ParentPrivilageVo();
			parentPrivillagesVo.setId(p.getId());
			parentPrivillagesVo.setName(p.getName());
			parentPrivillagesVo.setDescription(p.getDiscription());
			parentPrivillagesVo.setLastModifyedDate(p.getLastModifyedDate());
			parentPrivillagesVo.setCreatedDate(p.getCreatedDate());
			List<SubPrivillage> subPrivillages = subPrivillageRepo.findByParentPrivillageId(p.getId());
			if (!CollectionUtils.isEmpty(subPrivillages)) {
				parentPrivillagesVo.setSubPrivillages(subPrivillages);
			}
			listOfParentPrivillages.add(parentPrivillagesVo);

		});

		return listOfParentPrivillages;
	}

	public List<SubPrivillage> getSubPrivillages(long parentId) throws Exception {
		if (0L != parentId) {
			List<SubPrivillage> subPrivillages = subPrivillageRepo.findByParentPrivillageId(parentId);
			if (!CollectionUtils.isEmpty(subPrivillages)) {
				return subPrivillages;
			} else {
				throw new Exception("No subprivillages found");
			}
		} else {
			throw new Exception("parentId should not be null");
		}
	}

	public String createRole(CreateRoleRequest role) throws Exception {
		Role roleEntity = new Role();
		Role dbResult = null;
		roleEntity.setDiscription(role.getDescription());
		roleEntity.setRoleName(role.getRoleName());
		roleEntity.setCreatedDate(LocalDate.now());
		List<ParentPrivilages> parentPrivilageEntites = new ArrayList<>();
		List<SubPrivillage> subPrivilageEntites = new ArrayList<>();
	boolean isExits=	roleRepository.existsByRoleNameIgnoreCase(role.getRoleName());
		if(!isExits) {
		if (0L != role.getClientDomianId()) {
			Optional<ClientDomains> clientDomians = channelRepo.findById(role.getClientDomianId());
			if (clientDomians.isPresent()) {
				roleEntity.setClientDomian(clientDomians.get());
			} else {
				throw new Exception("No Client Domian found with this Id : " + role.getClientDomianId());
			}
		} else {
			throw new Exception("Client Domian Id required");
		}

		if (!CollectionUtils.isEmpty(role.getParentPrivilages())) {

			role.getParentPrivilages().forEach(a -> {
				Optional<ParentPrivilages> parentPrivilage = privilageRepo.findById(a.getId());
				if (parentPrivilage.isPresent()) {
					parentPrivilageEntites.add(parentPrivilage.get());
				} else {
					throw new RuntimeException("Given privilage not found in master");
				}
			});
			roleEntity.setParentPrivilages(parentPrivilageEntites);

		} else {
			throw new Exception("Atleast one parent privillage is required");
		}

		if (!CollectionUtils.isEmpty(role.getSubPrivillages())) {

			role.getSubPrivillages().stream().forEach(sub -> {
				Optional<SubPrivillage> privilage = subPrivillageRepo.findById(sub.getId());
				if (privilage.isPresent()) {
					subPrivilageEntites.add(privilage.get());
				} else {
					throw new RuntimeException("Given sub privilage not found in master");
				}
			});
			roleEntity.setSubPrivilages(subPrivilageEntites);
		} else {
			throw new Exception("Atleast one sub privillage is required");
		}
		try {
			dbResult = roleRepository.save(roleEntity);
			if (!dbResult.equals(null)) {
				if (!role.getRoleName().equalsIgnoreCase("CUSTOMER")) {

					/**
					 * If the Role is not a Customer then it wil Create in Cognito also
					 */
					CreateGroupResult cognitoResult = cognitoClient.createRole(role);
					if (cognitoResult.getSdkHttpMetadata().getHttpStatusCode() == 200) {
						return "sucessfully saved";
					}
				}
			}
			throw new RuntimeException("Role not saved");
		} catch (Exception e) {
			//roleRepository.delete(dbResult);
			throw new Exception(e.getMessage());
		}
	}else {
		throw new Exception("Role name already Exists");

	}
	}

	public Role getPrivilages(long roleId) throws Exception {
		try {
			Optional<Role> role = roleRepository.findById(roleId);
			return role.get();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public List<Role> getRolesForClientDomian(long clientId) throws Exception {
		try {
			List<Role> roles = roleRepository.findByClientDomian_clientDomainaId(clientId);
			if (CollectionUtils.isEmpty(roles)) {
				return roles;
			} else {
				throw new Exception("No Roles found for this client :" + clientId);
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Role> getRolesForClient(long clientId) throws Exception {
		try {
			List<Role> roles = roleRepository.findByClientDomian_client_Id(clientId);
			if (CollectionUtils.isEmpty(roles)) {
				return roles;
			} else {
				throw new Exception("No Roles found for this client :" + clientId);
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Role getPrivilagesByRoleName(String roleName) throws Exception {
		try {
			Optional<Role> role = roleRepository.findByRoleName(roleName);
			return role.get();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
