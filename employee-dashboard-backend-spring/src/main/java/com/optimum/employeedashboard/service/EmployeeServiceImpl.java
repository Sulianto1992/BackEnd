package com.optimum.employeedashboard.service;

import java.util.List;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.optimum.employeedashboard.dao.EmployeeDao;
import com.optimum.employeedashboard.dao.TestDetailDao;
import com.optimum.employeedashboard.dao.TestResultDao;
import com.optimum.employeedashboard.model.CacheHelper;
import com.optimum.employeedashboard.model.CustomPasswordGenerator;
import com.optimum.employeedashboard.model.Employee;
import com.optimum.employeedashboard.model.TestDetail;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	
	@Autowired
	CacheHelper cache;
	// Cache contains hashmap of K-V pairs
	// have employeeAccounts cache used in EmployeeService
	// have questionList cache used in TestResultService
	// So far, i have only put caching for the GET requests
	// for anything that changes values, i am not sure will be
	// saved properly in DB + cache so i left it alone
	
	
	// have yet to implement any use for this
	private static Logger logger = LogManager.getLogger();

	@Autowired
	EmployeeDao employeeDao;

	@Autowired
	TestDetailDao testDetailDao;

	@Autowired
	EmailService emailService;

	@Override
	public boolean createEmpAccount(Employee employee) {
		boolean status = true;
		try {	
			String hashedPW = BCrypt.hashpw(employee.getPassword(), BCrypt.gensalt());
			employee.setPassword(hashedPW);
			employeeDao.save(employee);
			logger.info("This is an info message");
		} catch (Exception e) {
			status = false;
			logger.error("This is an error message");
		}
		return status;
		
	}

	@Override
	public boolean register(Employee emp) {
		boolean status = true;
		try {
			if (employeeDao.findById(emp.getEmployeeId()).orElse(null) != null) {
				// checks if employee id already exists in db
				// to avoid allowing multiple registrations for same employee
				status = false;
			} else {
				String password = CustomPasswordGenerator.generatePassword();

				String subject = "Registration Application for : " + emp.getEmployeeId();
				String text = "The password to this account is : " + password + "\n"
						+ "The email address of the applicant is : " + emp.getEmail();
				emailService.sendEmail("yuxiong.ngiam@theoptimum.net", subject, text);
				// sends email to approver with the generated password
				// that approver can forward to employee if approved
				// REMEMBER TO CHANGE THE email String to match the APPROVER's email
				// Email address of registered employee also passed on to Approver
				// So he can easily forward the password to the applicant

				String hashedPW = BCrypt.hashpw(password, BCrypt.gensalt());
				emp.setPassword(hashedPW);
				// uses BCrypt static method to hash the plain-text password and store it

				emp.setIsActive(false);
				emp.setIsApproved(false);
				// initialized with false

				employeeDao.save(emp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	@Override
	public boolean login(int employeeId, String password) {
		boolean status = true;
		try {
			Employee emp = employeeDao.findById(employeeId).orElse(null);
			String hashedPassword = emp.getPassword();
			status = BCrypt.checkpw(password, hashedPassword);
			if (status == true) {
				emp.setIsApproved(true);
				employeeDao.save(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	@Override
	public boolean forgetPassword(String email) {
		boolean status = true;
		try {
			Employee emp = employeeDao.getEmployeeByEmail(email).orElse(null);
			if (emp != null) {
				String password = CustomPasswordGenerator.generatePassword();

				String subject = "Reset password application for : " + emp.getEmployeeId();
				String text = "The password to this account is : " + password;
				emailService.sendEmail(emp.getEmail(), subject, text);

				// sends email to employee with the generated password

				String hashedPW = BCrypt.hashpw(password, BCrypt.gensalt());
				emp.setPassword(hashedPW);
				// uses BCrypt static method to hash the plain-text password and store it

				employeeDao.save(emp);

			}

		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	@Override
	public boolean editProfileName(String name, int empId) {
		boolean status = true;
		try {
			Employee emp = employeeDao.findById(empId).orElse(null);
			emp.setName(name);
			employeeDao.save(emp);
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	@Override
	public boolean editProfilePassword(String oldPassword, String newPassword, int empId) {
		boolean status = true;
		try {
			Employee emp = employeeDao.findById(empId).orElse(null);
			if (BCrypt.checkpw(oldPassword, emp.getPassword())) {
				String hashedPW = BCrypt.hashpw(newPassword, BCrypt.gensalt());
				emp.setPassword(hashedPW);
				employeeDao.save(emp);
			} else {
				status = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	@Override
	public boolean editProfileImage(byte[] image, int empId) {
		boolean status = true;
		try {
			Employee emp = employeeDao.findById(empId).orElse(null);
			emp.setProfileImg(image);
			employeeDao.save(emp);
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	@Override
	public List<TestDetail> invokeGetTestDetailByCategory(String category) {
		return testDetailDao.getTestDetailByTestCat(category);
	}

	@Override
	public List<TestDetail> invokeGetTestDetailByType(String type) {
		return testDetailDao.getTestDetailByTestType(type);
	}

	@Override
	public Optional<TestDetail> invokeGetTestDetailBySubtype(String subtype) {
		return testDetailDao.getTestDetailByTestSubtype(subtype);
	}

	@Override
	public List<TestDetail> invokeGetActiveTestDetailCategories() {
		return testDetailDao.getDistinctTestDetailByIsHidden(false);
	}

	@Override
	public List<TestDetail> invokeGetActiveTestDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployeeProfile(int empId) {
		if (cache.getEmployeeCache().containsKey(empId)) {
			return cache.getEmployeeCache().get(empId);
		}
		System.out.println("Querying for employee : " + empId);
		Employee foundEmp = employeeDao.findById(empId).orElse(null);
		cache.getEmployeeCache().put(empId, foundEmp);
		return foundEmp;
	}

	@Override
	// might want to figure out how to use caching for this
	// so after getting full list once, will not have to
	// query again.
	// populate cache inside constructor already???
	public List<Employee> getAllEmployee() {
		return employeeDao.findAll();
	}

	@Override
	public Employee getEmployeeById(int id) {
		
		if (cache.getEmployeeCache().containsKey(id)) {
			return cache.getEmployeeCache().get(id);
		}
		System.out.println("Querying for employee : " + id);
		Employee foundEmp = employeeDao.findById(id).orElse(null);
		cache.getEmployeeCache().put(id, foundEmp);
		return foundEmp;
		
	}

	@Override
	public Optional<List<Employee>> getEmployeeByName(String name) {
		return employeeDao.findByNameContaining(name);
	}

	@Override
	public Employee deactivateUser(int userId) {
		Employee emp = employeeDao.findById(userId).orElse(null);
		emp.setIsActive(false);
		Employee updatedUser = employeeDao.save(emp);
		return updatedUser;
	}

	@Override
	public Employee activateUser(int userId) {
		Employee emp = employeeDao.findById(userId).orElse(null);
		emp.setIsActive(true);
		Employee updatedUser = employeeDao.save(emp);
		return updatedUser;
	}
}
