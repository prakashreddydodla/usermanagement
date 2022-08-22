package com.otsi.retail.authservice.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {

	Optional<UserDetails> findByUserName(String userName);

	Optional<UserDetails> findByPhoneNumber(String name);

	List<UserDetails> findByUserAv_IntegerValue(long clientId);

	boolean existsByUserName(String username);

	List<UserDetails> findByClientDomiansId(long clientDomianId);

	Page<UserDetails> findByUserAv_NameAndUserAv_IntegerValue(String clientId, Long clientId2, Pageable pageable);

	Optional<UserDetails> findByPhoneNumberAndRoleRoleName(String mobileNo, String roleName);

	List<UserDetails> findByRoleId(long roleId);

	List<UserDetails> findByStores_Id(long storeId);

	Optional<UserDetails> findByPhoneNumberAndIsCustomer(String mobileNo, Boolean true1);

	boolean existsByPhoneNumber(String phoneNumber);

	Optional<UserDetails> findById(Long l);

	boolean existsByUserNameAndIsCustomer(String username, Boolean false1);

	boolean existsByPhoneNumberAndIsCustomer(String phoneNumber, Boolean false1);

	List<UserDetails> findByRoleIdAndIsActive(long roleId, boolean active);

	List<UserDetails> findByStores_IdAndIsActive(long storeId, Boolean false1);

	Optional<UserDetails> findByUserNameAndIsCustomer(String mobileNo, Boolean false1);

	long countByRoleId(long roleId);

	List<UserDetails> findByclientDomians_clientIdAndIsActiveAndIsCustomer(Long clientId, Boolean true1,
			Boolean false1);

	List<UserDetails> findByClientDomiansClientIdAndRoleRoleNameAndIsCustomer(Long clientId, String r, Boolean false1);

	List<UserDetails> findByclientDomians_clientIdAndIsCustomer(Long clientId, Boolean false1);

	Page<UserDetails> findByRoleRoleNameAndIsActive(String roleName, Boolean true1, Pageable pageable);

	List<UserDetails> findByRoleRoleNameAndId(String roleName, Long userId);

	List<UserDetails> findByStores_NameAndIsActiveAndId(String storeName, Boolean false1, Long userId);

	List<UserDetails> findByStores_NameAndId(String storeName, Long userId);

	Page<UserDetails> findByStores_NameAndRoleRoleNameAndIsActive(String storeName, String roleName, Boolean true1, Pageable pageable);

	List<UserDetails> findByStores_NameAndRoleRoleName(String storeName, String roleName);

	List<UserDetails> findByIsActive(Boolean true1);

	List<UserDetails> findByIdInAndIsCustomer(List<Long> userIds, Boolean false1);

	List<UserDetails> findByIsActiveAndClientDomiansId(Boolean true1, long clientDomainId);

	List<UserDetails> findByIsActiveAndClientDomians_ClientId(Boolean true1, long clientDomainId);

	Page<UserDetails> findByIsActiveAndUserAv_NameAndUserAv_IntegerValue(Boolean true1, String clientId,
			Long clientDomainId, Pageable pageable);

	Page<UserDetails> findByStores_NameAndRoleRoleNameAndClient_Id(String storeName, String roleName,
			Long clientId, Pageable pageable);

	Page<UserDetails> findByStores_NameAndIsActiveAndClient_Id(String storeName, Boolean true1,
			Long clientId, Pageable pageable);

	Page<UserDetails> findByStores_NameAndClient_Id(String storeName, Long clientId, Pageable pageable);

	Page<UserDetails> findByRoleRoleNameAndIsActiveAndClient_Id(String roleName, Boolean false1,
			Long clientId, Pageable pageable);

	Page<UserDetails> findById(Long id, Pageable pageable);

	Page<UserDetails> findByUserName(String name, Pageable pageable);

	Page<UserDetails> findByPhoneNumber(String phoneNo, Pageable pageable);

	Page<UserDetails> findByRoleRoleNameAndClientId(String roleName, Long clientId, Pageable pageable);

	Optional<UserDetails> findByPhoneNumberAndClient_Id(String value, Long clientId);

	List<UserDetails> findByRole_RoleName(String roleName);

	UserDetails findByIdAndRole_RoleNameAndCreatedDateBetween(Long userId, String roleName,
			LocalDateTime createdDatefrom, LocalDateTime createdDateTo);

	Optional<UserDetails> findByIdAndRole_RoleName(Long userId, String roleName);

	UserDetails findByIdAndCreatedDateBetween(Long userId, LocalDateTime createdDatefrom, LocalDateTime createdDateTo);

	List<UserDetails> findByIdInAndCreatedDateBetween(List<Long> userIds, LocalDateTime createdDatefrom,
			LocalDateTime createdDateTo);

	List<UserDetails> findByIdIn(List<Long> userIds);

	List<UserDetails> findByCreatedDateBetween(LocalDateTime createdDatefrom, LocalDateTime createdDateTo);
	

}
