package com.otsi.retail.authservice.services;

import java.util.List;

import org.springframework.stereotype.Component;
import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.Domain_Master;
import com.otsi.retail.authservice.requestModel.ClientDetailsVO;
import com.otsi.retail.authservice.requestModel.ClientDomianVo;
import com.otsi.retail.authservice.requestModel.MasterDomianVo;

@Component
public interface ClientAndDomianService {

	String createMasterDomain(MasterDomianVo masterDomianVo) throws Exception;

	List<Domain_Master> getMasterDomains();

	ClientDetails createClient(ClientDetailsVO clientDetailsVO);

	String assignDomianToClient(ClientDomianVo clientDomianVo);

	ClientDetails getClient(long clientId) throws Exception;

	List<ClientDetails> getAllClient() throws Exception;

	List<ClientDomains> getDomainsForClient(long clientId);

	ClientDomains getDomianById(long l);
}
