package com.otsi.retail.authservice.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.services.cognitoidp.model.CreateGroupResult;
import com.amazonaws.services.cognitoidp.model.GroupExistsException;
import com.otsi.retail.authservice.Entity.ChildPrivilege;
import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.ParentPrivilege;
import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.SubPrivilege;
import com.otsi.retail.authservice.Exceptions.InvalidInputsException;
import com.otsi.retail.authservice.Exceptions.RecordNotFoundException;
import com.otsi.retail.authservice.Exceptions.RolesNotFoundException;
import com.otsi.retail.authservice.Repository.ChannelRepo;
import com.otsi.retail.authservice.Repository.ChildPrivilegeRepo;
import com.otsi.retail.authservice.Repository.ClientDetailsRepo;
import com.otsi.retail.authservice.Repository.PrivilageRepo;
import com.otsi.retail.authservice.Repository.RoleRepository;
import com.otsi.retail.authservice.Repository.SubPrivillageRepo;
import com.otsi.retail.authservice.mapper.RoleMapper;
import com.otsi.retail.authservice.requestModel.CreatePrivilegesRequest;
import com.otsi.retail.authservice.requestModel.CreateRoleRequest;
import com.otsi.retail.authservice.requestModel.ParentPrivilegeVO;
import com.otsi.retail.authservice.requestModel.PrivilegeVO;
import com.otsi.retail.authservice.requestModel.RoleVO;
import com.otsi.retail.authservice.requestModel.RolesFilterRequest;
import com.otsi.retail.authservice.requestModel.SubPrivilegesVO;
import com.otsi.retail.authservice.utils.DateConverters;
import com.otsi.retail.authservice.utils.PrevilegeType;

@Service
public class RolesAndPrivillagesServiceImpl implements RolesAndPrivillagesService {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private RoleMapper rolemapper;
	@Autowired
	private PrivilageRepo privilageRepository;
	@Autowired
	private CognitoClient cognitoClient;
	@Autowired
	private SubPrivillageRepo subPrivillageRepo;
	@Autowired
	private ChannelRepo channelRepo;
	
	@Autowired
	private ClientDetailsRepo clientDetailsrepo;
	
	

	@Autowired
	private ChildPrivilegeRepo childPrivilegeRepo;

	private Logger logger = LogManager.getLogger(RolesAndPrivillagesServiceImpl.class);

	@Override
	public String savePrivilege(List<CreatePrivilegesRequest> privilages) throws Exception {
		try {
			privilages.stream().forEach(privil -> {
				ParentPrivilege parentPrivilege = new ParentPrivilege();
				parentPrivilege.setName(privil.getParentPrivilege().getName());
				parentPrivilege.setDescription(privil.getParentPrivilege().getDescription());
				parentPrivilege.setRead(Boolean.TRUE);
				parentPrivilege.setWrite(Boolean.TRUE);
				parentPrivilege.setPrevilegeType(privil.getParentPrivilege().getPrevilegeType());
				// privilage.setCreatedDate(LocalDate.now());
				parentPrivilege.setDomain(privil.getParentPrivilege().getDomain());
				// privilage.setLastModifyedDate(LocalDate.now());

				parentPrivilege.setParentImage(privil.getParentPrivilege().getParentImage());
				parentPrivilege.setPath(privil.getParentPrivilege().getPath());
				ParentPrivilege parentPrivillage = privilageRepository.save(parentPrivilege);
				if (!CollectionUtils.isEmpty(privil.getSubPrivileges())) {
					privil.getSubPrivileges().stream().forEach(subPrivil -> {
						SubPrivilege subPrivilege = new SubPrivilege();
						subPrivilege.setName(subPrivil.getName());
						subPrivilege.setDescription(subPrivil.getDescription());
						subPrivilege.setChildPath(subPrivil.getChildPath());
						subPrivilege.setChildImage(subPrivil.getChildImage());
						subPrivilege.setParentPrivilegeId(parentPrivillage.getId());
						subPrivilege.setPrevilegeType(subPrivil.getPrevilegeType());
						subPrivilege.setDomain(subPrivil.getDomain());
						SubPrivilege subPrivillagesSave = subPrivillageRepo.save(subPrivilege);

						if (!CollectionUtils.isEmpty(subPrivil.getChildPrivillages())) {

							subPrivil.getChildPrivillages().stream().forEach(c -> {

								ChildPrivilege childPrivilege = new ChildPrivilege();
								childPrivilege.setName(c.getName());
								childPrivilege.setDescription(c.getDescription());
								childPrivilege.setSubChildPath(c.getSubChildPath());
								childPrivilege.setPrevilegeType(c.getPrevilegeType());
								childPrivilege.setSubChildImage(c.getSubChildImage());
								childPrivilege.setSubPrivillageId(subPrivillagesSave.getId());
								childPrivilege.setDomian(c.getDomian());
								childPrivilegeRepo.save(childPrivilege);

							});
						}
					});
				}
			});
			return "Saved Successfully";
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public String saveSubPrivillages(SubPrivilegesVO vo) throws Exception {
		try {
			SubPrivilege subPrivillage = new SubPrivilege();
			subPrivillage.setName(vo.getName());
			subPrivillage.setDescription(vo.getDescription());
			subPrivillage.setParentPrivilegeId(vo.getParentId());
			/*
			 * subPrivillage.setCreatedDate(LocalDate.now());
			 * subPrivillage.setModifyDate(LocalDate.now());
			 */
			subPrivillageRepo.save(subPrivillage);
			return "Sub privillege is created";
		} catch (Exception e) {
			throw new Exception();
		}
	}

	/*
	 * @Override public List<ParentPrivilegeVO> getAllPrivilages() { logger.
	 * info("############### getAllPrivilages method Starts ###################");
	 * 
	 * List<ParentPrivilegeVO> listOfParentPrivillages = new ArrayList<>();
	 * 
	 * List<ParentPrivilege> entity = privilageRepository.findAll();
	 * entity.stream().forEach(p -> { ParentPrivilegeVO parentPrivillagesVo = new
	 * ParentPrivilegeVO(); parentPrivillagesVo.setId(p.getId());
	 * parentPrivillagesVo.setName(p.getName());
	 * parentPrivillagesVo.setDescription(p.getDescription());
	 * parentPrivillagesVo.setLastModifyedDate(p.getLastModifiedDate());
	 * parentPrivillagesVo.setCreatedDate(p.getCreatedDate()); List<SubPrivilege>
	 * subPrivillages = subPrivillageRepo.findByParentPrivilegeId(p.getId()); if
	 * (!CollectionUtils.isEmpty(subPrivillages)) {
	 * parentPrivillagesVo.setSubPrivileges(subPrivillages); }
	 * 
	 * subPrivillages.stream().forEach(s -> {
	 * 
	 * List<ChildPrivilege> childPrivillages =
	 * childPrivilegeRepo.findBySubPrivillageId(s.getId()); if
	 * (!CollectionUtils.isEmpty(childPrivillages)) {
	 * 
	 * parentPrivillagesVo.setChildPrivillages(childPrivillages);
	 * 
	 * } });
	 * 
	 * listOfParentPrivillages.add(parentPrivillagesVo);
	 * 
	 * }); logger.
	 * info("############### getAllPrivilages method ends ###################");
	 * 
	 * return listOfParentPrivillages; }
	 * 
	 * ////////////
	 */

	public PrivilegeVO getAllPrivilages() {
		logger.info("############### getAllPrivilages method Starts ###################");

		List<ParentPrivilegeVO> listOfwebPrivillages = new ArrayList<>();
		List<ParentPrivilegeVO> listOfmobilePrivillages = new ArrayList<>();

		PrivilegeVO privilegeVo = new PrivilegeVO();

		List<ParentPrivilege> entity = privilageRepository.findAll();
		entity.stream().forEach(p -> {
			if (p.getPrevilegeType() == PrevilegeType.Web) {
				ParentPrivilegeVO parentPrivillagesVo = new ParentPrivilegeVO();
				parentPrivillagesVo.setPath(p.getPath());
				parentPrivillagesVo.setPrevilegeType(p.getPrevilegeType());
			    parentPrivillagesVo.setParentImage(p.getParentImage());

				parentPrivillagesVo.setId(p.getId());
				parentPrivillagesVo.setName(p.getName());
				parentPrivillagesVo.setDescription(p.getDescription());
				parentPrivillagesVo.setLastModifyedDate(p.getLastModifiedDate());
				parentPrivillagesVo.setCreatedDate(p.getCreatedDate());
				List<SubPrivilege> subPrivillages = subPrivillageRepo.findByParentPrivilegeId(p.getId());
				if (!CollectionUtils.isEmpty(subPrivillages)) {
					parentPrivillagesVo.setSubPrivileges(subPrivillages);
				}

				subPrivillages.stream().forEach(s -> {

					List<ChildPrivilege> childPrivillages = childPrivilegeRepo.findBySubPrivillageId(s.getId());
					if (!CollectionUtils.isEmpty(childPrivillages)) {

						parentPrivillagesVo.setChildPrivillages(childPrivillages);

					}
				});

				listOfwebPrivillages.add(parentPrivillagesVo);
				privilegeVo.setWebPrivileges(listOfwebPrivillages);

			} else {

				ParentPrivilegeVO parentPrivillagesVo = new ParentPrivilegeVO();
				parentPrivillagesVo.setId(p.getId());
				parentPrivillagesVo.setName(p.getName());
				parentPrivillagesVo.setDescription(p.getDescription());
				parentPrivillagesVo.setPath(p.getPath());
				parentPrivillagesVo.setPrevilegeType(p.getPrevilegeType());
				parentPrivillagesVo.setParentImage(p.getParentImage());

				parentPrivillagesVo.setLastModifyedDate(p.getLastModifiedDate());
				parentPrivillagesVo.setCreatedDate(p.getCreatedDate());
				List<SubPrivilege> subPrivillages = subPrivillageRepo.findByParentPrivilegeId(p.getId());
				if (!CollectionUtils.isEmpty(subPrivillages)) {
					parentPrivillagesVo.setSubPrivileges(subPrivillages);
				}

				subPrivillages.stream().forEach(s -> {

					List<ChildPrivilege> childPrivillages = childPrivilegeRepo.findBySubPrivillageId(s.getId());
					if (!CollectionUtils.isEmpty(childPrivillages)) {

						parentPrivillagesVo.setChildPrivillages(childPrivillages);

					}
				});

				listOfmobilePrivillages.add(parentPrivillagesVo);
				privilegeVo.setMobilePrivileges(listOfmobilePrivillages);

			}

		});
		logger.info("############### getAllPrivilages method ends ###################");
		return privilegeVo;
	}

	@Override
	public List<SubPrivilege> getSubPrivillages(long parentId) throws Exception {
		if (0L != parentId) {
			List<SubPrivilege> subPrivillages = subPrivillageRepo.findByParentPrivilegeId(parentId);
			if (!CollectionUtils.isEmpty(subPrivillages)) {

				return subPrivillages;
			} else {
				logger.error("No sub privileges found");
				throw new Exception("No sub privileges found");
			}
		} else {
			logger.error("parentId should not be null");
			throw new Exception("parentId should not be null");
		}
	}

	@Override
	@Transactional(rollbackOn = { RuntimeException.class, GroupExistsException.class, Exception.class })
	public Role createRole(CreateRoleRequest createRoleRequest) throws Exception {

		if (CollectionUtils.isEmpty(createRoleRequest.getParentPrivileges())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "atleast one privilege is required");
		}

		if (CollectionUtils.isEmpty(createRoleRequest.getSubPrivileges())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "atleast one sub privilege is required");
		}

		boolean isExits = roleRepository.existsByRoleNameIgnoreCase(createRoleRequest.getRoleName());
		if (isExits) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"role name already exists:" + createRoleRequest.getRoleName());
		}

		Role role = new Role();
		role.setDescription(createRoleRequest.getDescription());
		role.setRoleName(createRoleRequest.getRoleName());
		role.setCreatedBy(createRoleRequest.getCreatedBy());
		ClientDetails clientDetails = new ClientDetails();
		clientDetails.setId(createRoleRequest.getClientId());
		role.setClient(clientDetails);
		List<ParentPrivilege> parentPrivilageEntites = new ArrayList<>();
		List<SubPrivilege> subPrivilageEntites = new ArrayList<>();
		List<ChildPrivilege> childPrivilageEntities = new ArrayList<>();

		try {
			if (!createRoleRequest.getRoleName().equalsIgnoreCase("CUSTOMER")) {

				if (null != createRoleRequest.getClientDomianId()) {
					Optional<ClientDomains> clientDomians = channelRepo.findById(createRoleRequest.getClientDomianId());
					if (clientDomians.isPresent()) {
						role.setClientDomian(clientDomians.get());
					} else {
						logger.error("No Client Domian found with this Id : " + createRoleRequest.getClientDomianId());
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
								"No Client Domian found with this Id :" + createRoleRequest.getClientDomianId());
					}
				}

				createRoleRequest.getParentPrivileges().forEach(privilege -> {
					Optional<ParentPrivilege> parentPrivilage = privilageRepository.findById(privilege.getId());
					if (parentPrivilage.isPresent()) {
						parentPrivilageEntites.add(parentPrivilage.get());
					} else {
						logger.error("Given privilege not found in master");
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "privilege not found");
					}
				});
				role.setParentPrivileges(parentPrivilageEntites);

				createRoleRequest.getSubPrivileges().stream().forEach(sub -> {
					Optional<SubPrivilege> privilage = subPrivillageRepo.findById(sub.getId());
					if (privilage.isPresent()) {
						subPrivilageEntites.add(privilage.get());
					} else {
						logger.error("Given sub privilege not found in master");
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sub privilege not found");
					}

					// code added by sudheer

					if (!CollectionUtils.isEmpty(sub.getChildPrivillages())) {
						sub.getChildPrivillages().stream().forEach(child -> {

							Optional<ChildPrivilege> privilege = childPrivilegeRepo.findById(child.getId());
							if (privilege.isPresent()) {

								childPrivilageEntities.add(privilege.get());

							} else {

								logger.debug("Given child privilege not found in master");
								logger.error("Given child privilege not found in master");
								throw new RuntimeException("Given  privilege not found in master");
							}

						});

					}
				});
				role.setChildPrivilages(childPrivilageEntities);

				role.setSubPrivileges(subPrivilageEntites);

				role = roleRepository.save(role);
				CreateGroupResult cognitoResult = cognitoClient.createRole(createRoleRequest);
				if (cognitoResult.getSdkHttpMetadata().getHttpStatusCode() == HttpStatus.OK.value()) {
					return role;
				}
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "customer is not a role");
			}
		} catch (GroupExistsException ge) {
			logger.error("role name already Exists in cognito userpool");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "role name already exists in cognito userpool");
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
			logger.debug("Error occurs while get privileges : " + e.getMessage());
			logger.error("Error occurs while get privileges : " + e.getMessage());
			throw new Exception(e.getMessage());
		}

	}

	@Override
	public List<RoleVO> getRolesForClientDomian(long clientId) throws Exception {
		List<RoleVO> rolevo = new ArrayList<>();
		try {
			List<Role> roles = roleRepository.findByClientDomianId(clientId);
			if (!CollectionUtils.isEmpty(roles)) {
				roles.stream().forEach(r -> {

					RoleVO vo = rolemapper.convertEntityToRoleVo(r);

					rolevo.add(vo);

				});
				return rolevo;
			} else {
				logger.error("No Roles found for this clientDomian :" + clientId);
				throw new Exception("No Roles found for this clientDomian :" + clientId);
			}
		} catch (Exception e) {
			logger.error("Errors occurs while fecthing roles for Client domian :" + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<RoleVO> getRolesByClient(Long clientId) {
		List<RoleVO> rolesVO = new ArrayList<>();
		List<Role> roles = roleRepository.findByClientId(clientId);
		if (CollectionUtils.isEmpty(roles)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Roles found for this client:" + clientId);
		}
		roles.stream().forEach(role -> {
			RoleVO roleVO = rolemapper.convertEntityToRoleVo(role);
			rolesVO.add(roleVO);
		});
		return rolesVO;
	}

	@Override
	public Optional<Role> getPrivilagesByRoleName(String roleName) {
		return roleRepository.findByRoleName(roleName);

	}

	@Override
	public List<RoleVO> getRolesWithFilter(RolesFilterRequest req, Long clientId) throws RuntimeException {
		List<RoleVO> rolevo = new ArrayList<RoleVO>();

		if (null != req.getRoleName() && null != req.getCreatedDate() && null != req.getCreatedBy()) {
			LocalDateTime createdDatefrom = DateConverters.convertLocalDateToLocalDateTime(req.getCreatedDate());
			LocalDateTime createdDateTo = DateConverters.convertToLocalDateTimeMax(req.getCreatedDate());
			Optional<Role> role = roleRepository.findByRoleNameAndCreatedByAndCreatedDateBetweenAndClient_Id(
					req.getRoleName(), req.getCreatedBy(), createdDatefrom, createdDateTo, clientId);
			if (role.isPresent()) {
				List<Role> roles = new ArrayList<>();
				roles.add(role.get());
				roles.stream().forEach(r -> {
					RoleVO vo = rolemapper.convertEntityToRoleVo(r);
					rolevo.add(vo);
				});
				return rolevo;
			} else {
				logger.error("Roles not found with this given details : " + req.getRoleName());
				throw new RolesNotFoundException("Roles not found with this givenDetails : " + req.getRoleName());
			}

		}

		if (null != req.getRoleName() && null != req.getCreatedDate() && null == req.getCreatedBy()) {
			LocalDateTime createdDatefrom = DateConverters.convertLocalDateToLocalDateTime(req.getCreatedDate());
			LocalDateTime createdDateTo = DateConverters.convertToLocalDateTimeMax(req.getCreatedDate());
			Optional<Role> role = roleRepository.findByRoleNameAndCreatedDateBetweenAndClient_Id(req.getRoleName(),
					createdDatefrom, createdDateTo, clientId);
			if (role.isPresent()) {
				List<Role> roles = new ArrayList<>();
				roles.add(role.get());
				roles.stream().forEach(r -> {
					RoleVO vo = rolemapper.convertEntityToRoleVo(r);
					rolevo.add(vo);
				});
				logger.info("############### getRolesWithFilter method ends ###################");
				return rolevo;
			} else {
				logger.error("Roles not found with this given details : " + req.getRoleName());
				throw new RolesNotFoundException("Roles not found with this givenDetails : " + req.getRoleName());
			}
		}

		if (null != req.getRoleName() && null == req.getCreatedDate() && null != req.getCreatedBy()) {

			Optional<Role> role = roleRepository.findByRoleNameAndCreatedByAndClient_Id(req.getRoleName(),
					req.getCreatedBy(), clientId);
			if (role.isPresent()) {
				List<Role> roles = new ArrayList<>();
				roles.add(role.get());
				roles.stream().forEach(r -> {
					RoleVO vo = rolemapper.convertEntityToRoleVo(r);
					rolevo.add(vo);
				});
				return rolevo;
			} else {
				logger.error("Roles not found with this given details : " + req.getRoleName());
				throw new RolesNotFoundException("Roles not found with this givenDetails : " + req.getRoleName());
			}

		}

		if (null == req.getRoleName() && null != req.getCreatedDate() && null != req.getCreatedBy()) {
			LocalDateTime createdDatefrom = DateConverters.convertLocalDateToLocalDateTime(req.getCreatedDate());
			LocalDateTime createdDateTo = DateConverters.convertToLocalDateTimeMax(req.getCreatedDate());
			List<Role> roles = roleRepository.findByCreatedByAndCreatedDateBetweenAndClient_Id(req.getCreatedBy(),
					createdDatefrom, createdDateTo, clientId);
			if (!CollectionUtils.isEmpty(roles)) {
				roles.stream().forEach(r -> {
					RoleVO vo = rolemapper.convertEntityToRoleVo(r);
					rolevo.add(vo);
				});
				return rolevo;
			} else {
				logger.error("Roles not found with this given details : " + req.getRoleName());
				throw new RolesNotFoundException("Roles not found with this givenDetails : " + req.getRoleName());
			}
		}

		if (null != req.getRoleName()) {
			Optional<Role> role = roleRepository.findByRoleNameAndClient_Id(req.getRoleName(), clientId);
			if (role.isPresent()) {
				List<Role> roles = new ArrayList<>();
				roles.add(role.get());
				roles.stream().forEach(r -> {
					RoleVO vo = rolemapper.convertEntityToRoleVo(r);
					rolevo.add(vo);
				});
				return rolevo;
			} else {
				logger.error("Roles not found with this RoleName : " + req.getRoleName());
				throw new RolesNotFoundException("Roles not found with this RoleName : " + req.getRoleName());
			}
		}

		if (null != req.getCreatedBy() && 0l != req.getCreatedBy()) {
			List<Role> roles = roleRepository.findByCreatedByAndClient_Id(req.getCreatedBy(), clientId);
			if (!CollectionUtils.isEmpty(roles)) {
				roles.stream().forEach(r -> {
					RoleVO vo = rolemapper.convertEntityToRoleVo(r);
					rolevo.add(vo);
				});
				return rolevo;
			} else {
				logger.error("No roles created by with this User : " + req.getCreatedBy());
				throw new RolesNotFoundException("No roles created by with this User : " + req.getCreatedBy());
			}
		}

		if (null != req.getCreatedDate()) {
			LocalDateTime createdDatefrom = DateConverters.convertLocalDateToLocalDateTime(req.getCreatedDate());
			LocalDateTime createdDateTo = DateConverters.convertToLocalDateTimeMax(req.getCreatedDate());
			List<Role> roles = roleRepository.findByCreatedDateBetweenAndClientId(createdDatefrom, createdDateTo,
					clientId);
			if (!CollectionUtils.isEmpty(roles)) {
				roles.stream().forEach(r -> {
					RoleVO vo = rolemapper.convertEntityToRoleVo(r);
					rolevo.add(vo);
				});
				return rolevo;
			} else {
				logger.error("No roles created  in this Date : " + req.getCreatedDate());
				throw new RolesNotFoundException("No roles created  in this Date : " + req.getCreatedDate());
			}
		}
		logger.error("Please give any one input feild for filter");
		throw new InvalidInputsException("Please give any one input feild for filter");
	}

	@Override
	public String updateRole(CreateRoleRequest request) throws Exception {
		try {
			Optional<Role> roleOptional = roleRepository.findById(request.getRoleId());
			Role roleEntity = roleOptional.get();
			roleEntity.setId(request.getRoleId());
			roleEntity.setDescription(request.getDescription());
			roleEntity.setRoleName(request.getRoleName());
			// roleEntity.setLastModifyedDate(LocalDate.now());
			roleEntity.setModifiedBy(request.getCreatedBy());
			List<ParentPrivilege> parentPrivilageEntites = new ArrayList<>();
			List<SubPrivilege> subPrivilageEntites = new ArrayList<>();

			if (0L != request.getClientId()) {
				Optional<ClientDetails> client = clientDetailsrepo.findById(request.getClientId());
				if (client.isPresent()) {
					roleEntity.setClient(client.get());
				} else {
					logger.error("No Client found with this Id : " + request.getClientDomianId());
					throw new Exception("No Client  found with this Id : " + request.getClientDomianId());
				}
			} else {
				logger.error("Client  Id required");
				throw new Exception("Client Id required");
			}

			if (!CollectionUtils.isEmpty(request.getParentPrivileges())) {
				request.getParentPrivileges().forEach(a -> {
					Optional<ParentPrivilege> parentPrivilage = privilageRepository.findById(a.getId());
					if (parentPrivilage.isPresent()) {
						parentPrivilageEntites.add(parentPrivilage.get());
					} else {
						logger.error("Given privilage not found in master");
						throw new RuntimeException("Given privilage not found in master");
					}
				});
				roleEntity.setParentPrivileges(parentPrivilageEntites);
			} else {
				logger.error("Atleast one parent privillage is required");
				throw new Exception("Atleast one parent privillage is required");
			}

			if (!CollectionUtils.isEmpty(request.getSubPrivileges())) {

				request.getSubPrivileges().stream().forEach(sub -> {
					Optional<SubPrivilege> privilage = subPrivillageRepo.findById(sub.getId());
					if (privilage.isPresent()) {
						subPrivilageEntites.add(privilage.get());
					} else {
						logger.error("Given sub privilage not found in master");
						throw new RuntimeException("Given sub privilage not found in master");
					}
				});
				roleEntity.setSubPrivileges(subPrivilageEntites);
			} else {
				logger.error("Atleast one sub privillage is required");
				throw new Exception("Atleast one sub privillage is required");
			}

			roleRepository.save(roleEntity);
			cognitoClient.updateRole(request);
			logger.info("Update role method Ends");
			return "Successfully update the role";

		} catch (RuntimeException re) {
			logger.error("Error occurs while updateing the role Error : " + re.getMessage());
			throw new RuntimeException(re.getMessage());

		} catch (Exception e) {
			logger.error("Error occurs while updateing the role Error : " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public PrivilegeVO getAllPrivilagesForDomian() {
		List<ParentPrivilegeVO> listOfwebPrivillages = new ArrayList<>();
		List<ParentPrivilegeVO> listOfmobilePrivillages = new ArrayList<>();

		// List<ParentPrivilages> entity = privilageRepository.findByDomian(domian);
		PrivilegeVO privilegeVo = new PrivilegeVO();

		List<ParentPrivilege> entity = privilageRepository.findByIsActiveTrue();

		entity.stream().forEach(p -> {
			if (p.getPrevilegeType() == PrevilegeType.Web) {
				ParentPrivilegeVO parentPrivillagesVo = new ParentPrivilegeVO();
				parentPrivillagesVo.setId(p.getId());
				parentPrivillagesVo.setName(p.getName());
				parentPrivillagesVo.setDescription(p.getDescription());
				parentPrivillagesVo.setLastModifyedDate(p.getLastModifiedDate());
				parentPrivillagesVo.setCreatedDate(p.getCreatedDate());
				parentPrivillagesVo.setPrevilegeType(p.getPrevilegeType());
				List<SubPrivilege> subPrivillages = subPrivillageRepo.findByParentPrivilegeId(p.getId());
				if (!CollectionUtils.isEmpty(subPrivillages)) {
					parentPrivillagesVo.setSubPrivileges(subPrivillages);
				}

				subPrivillages.stream().forEach(s -> {

					List<ChildPrivilege> childPrivillages = childPrivilegeRepo.findBySubPrivillageId(s.getId());
					if (!CollectionUtils.isEmpty(childPrivillages)) {
						
						parentPrivillagesVo.setChildPrivillages(childPrivillages);

					}
				});

				listOfwebPrivillages.add(parentPrivillagesVo);
				privilegeVo.setWebPrivileges(listOfwebPrivillages);

			} else {

				ParentPrivilegeVO parentPrivillagesVo = new ParentPrivilegeVO();
				parentPrivillagesVo.setId(p.getId());
				parentPrivillagesVo.setName(p.getName());
				parentPrivillagesVo.setDescription(p.getDescription());
				parentPrivillagesVo.setLastModifyedDate(p.getLastModifiedDate());
				parentPrivillagesVo.setCreatedDate(p.getCreatedDate());
				parentPrivillagesVo.setPrevilegeType(p.getPrevilegeType());
				List<SubPrivilege> subPrivillages = subPrivillageRepo.findByParentPrivilegeId(p.getId());
				if (!CollectionUtils.isEmpty(subPrivillages)) {
					parentPrivillagesVo.setSubPrivileges(subPrivillages);
				}

				subPrivillages.stream().forEach(s -> {

					List<ChildPrivilege> childPrivillages = childPrivilegeRepo.findBySubPrivillageId(s.getId());
					if (!CollectionUtils.isEmpty(childPrivillages)) {

						parentPrivillagesVo.setChildPrivillages(childPrivillages);

					}
				});

				listOfmobilePrivillages.add(parentPrivillagesVo);
				privilegeVo.setMobilePrivileges(listOfmobilePrivillages);

			}

		});
		logger.info("############### getAllPrivilages method ends ###################");
		return privilegeVo;
	}

	@Override
	public String deletePrevileges(Long id) {

		Optional<ParentPrivilege> parentId = privilageRepository.findById(id);

		if (parentId.isPresent()) {

			privilageRepository.deleteById(parentId.get().getId());

			List<SubPrivilege> parentPrivillageIds = subPrivillageRepo.findByParentPrivilegeId(parentId.get().getId());

			parentPrivillageIds.stream().forEach(p -> {

				List<ChildPrivilege> subPrivillageIds = childPrivilegeRepo.findBySubPrivillageId(p.getId());
				childPrivilegeRepo.deleteInBatch(subPrivillageIds);
			});

			subPrivillageRepo.deleteInBatch(parentPrivillageIds);
		} else {

			throw new RecordNotFoundException("Parent Id Not Exits", 400);

		}

		return "Privileges deleted Successfully";
	}

	@Override
	public List<ChildPrivilege> getChildPrivileges(long subPrivillageId) throws Exception {

		if (0L != subPrivillageId) {
			List<ChildPrivilege> childPrevilages = childPrivilegeRepo.findBySubPrivillageId(subPrivillageId);

			if (!(CollectionUtils.isEmpty(childPrevilages))) {

				return childPrevilages;

			} else {

				throw new Exception("No childprivillages found");
			}
		} else {

			throw new Exception("subPrivillageId should not be null");
		}
	}
}
