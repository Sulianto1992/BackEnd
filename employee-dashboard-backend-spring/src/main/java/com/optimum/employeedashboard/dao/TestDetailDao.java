package com.optimum.employeedashboard.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.optimum.employeedashboard.model.TestDetail;


@Repository
public interface TestDetailDao extends JpaRepository<TestDetail, Integer>{

	// For Employee to use
	@Query("Select DISTINCT td.testCat from TestDetail td where td.isHidden = ?1")
	List <TestDetail> getDistinctTestDetailByIsHidden(boolean isHidden);

	
	// Query method names have to be exactly matching the pattern
	// get-EntityName-By-AttributeName
	// else will not work
	
	@Query("SELECT td from TestDetail td WHERE td.isHidden = ?1")
	// query all non-hidden test details
	List<TestDetail> getTestDetailByIsHidden(boolean status);


	@Query("SELECT td from TestDetail td WHERE td.testCat = ?1")
	// query using WHERE condition for the specific column
	List<TestDetail> getTestDetailByTestCat(String testCategory);
	
	
	@Query("SELECT td from TestDetail td WHERE td.testType = ?1")
	// query using WHERE condition for the specific column
	List<TestDetail> getTestDetailByTestType(String testType);

	@Query("SELECT td from TestDetail td WHERE td.Subtype = ?1")
	// query using WHERE condition for the specific column
	Optional<TestDetail> getTestDetailByTestSubtype(String testSubType);
	
	
}
