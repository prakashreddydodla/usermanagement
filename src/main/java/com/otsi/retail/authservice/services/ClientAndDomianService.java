package com.otsi.retail.authservice.services;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.Domain_Master;
import com.otsi.retail.authservice.requestModel.ClientDetailsVo;
import com.otsi.retail.authservice.requestModel.ClientDomianVo;
import com.otsi.retail.authservice.requestModel.MasterDomianVo;

@Component
public interface ClientAndDomianService {

	String createMasterDomain(MasterDomianVo domainVo) throws Exception;

	List<Domain_Master> getMasterDomains();

	String createClient(ClientDetailsVo clientVo) throws Exception;

	String assignDomianToClient(ClientDomianVo domianVo);

	ClientDetails getClient(long clientId) throws Exception;

	List<ClientDetails> getAllClient() throws Exception;

	List<ClientDomains> getDomainsForClient(long clientId);
}
