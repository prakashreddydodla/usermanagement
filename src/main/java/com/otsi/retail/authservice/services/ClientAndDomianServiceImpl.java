package com.otsi.retail.authservice.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.Domain_Master;
import com.otsi.retail.authservice.Repository.ChannelRepo;
import com.otsi.retail.authservice.Repository.ClientDetailsRepo;
import com.otsi.retail.authservice.Repository.Domian_MasterRepo;
import com.otsi.retail.authservice.requestModel.ClientDetailsVo;
import com.otsi.retail.authservice.requestModel.ClientDomianVo;
import com.otsi.retail.authservice.requestModel.MasterDomianVo;

@Service
public class ClientAndDomianServiceImpl implements ClientAndDomianService {

	@Autowired
	private ChannelRepo clientChannelRepo;
	@Autowired
	private ClientDetailsRepo clientDetailsRepo;
	@Autowired
	private Domian_MasterRepo domian_MasterRepo;
	private Logger logger = LogManager.getLogger(CognitoClient.class);

	@Override
	public String createMasterDomain(MasterDomianVo domainVo) throws Exception {
		logger.info("############### createMasterDomain method Starts ###################");

		Domain_Master domain = new Domain_Master();
		try {
			domain.setChannelName(domainVo.getDomainName());
			domain.setDiscription(domainVo.getDiscription());
			domain.setCreatedDate(LocalDate.now());
			domain.setLastModifyedDate(LocalDate.now());

			Domain_Master savedChannel = domian_MasterRepo.save(domain);
			logger.info("############### createMasterDomain method ends ###################");

			return "Channel created with Id : " + savedChannel.getId();

		} catch (Exception e) {
			logger.debug(e.getMessage());
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Domain_Master> getMasterDomains() {
		logger.info("############### getMasterDomains method Starts ###################");

		List<Domain_Master> domains = domian_MasterRepo.findAll();
		if (CollectionUtils.isEmpty(domains)) {
			logger.debug("No master domians present in DB ");
			logger.error("No master domians present in DB ");
			throw new RuntimeException("No master domians present in DB ");
		}
		logger.info("############### getMasterDomains method ends ###################");

		return domains;
	}

	@Override
	@Transactional(rollbackOn = { Exception.class })
	public String createClient(ClientDetailsVo clientVo) throws Exception {
		logger.info("############### createClient method starts ###################");

		try {
			boolean clientExists=	clientDetailsRepo.existsByName(clientVo.getName());
			if(!clientExists) {
			ClientDetails clientEntity = new ClientDetails();
			clientEntity.setName(clientVo.getName());
			clientEntity.setAddress(clientVo.getAddress());
			clientEntity.setCreatedDate(LocalDate.now());
			clientEntity.setLastModifyedDate(LocalDate.now());
			clientEntity.setCreatedBy(clientVo.getCreatedBy());
			ClientDetails savedClient = clientDetailsRepo.save(clientEntity);
			logger.info("############### createClient method ends ###################");

			return "Client created successfully with ClientId :" + savedClient.getId();
			}else {
				logger.debug("Client Name already exists in DB : "+clientVo.getName());
				logger.error("Client Name already exists in DB : "+clientVo.getName());
				throw new RuntimeException("Client Name already exists in DB : "+clientVo.getName());
			}
		}catch (RuntimeException e) {
			 logger.debug(e.getMessage());
				logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		 catch (Exception e) {
			 logger.debug(e.getMessage());
				logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String assignDomianToClient(ClientDomianVo domianVo) {
		logger.info("############### assignDomianToClient method Starts ###################");

		boolean isExists = clientChannelRepo.existsByDomain_IdAndClientId(domianVo.getMasterDomianId(),
				domianVo.getClientId());
		if (!isExists) {
			try {

				ClientDomains clientDomians = new ClientDomains();
				clientDomians.setDomaiName(domianVo.getName());
				clientDomians.setDiscription(domianVo.getDiscription());
				clientDomians.setCreatedDate(LocalDate.now());
				clientDomians.setLastModifyedDate(LocalDate.now());
				clientDomians.setCreatedBy(domianVo.getCreatedBy());
				if (0L != domianVo.getClientId()) {
					Optional<ClientDetails> client_db = clientDetailsRepo.findById(domianVo.getClientId());
					if (client_db.isPresent()) {
						clientDomians.setClient(client_db.get());
					} else {
						logger.debug("No client details found with this Client Id : " + domianVo.getClientId());
						logger.error("No client details found with this Client Id : " + domianVo.getClientId());
						throw new RuntimeException("No client details found with this Client Id : " + domianVo.getClientId());
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
						logger.debug("Master Domian not found with this Id : " + domianVo.getMasterDomianId());
						logger.error("Master Domian not found with this Id : " + domianVo.getMasterDomianId());
						throw new RuntimeException("Master Domian not found with this Id : " + domianVo.getMasterDomianId());
					}
				} else {
					List<Domain_Master> newAssignedDomians = new ArrayList<>();
					Optional<Domain_Master> masterDomianOptional = domian_MasterRepo
							.findById(domianVo.getMasterDomianId());
					if (masterDomianOptional.isPresent()) {
						newAssignedDomians.add(masterDomianOptional.get());
						clientDomians.setDomain(newAssignedDomians);
					} else {
						logger.debug("Master Domian not found with this Id : " + domianVo.getMasterDomianId());
						logger.error("Master Domian not found with this Id : " + domianVo.getMasterDomianId());
						throw new RuntimeException("Master Domian not found with this Id : " + domianVo.getMasterDomianId());
					}
				}

				ClientDomains dbObject = clientChannelRepo.save(clientDomians);
				if (dbObject != null) {
					logger.info("############### assignDomianToClient method ends ###################");
					return "Domian assigned to client with domainId : " + dbObject.getClientDomainaId();
				} else {
					logger.debug("Domain not assinged to client");
					logger.error("Domain not assinged to client");
					throw new RuntimeException("Domain not assinged to client");
				}

			} catch (Exception e) {
				logger.debug(e.getMessage());
				logger.error(e.getMessage());
				throw new RuntimeException(e.getMessage());
			}
		} else {
			logger.debug("This Domian already assigned to client");
			logger.error("This Domian already assigned to client");
			throw new RuntimeException("This Domian already assigned to client");

		}
	}

	@Override
	public ClientDetails getClient(long clientId) throws Exception {
		logger.info("############### getClient method starts ###################");

		Optional<ClientDetails> client = clientDetailsRepo.findById(clientId);
		if (client.isPresent()) {
			logger.info("############### getClient method ends ###################");

			return client.get();
		} else {
			logger.debug("No Client found with this Id : " + clientId);
			logger.error("No Client found with this Id : " + clientId);
			throw new Exception("No Client found with this Id : " + clientId);

		}
	}

	@Override
	public List<ClientDetails> getAllClient() throws Exception {
		logger.info("############### getAllClient method starts ###################");

		List<ClientDetails> clients = clientDetailsRepo.findAll();
		if (!CollectionUtils.isEmpty(clients)) {
			logger.info("############### getAllClient method ends ###################");
			return clients;

		}
		else {
			logger.debug("No clients found");
			logger.error("No clients found");
			throw new Exception("No clients found");

		}
	}

	@Override
	public List<ClientDomains> getDomainsForClient(long clientId) {
		logger.info("############### getDomainsForClient method starts ###################");

		List<ClientDomains> clientDomians = clientChannelRepo.findByClient_Id(clientId);
		if (!CollectionUtils.isEmpty(clientDomians)) {
			logger.info("############### getDomainsForClient method ends ###################");

			return clientDomians;
		} else {
			logger.debug("No domian found with this Client :" + clientId);
			logger.error("No domian found with this Client :" + clientId);
			throw new RuntimeException("No domian found with this Client :" + clientId);
		}

	}

	@Override
	public ClientDomains getDomianById(long clientDomianId) {
		logger.info("############### getDomianById method starts ###################");

		Optional<ClientDomains> domianOptional = clientChannelRepo.findByClientDomainaId(clientDomianId);
		if (domianOptional.isPresent()) {
			logger.info("############### getDomianById method ends ###################");

			return domianOptional.get();

		} else {
			logger.debug("Client domian not found with this Id : " + clientDomianId);
			logger.error("Client domian not found with this Id : " + clientDomianId);
			throw new RuntimeException("Client domian not found with this Id : " + clientDomianId);
		}
	}

}
