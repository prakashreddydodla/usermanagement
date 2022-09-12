package com.otsi.retail.authservice.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.Domain_Master;
import com.otsi.retail.authservice.requestModel.ClientDetailsVO;
import com.otsi.retail.authservice.requestModel.ClientDomianVo;
import com.otsi.retail.authservice.requestModel.ClientMappingVO;
import com.otsi.retail.authservice.requestModel.ClientSearchVO;
import com.otsi.retail.authservice.requestModel.MasterDomianVo;
import com.razorpay.RazorpayException;

@Component
public interface ClientAndDomianService {

	String createMasterDomain(MasterDomianVo masterDomianVo) throws Exception;

	List<Domain_Master> getMasterDomains();
	
	String editClientMapping(ClientMappingVO clientMappingVo);

	ClientDetails createClient(ClientDetailsVO clientDetailsVO) throws RazorpayException;

	String assignDomianToClient(ClientDomianVo clientDomianVo);

	ClientDetails getClient(long clientId) throws Exception;

	Page<ClientDetailsVO> getAllClient(Pageable pageable) throws Exception;

	List<ClientDomains> getDomainsForClient(long clientId);

	ClientDomains getDomianById(long l);

	String clientMapping(ClientMappingVO clientMappingVo);

	Page<ClientDetailsVO> clientSerach(ClientSearchVO clientSearchVo, Pageable pageable);

	List<ClientDetailsVO> getClientsForUser(Long userId);

	Page<ClientMappingVO> getClientMappingDetails(Pageable pageable);

	Page<ClientMappingVO> getClientMappingSearchDetails(ClientMappingVO clientMappingVo,Pageable pageable);

	String editClient(ClientDetailsVO clientDetailsVO);

	String deleteClient(ClientMappingVO clientMappingVO);
}
