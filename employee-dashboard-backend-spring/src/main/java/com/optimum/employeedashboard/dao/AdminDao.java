package com.optimum.employeedashboard.dao;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.optimum.employeedashboard.model.Admin;
import com.optimum.employeedashboard.model.Employee;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer>{

	@Query("Select admin from Admin admin where admin.email = ?1")
	// gets employee object using email
	// used in forgot password method
	Optional <Admin> getAdminByEmail(String email);
	
}
