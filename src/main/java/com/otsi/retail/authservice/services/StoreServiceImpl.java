package com.otsi.retail.authservice.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.Districts;
import com.otsi.retail.authservice.Entity.GstDetails;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.Entity.UserDeatils;
import com.otsi.retail.authservice.Repository.ChannelRepo;
import com.otsi.retail.authservice.Repository.GstRepository;
import com.otsi.retail.authservice.Repository.StoreRepo;
import com.otsi.retail.authservice.Repository.UserRepo;
import com.otsi.retail.authservice.requestModel.DomianStoresVo;
import com.otsi.retail.authservice.requestModel.GetStoresRequestVo;
import com.otsi.retail.authservice.requestModel.StoreVo;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepo storeRepo;

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private GstRepository gstRepo;

	@Autowired
	private ChannelRepo clientChannelRepo;
	private Logger logger = LogManager.getLogger(StoreServiceImpl.class);

	@Override
	@Transactional(rollbackOn = { RuntimeException.class })
	public String createStore(StoreVo vo) throws RuntimeException, Exception {

		Store storeEntity = new Store();
		try {
			logger.info("Create store method Starts");
			storeEntity.setName(vo.getName());
			storeEntity.setAddress(vo.getAddress());
			storeEntity.setStateId(vo.getStateId());
			storeEntity.setDistrictId(vo.getDistrictId());
			storeEntity.setCityId(vo.getCityId());
			storeEntity.setArea(vo.getArea());
			storeEntity.setPhoneNumber(vo.getPhoneNumber());
			storeEntity.setCreatedBy(vo.getCreatedBy());
			storeEntity.setStateCode(vo.getStateCode());
			if(null!= vo.getGstNumber()) {
				Optional<GstDetails> gstDetailsopt= gstRepo.findByGstNumber(vo.getGstNumber());
				if(!gstDetailsopt.isPresent()) {
					GstDetails gstInfo =new GstDetails();
					gstInfo.setClientId(vo.getClientId());
					gstInfo.setCreatedBy(vo.getCreatedBy());
					gstInfo.setCreatedDate(LocalDate.now());
					gstInfo.setGstNumber(vo.getGstNumber());
					gstInfo.setStateCode(vo.getStateCode());
			        gstRepo.save(gstInfo);
					
				}
				
			}else {
				logger.debug("gstNumber should not be null");
				logger.error("gstNumber should not be null");
				throw new RuntimeException("gstNumber should not be null");
			}
			
			if (null != vo.getStoreOwner()) {
				Optional<UserDeatils> userfromDb = userRepo.findById(vo.getStoreOwner().getUserId());
				if (userfromDb.isPresent()) {
					storeEntity.setStoreOwner(userfromDb.get());
				} else {
					logger.debug("No user found in database for StoreOwner");
					logger.error("No user found in database for StoreOwner");
					throw new RuntimeException("No user found in database for StoreOwner");
				}
			}
			if (0L != vo.getDomainId()) {
				Optional<ClientDomains> clientDomian = clientChannelRepo.findById(vo.getDomainId());
				if (clientDomian.isPresent()) {
					storeEntity.setClientDomianlId(clientDomian.get());
				} else {
					logger.debug("No client Domian found with this DomianId :" + vo.getDomainId());
					logger.error("No client Domian found with this DomianId :" + vo.getDomainId());
					throw new RuntimeException("No client Domian found with this DomianId :" + vo.getDomainId());
				}
			}
			storeEntity.setCreatedDate(LocalDate.now());
			storeEntity.setLastModifyedDate(LocalDate.now());
			Store savedStore = storeRepo.save(storeEntity);
			logger.info("Create store method End");
			return "Store created with storeId : " + savedStore.getId();
		} catch (RuntimeException re) {
			logger.debug("Error occurs while Creating Store :" + re.getMessage());
			logger.error("Error occurs while Creating Store :" + re.getMessage());
			throw new Exception(re.getMessage());
		} catch (Exception e) {
			logger.debug("Error occurs while Creating Store :" + e.getMessage());
			logger.error("Error occurs while Creating Store :" + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	/////////////////////////
	@Override
	@Transactional(rollbackOn = { RuntimeException.class })
	public String updateStore(StoreVo vo) throws RuntimeException, Exception {

		// Store storeEntity = new Store();
		try {
			logger.info("Update store method Starts");
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
			storeEntity.setLastModifyedDate(LocalDate.now());
			if (null != vo.getStoreOwner()) {
				Optional<UserDeatils> userfromDb = userRepo.findById(vo.getStoreOwner().getUserId());
				if (userfromDb.isPresent()) {
					storeEntity.setStoreOwner(userfromDb.get());
				} else {
					logger.debug("No user found in database for StoreOwner");
					logger.error("No user found in database for StoreOwner");
					throw new RuntimeException("No user found in database for StoreOwner");
				}
			}
			if (0L != vo.getDomainId()) {
				Optional<ClientDomains> clientDomian = clientChannelRepo.findById(vo.getDomainId());
				if (clientDomian.isPresent()) {
					storeEntity.setClientDomianlId(clientDomian.get());
				} else {
					logger.debug("No client Domian found with this DomianId :" + vo.getDomainId());
					logger.error("No client Domian found with this DomianId :" + vo.getDomainId());
					throw new RuntimeException("No client Domian found with this DomianId :" + vo.getDomainId());
				}
			}
			storeEntity.setLastModifyedDate(LocalDate.now());
			Store savedStore = storeRepo.save(storeEntity);
			logger.info("Update store method End");
			return "Store Updated with storeId : " + savedStore.getId();
		} catch (RuntimeException re) {
			logger.debug("Error occurs while updating Store :" + re.getMessage());
			logger.error("Error occurs while updating Store :" + re.getMessage());
			throw new Exception(re.getMessage());
		} catch (Exception e) {
			logger.debug("Error occurs while updating Store :" + e.getMessage());
			logger.error("Error occurs while updating Store :" + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	///////////////////////////

	@Override
	public List<Store> getStoresForClientDomian(long clientDomianId) throws Exception {
		try {
			logger.info("**********getStoresForClientDomia Method Statrs");
			List<Store> stores = storeRepo.findByClientDomianlId_ClientDomainaId(clientDomianId);
			if (!CollectionUtils.isEmpty(stores)) {
				logger.info("**********getStoresForClientDomia Method Ends");
				return stores;
			} else {
				logger.debug("Error occurs while get stores for Cilent Domian: No stores found");
				logger.error("Error occurs while get stores for Cilent Domian: No stores found");
				throw new Exception("No stores found");
			}
		} catch (Exception e) {
			logger.debug("Error occurs while get stores for Cilent Domian " + e.getMessage());
			logger.error("Error occurs while get stores for Cilent Domian " + e.getMessage());
			throw new Exception(e.getMessage());
		}

	}

	@Override
	public List<Store> getStoresForClient(long clientId) throws Exception {
		try {
			logger.info("################  getStoresForClient  method starts ###########");
			List<Store> stores = storeRepo.findByClientDomianlId_Client_Id(clientId);
			if (!CollectionUtils.isEmpty(stores)) {
				logger.info("################  getStoresForClient  method ends ###########");
				return stores;
			} else
			logger.debug("No stores found");
			logger.error("No stores found");
			throw new Exception("No stores found");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String assignStoreToClientDomain(DomianStoresVo vo) throws Exception {
		logger.info("################  assignStoreToClientDomain  method starts ###########");

		try {
			Optional<ClientDomains> clientDetails = clientChannelRepo.findById(vo.getDomain().getClientChannelid());

			if (clientDetails.isPresent()) {
				List<Store> selectedStores = new ArrayList<>();
				ClientDomains clientDomain = clientDetails.get();
				vo.getStores().stream().forEach(a -> {
					selectedStores.add(storeRepo.findById(a.getId()).get());
				});
				clientDomain.setStore(selectedStores);

				clientChannelRepo.save(clientDomain);
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
	public List<Store> getStoresOnFilter(GetStoresRequestVo vo) {
		logger.info("################  getStoresOnFilter  method starts ###########");
		
		
		if (0L != vo.getDistrictId() && null != vo.getStateId()&& null!=vo.getCityId()) {
			List<Store> stores = storeRepo.findByStateCodeAndDistrictIdAndCityId(vo.getStateId(), vo.getDistrictId(),vo.getCityId());
			if (!CollectionUtils.isEmpty(stores)) {
				logger.info("################  getStoresOnFilter  method ends ###########");

				return stores;
			} else {
				logger.debug("Stores not found with this DistrictId : " + vo.getDistrictId());
				logger.error("Stores not found with this DistrictId : " + vo.getDistrictId());
				throw new RuntimeException("Stores not found with this DistrictId : " + vo.getDistrictId());

			}
			

		}
		
		if (0L != vo.getDistrictId() && null != vo.getStateId()) {
			List<Store> stores = storeRepo.findByStateCodeAndDistrictId(vo.getStateId(), vo.getDistrictId());
			if (!CollectionUtils.isEmpty(stores)) {
				logger.info("################  getStoresOnFilter  method ends ###########");

				return stores;
			} else {
				logger.debug("Stores not found with this DistrictId : " + vo.getDistrictId());
				logger.error("Stores not found with this DistrictId : " + vo.getDistrictId());
				throw new RuntimeException("Stores not found with this DistrictId : " + vo.getDistrictId());

			}
			

		}

		if (null != vo.getStateId()) {
			List<Store> stores = storeRepo.findByStateCode(vo.getStateId());
			if (!CollectionUtils.isEmpty(stores)) {
				return stores;
			} else {
				logger.debug("Stores not found with this StateId : " + vo.getStateId());
				logger.error("Stores not found with this StateId : " + vo.getStateId());
				throw new RuntimeException("Stores not found with this StateId : " + vo.getStateId());
			}
		}
		if (null != vo.getCityId()) {
			List<Store> stores = storeRepo.findByCityId(vo.getCityId());
			if (!CollectionUtils.isEmpty(stores)) {
				logger.info("################  getStoresOnFilter  method ends ###########");

				return stores;
			} else {
				logger.debug("Stores not found with this CityId : " + vo.getCityId());
				logger.error("Stores not found with this CityId : " + vo.getCityId());
				throw new RuntimeException("Stores not found with this CityId : " + vo.getCityId());

			}
		}
		
		
		if (null != vo.getStoreName()) {
			Optional<Store> storeOptional = storeRepo.findByName(vo.getStoreName());
			if (storeOptional.isPresent()) {
				List<Store> stores = new ArrayList<>();
				stores.add(storeOptional.get());
				logger.info("################  getStoresOnFilter  method ends ###########");

				return stores;
			} else {
				logger.debug("Stores not found with this StoreName : " + vo.getStoreName());
				logger.error("Stores not found with this StoreName : " + vo.getStoreName());
				throw new RuntimeException("Stores not found with this StoreName : " + vo.getStoreName());
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
	public GstDetails getGstDetails(long clientId,String stateCode) {
		logger.info("################  getGstDetails  method starts ###########");
		if (clientId!=0L&& stateCode!=null) {
	    GstDetails gstDetails = gstRepo.findByClientIdAndStateCode(clientId,stateCode);
		if(gstDetails!= null) {
			logger.info("################  getGstDetails  method ends ###########");
			return gstDetails;
		}
		else {
			logger.debug("gst Details not found with the given Details");
			logger.error("gst Details not found with the given Details");
			throw new RuntimeException("gst Details not found with the given Details");
		}
			
		}else {
			logger.debug("clientId and sateCode should not be null");
			logger.error("clientId and sateCode should not be null");
			throw new RuntimeException("clientId and sateCode should not be null");
		}
		
	}
}
