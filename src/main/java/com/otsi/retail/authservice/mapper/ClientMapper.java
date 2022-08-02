package com.otsi.retail.authservice.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.requestModel.ClientDetailsVO;
@Component
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
		if(clientDetail.getPlanDetails()!=null) {
		clientVo.setPlanName(clientDetail.getPlanDetails().getPlanName());
		}
		clientVo.setDescription(clientDetail.getDescription());
		
		
		
		return clientVo;
	}

}
