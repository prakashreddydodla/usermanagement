package com.otsi.retail.authservice.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.ClientUsers;
import com.otsi.retail.authservice.Entity.Domain_Master;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.Entity.UserDetails;
import com.otsi.retail.authservice.Exceptions.BusinessException;
import com.otsi.retail.authservice.Exceptions.RecordNotFoundException;
import com.otsi.retail.authservice.Repository.ChannelRepo;
import com.otsi.retail.authservice.Repository.ClientDetailsRepo;
import com.otsi.retail.authservice.Repository.ClientUserRepo;
import com.otsi.retail.authservice.Repository.Domian_MasterRepo;
import com.otsi.retail.authservice.Repository.StoreRepo;
import com.otsi.retail.authservice.Repository.UserRepository;
import com.otsi.retail.authservice.mapper.ClientMapper;
import com.otsi.retail.authservice.requestModel.ClientDetailsVO;
import com.otsi.retail.authservice.requestModel.ClientDomianVo;
import com.otsi.retail.authservice.requestModel.ClientMappingVO;
import com.otsi.retail.authservice.requestModel.ClientSearchVO;
import com.otsi.retail.authservice.requestModel.MasterDomianVo;
import com.otsi.retail.authservice.requestModel.UpdateUserRequest;
import com.otsi.retail.authservice.utils.DateConverters;

@Service
public class ClientAndDomianServiceImpl implements ClientAndDomianService {

	@Autowired
	private ChannelRepo clientChannelRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ClientDetailsRepo clientDetailsRepository;

	@Autowired
	private Domian_MasterRepo domian_MasterRepo;
	
	@Autowired
	private ClientMapper clientMapper;
	
	@Autowired
	private CognitoClient cognitoClient;
		
	@Autowired
	private ClientUserRepo clientUserRepo;
	
	@Autowired
	private StoreRepo storeRepo;

	private Logger logger = LogManager.getLogger(CognitoClient.class);

	@Override
	public String createMasterDomain(MasterDomianVo domainVo) throws Exception {
		Domain_Master domain = new Domain_Master();
		try {
			domain.setChannelName(domainVo.getDomainName());
			domain.setDiscription(domainVo.getDiscription());
			Domain_Master savedChannel = domian_MasterRepo.save(domain);
			return "Channel created with Id : " + savedChannel.getId();

		} catch (Exception e) {
			logger.debug(e.getMessage());
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Domain_Master> getMasterDomains() {
		List<Domain_Master> domains = domian_MasterRepo.findAll();
		if (CollectionUtils.isEmpty(domains)) {
			logger.error("No master domians present in DB ");
			throw new RecordNotFoundException(BusinessException.RNF_DESCRIPTION,
					BusinessException.RECORD_NOT_FOUND_STATUSCODE);
		}
		return domains;
	}

	@Override
	@Transactional(rollbackOn = { Exception.class })
	public ClientDetails createClient(ClientDetailsVO clientDetailsVO) {
		boolean clientExists = clientDetailsRepository.existsByName(clientDetailsVO.getName());
		if (!clientExists) {
			ClientDetails clientDetails = new ClientDetails();
			clientDetails.setName(clientDetailsVO.getName());
			clientDetails.setAddress(clientDetailsVO.getAddress());
			clientDetails.setCreatedBy(clientDetailsVO.getCreatedBy());
			clientDetails.setOrganizationName(clientDetailsVO.getOrganizationName());
			clientDetails.setMobile(clientDetailsVO.getMobile());
			clientDetails.setIsTaxIncluded(clientDetailsVO.getIsTaxIncluded());
			clientDetails.setIsEsSlipEnabled(clientDetailsVO.getIsEsSlipEnabled());
			clientDetails = clientDetailsRepository.save(clientDetails);
			return clientDetails;
		} else {
			logger.error("client name already exists: " + clientDetailsVO.getName());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"client name already exists:" + clientDetailsVO.getName());
		}
	}

	@Override
	public String assignDomianToClient(ClientDomianVo domianVo) {
		boolean isExists = clientChannelRepository.existsByDomain_IdAndClientId(domianVo.getMasterDomianId(),
				domianVo.getClientId());
		if (!isExists) {
			try {
				ClientDomains clientDomians = new ClientDomains();
				clientDomians.setDomaiName(domianVo.getName());
				clientDomians.setDiscription(domianVo.getDiscription());
				clientDomians.setCreatedBy(domianVo.getCreatedBy());
				if (0L != domianVo.getClientId()) {
					Optional<ClientDetails> client_db = clientDetailsRepository.findById(domianVo.getClientId());
					if (client_db.isPresent()) {
						clientDomians.setClient(client_db.get());
					} else {
						logger.error("No client details found with this Client Id : " + domianVo.getClientId());
						throw new RecordNotFoundException(
								"No client details found with this Client Id : " + domianVo.getClientId(),
								BusinessException.RECORD_NOT_FOUND_STATUSCODE);
					}
				}
				if (null != clientDomians.getDomain()) {

					List<Domain_Master> asssingedDomians = clientDomians.getDomain();
					Optional<Domain_Master> masterDomianOptional = domian_MasterRepo
							.findById(domianVo.getMasterDomianId());
					if (masterDomianOptional.isPresent()) {
						asssingedDomians.add(masterDomianOptional.get());
						clientDomians.setDomain(asssingedDomians);
					} else {
						logger.error("Master Domian not found with this Id : " + domianVo.getMasterDomianId());
						throw new RecordNotFoundException(
								"Master Domian not found with this Id : " + domianVo.getMasterDomianId(),
								BusinessException.RECORD_NOT_FOUND_STATUSCODE);
					}
				} else {
					List<Domain_Master> newAssignedDomians = new ArrayList<>();
					Optional<Domain_Master> masterDomianOptional = domian_MasterRepo
							.findById(domianVo.getMasterDomianId());
					if (masterDomianOptional.isPresent()) {
						newAssignedDomians.add(masterDomianOptional.get());
						clientDomians.setDomain(newAssignedDomians);
					} else {
						logger.error("Master Domian not found with this Id : " + domianVo.getMasterDomianId());
						throw new RecordNotFoundException(
								"Master Domian not found with this Id : " + domianVo.getMasterDomianId(),
								BusinessException.RECORD_NOT_FOUND_STATUSCODE);
					}
				}

				ClientDomains dbObject = clientChannelRepository.save(clientDomians);
				if (dbObject != null) {
					return "Domian assigned to client with domainId : " + dbObject.getId();
				} else {
					throw new RuntimeException("Domain not assinged to client");
				}

			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new RuntimeException(e.getMessage());
			}
		} else {
			logger.error("This Domian already assigned to client");
			throw new RuntimeException("This Domian already assigned to client");

		}
	}

	@Override
	public ClientDetails getClient(long clientId) throws Exception {
		Optional<ClientDetails> client = clientDetailsRepository.findById(clientId);
		if (client.isPresent()) {
			return client.get();
		} else {
			logger.error("No Client found with this Id : " + clientId);
			throw new RecordNotFoundException("No Client found with this Id : " + clientId,
					BusinessException.RECORD_NOT_FOUND_STATUSCODE);

		}
	}

	@Override
	public List<ClientDetails> getAllClient() throws Exception {
		List<ClientDetails> clients = clientDetailsRepository.findAll();
		if (!CollectionUtils.isEmpty(clients)) {
			return clients;
		}
		return Collections.EMPTY_LIST;
	}

	@Override
	public List<ClientDomains> getDomainsForClient(long clientId) {
		List<ClientDomains> clientDomians = clientChannelRepository.findByClient_Id(clientId);
		if (!CollectionUtils.isEmpty(clientDomians)) {
			return clientDomians;
		} else {
			logger.error("No domian found with this Client :" + clientId);
			throw new RecordNotFoundException("No domian found with this Client :" + clientId,
					BusinessException.RECORD_NOT_FOUND_STATUSCODE);
		}

	}

	@Override
	public ClientDomains getDomianById(long clientDomianId) {
		Optional<ClientDomains> domianOptional = clientChannelRepository.findById(clientDomianId);
		if (domianOptional.isPresent()) {
			return domianOptional.get();
		} else {
			logger.error("Client domian not found with this Id : " + clientDomianId);
			throw new RecordNotFoundException("Client domian not found with this Id : " + clientDomianId,
					BusinessException.RECORD_NOT_FOUND_STATUSCODE);
		}
	}

	@Override
	public String clientMapping(ClientMappingVO clientMappingVo) {

		if((ObjectUtils.isNotEmpty(clientMappingVo))) {
			
			clientMappingVo.getClientIds().stream().forEach(clientId->{
				/*
				 * List<ClientUsers> client= clientUserRepo.findByClientId(clientId);
				 * if(client!=null) { throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
				 * "support person already mapped to this client "); }
				 */
				clientMappingVo.getUserIds().stream().forEach(userId->{
					ClientUsers clientUsers = new ClientUsers();

					clientUsers.setCreatedBy(clientMappingVo.getCreatedBy());
					clientUsers.setModifiedBy(clientMappingVo.getModifiedBy());
					clientUsers.setUserId(userId);
				    clientUsers.setClientId(clientId);
				    clientUserRepo.save(clientUsers);
				    
				    Optional<UserDetails> user = userRepository.findById(userId.getId());
				    		if(user.isPresent()) {
						/*
						 * UpdateUserRequest request = new UpdateUserRequest();
						 * request.setName(user.get().getUserName());
						 * request.setUsername(user.get().getUserName());
						 * request.setClientId(Long.toString(clientId.getId()));
						 */
				    			
				    			List<ClientDetails>	clients = new ArrayList<>()	;
						    	clients.add(clientId)	;
						    	String userName= user.get().getUserName();
						    try {
								cognitoClient.addClientToUser(clients,userName);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						    		}				    
				
			});
			});

			return "clientMapped successfully";

				/*clientUsers.setClientId(clientMappingVo.getClientIds());
				clientUsers.setUserId(clientMappingVo.getUserIds());*/
		}else
		
			throw new RuntimeException("client not assinged to clientSupport");
	}

	@Override
	public List<ClientDetailsVO> clientSerach(ClientSearchVO clientSearchVo) {
		/*LocalDateTime createdDateTo = null;
		LocalDateTime createdDatefrom1 = null;
		if (clientSearchVo.getFromDate() != null) {
			createdDatefrom1 = DateConverters.convertLocalDateToLocalDateTime(clientSearchVo.getFromDate());

			if (clientSearchVo.getToDate() != null) {	
				createdDateTo = DateConverters.convertToLocalDateTimeMax(clientSearchVo.getToDate());
			} else {
				createdDateTo = DateConverters.convertToLocalDateTimeMax(clientSearchVo.getFromDate());

			}
		}*/
		
		List<ClientDetails> clientDetails = new ArrayList<>();
		if(clientSearchVo.getStoreName()!= null && clientSearchVo.getFromDate()!=null && clientSearchVo.getToDate()!=null) {
			List<Store> stores = storeRepo.findByName(clientSearchVo.getStoreName());
			stores.stream().forEach(store->{
				LocalDateTime	createdDatefrom = DateConverters.convertLocalDateToLocalDateTime(clientSearchVo.getFromDate());
				LocalDateTime	createdDateTo = DateConverters.convertToLocalDateTimeMax(clientSearchVo.getToDate());

				Long clientId = store.getClient().getId();
				ClientDetails clientdetail= clientDetailsRepository.findByIdAndCreatedDateBetween(clientId,createdDatefrom,createdDateTo);
				
					clientDetails.add(clientdetail);
				
			});
		}else if(clientSearchVo.getStoreName()!= null && clientSearchVo.getFromDate()!=null) {
			List<Store> stores = storeRepo.findByName(clientSearchVo.getStoreName());
			stores.stream().forEach(store->{
				LocalDateTime	createdDatefrom = DateConverters.convertLocalDateToLocalDateTime(clientSearchVo.getFromDate());
				LocalDateTime createdDateTo = DateConverters.convertToLocalDateTimeMax(clientSearchVo.getFromDate());

				Long clientId = store.getClient().getId();
				ClientDetails clientdetail= clientDetailsRepository.findByIdAndCreatedDateBetween(clientId,createdDatefrom,createdDateTo);
				
					clientDetails.add(clientdetail);
				
			});
			
		}else if(clientSearchVo.getStoreName()!=null) {
			List<Store> stores = storeRepo.findByName(clientSearchVo.getStoreName());
			stores.stream().forEach(store->{
				 
				Long clientId = store.getClient().getId();
				Optional<ClientDetails> clientdetail= clientDetailsRepository.findById(clientId);
				if(clientdetail.isPresent()) {
					clientDetails.add(clientdetail.get());
				}
				
			});
			
		}else {
			LocalDateTime	createdDatefrom = DateConverters.convertLocalDateToLocalDateTime(clientSearchVo.getFromDate());
			LocalDateTime	createdDateTo = DateConverters.convertToLocalDateTimeMax(clientSearchVo.getToDate());
	List<ClientDetails>	 clientdetails= clientDetailsRepository.findByCreatedDateBetween(createdDatefrom,createdDateTo);
	clientdetails.stream().forEach(client->{
		clientDetails.add(client);

		
		
	});
	
		
		}
		List<ClientDetailsVO> clientVo = clientMapper.convertListEntityToVo(clientDetails);
		return clientVo;
	}

}
