package com.optimum.employeedashboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimum.employeedashboard.dao.TestDetailDao;
import com.optimum.employeedashboard.model.TestDetail;

@Service
public class TestDetailServiceImpl implements TestDetailService {

	@Autowired
	TestDetailDao testDetailDao;
	
	@Override
	// might want to remove this method. Else will
	// defeat the purpose of "deleting" / "hiding"
	// the test details.
	public List<TestDetail> displayAllTestDetail() {
		return testDetailDao.findAll();
	}

	@Override
	public List<TestDetail> invokeGetActiveTestDetails() {
		return testDetailDao.getTestDetailByIsHidden(false);
	}

	@Override
	public Optional<TestDetail> getTestDetailById(int settingsId) {
		return testDetailDao.findById(settingsId);
	}

	@Override
	public List<TestDetail> getTestDetailByCategory(String category) {
		return testDetailDao.getTestDetailByTestCat(category);
	}

	@Override
	public List<TestDetail> getTestDetailByType(String type) {
		return testDetailDao.getTestDetailByTestType(type);
	}

	@Override
	public Optional<TestDetail> getTestDetailBySubtype(String subtype) {
		return testDetailDao.getTestDetailByTestSubtype(subtype);
	}

	@Override
	public boolean updateTestDetail(TestDetail testDetail) {

		boolean status = true;
		try {
			TestDetail td = testDetailDao.findById(testDetail.getSettingsId()).orElse(null);
			// checks if the input test Details already exists in DB
			// if so, will update the value
			// else will save a new object
			if (td != null) {
				td.setTimeLimit(testDetail.getTimeLimit());
				td.setNoOfQns(testDetail.getNoOfQns());
				// As admin should only be allowed to change the # of qns
				// and the time limit
				testDetailDao.save(td);
			} else {
				testDetailDao.save(testDetail);
			}

			// updates / creates the object in the DB
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;

	}

	@Override
	public boolean hideTestDetail(int testDetailId) {
		// retrieves the TestDetail according to the admin selection

		boolean status = true;
		try {
			TestDetail td = testDetailDao.findById(testDetailId).orElse(null);
			// sets the attribute of isHidden to be true
			td.setIsHidden(true);
			testDetailDao.save(td);
			// updates the object in the DB
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

}
