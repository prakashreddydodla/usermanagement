package com.otsi.retail.authservice.requestModel;

import java.util.List;



import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.Entity.Store;

import lombok.Data;

@Data
public class DomainVo {
	private long id;
	private String channelName;
	private String discription;
	private ClientDetails client;
	private List<Store> store;
}
