package com.otsi.retail.authservice.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otsi.retail.authservice.Entity.ColorEntity;
import com.otsi.retail.authservice.Entity.Role;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.Entity.UserDeatils;
import com.otsi.retail.authservice.Repository.ColorRepo;
import com.otsi.retail.authservice.Repository.RoleRepository;
import com.otsi.retail.authservice.Repository.StoreRepo;
import com.otsi.retail.authservice.Repository.UserRepo;
import com.otsi.retail.authservice.requestModel.ColorCodeVo;
import com.otsi.retail.authservice.requestModel.ReportVo;

@Service
public class ReportsServiceImpl implements ReportsService {
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private StoreRepo storeRepo;
	@Autowired
	private ColorRepo colorRepo;
	int count = 0;

	@Override
	public List<ReportVo> getUsersByRole(Long clientId) {

		List<ReportVo> rvo = new ArrayList<ReportVo>();

		List<Role> role = roleRepo.findAll();
		List<String> roleName = role.stream().map(a -> a.getRoleName()).distinct().collect(Collectors.toList());
		roleName.stream().forEach(r -> {
			List<UserDeatils> users = userRepo.findByclientDomians_clientIdAndRoleRoleNameAndIsCustomer(clientId, r,
					Boolean.FALSE);
			Long usersCount = users.stream().map(a -> a.getUserId()).count();

			ReportVo vo = new ReportVo();
			vo.setName(r);
			vo.setCount(usersCount);
			rvo.add(vo);

		});
		List<ColorEntity> colorCodes = colorRepo.findAll();
		rvo.stream().forEach(r -> {
			r.setColorCode(colorCodes.get(count).getColorCode());
			count++;

		});

		return rvo;
	}

	@Override
	public List<ReportVo> getActiveUsers(Long clientId) {
		List<ReportVo> rvo = new ArrayList<ReportVo>();
		List<UserDeatils> users = userRepo.findByclientDomians_clientIdAndIsActiveAndIsCustomer(clientId, Boolean.TRUE,
				Boolean.FALSE);
		Long count = users.stream().map(u -> u.getUserId()).count();
		ReportVo vo = new ReportVo();
		vo.setName("ActiveUsers");
		vo.setCount(count);
		rvo.add(vo);
		List<UserDeatils> Inactiveusers = userRepo.findByclientDomians_clientIdAndIsActiveAndIsCustomer(clientId,
				Boolean.FALSE, Boolean.FALSE);
		Long incount = Inactiveusers.stream().map(u -> u.getUserId()).count();
		vo.setName("inactiveUsers");
		vo.setCount(incount);
		rvo.add(vo);
		return rvo;
	}

	@Override
	public List<ReportVo> StoresVsEmployees(Long clientId) {

		List<ReportVo> rvo = new ArrayList<ReportVo>();
		List<Store> stores = storeRepo.findAll();
		List<String> storeName = stores.stream().map(a -> a.getName()).distinct().collect(Collectors.toList());
		storeName.stream().forEach(s -> {
			List<UserDeatils> users = userRepo.findByclientDomians_clientIdAndStores_NameAndIsCustomer(clientId, s,
					Boolean.FALSE);
			Long usersCount = users.stream().map(a -> a.getUserId()).count();
			ReportVo vo = new ReportVo();
			vo.setName(s);
			vo.setCount(usersCount);
			rvo.add(vo);

		});
		List<ColorEntity> colorCodes = colorRepo.findAll();
		rvo.stream().forEach(r -> {
			r.setColorCode(colorCodes.get(count).getColorCode());
			count++;

		});

		return rvo;
	}

	@Override
	public String SaveColorCodes(List<ColorCodeVo> colorCodes) {
		ColorEntity color = new ColorEntity();
		colorCodes.forEach(a -> {
			color.setColorCode(a.getColorCode());
			colorRepo.save(color);

		});
		return "colors saved successfully";
	}

}
