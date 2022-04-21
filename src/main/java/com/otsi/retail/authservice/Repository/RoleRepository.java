/**
 * 
 */
package com.otsi.retail.authservice.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.Role;

/**
 * @author Manideep
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByRoleName(String roleName);

	List<Role> findByClientDomian_clientDomainaId(long clientId);

	List<Role> findByClientDomian_client_Id(long clientId);

	boolean existsByRoleNameIgnoreCase(String roleName);

	List<Role> findByCreatedBy(String string);

	List<Role> findByCreatedDate(LocalDate createdDate);

	Optional<Role> findByRoleId(long roleId);

	Optional<Role> findByRoleNameAndCreatedByAndCreatedDate(String roleName, String createdBy, LocalDate createdDate);

	Optional<Role> findByRoleNameAndCreatedDate(String roleName, LocalDate createdDate);

	Optional<Role> findByRoleNameAndCreatedBy(String roleName, String createdBy);

	List<Role> findByCreatedByAndCreatedDate(String createdBy, LocalDate createdDate);

	

}
