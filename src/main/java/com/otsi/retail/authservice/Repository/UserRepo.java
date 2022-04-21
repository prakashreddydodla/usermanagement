package com.otsi.retail.authservice.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.UserDeatils;


@Repository
public interface UserRepo extends JpaRepository<UserDeatils,Long> {

	Optional<UserDeatils> findByUserName(String userName);

	Optional<UserDeatils> findByPhoneNumber(String name);

	List<UserDeatils> findByUserAv_IntegerValue(long clientId);

	boolean existsByUserName(String username);

	List<UserDeatils> findByClientDomians_ClientDomainaId(long clientDomianId);


	List<UserDeatils> findByUserAv_NameAndUserAv_IntegerValue(String clientId, int clientId2);


	Optional<UserDeatils> findByPhoneNumberAndRoleRoleName(String mobileNo, String roleName);

	List<UserDeatils> findByRoleRoleId(long roleId);

	List<UserDeatils> findByStores_Id(long storeId);

	Optional<UserDeatils> findByPhoneNumberAndIsCustomer(String mobileNo, Boolean true1);

	boolean existsByPhoneNumber(String phoneNumber);

	Optional<UserDeatils> findByUserId(Long l);

	boolean existsByUserNameAndIsCustomer(String username, Boolean false1);

	boolean existsByPhoneNumberAndIsCustomer(String phoneNumber, Boolean false1);

	List<UserDeatils> findByRoleRoleIdAndIsActive(long roleId, boolean active);

	List<UserDeatils> findByStores_IdAndIsActive(long storeId, Boolean false1);

	Optional<UserDeatils> findByUserNameAndIsCustomer(String mobileNo, Boolean false1);

	
  
  //  @Query("select count(user_Id) as usercount from user_deatils as u GROUP BY u.role_Id")
	//int countByRoleId(long roleId);

	long countByRoleRoleId(long roleId);


	//List<UserDeatils> findByIsActiveAndIsCustomer(Boolean true1, Boolean false1);

	//List<UserDeatils> findByStoresIdAndisCustomer(long id, Boolean false1);

	//List<UserDeatils> findByRoleRoleIdAndIsCustomer(long roleId, Boolean false1);

	

	List<UserDeatils> findByclientDomians_clientIdAndIsActiveAndIsCustomer(Long clientId, Boolean true1,
			Boolean false1);


	List<UserDeatils> findByclientDomians_clientIdAndRoleRoleNameAndIsCustomer(Long clientId, String r, Boolean false1);

	//List<UserDeatils> findByclientDomians_clientIdAndStores_NameAndIsCustomer(Long clientId, String s, Boolean false1);

	List<UserDeatils> findByclientDomians_clientIdAndIsCustomer(Long clientId, Boolean false1);

	List<UserDeatils> findByRoleRoleNameAndIsActive(String roleName, Boolean true1);

	List<UserDeatils> findByRoleRoleName(String roleName);

	List<UserDeatils> findByStores_NameAndIsActive(String storeName, Boolean false1);

	List<UserDeatils> findByStores_Name(String storeName);

	List<UserDeatils> findByStores_NameAndRoleRoleNameAndIsActive(String storeName, String roleName, Boolean true1);

	List<UserDeatils> findByStores_NameAndRoleRoleName(String storeName, String roleName);

	List<UserDeatils> findByIsActive(Boolean true1);

	

	List<UserDeatils> findByUserIdInAndIsCustomer(List<Long> userIds, Boolean false1);

	List<UserDeatils> findByIsActiveAndClientDomians_ClientDomainaId(Boolean true1, long clientDomainId);

	List<UserDeatils> findByIsActiveAndClientDomians_ClientId(Boolean true1, long clientDomainId);

	List<UserDeatils> findByIsActiveAndUserAv_NameAndUserAv_IntegerValue(Boolean true1, String clientId,
			int clientDomainId);






}
