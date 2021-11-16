package com.otsi.retail.authservice.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.Domain_Master;
import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Repository.ChannelRepo;
import com.otsi.retail.authservice.Repository.ClientDetailsRepo;
import com.otsi.retail.authservice.Repository.Domian_MasterRepo;
import com.otsi.retail.authservice.requestModel.ClientDetailsVo;
import com.otsi.retail.authservice.requestModel.ClientDomianVo;
import com.otsi.retail.authservice.requestModel.MasterDomianVo;

@Service
public class ClientAndDomianServiceImpl implements ClientAndDomianService {

	@Autowired
	private CognitoClient cognitoClient;
	@Autowired
	private ChannelRepo clientChannelRepo;
	@Autowired
	private ClientDetailsRepo clientDetailsRepo;
	@Autowired
	private Domian_MasterRepo domian_MasterRepo;

	@Override
	public String createMasterDomain(MasterDomianVo domainVo) throws Exception {
		Domain_Master domain = new Domain_Master();
		try {
			domain.setChannelName(domainVo.getDomainName());
			domain.setDiscription(domainVo.getDiscription());
			domain.setCreatedDate(LocalDate.now());
			domain.setLastModifyedDate(LocalDate.now());

			Domain_Master savedChannel = domian_MasterRepo.save(domain);
			return "Channel created with Id : " + savedChannel.getId();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Domain_Master> getMasterDomains() {
		List<Domain_Master> domains = domian_MasterRepo.findAll();
		if (CollectionUtils.isEmpty(domains)) {
			throw new RuntimeException("No master domians present in DB ");
		}
		return domains;
	}

	@Override
	@Transactional(rollbackOn = { Exception.class })
	public String createClient(ClientDetailsVo clientVo) throws Exception {
		try {
			ClientDetails clientEntity = new ClientDetails();
			clientEntity.setName(clientVo.getName());
			clientEntity.setAddress(clientVo.getAddress());
			clientEntity.setCreatedDate(LocalDate.now());
			clientEntity.setLastModifyedDate(LocalDate.now());
			clientEntity.setCreatedBy(clientVo.getCreatedBy());
			ClientDetails savedClient = clientDetailsRepo.save(clientEntity);
			return "Client created successfully with ClientId :" + savedClient.getId();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String assignDomianToClient(ClientDomianVo domianVo) {

		boolean isExists = clientChannelRepo.existsByDomain_IdAndClientId(domianVo.getMasterDomianId(),
				domianVo.getClientId());
		if (!isExists) {
			try {

				ClientDomains clientDomians = new ClientDomains();
				clientDomians.setDomaiName(domianVo.getName());
				clientDomians.setDiscription(domianVo.getDiscription());
				clientDomians.setCreatedDate(LocalDate.now());
				clientDomians.setLastModifyedDate(LocalDate.now());
				if (0L != domianVo.getClientId()) {
					Optional<ClientDetails> client_db = clientDetailsRepo.findById(domianVo.getClientId());
					if (client_db.isPresent()) {
						clientDomians.setClient(client_db.get());
					} else {
						throw new RuntimeException(
								"No client details found with this Client Id : " + domianVo.getClientId());
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
						throw new RuntimeException(
								"Master Domian not found with this Id : " + domianVo.getMasterDomianId());
					}
				} else {
					List<Domain_Master> newAssignedDomians = new ArrayList<>();
					Optional<Domain_Master> masterDomianOptional = domian_MasterRepo
							.findById(domianVo.getMasterDomianId());
					if (masterDomianOptional.isPresent()) {
						newAssignedDomians.add(masterDomianOptional.get());
						clientDomians.setDomain(newAssignedDomians);
					} else {
						throw new RuntimeException(
								"Master Domian not found with this Id : " + domianVo.getMasterDomianId());
					}
				}

				ClientDomains dbObject = clientChannelRepo.save(clientDomians);
				if (dbObject != null) {
					return "Domian assigned to client with domainId : " + dbObject.getClientDomainaId();
				} else {
					throw new RuntimeException("Domain not assinged to client");
				}

			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		} else {
			throw new RuntimeException("This Domian already assigned to client");

		}
	}

	@Override
	public ClientDetails getClient(long clientId) throws Exception {

		Optional<ClientDetails> client = clientDetailsRepo.findById(clientId);
		if (client.isPresent()) {
			return client.get();
		} else
			throw new Exception("No Client found with this Id : " + clientId);
	}

	@Override
	public List<ClientDetails> getAllClient() throws Exception {
		List<ClientDetails> clients = clientDetailsRepo.findAll();
		if (!CollectionUtils.isEmpty(clients))
			return clients;
		else
			throw new Exception("No clients found");
	}

	@Override
	public List<ClientDomains> getDomainsForClient(long clientId) {

		List<ClientDomains> clientDomians = clientChannelRepo.findByClient_Id(clientId);
		if (!CollectionUtils.isEmpty(clientDomians)) {
			return clientDomians;
		} else {
			throw new RuntimeException("No domian found with this Client :" + clientId);
		}

	}

	@Override
	public ClientDomains getDomianById(String clientDomianId) {

		Optional<ClientDomains> domianOptional = clientChannelRepo.findByClientDomainaId(clientDomianId);
		if (domianOptional.isPresent()) {
			return domianOptional.get();

		} else {
			throw new RuntimeException("Client domian not found with this Id : " + clientDomianId);
		}
	}

}
