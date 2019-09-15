package com.optimum.employeedashboard.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.optimum.employeedashboard.dao.AdminDao;
import com.optimum.employeedashboard.dao.QuestionDao;
import com.optimum.employeedashboard.dao.QuestionPoolDao;
import com.optimum.employeedashboard.model.Admin;
import com.optimum.employeedashboard.model.CustomPasswordGenerator;


@Service
public class AdminServiceImpl implements AdminService {
	
	// No caching done at this level as admin expected to only have 1 account
	// pointless to have cache

	@Autowired
	QuestionDao questionDao;

	@Autowired
	QuestionPoolDao questionPoolDao;

	@Autowired
	AdminDao adminDao;

	@Autowired
	EmailService emailService;

	@Override
	public boolean createAdminAccount(Admin admin) {
		boolean status = true;
		try {
			String hashedPW = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());
			admin.setPassword(hashedPW);
			adminDao.save(admin);
		} catch (Exception e) {
			status = false;
		}
		return status;
	}

	@Override
	public boolean login(int adminId, String password) {
		boolean status = true;
		try {
			Admin admin = adminDao.findById(adminId).orElse(null);
			String hashedPassword = admin.getPassword();
			status = BCrypt.checkpw(password, hashedPassword);
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}

		// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// String hashedPassword = passwordEncoder.encode(password);
		return status;
	}

	@Override
	public boolean forgetPassword(String email) {
		boolean status = true;
		try {
			Admin admin = adminDao.getAdminByEmail(email).orElse(null);
			if (admin != null) {
				String password = CustomPasswordGenerator.generatePassword();

				String subject = "Reset password application for : " + admin.getAdminId();
				String text = "The password to this account is : " + password;
				emailService.sendEmail(admin.getEmail(), subject, text);

				// sends email to employee with the generated password

				String hashedPW = BCrypt.hashpw(password, BCrypt.gensalt());
				admin.setPassword(hashedPW);
				// uses BCrypt static method to hash the plain-text password and store it

				adminDao.save(admin);
			}

		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	@Override
	public Admin getAdminProfile(int adminId) {
		Admin admin = adminDao.findById(adminId).orElse(null);
		return admin;
	}

	@Override
	public boolean editProfileName(String name, int adminId) {
		boolean status = true;
		try {
			Admin admin = adminDao.findById(adminId).orElse(null);
			admin.setName(name);
			adminDao.save(admin);
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	@Override
	public boolean editProfilePassword(String oldPassword, String newPassword, int adminId) {
		boolean status = true;
		try {
			Admin admin = adminDao.findById(adminId).orElse(null);
			if (BCrypt.checkpw(oldPassword, admin.getPassword())) {
				String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
				admin.setPassword(hashedPassword);
				adminDao.save(admin);
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
	public boolean editProfileImage(byte[] image, int adminId) {
		boolean status = true;
		try {
			Admin admin = adminDao.findById(adminId).orElse(null);
			admin.setProfileImg(image);
			adminDao.save(admin);
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

}
