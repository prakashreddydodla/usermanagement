/**
 * 
 */
package com.otsi.retail.authservice.Repository;

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

}
