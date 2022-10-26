package com.otsi.retail.authservice.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.ClientUsers;
import com.otsi.retail.authservice.Entity.Domain_Master;
import com.otsi.retail.authservice.Entity.PlanDetails;
import com.otsi.retail.authservice.Entity.PlanTransactionDetails;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.Entity.UserAv;
import com.otsi.retail.authservice.Entity.UserDetails;
import com.otsi.retail.authservice.Exceptions.BusinessException;
import com.otsi.retail.authservice.Exceptions.PlanExpirationException;
import com.otsi.retail.authservice.Exceptions.RecordNotFoundException;
import com.otsi.retail.authservice.Repository.ChannelRepo;
import com.otsi.retail.authservice.Repository.ClientDetailsRepo;
import com.otsi.retail.authservice.Repository.ClientUserRepo;
import com.otsi.retail.authservice.Repository.Domian_MasterRepo;
import com.otsi.retail.authservice.Repository.PlanTransactionRepo;
import com.otsi.retail.authservice.Repository.PlandetailsRepo;
import com.otsi.retail.authservice.Repository.StoreRepo;
import com.otsi.retail.authservice.Repository.UserAvRepo;
import com.otsi.retail.authservice.Repository.UserRepository;
import com.otsi.retail.authservice.mapper.ClientMapper;
import com.otsi.retail.authservice.mapper.userDetailsMapper;
import com.otsi.retail.authservice.requestModel.ClientDetailsVO;
import com.otsi.retail.authservice.requestModel.ClientDomianVo;
import com.otsi.retail.authservice.requestModel.ClientMappingVO;
import com.otsi.retail.authservice.requestModel.ClientSearchVO;
import com.otsi.retail.authservice.requestModel.MasterDomianVo;
import com.otsi.retail.authservice.requestModel.UserDetailsVO;
import com.otsi.retail.authservice.utils.CognitoAtributes;
import com.otsi.retail.authservice.utils.Config;
import com.otsi.retail.authservice.utils.DateConverters;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class ClientAndDomianServiceImpl implements ClientAndDomianService {

	@Autowired
	private ChannelRepo clientChannelRepository;
	
	@Value("${spring.mail.username}")
	private String from;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Config config;

	@Autowired
	private UserAvRepo userAvRepo;

	@Autowired
	private PlandetailsRepo planDetailsRepo;

	@Autowired
	private ClientDetailsRepo clientDetailsRepository;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Domian_MasterRepo domian_MasterRepo;

	@Autowired
	private ClientMapper clientMapper;

	@Autowired
	private CognitoClient cognitoClient;
	
	@Autowired
	private PlanTransactionRepo planTransactionRepo;
	
	@Autowired
	private	userDetailsMapper userMapper;

	@Autowired
	private ClientUserRepo clientUserRepo;

	@Autowired
	private StoreRepo storeRepo;

	public static final String TICKET_MAIL_SUBJECT = "Client Creation";
	public static final String TICKET_MAIL_BODY = "Client registered successfully please contact admin team ";

	// private Logger logger = LogManager.getLogger(CognitoClient.class);

	@Override
	public String createMasterDomain(MasterDomianVo domainVo) throws Exception {
		Domain_Master domain = new Domain_Master();
		try {
			domain.setChannelName(domainVo.getDomainName());
			domain.setDiscription(domainVo.getDiscription());
			Domain_Master savedChannel = domian_MasterRepo.save(domain);
			return "Channel created with Id : " + savedChannel.getId();

		} catch (Exception e) {
			// logger.debug(e.getMessage());
			// logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Domain_Master> getMasterDomains() {
		List<Domain_Master> domains = domian_MasterRepo.findAll();
		if (CollectionUtils.isEmpty(domains)) {
			// logger.error("No master domians present in DB ");
			throw new RecordNotFoundException(BusinessException.RNF_DESCRIPTION,
					BusinessException.RECORD_NOT_FOUND_STATUSCODE);
		}
		return domains;
	}

	@Override
	@Transactional(rollbackOn = { Exception.class })
	public ClientDetails createClient(ClientDetailsVO clientDetailsVO) throws RazorpayException {
		boolean clientExists = clientDetailsRepository.existsByName(clientDetailsVO.getName());

		if (!clientExists) {
			try {
				ClientDetails clientDetails = clientMapper.convertVOToEntity(clientDetailsVO);
				/*clientDetails.setName(clientDetailsVO.getName());
				clientDetails.setAddress(clientDetailsVO.getAddress());
				clientDetails.setCreatedBy(clientDetailsVO.getCreatedBy());
				clientDetails.setOrganizationName(clientDetailsVO.getOrganizationName());
				clientDetails.setMobile(clientDetailsVO.getMobile());
				clientDetails.setIsTaxIncluded(clientDetailsVO.getIsTaxIncluded());
				clientDetails.setIsEsSlipEnabled(clientDetailsVO.getIsEsSlipEnabled());
				clientDetails.setPlanTenure(clientDetailsVO.getPlanTenure());
				clientDetails.setDescription(clientDetailsVO.getDescription());
				clientDetails.setEmail(clientDetailsVO.getEmail());
				clientDetails.setActive(Boolean.TRUE);*/
				if (ObjectUtils.isNotEmpty(clientDetailsVO.getPlanId())) {
					Optional<PlanDetails> plans = planDetailsRepo.findById(clientDetailsVO.getPlanId());
					if (plans.isPresent()) {
						clientDetails.setPlanDetails(plans.get());
					}
				}
				RazorpayClient razorpay = new RazorpayClient(config.getKey(), config.getSecert());

				Payment payment = razorpay.Payments.fetch(clientDetailsVO.getRayzorPayPaymentId());

				clientDetails.setAmount(clientDetailsVO.getAmount());
				clientDetails.setRazorPayPaymentId(clientDetailsVO.getRayzorPayPaymentId());
				clientDetails = clientDetailsRepository.save(clientDetails);
				savePlanTransactionDetails(clientDetails);
				  if (null != clientDetails.getId()) { 
					  sendEmail(clientDetailsVO.getEmail(),TICKET_MAIL_BODY, TICKET_MAIL_SUBJECT); 
					  }
				 
				return clientDetails;

			} catch (RazorpayException e) {
				// Handle Exception
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid Payment Id");
			}

		} else {
			// logger.error("client name already exists: " + clientDetailsVO.getName());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"client name already exists:" + clientDetailsVO.getName());
		}
	}

	private void savePlanTransactionDetails(ClientDetails clientDetails) {
		PlanTransactionDetails planDetails = new PlanTransactionDetails();
		planDetails.setClientId(clientDetails.getId());
		planDetails.setPlanName(clientDetails.getPlanDetails().getPlanName());
		planDetails.setPlanTenure(clientDetails.getPlanTenure());
		planDetails.setAmount(clientDetails.getAmount());
		planDetails.setPlanActivationDate(clientDetails.getPlanActivationDate());
		planDetails.setPlanExpiryDate(clientDetails.getPlanExpiryDate());
		planTransactionRepo.save(planDetails);
	}

	private void sendEmail(String toEmail, String body, String subject) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		message.setFrom(from);
		mailSender.send(message);
		// logger.info("mail sent sucessfully");
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
						// logger.error("No client details found with this Client Id : " +
						// domianVo.getClientId());
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
						// logger.error("Master Domian not found with this Id : " +
						// domianVo.getMasterDomianId());
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
						// logger.error("Master Domian not found with this Id : " +
						// domianVo.getMasterDomianId());
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
				// logger.error(e.getMessage());
				throw new RuntimeException(e.getMessage());
			}
		} else {
			// logger.error("This Domian already assigned to client");
			throw new RuntimeException("This Domian already assigned to client");

		}
	}

	@Override
	public ClientDetails getClient(long clientId) throws Exception {
		Optional<ClientDetails> client = clientDetailsRepository.findById(clientId);
		if (client.isPresent()) {
			return client.get();
		} else {
			
			// logger.error("No Client found with this Id : " + clientId);
			throw new RecordNotFoundException("No Client found with this Id : " + clientId,
					BusinessException.RECORD_NOT_FOUND_STATUSCODE);

		}
	}

	@Override
	public Page<ClientDetailsVO> getAllClient(Pageable pageable) throws Exception {
		Page<ClientDetails> clients = clientDetailsRepository.findAllByOrderByCreatedDateDesc(pageable);
		if (clients.hasContent()) {

			Page<ClientDetailsVO> clientsVo = clientMapper.convertListEntityToVo(clients);
			return clientsVo;
		}
		return Page.empty();
	}

	@Override
	public List<ClientDomains> getDomainsForClient(long clientId) {
		List<ClientDomains> clientDomians = clientChannelRepository.findByClient_Id(clientId);
		if (!CollectionUtils.isEmpty(clientDomians)) {
			return clientDomians;
		} else {
			// logger.error("No domian found with this Client :" + clientId);
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
			// logger.error("Client domian not found with this Id : " + clientDomianId);
			throw new RecordNotFoundException("Client domian not found with this Id : " + clientDomianId,
					BusinessException.RECORD_NOT_FOUND_STATUSCODE);
		}
	}

	@Override
	public String clientMapping(ClientMappingVO clientMappingVo) {

		if ((ObjectUtils.isNotEmpty(clientMappingVo))) {

			clientMappingVo.getClientIds().stream().forEach(clientId -> {

				/*
				 * List<ClientUsers> clientsUsers =
				 * clientUserRepo.findByClientId_Id(clientId.getId()); if
				 * (!CollectionUtils.isEmpty(clientsUsers)) { throw new
				 * ResponseStatusException(HttpStatus.BAD_REQUEST,
				 * "support person already mapped to this client "); }
				 */

				clientMappingVo.getUserIds().stream().forEach(userId -> {
					
					List<ClientUsers> clientsUsers = clientUserRepo.findByClientId_IdAndUserId_IdAndStatus(clientId.getId(),userId.getId(),Boolean.TRUE);
					if(CollectionUtils.isEmpty(clientsUsers)) {
	

					ClientUsers clientUsers = new ClientUsers();

					clientUsers.setCreatedBy(clientMappingVo.getCreatedBy());
					clientUsers.setModifiedBy(clientMappingVo.getModifiedBy());
					clientUsers.setUserId(userId);
					clientUsers.setClientId(clientId);
					clientUsers.setStatus(Boolean.TRUE);
					clientUserRepo.save(clientUsers);
					
					
					}
					

				});
				
		ClientDetails client =	saveClientExpiryAndActivationDate(clientId.getId());
		clientDetailsRepository.save(client);				
			});

			return "clientMapped successfully";

		} else

			throw new RuntimeException("client not assinged to clientSupport");
	}

	private ClientDetails saveClientExpiryAndActivationDate(Long id) {
		Optional<ClientDetails> clientDetails = clientDetailsRepository.findById(id);
		ClientDetails client = clientDetails.get();
		client.setPlanActivationDate(LocalDateTime.now());
		
		 switch(client.getPlanTenure()){    
		   
		    
		    case "OneMonth": 
		    	client.setPlanExpiryDate(LocalDateTime.now().plusMonths(1));

		    break;
		    case "ThreeMonths": 
		    	client.setPlanExpiryDate(LocalDateTime.now().plusMonths(3));

			    break;    
		    case "sixMonths": 
		    	client.setPlanExpiryDate(LocalDateTime.now().plusMonths(6));

		    break; 
		    case "OneYear": 
		    	client.setPlanExpiryDate(LocalDateTime.now().plusMonths(12));

		    	
		    break; 
		    default:
		    	
		    }
		 
		 return client;
		// clientDetailsRepository.save(client);
		
		
	}

	@Override
	public Page<ClientDetailsVO> clientSerach(ClientSearchVO clientSearchVo, Pageable pageable) {
		try {
			if (clientSearchVo.getClientName() != null && clientSearchVo.getClientName().length() >= 3
					&& clientSearchVo.getFromDate() != null && clientSearchVo.getToDate() != null) {
				LocalDateTime createdDatefrom = DateConverters
						.convertLocalDateToLocalDateTime(clientSearchVo.getFromDate());
				LocalDateTime createdDateTo = DateConverters.convertToLocalDateTimeMax(clientSearchVo.getToDate());

				Page<ClientDetails> clientdetails = clientDetailsRepository.findByNameAndCreatedDateBetween(
						clientSearchVo.getClientName(), createdDatefrom, createdDateTo, pageable);

				Page<ClientDetailsVO> clientVo = clientMapper.convertListEntityToVo(clientdetails);
				return clientVo;

			} else if (clientSearchVo.getClientName() != null && clientSearchVo.getClientName().length() >= 3
					&& clientSearchVo.getFromDate() != null) {
				List<Store> stores = storeRepo.findByName(clientSearchVo.getStoreName());

				LocalDateTime createdDatefrom = DateConverters
						.convertLocalDateToLocalDateTime(clientSearchVo.getFromDate());
				LocalDateTime createdDateTo = DateConverters.convertToLocalDateTimeMax(clientSearchVo.getFromDate());
				Page<ClientDetails> clientdetails = clientDetailsRepository.findByNameAndCreatedDateBetween(
						clientSearchVo.getClientName(), createdDatefrom, createdDateTo, pageable);
				Page<ClientDetailsVO> clientVo = clientMapper.convertListEntityToVo(clientdetails);
				return clientVo;

			} else if (clientSearchVo.getClientName() != null && clientSearchVo.getClientName().length() >= 3) {
				Page<ClientDetails> clientdetails = clientDetailsRepository.findByName(clientSearchVo.getClientName(),
						pageable);
				Page<ClientDetailsVO> clientVo = clientMapper.convertListEntityToVo(clientdetails);
				return clientVo;

			} else if(clientSearchVo.getFromDate() != null && clientSearchVo.getToDate() != null) {
				LocalDateTime createdDatefrom = DateConverters
						.convertLocalDateToLocalDateTime(clientSearchVo.getFromDate());
				LocalDateTime createdDateTo = DateConverters.convertToLocalDateTimeMax(clientSearchVo.getToDate());
				Page<ClientDetails> clientdetails = clientDetailsRepository.findByCreatedDateBetween(createdDatefrom,
						createdDateTo, pageable);
				if (!clientdetails.hasContent()) {
					return Page.empty();
				}
				Page<ClientDetailsVO> clientVo = clientMapper.convertListEntityToVo(clientdetails);
				return clientVo;

			}else if(clientSearchVo.getFromDate() != null && clientSearchVo.getToDate() == null) {
				LocalDateTime createdDatefrom = DateConverters
						.convertLocalDateToLocalDateTime(clientSearchVo.getFromDate());
				LocalDateTime createdDateTo = DateConverters.convertToLocalDateTimeMax(clientSearchVo.getFromDate());
				Page<ClientDetails> clientdetails = clientDetailsRepository.findByCreatedDateBetween(createdDatefrom,
						createdDateTo, pageable);
				if (!clientdetails.hasContent()) {
					return Page.empty();
				}
				Page<ClientDetailsVO> clientVo = clientMapper.convertListEntityToVo(clientdetails);
				return clientVo;

			}
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());

		}
		return Page.empty();

	}

	@Override
	public List<ClientDetailsVO> getClientsForUser(Long userId) {
		List<ClientDetails> clientDetails = new ArrayList<>();
		List<ClientUsers> clientUsers = clientUserRepo.findByUserId_IdAndStatus(userId,Boolean.TRUE);
		clientUsers.stream().forEach(clientId -> {
			Long clientid = clientId.getClientId().getId();
			Optional<ClientDetails> clients = clientDetailsRepository.findById(clientid);
			if (clients.isPresent()) {
				clientDetails.add(clients.get());
			}

		});

		if (!CollectionUtils.isEmpty(clientDetails)) {
			List<ClientDetailsVO> clientVo = clientMapper.convertListEntityToVo(clientDetails);

			return clientVo;
		}
		return Collections.EMPTY_LIST;

	}

	@Override
	public Page<ClientMappingVO> getClientMappingDetails(Pageable pageable) {

		Page<ClientUsers> clientUsers = clientUserRepo.findByStatusOrderByCreatedDateDesc(Boolean.TRUE,pageable);

		return clientUsers.map(user -> clientMappingDetails(user));

	}

	private ClientMappingVO clientMappingDetails(ClientUsers clientuser) {
		ClientMappingVO clientMappingList = new ClientMappingVO();
		Map<Long, String> map = new HashMap<>();
		ClientMappingVO vo = new ClientMappingVO();

		Long clientId = clientuser.getClientId().getId();
		String clientname = clientuser.getClientId().getName();
		map.put(clientId, clientname);

		Long userId = clientuser.getUserId().getId();
		Optional<UserDetails> users = userRepository.findById(userId);
		if (users.isPresent()) {
			vo.setSupporterName(users.get().getUserName());
			vo.setUserId(users.get().getId());
			Optional<UserDetails> userDetails = userRepository.findById(clientuser.getCreatedBy());
if(userDetails.isPresent()) {
	vo.setMappingBy(userDetails.get().getUserName());
}
			vo.setCreatedBy(clientuser.getCreatedBy());
			vo.setCreatedOn(clientuser.getCreatedDate().toLocalDate());
			List<UserAv> usersList = userAvRepo.findByUserDataId(userId);
			usersList.stream().forEach(user -> {
				if (user.getName().equalsIgnoreCase(CognitoAtributes.EMAIL)) {
					vo.setEmail(user.getStringValue());
				}

			});
			if (map.containsKey(clientId)) {
				vo.setClientName(map.get(clientId));
				vo.setClientId(clientId);
			}

		}

		// });

		return vo;
	}

	private Page<ClientMappingVO> getClientsearchDetails(Page<ClientUsers> clientUsers) {

		return clientUsers.map(user -> clientMappingDetails(user));

	}

	@Override
	public Page<ClientMappingVO> getClientMappingSearchDetails(ClientMappingVO clientMappingVo, Pageable pageable) {
		try {
			if (clientMappingVo.getClientName() != null && clientMappingVo.getClientName().length() >= 3
					&& clientMappingVo.getSupporterName() == null && clientMappingVo.getFromDate() != null
					&& clientMappingVo.getToDate() != null) {

				Page<ClientDetails> clients = clientDetailsRepository.findByName(clientMappingVo.getClientName(),
						pageable);

				if (clients.hasContent()) {
					LocalDateTime createdDatefrom = DateConverters
							.convertLocalDateToLocalDateTime(clientMappingVo.getFromDate());
					LocalDateTime createdDateTo = DateConverters.convertToLocalDateTimeMax(clientMappingVo.getToDate());

					List<Long> clientIds = clients.stream().map(client -> client.getId()).collect(Collectors.toList());
					if (clientIds != null) {
						Page<ClientUsers> clientUsers = clientUserRepo.findByClientId_IdInAndStatusAndCreatedDateBetween(
								clientIds,Boolean.TRUE, createdDatefrom, createdDateTo, pageable);
						if (clientUsers.hasContent()) {

							Page<ClientMappingVO> clientMappingList = getClientsearchDetails(clientUsers);
							return clientMappingList;

						}
					}

				}
			}
			if (clientMappingVo.getClientName() == null && clientMappingVo.getSupporterName() != null
					&& clientMappingVo.getFromDate() != null && clientMappingVo.getToDate() != null) {

				Page<UserDetails> users = userRepository.findByUserName(clientMappingVo.getSupporterName(),pageable);
				if (users.hasContent()) {
					LocalDateTime createdDatefrom = DateConverters
							.convertLocalDateToLocalDateTime(clientMappingVo.getFromDate());
					LocalDateTime createdDateTo = DateConverters.convertToLocalDateTimeMax(clientMappingVo.getToDate());

					List<Long> userIds = users.stream().map(user -> user.getId()).collect(Collectors.toList());
					if (userIds != null) {
						Page<ClientUsers> clientUsers = clientUserRepo.findByUserId_IdInAndStatusAndCreatedDateBetween(userIds,Boolean.TRUE,
								createdDatefrom, createdDateTo, pageable);
						if (clientUsers.hasContent()) {

							Page<ClientMappingVO> clientMappingList = getClientsearchDetails(clientUsers);
							return clientMappingList;

						}
					}

				}
			} else if ((clientMappingVo.getClientName() != null || clientMappingVo.getSupporterName() != null)
					&& clientMappingVo.getFromDate() != null) {

				if (clientMappingVo.getClientName() != null) {
					Page<ClientDetails> clients = clientDetailsRepository.findByName(clientMappingVo.getClientName(),
							pageable);
					if (clients.hasContent()) {

						LocalDateTime createdDatefrom = DateConverters
								.convertLocalDateToLocalDateTime(clientMappingVo.getFromDate());
						LocalDateTime createdDateTo = DateConverters
								.convertToLocalDateTimeMax(clientMappingVo.getFromDate());

						List<Long> clientIds = clients.stream().map(client -> client.getId())
								.collect(Collectors.toList());
						if (clientIds != null) {

							Page<ClientUsers> clientUsers = clientUserRepo.findByClientId_IdInAndStatusAndCreatedDateBetween(
									clientIds,Boolean.TRUE, createdDatefrom, createdDateTo, pageable);

							if (clientUsers.hasContent()) {

								Page<ClientMappingVO> clientMappingList = getClientsearchDetails(clientUsers);
								return clientMappingList;
							}
						}

					}

				} else {

					Page<UserDetails> users = userRepository.findByUserName(clientMappingVo.getSupporterName(),pageable);

					if (users.hasContent()) {

						LocalDateTime createdDatefrom = DateConverters
								.convertLocalDateToLocalDateTime(clientMappingVo.getFromDate());
						LocalDateTime createdDateTo = DateConverters
								.convertToLocalDateTimeMax(clientMappingVo.getFromDate());

						List<Long> userIds = users.stream().map(user -> user.getId()).collect(Collectors.toList());
						if (userIds != null) {

							Page<ClientUsers> clientUsers = clientUserRepo.findByUserId_IdInAndStatusAndCreatedDateBetween(
									userIds,Boolean.TRUE, createdDatefrom, createdDateTo, pageable);

							if (clientUsers.hasContent()) {

								Page<ClientMappingVO> clientMappingList = getClientsearchDetails(clientUsers);
								return clientMappingList;
							}
						}

					}
				}

			} else if ((clientMappingVo.getClientName() != null && clientMappingVo.getClientName().length() >= 3)
					|| (clientMappingVo.getSupporterName() != null
							&& clientMappingVo.getSupporterName().length() >= 3)) {
				if (StringUtils.isNotEmpty(clientMappingVo.getClientName())) {

					Page<ClientDetails> clients = clientDetailsRepository.findByName(clientMappingVo.getClientName(),
							pageable);
					if (clients.hasContent()) {

						List<Long> clientIds = clients.stream().map(client -> client.getId())
								.collect(Collectors.toList());
						if (clientIds != null) {

							Page<ClientUsers> clientUsers = clientUserRepo.findByClientId_IdInAndStatus(clientIds,Boolean.TRUE, pageable);

							if (clientUsers.hasContent()) {

								Page<ClientMappingVO> clientMappingList = getClientsearchDetails(clientUsers);
								return clientMappingList;
							}
						}
					}
				} else {

					Page<UserDetails> users = userRepository.findByUserName(clientMappingVo.getSupporterName(),pageable);

					if (users.hasContent()) {

						List<Long> userIds = users.stream().map(user -> user.getId()).collect(Collectors.toList());
						if (userIds != null) {

							Page<ClientUsers> clientUsers = clientUserRepo.findByUserId_IdInAndStatus(userIds,Boolean.TRUE, pageable);

							if (clientUsers.hasContent()) {

								Page<ClientMappingVO> clientMappingList = getClientsearchDetails(clientUsers);
								return clientMappingList;
							}
						}

					}
				}

			} else if(clientMappingVo.getFromDate() != null && clientMappingVo.getToDate() != null) {
				LocalDateTime createdDatefrom = DateConverters
						.convertLocalDateToLocalDateTime(clientMappingVo.getFromDate());
				LocalDateTime createdDateTo = DateConverters.convertToLocalDateTimeMax(clientMappingVo.getToDate());
				Page<ClientUsers> clientUsers = clientUserRepo.findByCreatedDateBetweenAndStatus(createdDatefrom, createdDateTo,Boolean.TRUE,
						pageable);
				if (!clientUsers.hasContent()) {
					return Page.empty();
				}
				Page<ClientMappingVO> clientMappingList = getClientsearchDetails(clientUsers);
				return clientMappingList;

			}else if(clientMappingVo.getFromDate() != null && clientMappingVo.getToDate() == null) {
				
				LocalDateTime createdDatefrom = DateConverters
						.convertLocalDateToLocalDateTime(clientMappingVo.getFromDate());
				LocalDateTime createdDateTo = DateConverters
						.convertToLocalDateTimeMax(clientMappingVo.getFromDate());
				Page<ClientUsers> clientUsers = clientUserRepo.findByCreatedDateBetweenAndStatus(createdDatefrom, createdDateTo,Boolean.TRUE,
						pageable);
				if (!clientUsers.hasContent()) {
					return Page.empty();
				}
				Page<ClientMappingVO> clientMappingList = getClientsearchDetails(clientUsers);
				return clientMappingList;

		}
			}catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());

		}
		return Page.empty();
	}

	@Override
	public String editClientMapping(ClientMappingVO clientMappingVo) {
		clientMappingVo.getClientIds().stream().forEach(clientId -> {
			clientMappingVo.getUserIds().stream().forEach(userId -> {


			List<ClientUsers> clientsUsers = clientUserRepo.findByClientId_Id(clientId.getId());
			if (CollectionUtils.isEmpty(clientsUsers)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "support person not mapped to this client ");
			}

			/*clientMappingVo.getUserIds().stream().forEach(userId -> {*/

				clientsUsers.stream().forEach(clientUsers -> {

					clientUsers.setCreatedBy(clientMappingVo.getCreatedBy());
					clientUsers.setModifiedBy(clientMappingVo.getModifiedBy());
					clientUsers.setUserId(userId);
					clientUsers.setClientId(clientId);
					clientUserRepo.save(clientUsers);
					

				});
			});
		});
	//	});

		return "clientEdit successfully";

	}

	@Override
	public String editClient(ClientDetailsVO clientDetailsVO) {
		
	Optional<ClientDetails> clients =	clientDetailsRepository.findById(clientDetailsVO.getId());
	if(clients.isPresent()) {
		ClientDetails client = clients.get();
		//client = clientMapper.convertVOToEntity(clientDetailsVO);
		client.setActive(clientDetailsVO.isActive());
		client.setAddress(clientDetailsVO.getAddress());
		client.setAmount(clientDetailsVO.getAmount());
		client.setCreatedBy(clientDetailsVO.getCreatedBy());
		client.setDescription(clientDetailsVO.getDescription());
		client.setEmail(clientDetailsVO.getEmail());
		client.setMobile(clientDetailsVO.getMobile());
client.setName(clientDetailsVO.getName());	
client.setOrganizationName(clientDetailsVO.getOrganizationName());
client.setPlanTenure(clientDetailsVO.getPlanTenure());
if (ObjectUtils.isNotEmpty(clientDetailsVO.getPlanId())) {
	Optional<PlanDetails> plans = planDetailsRepo.findById(clientDetailsVO.getPlanId());
	if (plans.isPresent()) {
		client.setPlanDetails(plans.get());
	}
}
//client.setPlanDetails(clientDetailsVO.getPlandetails());
ClientDetails client1=saveClientExpiryAndActivationDate(clientDetailsVO.getId());
client.setPlanActivationDate(client1.getPlanActivationDate());
client.setPlanExpiryDate(client1.getPlanExpiryDate());
clientDetailsRepository.save(client);
savePlanTransactionDetails(client);
return "clientUpdatedSuceesfully";


	}
	throw new RuntimeException("client details not found with this id");
		}

	@Override
	public String deleteClient(ClientMappingVO clientMappingVo) {
		
		if ((ObjectUtils.isNotEmpty(clientMappingVo))) {

			clientMappingVo.getClientIds().stream().forEach(clientId -> {

				

				clientMappingVo.getUserIds().stream().forEach(userId -> {
					
					List<ClientUsers> clientsUsers = clientUserRepo.findByClientId_IdAndUserId_Id(clientId.getId(),userId.getId());
					if(!CollectionUtils.isEmpty(clientsUsers)) {
						clientsUsers.stream().forEach(clientUser->{
							
						

					
							clientUser.setStatus(Boolean.FALSE);
					clientUserRepo.save(clientUser);
						});
					}
					

				});
			});

			return "clientMapped deleted successfully";

		} else

			throw new RuntimeException("client not assinged to clientSupport");	}

	@Override
	public List<UserDetailsVO> getUsersForClient(Long clientId) {
		List<UserDetails> userDetails = new ArrayList<>();
		List<ClientUsers> clientUsers = clientUserRepo.findByClientId_IdAndStatus(clientId,Boolean.TRUE);
		clientUsers.stream().forEach(userId -> {
			Long userid = userId.getUserId().getId();
			Optional<UserDetails> users = userRepository.findById(userid);
			if (users.isPresent()) {
				userDetails.add(users.get());
			}

		});

		if (!CollectionUtils.isEmpty(userDetails)) {
			List<UserDetailsVO> userVo = userMapper.convertListUsersDetailsToVO(userDetails);

			return userVo;
		}
		return Collections.EMPTY_LIST;
	}
	
	@Override
	public ClientDetailsVO getPlanExpiry(Long clientId) {
		Optional<ClientDetails> clientDetails = clientDetailsRepository.findById(clientId);
		ClientDetails clientDetailsEntity = clientDetails.get();
		ClientDetailsVO clientDetailsVo = new ClientDetailsVO();
		if (clientDetails.isPresent()) {
			if (LocalDateTime.now().isAfter(clientDetailsEntity.getPlanExpiryDate())) {
				throw new PlanExpirationException("Plan Expired", 404);
			} else {

				BeanUtils.copyProperties(clientDetailsEntity, clientDetailsVo);

				return clientDetailsVo;

			}
		}
		return clientDetailsVo;

	}
}
