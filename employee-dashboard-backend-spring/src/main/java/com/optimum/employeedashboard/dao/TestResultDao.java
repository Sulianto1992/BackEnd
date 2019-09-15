package com.optimum.employeedashboard.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.optimum.employeedashboard.model.TestResult;


@Repository
public interface TestResultDao extends JpaRepository<TestResult, Integer>{

	// void createTestResult(TestResult testResult);
	
	// List<TestResult> getGuestTestResult(int guestId);
	
	// List<TestResult> getEmployeeTestResult(int employeeId);

}
