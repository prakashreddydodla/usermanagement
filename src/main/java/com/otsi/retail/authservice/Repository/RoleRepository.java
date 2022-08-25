/**
 * 
 */
package com.otsi.retail.authservice.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.Role;

/**
 * @author Manideep
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	//@Query(value="select * from role WHERE role_name LIKE '---'" ,nativeQuery=true)
	Optional<Role> findByRoleName(String roleName);

	List<Role> findByClientDomianId(long clientId);

	List<Role> findByClientDomian_client_Id(long clientId);
	
	List<Role> findByClientId(Long clientId);

	boolean existsByRoleNameIgnoreCase(String roleName);

	List<Role> findByCreatedBy(Long long1);

	List<Role> findByCreatedDate(LocalDateTime createdDate);

	Optional<Role> findById(long roleId);

	Optional<Role> findByRoleNameAndCreatedByAndCreatedDate(String roleName, Long string, LocalDateTime localDate);

	Optional<Role> findByRoleNameAndCreatedDate(String roleName, LocalDateTime createdDate);

	Optional<Role> findByRoleNameAndCreatedBy(String roleName, Long createdBy);

	List<Role> findByCreatedByAndCreatedDate(Long createdBy, LocalDateTime createdDate);

	List<Role> findByCreatedDateBetweenAndClientId(LocalDateTime createdDatefrom, LocalDateTime createdDateTo,Long clientId);

	Optional<Role> findByRoleNameAndCreatedDateBetween(String roleName, LocalDateTime createdDatefrom,
			LocalDateTime createdDateTo);

	List<Role> findByCreatedByAndCreatedDateBetween(Long createdBy, LocalDateTime createdDatefrom,
			LocalDateTime createdDateTo);
	///////////
	Optional<Role> findByRoleNameAndCreatedByAndCreatedDateBetweenAndClient_Id(String roleName,
			Long createdBy, LocalDateTime createdDatefrom, LocalDateTime createdDateTo, Long clientId);

	Optional<Role> findByRoleNameAndCreatedDateBetweenAndClient_Id(String roleName,
			LocalDateTime createdDatefrom, LocalDateTime createdDateTo, Long clientId);

	Optional<Role> findByRoleNameAndCreatedByAndClient_Id(String roleName, Long createdBy, Long clientId);

	List<Role> findByCreatedByAndCreatedDateBetweenAndClient_Id(Long createdBy,
			LocalDateTime createdDatefrom, LocalDateTime createdDateTo, Long clientId);

	Optional<Role> findByRoleNameAndClient_Id(String roleName, Long clientId);

	List<Role> findByCreatedByAndClient_Id(Long createdBy, Long clientId);

	

	

}
