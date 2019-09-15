package com.optimum.employeedashboard.service;

import java.util.List;
import java.util.Optional;

import com.optimum.employeedashboard.model.TestDetail;

public interface TestDetailService {

	// Use case: View Test Detail

	List<TestDetail> displayAllTestDetail();

	// Use case: Get Test Detail for editing

	Optional<TestDetail> getTestDetailById(int settingsId);

	// Use case: Create / Edit Test Detail

	boolean updateTestDetail(TestDetail testDetail);

	// Use case: Delete Test Detail

	boolean hideTestDetail(int testDetailId);

	List<TestDetail> getTestDetailByCategory(String category);

	List<TestDetail> getTestDetailByType(String type);

	Optional<TestDetail> getTestDetailBySubtype(String subtype);

	List<TestDetail> invokeGetActiveTestDetails();
}
