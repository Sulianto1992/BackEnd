package com.optimum.employeedashboard.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.optimum.employeedashboard.model.Employee;


public interface EmployeeDao extends JpaRepository<Employee, Integer> {

	// For Admin to use
	@Query("Select emp from Employee emp where emp.isApproved = ?1")
	// gets all employees that are approved
	// verified employee accounts
	List<Employee> getEmployeeByIsApproved(boolean isApproved);
	
	@Query("Select emp from Employee emp where emp.name like %?1%")
	// supports searching of employee by name for HR & admin
	List<Employee> getEmployeesByNameContaining(String name); 

	@Query("Select emp from Employee emp where emp.isActive = ?1")
	// gets all employees that are active or inactive
	// supports filter function for admin to manage accounts
	List<Employee> getEmployeeByIsActive(boolean isActive);
  
  @Query("Select emp from Employee emp where emp.email = ?1")
	// gets employee object using email
	// used in forgot password method
	Optional <Employee> getEmployeeByEmail(String email);

	Optional<List<Employee>> findByNameContaining(String name);
	
	
	
}
