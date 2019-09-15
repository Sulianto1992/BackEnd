package com.optimum.employeedashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.optimum.employeedashboard.dao.EmployeeDao;
import com.optimum.employeedashboard.model.Employee;
import com.optimum.employeedashboard.model.TestDetail;
import com.optimum.employeedashboard.service.EmployeeService;
import com.optimum.employeedashboard.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/register")
	public boolean registerAccount(@RequestBody Employee emp) {
		return employeeService.register(emp);
	}

	@PostMapping("/{empid}/login")
	public boolean loginAccount(@RequestBody Employee emp, @PathVariable int empid) {
		return employeeService.login(empid, emp.getPassword());
	}
	
	@PostMapping ("/forgot-password")
	public boolean forgotPassword(@RequestBody Employee emp) {
		return employeeService.forgetPassword(emp.getEmail());
	}

	// THIS MAPPING should ONLY BE USED to generate first row of table
	// to allow for Login authentication testing
	// REMOVE THIS method in controller & service layers afterwards
	// As all new employee accounts will be created through the Register method
	// instead
	@PostMapping("/create")
	public boolean createAccount(@RequestBody Employee emp) {
		return employeeService.createEmpAccount(emp);
	}

	@PostMapping("/{empid}/update-profile-name")
	// updates Employee profile name
	public boolean updateProfileName(@RequestBody Employee emp) {
		return employeeService.editProfileName(emp.getName(), emp.getEmployeeId());
	}

	@PostMapping("/{empid}/update-profile-password")
	// updates Employee profile password
	public boolean updateProfilePassword(@RequestParam String oldPassword, @RequestParam String newPassword,
			@PathVariable int empid) {
		return employeeService.editProfilePassword(oldPassword, newPassword, empid);
	}

	@PostMapping("/{empid}/update-profile-image")
	// updates Employee profile image
	public boolean updateProfileImage(@RequestBody Employee emp, @PathVariable int empid) {
		return employeeService.editProfileImage(emp.getProfileImg(), empid);
	}

	@GetMapping("/{empid}/profile")
	// retrieves Employee profile
	public Employee getProfile(@PathVariable int empid) {
		return employeeService.getEmployeeProfile(empid);
	}

	@GetMapping("/{empid}/tests")
	// retrieves all available test categories
	public List<TestDetail> getTestDetailCategories() {
		return employeeService.invokeGetActiveTestDetailCategories();
	}

}
