package com.optimum.employeedashboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.optimum.employeedashboard.model.Admin;
import com.optimum.employeedashboard.model.Employee;
import com.optimum.employeedashboard.model.Question;
import com.optimum.employeedashboard.model.QuestionPool;
import com.optimum.employeedashboard.model.TestDetail;

public interface AdminService {

	// Use case: Login

	boolean login(int adminId, String password);

	// Use case: Forgot password

	boolean forgetPassword(String email);

	// Use case: Edit Profile

	boolean editProfileName(String name, int adminId);

	boolean editProfilePassword(String oldPassword, String newPassword, int adminId);

	boolean editProfileImage(byte[] image, int adminId);

	Admin getAdminProfile(int adminId);

	boolean createAdminAccount(Admin admin);

}
