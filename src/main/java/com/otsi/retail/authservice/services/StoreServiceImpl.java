package com.otsi.retail.authservice.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.ObjectUtils;
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
import com.otsi.retail.authservice.Exceptions.BusinessException;
import com.otsi.retail.authservice.Exceptions.DuplicateRecordException;
import com.otsi.retail.authservice.Repository.ChannelRepo;
import com.otsi.retail.authservice.Repository.ClientDetailsRepo;
import com.otsi.retail.authservice.Repository.GstRepository;
import com.otsi.retail.authservice.Repository.StoreRepo;
import com.otsi.retail.authservice.Repository.UserRepository;
import com.otsi.retail.authservice.mapper.StoreMapper;
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

	@Autowired
	private ClientDetailsRepo clientRepo;

	@Autowired
	private StoreMapper storeMapper;

	// private Logger logger = LogManager.getLogger(StoreServiceImpl.class);

	@Override
	@Transactional(rollbackOn = { RuntimeException.class })
	public Store createStore(StoreVO vo) {
		Store store = storeRepo.findByNameAndClient_Id(vo.getName(), vo.getClientId());
		if (store == null) {
			/*Store storeEntity = new Store();
			storeEntity.setName(vo.getName());
			storeEntity.setAddress(vo.getAddress());
			storeEntity.setStateId(vo.getStateId());
			storeEntity.setDistrictId(vo.getDistrictId());
			storeEntity.setCityId(vo.getCityId());
			storeEntity.setIsActive(Boolean.TRUE);
			storeEntity.setArea(vo.getArea());
			storeEntity.setPhoneNumber(vo.getPhoneNumber());
			storeEntity.setCreatedBy(vo.getCreatedBy());
			storeEntity.setStateCode(vo.getStateCode());*/
			Store storeEntity=	storeMapper.convertStoreVoToEntity(vo);
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
				// logger.error("gstNumber should not be null");
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
					// logger.error("No user found in database for StoreOwner");
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not found for store");
				}
			}
			if (vo.getDomainId() != null) {
				Optional<ClientDomains> clientDomian = clientChannelRepository.findById(vo.getDomainId());
				if (clientDomian.isPresent()) {
					storeEntity.setClientDomianlId(clientDomian.get());
				} else {
					// logger.error("No client Domian found with this DomianId :" +
					// vo.getDomainId());
					throw new RuntimeException("No client Domian found with this DomianId :" + vo.getDomainId());
				}
			}
			storeEntity = storeRepo.save(storeEntity);
			return storeEntity;
		} else {
			throw new DuplicateRecordException("storeName already exist with this clientId" + vo.getClientId(),
					BusinessException.DRF_STATUSCODE);
		}
	}

	@Override
	@Transactional(rollbackOn = { RuntimeException.class })
	public Store updateStore(StoreVO vo) {
		Optional<Store> storeOptional = storeRepo.findById(vo.getId());
		Store storeEntity = storeOptional.get();
		/*storeEntity.setName(vo.getName());
		storeEntity.setAddress(vo.getAddress());
		storeEntity.setStateId(vo.getStateId());
		storeEntity.setDistrictId(vo.getDistrictId());
		storeEntity.setCityId(vo.getCityId());
		storeEntity.setArea(vo.getArea());
		storeEntity.setIsActive(vo.getIsActive());
		storeEntity.setPhoneNumber(vo.getPhoneNumber());
		storeEntity.setModifiedBy(vo.getCreatedBy());*/
		 storeEntity = storeMapper.convertStoreVoToEntity(vo);
		if (null != vo.getStoreOwner()) {
			Optional<UserDetails> userfromDb = userRepository.findById(vo.getStoreOwner().getId());
			if (userfromDb.isPresent()) {
				storeEntity.setStoreOwner(userfromDb.get());
			} else {
				// logger.error("No user found in database for StoreOwner");
				throw new RuntimeException("No user found in database for StoreOwner");
			}
		}
		if (0L != vo.getClientId()) {
			Optional<ClientDetails> client = clientRepo.findById(vo.getClientId());
			if (client.isPresent()) {
				storeEntity.setClient(client.get());
			} else {
				// logger.error("No client found with this DomianId :" + vo.getDomainId());
				throw new RuntimeException("No client found with this DomianId :" + vo.getDomainId());
			}
		}
		storeEntity = storeRepo.save(storeEntity);
		return storeEntity;
	}

	@Override
	public List<Store> getStoresForClientDomian(Long clientDomianId) {
		List<Store> stores = storeRepo.findByclient_Id(clientDomianId);
		if (!CollectionUtils.isEmpty(stores)) {
			// logger.info("**********getStoresForClientDomia Method Ends");
			return stores;
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No stores found");
		}
	}

	@Override
	public List<StoreVO> getStoresByClient(Long clientId, Boolean isActive) {
		List<Store> stores = new ArrayList<>();
		if (isActive == Boolean.FALSE) {
			stores = storeRepo.findByClientId(clientId);
		} else {
			stores = storeRepo.findByClientIdAndIsActive(clientId, Boolean.TRUE);
		}
		if (CollectionUtils.isEmpty(stores)) {
			return Collections.emptyList();
		}
		List<StoreVO> storesVO = new ArrayList<>();

		stores.stream().forEach(store -> {
			GstDetails gstdetails = getGstDetails(clientId, store.getStateCode());
			StoreVO storeVo =storeMapper. convertToVo(store);
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

	

	@Override
	public String assignStoreToClientDomain(DomianStoresVo vo) throws Exception {
		// logger.info("################ assignStoreToClientDomain method starts
		// ###########");

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
				// logger.info("################ assignStoreToClientDomain method ends
				// ###########");

				return "success";
			} else {
				// logger.debug("Selected Domain not found ");
				// logger.error("Selected Domain not found ");
				throw new RuntimeException("Selected Domain not  found ");
			}

		} catch (Exception e) {
			// logger.debug(e.getMessage());
			// logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	private List<StoreVO> getUserName(List<StoreVO> storeVo) {

		List<Long> ids = storeVo.stream().map(s -> s.getCreatedBy()).collect(Collectors.toList());
		List<UserDetailsVO> userDetailsList = userserviceImpl.getUserDetailsByIds(ids);
		Map<Long, String> userDetailsMap = userDetailsList.stream()
				.collect(Collectors.toMap(UserDetailsVO::getId, UserDetailsVO::getUserName));
		storeVo.stream().forEach(storesVo -> {
			if (userDetailsMap.containsKey(storesVo.getCreatedBy())) {
				storesVo.setUserName(userDetailsMap.get(storesVo.getCreatedBy()));
			}
		});
		return storeVo;

	}

	@Override
	public List<StoreVO> getStoresOnFilter(GetStoresRequestVo vo, Long clientId) {
		// logger.info("################ getStoresOnFilter method starts ###########");

		if (0L != vo.getDistrictId() && null != vo.getStateId() && "" != vo.getStateId() && null != vo.getStoreName()
				&& "" != vo.getStoreName()) {
			List<Store> stores = storeRepo.findByStateCodeAndDistrictIdAndNameAndClient_Id(vo.getStateId(),
					vo.getDistrictId(), vo.getStoreName(), clientId);
			if (!CollectionUtils.isEmpty(stores)) {
				// logger.info("################ getStoresOnFilter method ends ###########");
				List<StoreVO> storeVo = storeMapper.convertStoresToVO(stores);
				List<StoreVO> storesVo = getUserName(storeVo);
				return storesVo;
			} else {
				// logger.debug("Stores not found with this DistrictId : " +
				// vo.getDistrictId());
				// logger.error("Stores not found with this DistrictId : " +
				// vo.getDistrictId());
				throw new RuntimeException("Stores not found with this given information : " + vo.getDistrictId());

			}

		}

		if (0L != vo.getDistrictId() && null != vo.getStateId() && "" != vo.getStateId()) {
			List<Store> stores = storeRepo.findByStateCodeAndDistrictIdAndClient_Id(vo.getStateId(), vo.getDistrictId(),
					clientId);
			if (!CollectionUtils.isEmpty(stores)) {
				// logger.info("################ getStoresOnFilter method ends ###########");

				List<StoreVO> storeVo = storeMapper.convertStoresToVO(stores);
				List<StoreVO> storesVo = getUserName(storeVo);
				return storesVo;
			} else {
				// logger.debug("Stores not found with this DistrictId : " +
				// vo.getDistrictId());
				// logger.error("Stores not found with this DistrictId : " +
				// vo.getDistrictId());
				throw new RuntimeException("Stores not found with this DistrictId  and stateId: " + vo.getDistrictId()
						+ "" + vo.getStateId());

			}

		}
		if (null != vo.getStoreName() && "" != vo.getStoreName() && null != vo.getStateId() && "" != vo.getStateId()
				&& 0L == vo.getDistrictId()) {
			List<Store> stores = storeRepo.findByStateCodeAndNameAndClient_Id(vo.getStateId(), vo.getStoreName(),
					clientId);
			if (!CollectionUtils.isEmpty(stores)) {
				// logger.info("################ getStoresOnFilter method ends ###########");
				List<StoreVO> storeVo = storeMapper.convertStoresToVO(stores);
				List<StoreVO> storesVo = getUserName(storeVo);
				return storesVo;
			} else {
				// logger.debug("Stores not found with this DistrictId : " +
				// vo.getDistrictId());
				// logger.error("Stores not found with this DistrictId : " +
				// vo.getDistrictId());
				throw new RuntimeException("Stores not found with this StateId and storeName : " + vo.getStateId());

			}

		}

		if (null != vo.getStateId() && "" != vo.getStateId()) {
			List<Store> stores = storeRepo.findByStateCodeAndClient_Id(vo.getStateId(), clientId);
			if (!CollectionUtils.isEmpty(stores)) {
				List<StoreVO> storeVo = storeMapper.convertStoresToVO(stores);
				List<StoreVO> storesVo = getUserName(storeVo);
				return storesVo;
			} else {
				// logger.debug("Stores not found with this StateId : " + vo.getStateId());
				// logger.error("Stores not found with this StateId : " + vo.getStateId());
				throw new RuntimeException("Stores not found with this StateId : " + vo.getStateId());
			}
		}

		if (null != vo.getStoreName() && "" != vo.getStoreName()) {
			Store store = storeRepo.findByNameAndClient_Id(vo.getStoreName(), clientId);
			if (!ObjectUtils.isEmpty(store)) {
				// logger.info("################ getStoresOnFilter method ends ###########");
				List<Store> stores = new ArrayList<>();
				stores.add(store);
				List<StoreVO> storeVo = storeMapper.convertStoresToVO(stores);
				List<StoreVO> storesVo = getUserName(storeVo);
				return storesVo;
			} else {
				// logger.debug("Stores not found with this CityId : " + vo.getCityId());
				// logger.error("Stores not found with this CityId : " + vo.getCityId());
				throw new RuntimeException("Stores not found with this CityId : " + vo.getCityId());

			}
		}

		// logger.debug("Please provide valid information");
		// logger.error("Please provide valid information");
		throw new RuntimeException("Please provide valid information");
	}

	@Override
	public List<Store> getStoresForGivenIds(List<Long> storeIds) {

		// logger.info("################ getStoresForGivenIds method starts
		// ###########");

		if (!CollectionUtils.isEmpty(storeIds)) {
			List<Store> stores = storeRepo.findByIdIn(storeIds);
			if (!CollectionUtils.isEmpty(storeIds)) {
				// logger.info("################ getStoresForGivenIds method ends ###########");

				return stores;
			} else {
				// logger.debug("No stores found with these storeId's");
				// logger.error("No stores found with these storeId's");
				throw new RuntimeException("No stores found with these storeId's");
			}
		} else {
			// logger.debug("Store Id's should not be null");
			// logger.error("Store Id's should not be null");
			throw new RuntimeException("Store Id's should not be null");
		}

	}

	@Override
	public List<StoreVO> getStoresForGivenIdsForHsn(List<Long> storeIds) {

		// logger.info("################ getStoresForGivenIds method starts
		// ###########");

		if (!CollectionUtils.isEmpty(storeIds)) {
			List<Store> stores = storeRepo.findByIdIn(storeIds);
			if (!CollectionUtils.isEmpty(storeIds)) {
				// logger.info("################ getStoresForGivenIds method ends ###########");
				List<StoreVO> stList = new ArrayList<>();
				stores.stream().forEach(s -> {
					StoreVO svo = new StoreVO();
					svo.setId(s.getId());
					svo.setName(s.getName());
					svo.setStateId(s.getStateId());
					svo.setStateCode(s.getStateCode());
					svo.setDistrictId(s.getDistrictId());
					svo.setCityId(s.getCityId());
					svo.setArea(s.getArea());
					svo.setAddress(s.getAddress());
					svo.setPhoneNumber(s.getPhoneNumber());
					svo.setIsActive(s.getIsActive());
					svo.setCreatedBy(s.getCreatedBy());
					svo.setCreatedDate(s.getCreatedDate());
					svo.setLastModifyedDate(s.getLastModifiedDate());
					stList.add(svo);
				});
				return stList;
			} else {
				// logger.debug("No stores found with these storeId's");
				// logger.error("No stores found with these storeId's");
				throw new RuntimeException("No stores found with these storeId's");
			}
		} else {
			// logger.debug("Store Id's should not be null");
			// logger.error("Store Id's should not be null");
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
				// logger.error("gst Details not found with the given Details");
				throw new RuntimeException("gst Details not found with the given Details");
			}

		} else {
			// logger.error("clientId and sateCode should not be null");
			throw new RuntimeException("clientId and sateCode should not be null");
		}

	}

	@Override
	public String deleteStore(Long id) {

		Optional<Store> store = storeRepo.findById(id);

		if (store.isPresent()) {
			Store storeEntity = store.get();

			storeEntity.setIsActive(Boolean.FALSE);

			storeRepo.save(storeEntity);
			return "Store Deleted with storeId : " + storeEntity.getId();

		} else {
			throw new RuntimeException("No stores found with these storeId" + id);
		}

	}

	@Override
	public Store getActiveStores(Long userId, Long clientId) {
		Store store = storeRepo.findBystoreUsers_IdAndClient_IdAndIsActive(userId, clientId, Boolean.TRUE);
		if (store != null) {

			return store;
		} else
			return null;
	}

	@Override
	public List<StoreVO> getStores() {
		List<Store> stores = storeRepo.findAll();
		if (!CollectionUtils.isEmpty(stores)) {
			List<StoreVO> storeVo = storeMapper.convertStoresToVO(stores);
			return storeVo;
		} else {
			return Collections.EMPTY_LIST;

		}
	}

	@Override
	public List<StoreVO> getStoresByUser(long userId, Boolean isActive) {
		List<Store> stores = new ArrayList<>();
		if (isActive == Boolean.FALSE) {
			stores = storeRepo.findByStoreUsers_Id(userId);
		} else {
			stores = storeRepo.findByStoreUsers_IdAndIsActive(userId, Boolean.TRUE);
		}
		if (CollectionUtils.isEmpty(stores)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No stores found for client:" + userId);
		}
		List<StoreVO> storesVO = new ArrayList<>();

		stores.stream().forEach(store -> {
			// GstDetails gstdetails = getGstDetails(clientId, store.getStateCode());
			StoreVO storeVo =storeMapper. convertToVo(store);
			// storeVo.setGstNumber(gstdetails.getGstNumber());
			storesVO.add(storeVo);
		});
		return storesVO;
	}

	@Override
	public StoreVO getStore(Long storeId) {
		Optional<Store> storeOptional = storeRepo.findById(storeId);
		if (storeOptional.isPresent()) {
			Store store = storeOptional.get();
			StoreVO storeVO = new StoreVO();
			storeVO.setId(store.getId());
			storeVO.setName(store.getName());
			return storeVO;
		} else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "store was not found with id:" + storeId);

	}

}
