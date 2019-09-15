package com.optimum.employeedashboard.dao;

import org.springframework.stereotype.Repository;

import com.optimum.employeedashboard.model.GuestLogin;


@Repository
public interface GuestLoginDao {
	
	GuestLogin getGuestLogin(String username);

}
