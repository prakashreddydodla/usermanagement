package com.otsi.retail.authservice.mapper;

import java.util.ArrayList;
import java.util.List;

import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.requestModel.ClientDetailsVO;

public class ClientMapper {
	
	public List<ClientDetailsVO> convertListEntityToVo(List<ClientDetails> clientDetails) {
		List<ClientDetailsVO> clientsVo = new ArrayList<>();
		clientDetails.stream().forEach(clientDetail->{
			ClientDetailsVO VO = convertEntityToVO(clientDetail);
			clientsVo.add(VO);
		});
		return clientsVo;
		
		
	}

	private ClientDetailsVO convertEntityToVO(ClientDetails clientDetail) {
		ClientDetailsVO clientVo = new ClientDetailsVO();
		clientVo.setId(clientDetail.getId());
		clientVo.setName(clientDetail.getName());
		clientVo.setOrganizationName(clientDetail.getOrganizationName());
		clientVo.setCreatedDate(clientDetail.getCreatedDate().toLocalDate());
		clientVo.setPlanName(clientDetail.getPlanDetails().getPlanName());
		clientVo.setDescription(clientDetail.getDescription());
		
		
		
		return clientVo;
	}

}
