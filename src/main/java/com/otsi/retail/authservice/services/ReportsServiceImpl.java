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
import com.otsi.retail.authservice.Exceptions.ColourCodeNotFoundException;
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
	public List<ReportVo> getUsersByRole(Long clientId) throws Exception {
		try {
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
				if (count > (colorCodes.size())) {

					throw new ColourCodeNotFoundException("color codes not available");
				}
				ColorCodeVo cvo = new ColorCodeVo();
				cvo.setColorCode(colorCodes.get(count).getColorCode());
				cvo.setColorName(colorCodes.get(count).getColorName());

				cvo.setRgb(colorCodes.get(count).getRgb());
				r.setColorCodeVo(cvo);
				count++;

			});
			count = 0;
			return rvo;
		} catch (Exception ex) {
			throw new Exception(ex);

		} finally {
			count = 0;
		}
	}

	@Override
	public List<ReportVo> getActiveUsers(Long clientId) throws Exception {
		try {
			List<ReportVo> rvo = new ArrayList<ReportVo>();
			List<UserDeatils> users = userRepo.findByclientDomians_clientIdAndIsActiveAndIsCustomer(clientId,
					Boolean.TRUE, Boolean.FALSE);
			Long acount = users.stream().map(u -> u.getUserId()).count();
			ReportVo vo = new ReportVo();
			vo.setName("ActiveUsers");
			vo.setCount(acount);
			rvo.add(vo);
			List<UserDeatils> Inactiveusers = userRepo.findByclientDomians_clientIdAndIsActiveAndIsCustomer(clientId,
					Boolean.FALSE, Boolean.FALSE);
			Long incount = Inactiveusers.stream().map(u -> u.getUserId()).count();
			ReportVo voI = new ReportVo();
			voI.setName("inactiveUsers");
			voI.setCount(incount);
			rvo.add(voI);
			List<ColorEntity> colorCodes = colorRepo.findAll();
			rvo.stream().forEach(r -> {
				if (count > (colorCodes.size())) {

					throw new ColourCodeNotFoundException("color codes not available");
				}
				ColorCodeVo cvo = new ColorCodeVo();
				cvo.setColorCode(colorCodes.get(count).getColorCode());
				cvo.setColorName(colorCodes.get(count).getColorName());

				cvo.setRgb(colorCodes.get(count).getRgb());
				r.setColorCodeVo(cvo);
				count++;

			});
			count = 0;
			return rvo;
		} catch (Exception ex) {
			throw new Exception(ex);
		} finally {
			count = 0;
		}
	}

	@Override
	public List<ReportVo> StoresVsEmployees(Long clientId) throws Exception {
		try {
			List<ReportVo> rvo = new ArrayList<ReportVo>();
			List<Store> stores = storeRepo.findAll();
			List<String> storeName = stores.stream().map(a -> a.getName()).distinct().collect(Collectors.toList());
			Long storesCount = storeName.stream().count();
			List<UserDeatils> users = userRepo.findByclientDomians_clientIdAndIsCustomer(clientId, Boolean.FALSE);
			Long usersCount = users.stream().map(a -> a.getUserId()).count();
			ReportVo vo = new ReportVo();

			vo.setName("stores");
			vo.setCount(storesCount);
			rvo.add(vo);
			ReportVo vo1 = new ReportVo();
			vo1.setName("users");
			vo1.setCount(usersCount);
			rvo.add(vo1);

			List<ColorEntity> colorCodes = colorRepo.findAll();
			rvo.stream().forEach(r -> {
				if (count > (colorCodes.size())) {

					throw new ColourCodeNotFoundException("color codes not available");
				}

				ColorCodeVo cvo = new ColorCodeVo();
				cvo.setColorCode(colorCodes.get(count).getColorCode());
				cvo.setColorName(colorCodes.get(count).getColorName());

				cvo.setRgb(colorCodes.get(count).getRgb());
				r.setColorCodeVo(cvo);

				count++;

			});
			count = 0;
			return rvo;
		} catch (Exception ex) {
			throw new Exception(ex);
		} finally {
			count = 0;
		}

	}

	@Override
	public String SaveColorCodes(List<ColorCodeVo> colorCodes) {
		List<ColorEntity> colorcode = new ArrayList<ColorEntity>();
		colorCodes.forEach(a -> {
			ColorEntity color = new ColorEntity();
			color.setColorCode(a.getColorCode());
			color.setColorName(a.getColorName());

			color.setRgb(a.getRgb());

			colorRepo.save(color);

		});

		return "colors saved successfully";
	}

}
