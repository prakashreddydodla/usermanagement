package com.otsi.retail.authservice.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.UserDeatils;


@Repository
public interface UserRepo extends JpaRepository<UserDeatils,Long> {

	Optional<UserDeatils> findByUserName(String userName);

	Optional<UserDeatils> findByPhoneNumber(String name);

	List<UserDeatils> findByUserAv_IntegerValue(long clientId);

	boolean existsByUserName(String username);

	List<UserDeatils> findByClientDomiansId(long clientDomianId);


	Page<UserDeatils> findByUserAv_NameAndUserAv_IntegerValue(String clientId, int clientId2, Pageable pageable);


	Optional<UserDeatils> findByPhoneNumberAndRoleRoleName(String mobileNo, String roleName);

	List<UserDeatils> findByRoleId(long roleId);

	List<UserDeatils> findByStores_Id(long storeId);

	Optional<UserDeatils> findByPhoneNumberAndIsCustomer(String mobileNo, Boolean true1);

	boolean existsByPhoneNumber(String phoneNumber);

	Optional<UserDeatils> findByUserId(Long l);

	boolean existsByUserNameAndIsCustomer(String username, Boolean false1);

	boolean existsByPhoneNumberAndIsCustomer(String phoneNumber, Boolean false1);

	List<UserDeatils> findByRoleIdAndIsActive(long roleId, boolean active);

	List<UserDeatils> findByStores_IdAndIsActive(long storeId, Boolean false1);

	Optional<UserDeatils> findByUserNameAndIsCustomer(String mobileNo, Boolean false1);

	
  
  //  @Query("select count(user_Id) as usercount from user_deatils as u GROUP BY u.role_Id")
	//int countByRoleId(long roleId);

	long countByRoleId(long roleId);


	//List<UserDeatils> findByIsActiveAndIsCustomer(Boolean true1, Boolean false1);

	//List<UserDeatils> findByStoresIdAndisCustomer(long id, Boolean false1);

	//List<UserDeatils> findByRoleRoleIdAndIsCustomer(long roleId, Boolean false1);

	

	List<UserDeatils> findByclientDomians_clientIdAndIsActiveAndIsCustomer(Long clientId, Boolean true1,
			Boolean false1);


	List<UserDeatils> findByClientDomiansClientIdAndRoleRoleNameAndIsCustomer(Long clientId, String r, Boolean false1);

	//List<UserDeatils> findByclientDomians_clientIdAndStores_NameAndIsCustomer(Long clientId, String s, Boolean false1);

	List<UserDeatils> findByclientDomians_clientIdAndIsCustomer(Long clientId, Boolean false1);

	List<UserDeatils> findByRoleRoleNameAndIsActiveAndUserId(String roleName, Boolean true1,Long userId);

	List<UserDeatils> findByRoleRoleNameAndUserId(String roleName,Long userId);

	List<UserDeatils> findByStores_NameAndIsActiveAndUserId(String storeName, Boolean false1,Long userId);

	List<UserDeatils> findByStores_NameAndUserId(String storeName,Long userId);

	Page<UserDeatils> findByStores_NameAndRoleRoleNameAndIsActive(String storeName, String roleName, Boolean true1, Pageable pageable);

	List<UserDeatils> findByStores_NameAndRoleRoleNameAndUserId(String storeName, String roleName,Long userId);

	List<UserDeatils> findByIsActive(Boolean true1);

	

	List<UserDeatils> findByuserIdInAndIsCustomer(List<Long> userIds, Boolean false1);

	List<UserDeatils> findByIsActiveAndClientDomiansId(Boolean true1, long clientDomainId);

	List<UserDeatils> findByIsActiveAndClientDomians_ClientId(Boolean true1, long clientDomainId);

	Page<UserDeatils> findByIsActiveAndUserAv_NameAndUserAv_IntegerValue(Boolean true1, String clientId,
			int clientDomainId, Pageable pageable);

	Page<UserDeatils> findByStores_NameAndRoleRoleNameAndClientDomians_Client_Id(String storeName, String roleName,
			Long clientId, Pageable pageable);

	Page<UserDeatils> findByRoleRoleNameAndIsActiveAndClientDomians_Client_Id(String roleName, Boolean true1,
			Long clientId, Pageable pageable);

	Page<UserDeatils> findByRoleRoleNameAndClientDomians_Client_Id(String roleName, Long clientId, Pageable pageable);

	Page<UserDeatils> findByStores_NameAndIsActiveAndClientDomians_Client_Id(String storeName, Boolean true1,
			Long clientId, Pageable pageable);

	Page<UserDeatils> findByStores_NameAndClientDomians_Client_Id(String storeName, Long clientId, Pageable pageable);

	Page<UserDeatils> findByUserId(Long id, Pageable pageable);

	Page<UserDeatils> findByUserName(String name, Pageable pageable);

	Page<UserDeatils> findByPhoneNumber(String phoneNo, Pageable pageable);

	






}
