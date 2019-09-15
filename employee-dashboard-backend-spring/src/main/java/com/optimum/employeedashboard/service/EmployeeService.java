package com.optimum.employeedashboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.optimum.employeedashboard.model.Employee;
import com.optimum.employeedashboard.model.TestDetail;

public interface EmployeeService {

	// Use case: Register

	boolean register(Employee emp);

	// Use case: Login

	boolean login(int employeeId, String password);

	// Use case: Forgot password

	boolean forgetPassword(String email);

	// Use case: Edit Profile

	boolean editProfileName(String name, int empId);

	boolean editProfilePassword(String oldPassword, String newPassword, int empId);

	boolean editProfileImage(byte[] image, int empId);

	// Use case: View Test Detail

	List<TestDetail> invokeGetTestDetailByCategory(String category);

	List<TestDetail> invokeGetTestDetailByType(String type);

	Optional<TestDetail> invokeGetTestDetailBySubtype(String subtype);

	List<TestDetail> invokeGetActiveTestDetailCategories();

	List<TestDetail> invokeGetActiveTestDetails();

	Employee getEmployeeProfile(int empId);

	boolean createEmpAccount(Employee emp);

	List<Employee> getAllEmployee();

	Employee getEmployeeById(int id);

	Optional<List<Employee>> getEmployeeByName(String name);

	Employee deactivateUser(int userId);

	Employee activateUser(int userId);

}
