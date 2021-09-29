package com.otsi.retail.authservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.otsi.retail.authservice.Entity.UserDeatils;

public interface UserRepo extends JpaRepository<UserDeatils,Long> {

}
