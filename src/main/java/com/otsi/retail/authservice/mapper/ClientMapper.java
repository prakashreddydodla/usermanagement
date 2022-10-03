package com.otsi.retail.authservice.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.requestModel.ClientDetailsVO;
@Component
public class ClientMapper {
	
	public Page<ClientDetailsVO> convertListEntityToVo(Page<ClientDetails> clientDetails) {
		/*Page<ClientDetailsVO> clientsVo = new ArrayList<>();
		clientDetails.stream().forEach(clientDetail->{
			ClientDetailsVO VO = convertEntityToVO(clientDetail);
			clientsVo.add(VO);
		});
		return clientsVo;*/
		return clientDetails.map(client -> convertEntityToVO(client));

		
	}

	private ClientDetailsVO convertEntityToVO(ClientDetails clientDetail) {
		ClientDetailsVO clientVo = new ClientDetailsVO();
		clientVo.setId(clientDetail.getId());
		clientVo.setName(clientDetail.getName());
		clientVo.setOrganizationName(clientDetail.getOrganizationName());
		clientVo.setCreatedDate(clientDetail.getCreatedDate().toLocalDate());
		clientVo.setDescription(clientDetail.getDescription());
		clientVo.setEmail(clientDetail.getEmail());
		clientVo.setMobile(clientDetail.getMobile());
		clientVo.setAddress(clientDetail.getAddress());
		clientVo.setActive(clientDetail.isActive());
		if(clientDetail.getPlanDetails()!=null) {
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

		clientDetails.stream().forEach(clientDetail->{
			ClientDetailsVO VO = convertEntityToVO(clientDetail);
			clientsVo.add(VO);
		});
		return clientsVo;
	}

}
