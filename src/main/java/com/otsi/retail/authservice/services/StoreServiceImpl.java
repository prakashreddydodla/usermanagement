package com.otsi.retail.authservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import com.otsi.retail.authservice.Entity.ClientDetails;
import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.GstDetails;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.Entity.UserDetails;
import com.otsi.retail.authservice.Repository.ChannelRepo;
import com.otsi.retail.authservice.Repository.GstRepository;
import com.otsi.retail.authservice.Repository.StoreRepo;
import com.otsi.retail.authservice.Repository.UserRepository;
import com.otsi.retail.authservice.requestModel.DomianStoresVo;
import com.otsi.retail.authservice.requestModel.GetStoresRequestVo;
import com.otsi.retail.authservice.requestModel.StoreVO;
import com.otsi.retail.authservice.requestModel.UserDetailsVO;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepo storeRepo;

	@Autowired
	private UserServiceImpl userserviceImpl;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GstRepository gstRepository;

	@Autowired
	private ChannelRepo clientChannelRepository;
	private Logger logger = LogManager.getLogger(StoreServiceImpl.class);

	@Override
	@Transactional(rollbackOn = { RuntimeException.class })
	public Store createStore(StoreVO vo) {
		Store storeEntity = new Store();
		storeEntity.setName(vo.getName());
		storeEntity.setAddress(vo.getAddress());
		storeEntity.setStateId(vo.getStateId());
		storeEntity.setDistrictId(vo.getDistrictId());
		storeEntity.setCityId(vo.getCityId());
		storeEntity.setArea(vo.getArea());
		storeEntity.setPhoneNumber(vo.getPhoneNumber());
		storeEntity.setCreatedBy(vo.getCreatedBy());
		storeEntity.setStateCode(vo.getStateCode());
		if (vo.getGstNumber() != null) {
			Optional<GstDetails> gstDetailsopt = gstRepository.findByGstNumber(vo.getGstNumber());
			if (!gstDetailsopt.isPresent()) {
				GstDetails gstInfo = new GstDetails();
				gstInfo.setClientId(vo.getClientId());
				gstInfo.setCreatedBy(vo.getCreatedBy());
				gstInfo.setGstNumber(vo.getGstNumber());
				gstInfo.setStateCode(vo.getStateCode());
				gstRepository.save(gstInfo);

			}
		} else {
			logger.error("gstNumber should not be null");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "gstNumber should not be null");
		}
		ClientDetails clientDetails = new ClientDetails();
		clientDetails.setId(vo.getClientId());
		storeEntity.setClient(clientDetails);

		if (vo.getStoreOwner() != null) {
			Optional<UserDetails> userfromDb = userRepository.findById(vo.getStoreOwner().getId());
			if (userfromDb.isPresent()) {
				storeEntity.setStoreOwner(userfromDb.get());
			} else {
				logger.error("No user found in database for StoreOwner");
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not found for store");
			}
		}
		if (vo.getDomainId() != null) {
			Optional<ClientDomains> clientDomian = clientChannelRepository.findById(vo.getDomainId());
			if (clientDomian.isPresent()) {
				storeEntity.setClientDomianlId(clientDomian.get());
			} else {
				logger.error("No client Domian found with this DomianId :" + vo.getDomainId());
				throw new RuntimeException("No client Domian found with this DomianId :" + vo.getDomainId());
			}
		}
		storeEntity = storeRepo.save(storeEntity);
		return storeEntity;
	}

	@Override
	@Transactional(rollbackOn = { RuntimeException.class })
	public Store updateStore(StoreVO vo) {
		Optional<Store> storeOptional = storeRepo.findById(vo.getId());
		Store storeEntity = storeOptional.get();
		storeEntity.setName(vo.getName());
		storeEntity.setAddress(vo.getAddress());
		storeEntity.setStateId(vo.getStateId());
		storeEntity.setDistrictId(vo.getDistrictId());
		storeEntity.setCityId(vo.getCityId());
		storeEntity.setArea(vo.getArea());
		storeEntity.setPhoneNumber(vo.getPhoneNumber());
		storeEntity.setModifiedBy(vo.getCreatedBy());
		if (null != vo.getStoreOwner()) {
			Optional<UserDetails> userfromDb = userRepository.findById(vo.getStoreOwner().getId());
			if (userfromDb.isPresent()) {
				storeEntity.setStoreOwner(userfromDb.get());
			} else {
				logger.error("No user found in database for StoreOwner");
				throw new RuntimeException("No user found in database for StoreOwner");
			}
		}
		if (0L != vo.getDomainId()) {
			Optional<ClientDomains> clientDomian = clientChannelRepository.findById(vo.getDomainId());
			if (clientDomian.isPresent()) {
				storeEntity.setClientDomianlId(clientDomian.get());
			} else {
				logger.error("No client Domian found with this DomianId :" + vo.getDomainId());
				throw new RuntimeException("No client Domian found with this DomianId :" + vo.getDomainId());
			}
		}
		storeEntity = storeRepo.save(storeEntity);
		return storeEntity;
	}

	@Override
	public List<Store> getStoresForClientDomian(Long clientDomianId) {
		List<Store> stores = storeRepo.findByClientDomianlIdId(clientDomianId);
		if (!CollectionUtils.isEmpty(stores)) {
			logger.info("**********getStoresForClientDomia Method Ends");
			return stores;
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No stores found");
		}
	}

	@Override
	public List<StoreVO> getStoresByClient(Long clientId) {
		List<Store> stores = storeRepo.findByClientId(clientId);
		if (CollectionUtils.isEmpty(stores)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No stores found for client:" + clientId);
		}
		List<StoreVO> storesVO = new ArrayList<>();

		stores.stream().forEach(store -> {
			GstDetails gstdetails = getGstDetails(clientId, store.getStateCode());
			StoreVO storeVo = convertToVo(store);
			storeVo.setGstNumber(gstdetails.getGstNumber());
			storesVO.add(storeVo);
		});

		List<Long> ids = stores.stream().map(s -> s.getCreatedBy()).collect(Collectors.toList());
		List<UserDetailsVO> userDetailsList = userserviceImpl.getUserDetailsByIds(ids);
		Map<Long, String> userDetailsMap = userDetailsList.stream()
				.collect(Collectors.toMap(UserDetailsVO::getId, UserDetailsVO::getUserName));
		storesVO.stream().forEach(storeVo -> {
			if (userDetailsMap.containsKey(storeVo.getCreatedBy())) {
				storeVo.setUserName(userDetailsMap.get(storeVo.getCreatedBy()));
			}
		});
		return storesVO;
	}

	private StoreVO convertToVo(Store store) {
		StoreVO storeVo = new StoreVO();
		storeVo.setAddress(store.getAddress());
		storeVo.setArea(store.getArea());
		storeVo.setCityId(store.getCityId());
		storeVo.setCreatedBy(store.getCreatedBy());
		storeVo.setDistrictId(store.getDistrictId());
		storeVo.setName(store.getName());
		storeVo.setPhoneNumber(store.getPhoneNumber());
		//storeVo.setDomainId(store.getClientDomianlId().getId());
		storeVo.setStateCode(store.getStateCode());
		storeVo.setCreatedDate(store.getCreatedDate());
		storeVo.setStateId(store.getStateId());
		storeVo.setId(store.getId());
		//storeVo.setDomainName(store.getClientDomianlId().getDomaiName());
		return storeVo;

	}

	@Override
	public String assignStoreToClientDomain(DomianStoresVo vo) throws Exception {
		logger.info("################  assignStoreToClientDomain  method starts ###########");

		try {
			Optional<ClientDomains> clientDetails = clientChannelRepository
					.findById(vo.getDomain().getClientChannelid());

			if (clientDetails.isPresent()) {
				List<Store> selectedStores = new ArrayList<>();
				ClientDomains clientDomain = clientDetails.get();
				vo.getStores().stream().forEach(a -> {
					selectedStores.add(storeRepo.findById(a.getId()).get());
				});
				clientDomain.setStore(selectedStores);

				clientChannelRepository.save(clientDomain);
				logger.info("################  assignStoreToClientDomain  method ends ###########");

				return "success";
			} else {
				logger.debug("Selected Domain not  found ");
				logger.error("Selected Domain not  found ");
				throw new RuntimeException("Selected Domain not  found ");
			}

		} catch (Exception e) {
			logger.debug(e.getMessage());
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Store> getStoresOnFilter(GetStoresRequestVo vo, Long clientId) {
		logger.info("################  getStoresOnFilter  method starts ###########");

		if (0L != vo.getDistrictId() && null != vo.getStateId() && "" != vo.getStateId() && null != vo.getStoreName()
				&& "" != vo.getStoreName()) {
			List<Store> stores = storeRepo.findByStateCodeAndDistrictIdAndNameAndClientDomianlId_Client_Id(
					vo.getStateId(), vo.getDistrictId(), vo.getStoreName(), clientId);
			if (!CollectionUtils.isEmpty(stores)) {
				logger.info("################  getStoresOnFilter  method ends ###########");

				return stores;
			} else {
				logger.debug("Stores not found with this DistrictId : " + vo.getDistrictId());
				logger.error("Stores not found with this DistrictId : " + vo.getDistrictId());
				throw new RuntimeException("Stores not found with this given information : " + vo.getDistrictId());

			}

		}

		if (0L != vo.getDistrictId() && null != vo.getStateId() && "" != vo.getStateId()) {
			List<Store> stores = storeRepo.findByStateCodeAndDistrictIdAndClientDomianlId_Client_Id(vo.getStateId(),
					vo.getDistrictId(), clientId);
			if (!CollectionUtils.isEmpty(stores)) {
				logger.info("################  getStoresOnFilter  method ends ###########");

				return stores;
			} else {
				logger.debug("Stores not found with this DistrictId : " + vo.getDistrictId());
				logger.error("Stores not found with this DistrictId : " + vo.getDistrictId());
				throw new RuntimeException("Stores not found with this DistrictId  and sateId: " + vo.getDistrictId()
						+ "" + vo.getStateId());

			}

		}
		if (null != vo.getStoreName() && "" != vo.getStoreName() && null != vo.getStateId() && "" != vo.getStateId()
				&& 0L == vo.getDistrictId()) {
			List<Store> stores = storeRepo.findByStateCodeAndNameAndClientDomianlId_Client_Id(vo.getStateId(),
					vo.getStoreName(), clientId);
			if (!CollectionUtils.isEmpty(stores)) {
				logger.info("################  getStoresOnFilter  method ends ###########");

				return stores;
			} else {
				logger.debug("Stores not found with this DistrictId : " + vo.getDistrictId());
				logger.error("Stores not found with this DistrictId : " + vo.getDistrictId());
				throw new RuntimeException("Stores not found with this StateId and storeName : " + vo.getStateId());

			}

		}

		if (null != vo.getStateId() && "" != vo.getStateId()) {
			List<Store> stores = storeRepo.findByStateCodeAndClientDomianlId_Client_Id(vo.getStateId(), clientId);
			if (!CollectionUtils.isEmpty(stores)) {
				return stores;
			} else {
				logger.debug("Stores not found with this StateId : " + vo.getStateId());
				logger.error("Stores not found with this StateId : " + vo.getStateId());
				throw new RuntimeException("Stores not found with this StateId : " + vo.getStateId());
			}
		}
		if (null != vo.getStoreName() && "" != vo.getStoreName()) {
			List<Store> stores = storeRepo.findByName(vo.getStoreName());
			if (!CollectionUtils.isEmpty(stores)) {
				logger.info("################  getStoresOnFilter  method ends ###########");

				return stores;
			} else {
				logger.debug("Stores not found with this CityId : " + vo.getCityId());
				logger.error("Stores not found with this CityId : " + vo.getCityId());
				throw new RuntimeException("Stores not found with this CityId : " + vo.getCityId());

			}
		}

		logger.debug("Please provide valid information");
		logger.error("Please provide valid information");
		throw new RuntimeException("Please provide valid information");
	}

	@Override
	public List<Store> getStoresForGivenIds(List<Long> storeIds) {

		logger.info("################  getStoresForGivenIds  method starts ###########");

		if (!CollectionUtils.isEmpty(storeIds)) {
			List<Store> stores = storeRepo.findByIdIn(storeIds);
			if (!CollectionUtils.isEmpty(storeIds)) {
				logger.info("################  getStoresForGivenIds  method ends ###########");

				return stores;
			} else {
				logger.debug("No stores found with these storeId's");
				logger.error("No stores found with these storeId's");
				throw new RuntimeException("No stores found with these storeId's");
			}
		} else {
			logger.debug("Store Id's should not be null");
			logger.error("Store Id's should not be null");
			throw new RuntimeException("Store Id's should not be null");
		}

	}

	@Override
	public GstDetails getGstDetails(Long clientId, String stateCode) {
		if (clientId != 0L && stateCode != null) {
			GstDetails gstDetails = gstRepository.findByClientIdAndStateCode(clientId, stateCode);
			if (gstDetails != null) {
				return gstDetails;
			} else {
				logger.error("gst Details not found with the given Details");
				throw new RuntimeException("gst Details not found with the given Details");
			}

		} else {
			logger.error("clientId and sateCode should not be null");
			throw new RuntimeException("clientId and sateCode should not be null");
		}

	}
}
