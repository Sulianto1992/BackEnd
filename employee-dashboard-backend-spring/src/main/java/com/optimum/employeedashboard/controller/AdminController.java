package com.optimum.employeedashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.optimum.employeedashboard.model.Admin;
import com.optimum.employeedashboard.model.Employee;
import com.optimum.employeedashboard.model.Question;
import com.optimum.employeedashboard.model.QuestionPool;
import com.optimum.employeedashboard.model.TestDetail;
import com.optimum.employeedashboard.service.AdminServiceImpl;
import com.optimum.employeedashboard.service.EmployeeService;
import com.optimum.employeedashboard.service.QuestionPoolService;
import com.optimum.employeedashboard.service.QuestionService;
import com.optimum.employeedashboard.service.TestDetailService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminServiceImpl adminService;

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	QuestionPoolService questionPoolService;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	TestDetailService testDetailService;

	@PostMapping("/{adminid}/login")
	public boolean loginEmpAccount(@RequestBody Admin admin, @PathVariable int adminid) {
		return adminService.login(adminid, admin.getPassword());
	}

	// THIS MAPPING should ONLY BE USED to generate first row of table
	// to allow for Login authentication testing
	// REMOVE THIS method in controller & service layers afterwards
	@PostMapping("/create-account")
	public boolean createAdminAccount(@RequestBody Admin admin) {
		return adminService.createAdminAccount(admin);
	}

	@GetMapping("/test-detail/active")
	// gets all active test details 'td'
	public List<TestDetail> showActiveTestDetails() {
		return testDetailService.invokeGetActiveTestDetails();
	}

	@GetMapping("/test-detail/id/{id}")
	// gets test detail 'td' by settings_id
	public Optional<TestDetail> showTestDetails(@PathVariable int id) {
		return testDetailService.getTestDetailById(id);
	}

	@GetMapping("/test-detail/category/{category}")
	// gets test detail 'td' by category
	public List<TestDetail> showTestDetailsByCat(@PathVariable String category) {
		return testDetailService.getTestDetailByCategory(category);
	}

	@GetMapping("/test-detail/type/{type}")
	// gets test detail 'td' by test type
	public List<TestDetail> showTestDetailsByType(@PathVariable String type) {
		return testDetailService.getTestDetailByType(type);
	}

	@GetMapping("/test-detail/subtype/{subtype}")
	// gets test detail 'td' by test subtype
	public Optional<TestDetail> showTestDetailsBySubtype(@PathVariable String subtype) {
		return testDetailService.getTestDetailBySubtype(subtype);
	}

	@PostMapping("/test-detail/update")
	// updates test detail 'td'
	public boolean updateTestDetails(@RequestBody TestDetail td) {
		return testDetailService.updateTestDetail(td);
	}

	@PostMapping("/test-detail/hide/{id}")
	// "deletes" test details 'td'
	public boolean hideTestDetails(@PathVariable int id) {
		return testDetailService.hideTestDetail(id);
	}

	@PostMapping("/{adminId}/profile-name")
	// updates Admin profile name
	public boolean updateProfileName(@RequestBody Admin admin, @PathVariable int adminId) {
		return adminService.editProfileName(admin.getName(), adminId);
	}

	@PostMapping("/{adminId}/profile-password")
	// updates Admin profile password
	public boolean updateProfilePassword(@RequestParam String oldPassword, @RequestParam String newPassword,
			@PathVariable int adminId) {
		return adminService.editProfilePassword(oldPassword, newPassword, adminId);
	}

	@PostMapping("/{adminId}/profile-image")
	// updates Admin profile image
	public boolean updateProfileImage(@RequestBody Admin admin, @PathVariable int adminId) {
		return adminService.editProfileImage(admin.getProfileImg(), adminId);
	}

	@GetMapping("/{adminId}/profile")
	// retrieves admin profile
	public Admin getAdminProfile(@PathVariable int adminId) {
		return adminService.getAdminProfile(adminId);
	}

	@GetMapping("/questionpool")
	public List<QuestionPool> getQuestionPool() {
		return questionPoolService.displayAllQuestionPool();
	}

	@PutMapping("/questionpool")
	public void createQuestionPool(@RequestBody QuestionPool pool) {
		questionPoolService.createQuestionPool(pool);
	}

	@PutMapping("/questionpool/create-question/{id}")
	public void createQuestion(@RequestBody Question question, @PathVariable("id") int poolId) {
		questionService.createQuestion(question, poolId);
	}

	@PutMapping("/questionpool/set-hidden/{id}")
	public void setQuestionPoolToHidden(@PathVariable("id") int poolId) {
		questionPoolService.hideQuestionPool(poolId);
	}

	// Get All Users
	@GetMapping("/employees")
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployee();
	}

	// Get a Single user
	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable(value = "id") int id) {
		return employeeService.getEmployeeById(id);
	}

	// Get a Single user
	@GetMapping("/employees/search/{name}")
	public Optional<List<Employee>> getEmployeeByName(@PathVariable(value = "name") String name) {
		return employeeService.getEmployeeByName(name);
	}

	// Update a user
	@PutMapping("/employees/{id}/deactivate")
	public Employee deactivateUser(@PathVariable(value = "id") int userId) {
		return employeeService.deactivateUser(userId);
	}

	// Update a user
	@PutMapping("/employees/{id}/activate")
	public Employee activateUser(@PathVariable(value = "id") int userId) {
		return employeeService.activateUser(userId);
	}

}
