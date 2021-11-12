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
			return "Channel created";

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Domain_Master> getMasterDomains() {
		List<Domain_Master> domains = domian_MasterRepo.findAll();
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

			ClientDetails savedClient = clientDetailsRepo.save(clientEntity);
			/*
			 * if (clientVo.getChannelId() != null) {
			 * clientVo.getChannelId().stream().forEach(domainVo -> { ClientDomains
			 * clientDomians = new ClientDomains();
			 * clientDomians.setDomaiName(domainVo.getName());
			 * clientDomians.setDiscription(domainVo.getDiscription());
			 * clientDomians.setCreatedDate(LocalDate.now());
			 * clientDomians.setLastModifyedDate(LocalDate.now());
			 * clientDomians.setClient(savedClient); List<Domain_Master> masterDomains = new
			 * ArrayList<>(); if (domainVo.getChannel() != null) {
			 * domainVo.getChannel().stream().forEach(a -> { try {
			 * masterDomains.add(domian_MasterRepo.findById(a.getId()).get()); } catch
			 * (Exception e) { throw new RuntimeException("Domain not found in master"); }
			 * }); } clientDomians.setDomain(masterDomains);
			 * clientChannelRepo.save(clientDomians); }); }
			 */
			return "Client created successfully with ClientId :" + savedClient.getId();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String assignDomianToClient(ClientDomianVo domianVo) {

		boolean isExists = clientChannelRepo.existsByDomain_IdAndClientId(domianVo.getMasterDomianId(),domianVo.getClientId());
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
					}
				}
				if (null != clientDomians.getDomain()) {

					List<Domain_Master> asssingedDomians = clientDomians.getDomain();
					asssingedDomians.add(domian_MasterRepo.findById(domianVo.getMasterDomianId()).get());
					clientDomians.setDomain(asssingedDomians);
				} else {
					List<Domain_Master> newAssignedDomians = new ArrayList<>();
					newAssignedDomians.add(domian_MasterRepo.findById(domianVo.getMasterDomianId()).get());
					clientDomians.setDomain(newAssignedDomians);
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
			throw new Exception("No Client found with this Name");
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

}
