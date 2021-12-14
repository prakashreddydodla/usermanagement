package com.otsi.retail.authservice.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.amazonaws.services.cognitoidp.model.CreateGroupResult;
import com.amazonaws.services.cognitoidp.model.GroupExistsException;
import com.amazonaws.services.cognitoidp.model.UpdateGroupResult;
import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.ParentPrivilages;
import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.SubPrivillage;
import com.otsi.retail.authservice.Entity.UserDeatils;
import com.otsi.retail.authservice.Exceptions.InvalidInputsException;
import com.otsi.retail.authservice.Exceptions.RolesNotFoundException;
import com.otsi.retail.authservice.Repository.ChannelRepo;
import com.otsi.retail.authservice.Repository.PrivilageRepo;
import com.otsi.retail.authservice.Repository.RoleRepository;
import com.otsi.retail.authservice.Repository.SubPrivillageRepo;
import com.otsi.retail.authservice.Repository.UserRepo;
import com.otsi.retail.authservice.mapper.RoleMapper;
import com.otsi.retail.authservice.requestModel.CreatePrivillagesRequest;
import com.otsi.retail.authservice.requestModel.CreateRoleRequest;
import com.otsi.retail.authservice.requestModel.ParentPrivilageVo;
import com.otsi.retail.authservice.requestModel.RoleVo;
import com.otsi.retail.authservice.requestModel.RolesFilterRequest;
import com.otsi.retail.authservice.requestModel.SubPrivillagesvo;

@Service
public class RolesAndPrivillagesServiceImpl implements RolesAndPrivillagesService {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleMapper rolemapper;
	@Autowired
	private PrivilageRepo privilageRepo;
	@Autowired
	private CognitoClient cognitoClient;
	@Autowired
	private SubPrivillageRepo subPrivillageRepo;
	@Autowired
	private ChannelRepo channelRepo;

	private Logger logger = LoggerFactory.getLogger(RolesAndPrivillagesServiceImpl.class);

	@Override
	public String savePrevilage(List<CreatePrivillagesRequest> privilages) throws Exception {
		try {
			privilages.stream().forEach(a -> {
				ParentPrivilages privilage = new ParentPrivilages();
				privilage.setName(a.getParentPrivillage().getName());
				privilage.setDiscription(a.getParentPrivillage().getDescription());
				privilage.setRead(Boolean.TRUE);
				privilage.setWrite(Boolean.TRUE);
				privilage.setCreatedDate(LocalDate.now());
				privilage.setDomian(a.getParentPrivillage().getDomian());
				privilage.setLastModifyedDate(LocalDate.now());

				privilage.setParentImage(a.getParentPrivillage().getParentImage());
				privilage.setPath(a.getParentPrivillage().getPath());
				ParentPrivilages parentPrivillage = privilageRepo.save(privilage);
				if (!CollectionUtils.isEmpty(a.getSubPrivillages())) {
					a.getSubPrivillages().stream().forEach(b -> {
						SubPrivillage subPrivillage = new SubPrivillage();
						subPrivillage.setName(b.getName());
						subPrivillage.setDescription(b.getDescription());
						subPrivillage.setCreatedDate(LocalDate.now());
						subPrivillage.setModifyDate(LocalDate.now());
						subPrivillage.setChildPath(b.getChildPath());
						subPrivillage.setChildImage(b.getChildImage());
						subPrivillage.setParentPrivillageId(parentPrivillage.getId());
						subPrivillage.setDomian(b.getDomian());
						subPrivillageRepo.save(subPrivillage);
					});
				}
			});
			return "Saved Successfully";
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
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

	@Override
	public List<ParentPrivilageVo> getAllPrivilages() {
		logger.info("############### getAllPrivilages method Starts ###################");

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
		logger.info("############### getAllPrivilages method ends ###################");

		return listOfParentPrivillages;
	}

	@Override
	public List<SubPrivillage> getSubPrivillages(long parentId) throws Exception {
		logger.info("############### getSubPrivillages method Starts ###################");

		if (0L != parentId) {
			List<SubPrivillage> subPrivillages = subPrivillageRepo.findByParentPrivillageId(parentId);
			if (!CollectionUtils.isEmpty(subPrivillages)) {
				logger.info("############### getSubPrivillages method ends ###################");

				return subPrivillages;
			} else {
				logger.debug("No subprivillages found");
				logger.error("No subprivillages found");
				throw new Exception("No subprivillages found");
			}
		} else {
			logger.debug("parentId should not be null");
			logger.error("parentId should not be null");
			throw new Exception("parentId should not be null");
		}
	}

	@Override
	@Transactional(rollbackOn = { RuntimeException.class, GroupExistsException.class, Exception.class })
	public String createRole(CreateRoleRequest role) throws RuntimeException,Exception {
		logger.info("###############Create Role method Starts###################");
		Role roleEntity = new Role();
		Role dbResult = null;
		roleEntity.setDiscription(role.getDescription());
		roleEntity.setRoleName(role.getRoleName());
		roleEntity.setCreatedDate(LocalDate.now());
		roleEntity.setCreatedBy(role.getCreatedBy());
		List<ParentPrivilages> parentPrivilageEntites = new ArrayList<>();
		List<SubPrivillage> subPrivilageEntites = new ArrayList<>();
		boolean isExits = roleRepository.existsByRoleNameIgnoreCase(role.getRoleName());
		if (!isExits) {
			try {
				/*
				 * We have to save role which is otherthan the customer. If role is customer we
				 * no need to save
				 */
				if (!role.getRoleName().equalsIgnoreCase("CUSTOMER")) {
					/*
					 * Http status code 200 means sucess then only we can allow to save role in our
					 * Role table in local DB
					 */
					if (0L != role.getClientDomianId()) {
						Optional<ClientDomains> clientDomians = channelRepo.findById(role.getClientDomianId());
						if (clientDomians.isPresent()) {
							roleEntity.setClientDomian(clientDomians.get());
						} else {
							logger.debug("No Client Domian found with this Id : " + role.getClientDomianId());
							logger.error("No Client Domian found with this Id : " + role.getClientDomianId());
							throw new Exception("No Client Domian found with this Id : " + role.getClientDomianId());
						}
					} // else {
						// logger.error("Client Domian Id required");
						// throw new Exception("Client Domian Id required");
						// }
					if (!CollectionUtils.isEmpty(role.getParentPrivilages())) {
						role.getParentPrivilages().forEach(a -> {
							Optional<ParentPrivilages> parentPrivilage = privilageRepo.findById(a.getId());
							if (parentPrivilage.isPresent()) {
								parentPrivilageEntites.add(parentPrivilage.get());
							} else {
								logger.debug("Given privilage not found in master");
								logger.error("Given privilage not found in master");
								throw new RuntimeException("Given privilage not found in master");
							}
						});
						roleEntity.setParentPrivilages(parentPrivilageEntites);
					} else {
						logger.debug("Atleast one parent privillage is required");
						logger.error("Atleast one parent privillage is required");
						throw new Exception("Atleast one parent privillage is required");
					}

					if (!CollectionUtils.isEmpty(role.getSubPrivillages())) {

						role.getSubPrivillages().stream().forEach(sub -> {
							Optional<SubPrivillage> privilage = subPrivillageRepo.findById(sub.getId());
							if (privilage.isPresent()) {
								subPrivilageEntites.add(privilage.get());
							} else {
								logger.debug("Given sub privilage not found in master");
								logger.error("Given sub privilage not found in master");
								throw new RuntimeException("Given sub privilage not found in master");
							}
						});
						roleEntity.setSubPrivilages(subPrivilageEntites);
					} else {
						logger.debug("Atleast one sub privillage is required");
						logger.error("Atleast one sub privillage is required");
						throw new Exception("Atleast one sub privillage is required");
					}

					dbResult = roleRepository.save(roleEntity);
					logger.info("role created in db-->Succes");
					/*
					 * We need to create group(role) in cognito with the given role name from
					 * request object
					 */
					CreateGroupResult cognitoResult = cognitoClient.createRole(role);
					if (cognitoResult.getSdkHttpMetadata().getHttpStatusCode() == 200) {
						logger.info("############### Create Role method End ###################");
						return "Role was Created with Id :" + dbResult.getRoleId();
					}
				} else {
					logger.debug("Customer is not a role");
					logger.error("Customer is not a role");
					throw new RuntimeException("Customer is not a role");
				}
			} catch (GroupExistsException ge) {
				logger.debug("Role name already Exists in cognito userpool");
				logger.error("Role name already Exists in cognito userpool");
				throw new RuntimeException("Role name already Exists in cognito userpool");
			} catch (Exception e) {
				logger.debug("Error occurs while creating role ===>" + e.getMessage());
				logger.error("Error occurs while creating role ===>" + e.getMessage());
				throw new Exception(e.getMessage());
			}
		} else {
			logger.debug("Role name already Exists in DB");
			logger.error("Role name already Exists in DB");
			throw new Exception("Role name already Exists in DB");
		}
		return null;
	}

	@Override
	public Role getPrivilages(long roleId) throws Exception {
		logger.info("############### getPrivilages method Starts ###################");

		try {
			Optional<Role> role = roleRepository.findById(roleId);
			if (role.isPresent()) {
				logger.info("############### getPrivilages method ends ###################");

				return role.get();
			} else {
				logger.debug("Role not found with this role Id: " + roleId);
				logger.error("Role not found with this role Id: " + roleId);
				throw new Exception("Role not found with this role Id: " + roleId);
			}
		} catch (Exception e) {
			logger.debug("Error occurs while get privillages : " + e.getMessage());
			logger.error("Error occurs while get privillages : " + e.getMessage());
			throw new Exception(e.getMessage());
		}

	}

	@Override
	public List<Role> getRolesForClientDomian(long clientId) throws Exception {
		logger.info("############### getRolesForClientDomian method starts ###################");

		try {
			logger.info("getRolesForClientDomian method Starts");
			List<Role> roles = roleRepository.findByClientDomian_clientDomainaId(clientId);
			if (!CollectionUtils.isEmpty(roles)) {
				logger.info("############### getRolesForClientDomian method ends ###################");
				return roles;
			} else {
				logger.debug("No Roles found for this clientDomian :" + clientId);
				logger.error("No Roles found for this clientDomian :" + clientId);
				throw new Exception("No Roles found for this clientDomian :" + clientId);
			}
		} catch (Exception e) {
			logger.debug("Errors occurs while fecthing roles for Client domian :" + e.getMessage());
			logger.error("Errors occurs while fecthing roles for Client domian :" + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Role> getRolesForClient(long clientId) throws Exception {
		logger.info("############### getRolesForClient method starts ###################");

		try {
			List<Role> roles = roleRepository.findByClientDomian_client_Id(clientId);
			if (!CollectionUtils.isEmpty(roles)) {
				logger.info("############### getRolesForClient method ends ###################");
				return roles;
			} else {
				logger.debug("No Roles found for this client :" + clientId);
				logger.error("No Roles found for this client :" + clientId);
				throw new Exception("No Roles found for this client :" + clientId);
			}
		} catch (Exception e) {
			logger.debug("Errors occurs while fecthing roles for client :" + e.getMessage());
			logger.error("Errors occurs while fecthing roles for client :" + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Role getPrivilagesByRoleName(String roleName) throws Exception {
		logger.info("############### getPrivilagesByRoleName method starts ###################");

		try {
			Optional<Role> role = roleRepository.findByRoleName(roleName);
			logger.info("############### getPrivilagesByRoleName method ends ###################");

			return role.get();
		} catch (Exception e) {
			logger.debug("Errors occurs while fecthing roles for client :" + e.getMessage());
			logger.error("Errors occurs while fecthing roles for client :" + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<RoleVo> getRolesWithFilter(RolesFilterRequest req) throws RuntimeException {
		logger.info("############### getRolesWithFilter method starts ###################");
		List<RoleVo>rolevo = new ArrayList<RoleVo>();

		if (null != req.getRoleName()) {
			Optional<Role> role = roleRepository.findByRoleName(req.getRoleName());
			if (role.isPresent()) {
				List<Role> roles = new ArrayList<>();
				roles.add(role.get());
				roles.stream().forEach(r->{ 
					
					
					RoleVo vo = rolemapper.convertEntityToRoleVo(r);
						
					rolevo.add(vo);
					
					});
				logger.info("############### getRolesWithFilter method ends ###################");
				

				return rolevo;
			} else {
				logger.debug("Roles not found with this RoleName : " + req.getRoleName());
				logger.error("Roles not found with this RoleName : " + req.getRoleName());
				throw new RolesNotFoundException("Roles not found with this RoleName : " + req.getRoleName());
			}
		}
		
		if (null != req.getCreatedBy() && "" != req.getCreatedBy()) {
			List<Role> roles = roleRepository.findByCreatedBy(req.getCreatedBy());
			if (!CollectionUtils.isEmpty(roles)) {
				
				
				roles.stream().forEach(r->{ 
					
					
				RoleVo vo = rolemapper.convertEntityToRoleVo(r);
					
				rolevo.add(vo);
				
				});
				logger.info("############### getRolesWithFilter method ends ###################");
				
				return rolevo;
				
			} else {
				logger.debug("No roles created by with this User : " + req.getCreatedBy());
				logger.error("No roles created by with this User : " + req.getCreatedBy());
				throw new RolesNotFoundException("No roles created by with this User : " + req.getCreatedBy());
			}
		}
		
		if (null != req.getCreatedDate()) {
			List<Role> roles = roleRepository.findByCreatedDate(req.getCreatedDate());
			if (!CollectionUtils.isEmpty(roles)) {
				roles.stream().forEach(r->{ 
					
					
					RoleVo vo = rolemapper.convertEntityToRoleVo(r);
						
					rolevo.add(vo);
					
					});
				logger.info("############### getRolesWithFilter method ends ###################");

				return rolevo;
			} else {
				logger.debug("No roles created  in this Date : " + req.getCreatedDate());
				logger.error("No roles created  in this Date : " + req.getCreatedDate());
				throw new RolesNotFoundException("No roles created  in this Date : " + req.getCreatedDate());
			}
		}
		logger.debug("Please give any one input feild for filter");
		logger.error("Please give any one input feild for filter");
		throw new InvalidInputsException("Please give any one input feild for filter");
	}

	@Override
	public String updateRole(CreateRoleRequest request) throws Exception {
		logger.info("############### Create Role method Starts ###################");
		// Role roleEntity = new Role();
		Role dbResult = null;
		try {
			Optional<Role> roleOptional = roleRepository.findByRoleId(request.getRoleId());
			Role roleEntity = roleOptional.get();
			roleEntity.setRoleId(request.getRoleId());
			roleEntity.setDiscription(request.getDescription());
			roleEntity.setRoleName(request.getRoleName());
			roleEntity.setLastModifyedDate(LocalDate.now());
			roleEntity.setModifiedBy(request.getCreatedBy());
			List<ParentPrivilages> parentPrivilageEntites = new ArrayList<>();
			List<SubPrivillage> subPrivilageEntites = new ArrayList<>();

			if (0L != request.getClientDomianId()) {
				Optional<ClientDomains> clientDomians = channelRepo.findById(request.getClientDomianId());
				if (clientDomians.isPresent()) {
					roleEntity.setClientDomian(clientDomians.get());
				} else {
					logger.debug("No Client Domian found with this Id : " + request.getClientDomianId());
					logger.error("No Client Domian found with this Id : " + request.getClientDomianId());
					throw new Exception("No Client Domian found with this Id : " + request.getClientDomianId());
				}
			} else {
				logger.debug("Client Domian Id required");
				logger.error("Client Domian Id required");
				throw new Exception("Client Domian Id required");
			}

			if (!CollectionUtils.isEmpty(request.getParentPrivilages())) {
				request.getParentPrivilages().forEach(a -> {
					Optional<ParentPrivilages> parentPrivilage = privilageRepo.findById(a.getId());
					if (parentPrivilage.isPresent()) {
						parentPrivilageEntites.add(parentPrivilage.get());
					} else {
						logger.debug("Given privilage not found in master");
						logger.error("Given privilage not found in master");
						throw new RuntimeException("Given privilage not found in master");
					}
				});
				roleEntity.setParentPrivilages(parentPrivilageEntites);
			} else {
				logger.debug("Atleast one parent privillage is required");
				logger.error("Atleast one parent privillage is required");
				throw new Exception("Atleast one parent privillage is required");
			}

			if (!CollectionUtils.isEmpty(request.getSubPrivillages())) {

				request.getSubPrivillages().stream().forEach(sub -> {
					Optional<SubPrivillage> privilage = subPrivillageRepo.findById(sub.getId());
					if (privilage.isPresent()) {
						subPrivilageEntites.add(privilage.get());
					} else {
						logger.debug("Given sub privilage not found in master");
						logger.error("Given sub privilage not found in master");
						throw new RuntimeException("Given sub privilage not found in master");
					}
				});
				roleEntity.setSubPrivilages(subPrivilageEntites);
			} else {
				logger.debug("Atleast one sub privillage is required");
				logger.error("Atleast one sub privillage is required");
				throw new Exception("Atleast one sub privillage is required");
			}

			roleRepository.save(roleEntity);
			UpdateGroupResult res = cognitoClient.updateRole(request);
			logger.info("Update role method Ends");
			return "Successfully update the role";

		} catch (RuntimeException re) {
			logger.debug("Error occurs while updateing the role Error : " + re.getMessage());
			logger.error("Error occurs while updateing the role Error : " + re.getMessage());
			throw new RuntimeException(re.getMessage());

		} catch (Exception e) {
			logger.debug("Error occurs while updateing the role Error : " + e.getMessage());
			logger.error("Error occurs while updateing the role Error : " + e.getMessage());
			throw new Exception(e.getMessage());
		}

	}

	public List<ParentPrivilageVo> getAllPrivilagesForDomian(int domian) {
		logger.info("############### getAllPrivilagesForDomian method Starts ###################");

		List<ParentPrivilageVo> listOfParentPrivillages = new ArrayList<>();
		List<ParentPrivilages> entity = privilageRepo.findByDomian(domian);
		entity.stream().forEach(p -> {
			ParentPrivilageVo parentPrivillagesVo = new ParentPrivilageVo();
			parentPrivillagesVo.setId(p.getId());
			parentPrivillagesVo.setName(p.getName());
			parentPrivillagesVo.setDescription(p.getDiscription());
			parentPrivillagesVo.setLastModifyedDate(p.getLastModifyedDate());
			parentPrivillagesVo.setCreatedDate(p.getCreatedDate());
			parentPrivillagesVo.setDomian(p.getDomian());
			List<SubPrivillage> subPrivillages = subPrivillageRepo.findByParentPrivillageId(p.getId());
			if (!CollectionUtils.isEmpty(subPrivillages)) {
				parentPrivillagesVo.setSubPrivillages(subPrivillages);
			}
			listOfParentPrivillages.add(parentPrivillagesVo);

		});
		logger.info("############### getAllPrivilagesForDomian method Starts ###################");
		return listOfParentPrivillages;
	}
}
