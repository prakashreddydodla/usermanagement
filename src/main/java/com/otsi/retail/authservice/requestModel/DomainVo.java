package com.otsi.retail.authservice.requestModel;

import java.util.List;



import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.Entity.Store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DomainVo {
	private long id;
	private String channelName;
	private String discription;
	private ClientDetails client;
	private List<Store> store;
}
