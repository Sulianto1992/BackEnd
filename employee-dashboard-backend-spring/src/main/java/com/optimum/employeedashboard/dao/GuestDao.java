package com.optimum.employeedashboard.dao;

import org.springframework.stereotype.Repository;

import com.optimum.employeedashboard.model.Guest;

@Repository
public interface GuestDao {

	boolean createGuest(Guest guest);
	
}
