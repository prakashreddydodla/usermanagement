package com.otsi.retail.authservice.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otsi.retail.authservice.Entity.UserDeatils;

public interface UserRepo extends JpaRepository<UserDeatils,Long> {

	Optional<UserDeatils> findByUserName(String userName);

}
