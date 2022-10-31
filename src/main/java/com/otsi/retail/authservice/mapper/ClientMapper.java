package com.otsi.retail.authservice.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.requestModel.ClientDetailsVO;

@Component
public class ClientMapper {

	public Page<ClientDetailsVO> convertListEntityToVo(Page<ClientDetails> clientDetails) {
		/*
		 * Page<ClientDetailsVO> clientsVo = new ArrayList<>();
		 * clientDetails.stream().forEach(clientDetail->{ ClientDetailsVO VO =
		 * convertEntityToVO(clientDetail); clientsVo.add(VO); }); return clientsVo;
		 */
		return clientDetails.map(client -> convertEntityToVO(client));

	}

	public ClientDetailsVO convertEntityToVO(ClientDetails clientDetail) {
		ClientDetailsVO clientVo = new ClientDetailsVO();
		clientVo.setSystemTime(LocalDateTime.now());
		clientVo.setId(clientDetail.getId());
		clientVo.setName(clientDetail.getName());
		clientVo.setOrganizationName(clientDetail.getOrganizationName());
		clientVo.setCreatedDate(clientDetail.getCreatedDate());
		clientVo.setDescription(clientDetail.getDescription());
		clientVo.setEmail(clientDetail.getEmail());
		clientVo.setMobile(clientDetail.getMobile());
		clientVo.setAddress(clientDetail.getAddress());
		clientVo.setActive(clientDetail.isActive());
		if (clientDetail.getPlanDetails() != null) {
			clientVo.setPlanName(clientDetail.getPlanDetails().getPlanName());
			clientVo.setPlanTenure(clientDetail.getPlanTenure());
			clientVo.setPlanId(clientDetail.getPlanDetails().getId());
			clientVo.setPlandetails(clientDetail.getPlanDetails());
			clientVo.setPlanActivatedDate(clientDetail.getPlanActivationDate());
			clientVo.setPlanExpiryDate(clientDetail.getPlanExpiryDate());
		}
		return clientVo;
	}

	public List<ClientDetailsVO> convertListEntityToVo(List<ClientDetails> clientDetails) {
		List<ClientDetailsVO> clientsVo = new ArrayList<>();

		clientDetails.stream().forEach(clientDetail -> {
			ClientDetailsVO VO = convertEntityToVO(clientDetail);
			clientsVo.add(VO);
		});
		return clientsVo;
	}

	public ClientDetails convertVOToEntity(ClientDetailsVO clientDetail) {
		ClientDetails client = new ClientDetails();
		client.setId(clientDetail.getId());
		client.setName(clientDetail.getName());
		client.setOrganizationName(clientDetail.getOrganizationName());
		client.setDescription(clientDetail.getDescription());
		client.setEmail(clientDetail.getEmail());
		client.setMobile(clientDetail.getMobile());
		client.setIsTaxIncluded(clientDetail.getIsTaxIncluded());
		client.setIsEsSlipEnabled(clientDetail.getIsEsSlipEnabled());
		client.setCreatedBy(clientDetail.getCreatedBy());
		client.setAddress(clientDetail.getAddress());
		client.setActive(Boolean.TRUE);
		client.setPlanTenure(clientDetail.getPlanTenure());

		return client;
	}

	public ClientDetailsVO convertClientDetailsEntityToVo(ClientDetails clientDetailsEntity) {
		ClientDetailsVO clientDetailsVo = new ClientDetailsVO();
		clientDetailsVo.setSystemTime(LocalDateTime.now());
		clientDetailsVo.setId(clientDetailsEntity.getId());
		clientDetailsVo.setName(clientDetailsEntity.getName());
		clientDetailsVo.setOrganizationName(clientDetailsEntity.getOrganizationName());
		clientDetailsVo.setAddress(clientDetailsEntity.getAddress());
		clientDetailsVo.setActive(clientDetailsEntity.isActive());
		clientDetailsVo.setCreatedDate(clientDetailsEntity.getCreatedDate());
		clientDetailsVo.setLastModifiedDate(clientDetailsEntity.getLastModifiedDate());
		clientDetailsVo.setMobile(clientDetailsEntity.getMobile());
		clientDetailsVo.setEmail(clientDetailsEntity.getEmail());
		clientDetailsVo.setIsEsSlipEnabled(clientDetailsEntity.getIsEsSlipEnabled());
		clientDetailsVo.setIsTaxIncluded(clientDetailsEntity.getIsTaxIncluded());
		clientDetailsVo.setDescription(clientDetailsEntity.getDescription());
		clientDetailsVo.setPlanTenure(clientDetailsEntity.getPlanTenure());
		clientDetailsVo.setRayzorPayPaymentId(clientDetailsEntity.getRazorPayPaymentId());
		clientDetailsVo.setAmount(clientDetailsEntity.getAmount());
		clientDetailsVo.setPlanActivatedDate(clientDetailsEntity.getPlanActivationDate());
		clientDetailsVo.setPlanExpiryDate(clientDetailsEntity.getPlanExpiryDate());
		clientDetailsVo.setPlanExpired(clientDetailsEntity.getPlanExpired());
		return clientDetailsVo;

	}

}
