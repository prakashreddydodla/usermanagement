package com.otsi.retail.authservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.Entity.UserDetails;
import com.otsi.retail.authservice.Repository.RoleRepository;
import com.otsi.retail.authservice.Repository.StoreRepo;
import com.otsi.retail.authservice.Repository.UserRepository;
import com.otsi.retail.authservice.requestModel.ReportVo;

@Service
public class ReportsServiceImpl implements ReportsService {
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private StoreRepo storeRepo;
	
	

	@Override
	public List<ReportVo> getUsersByRole(Long clientId) throws Exception {
		try {
			List<ReportVo> rvo = new ArrayList<ReportVo>();

			List<Role> role = roleRepo.findAll();
			List<String> roleName = role.stream().map(a -> a.getRoleName()).distinct().collect(Collectors.toList());
			roleName.stream().forEach(r -> {
				List<UserDetails> users = userRepo.findByClientDomiansClientIdAndRoleRoleNameAndIsCustomer(clientId, r,
						Boolean.FALSE);
				Long usersCount = users.stream().map(a -> a.getId()).count();

				ReportVo vo = new ReportVo();
				vo.setName(r);
				vo.setCount(usersCount);
				rvo.add(vo);

			});
			
			return rvo;
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());

		} 
	}

	@Override
	public List<ReportVo> getActiveUsers(Long clientId) throws Exception {
		try {
			List<ReportVo> rvo = new ArrayList<ReportVo>();
			List<UserDetails> users = userRepo.findByclientDomians_clientIdAndIsActiveAndIsCustomer(clientId,
					Boolean.TRUE, Boolean.FALSE);
			Long acount = users.stream().map(u -> u.getId()).count();
			ReportVo vo = new ReportVo();
			vo.setName("ActiveUsers");
			vo.setCount(acount);
			rvo.add(vo);
			List<UserDetails> Inactiveusers = userRepo.findByclientDomians_clientIdAndIsActiveAndIsCustomer(clientId,
					Boolean.FALSE, Boolean.FALSE);
			Long incount = Inactiveusers.stream().map(u -> u.getId()).count();
			ReportVo voI = new ReportVo();
			voI.setName("inactiveUsers");
			voI.setCount(incount);
			rvo.add(voI);
			
			
			return rvo;
		} catch (Exception ex) {
			throw new Exception(ex);
		}  
	}

	@Override
	public List<ReportVo> StoresVsEmployees(Long clientId) throws Exception {
		try {
			List<ReportVo> rvo = new ArrayList<ReportVo>();
			List<Store> stores = storeRepo.findByClientDomianlId_Client_Id(clientId);
			List<String> storeName = stores.stream().map(a -> a.getName()).distinct().collect(Collectors.toList());
			Long storesCount = storeName.stream().count();
			List<UserDetails> users = userRepo.findByclientDomians_clientIdAndIsCustomer(clientId, Boolean.FALSE);
			Long usersCount = users.stream().map(a -> a.getId()).count();
			ReportVo vo = new ReportVo();

			vo.setName("stores");
			vo.setCount(storesCount);
			rvo.add(vo);
			ReportVo vo1 = new ReportVo();
			vo1.setName("users");
			vo1.setCount(usersCount);
			rvo.add(vo1);

			
			return rvo;
		} catch (Exception ex) {
			throw new Exception(ex);
		} 

	}

	
	
	

}
