package com.otsi.retail.authservice.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.otsi.retail.authservice.Entity.ClientDomains;
import com.otsi.retail.authservice.Entity.Districts;
import com.otsi.retail.authservice.Entity.Store;
import com.otsi.retail.authservice.Entity.UserDeatils;
import com.otsi.retail.authservice.Repository.ChannelRepo;
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
	private ChannelRepo clientChannelRepo;
	private Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);

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
			if (null != vo.getStoreOwner()) {
				Optional<UserDeatils> userfromDb = userRepo.findById(vo.getStoreOwner().getUserId());
				if (userfromDb.isPresent()) {
					storeEntity.setStoreOwner(userfromDb.get());
				} else {
					logger.error("No user found in database for StoreOwner");
					throw new RuntimeException("No user found in database for StoreOwner");
				}
			}
			if (0L != vo.getDomainId()) {
				Optional<ClientDomains> clientDomian = clientChannelRepo.findById(vo.getDomainId());
				if (clientDomian.isPresent()) {
					storeEntity.setClientDomianlId(clientDomian.get());
				} else {
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
			logger.error("Error occurs while Creating Store :" + re.getMessage());
			throw new Exception(re.getMessage());
		} catch (Exception e) {
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
					logger.error("No user found in database for StoreOwner");
					throw new RuntimeException("No user found in database for StoreOwner");
				}
			}
			if (0L != vo.getDomainId()) {
				Optional<ClientDomains> clientDomian = clientChannelRepo.findById(vo.getDomainId());
				if (clientDomian.isPresent()) {
					storeEntity.setClientDomianlId(clientDomian.get());
				} else {
					logger.error("No client Domian found with this DomianId :" + vo.getDomainId());
					throw new RuntimeException("No client Domian found with this DomianId :" + vo.getDomainId());
				}
			}
			storeEntity.setLastModifyedDate(LocalDate.now());
			Store savedStore = storeRepo.save(storeEntity);
			logger.info("Update store method End");
			return "Store Updated with storeId : " + savedStore.getId();
		} catch (RuntimeException re) {
			logger.error("Error occurs while updating Store :" + re.getMessage());
			throw new Exception(re.getMessage());
		} catch (Exception e) {
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
				logger.error("Error occurs while get stores for Cilent Domian: No stores found");
				throw new Exception("No stores found");
			}
		} catch (Exception e) {
			logger.error("Error occurs while get stores for Cilent Domian " + e.getMessage());
			throw new Exception(e.getMessage());
		}

	}

	@Override
	public List<Store> getStoresForClient(long clientId) throws Exception {
		try {

			List<Store> stores = storeRepo.findByClientDomianlId_Client_Id(clientId);
			if (!CollectionUtils.isEmpty(stores)) {
				return stores;
			} else
				throw new Exception("No stores found");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public String assignStoreToClientDomain(DomianStoresVo vo) throws Exception {

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
				return "success";
			} else {
				throw new RuntimeException("Selected Domain not  found ");
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Store> getStoresOnFilter(GetStoresRequestVo vo) {
		if (0L != vo.getStateId()) {
			List<Store> stores = storeRepo.findByStateId(vo.getStateId());
			if (!CollectionUtils.isEmpty(stores)) {
				return stores;
			} else {
				throw new RuntimeException("Stores not found with this StateId : " + vo.getDistrictId());
			}
		}
		if (null != vo.getCityId()) {
			List<Store> stores = storeRepo.findByCityId(vo.getCityId());
			if (!CollectionUtils.isEmpty(stores)) {
				return stores;
			} else {
				throw new RuntimeException("Stores not found with this CityId : " + vo.getDistrictId());

			}
		}
		if (0L != vo.getDistrictId()&&0L != vo.getStateId()) {
			List<Store> stores = storeRepo.findByStateIdAndDistrictId(vo.getStateId(),vo.getDistrictId());
			if (!CollectionUtils.isEmpty(stores)) {
				return stores;
			} else {
				throw new RuntimeException("Stores not found with this DistrictId : " + vo.getDistrictId());

			}

		}
		if (null != vo.getStoreName()) {
			Optional<Store> storeOptional = storeRepo.findByName(vo.getStoreName());
			if (storeOptional.isPresent()) {
				List<Store> stores = new ArrayList<>();
				stores.add(storeOptional.get());
				return stores;
			} else {
				throw new RuntimeException("Stores not found with this StoreName : " + vo.getStoreName());
			}

		}
		throw new RuntimeException("Please provide valid information");
	}

	@Override
	public List<Store> getStoresForGivenIds(List<Long> storeIds) {

		
		  if(!CollectionUtils.isEmpty(storeIds)) {
		List<Store>  stores=storeRepo.findByIdIn(storeIds);
		  if(!CollectionUtils.isEmpty(storeIds)) {
			  return stores;
		  }else {
			  throw new RuntimeException("No stores found with these storeId's");
		  }
		  }else {
			  throw new RuntimeException("Store Id's should not be null");
		  }
		 
		
	}
}
