/**
 * 
 */
package com.otsi.retail.authservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otsi.retail.authservice.Entity.ChildPrivilege;

/**
 * @author Sudheer.Swamy
 *
 */
@Repository
public interface ChildPrivilegeRepo extends JpaRepository<ChildPrivilege, Long> {

	List<ChildPrivilege> findBySubPrivillageId(long subPrivillageId);

	
	
	

}
