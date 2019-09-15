package com.optimum.employeedashboard.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;



@Entity
@Table(name="employee_accounts")
public class Employee {

	// @GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name="employee_id")	
	private int employeeId;
	// primary key
	
	@Column(name="password")
	private String password;
	@Column(name="email")
	private String email;
	@Column(name="name")
	private String name;
	@Column(name="department")
	private String department;
	@Column(name="mobile_no")
	private String mobileNo;
	@Column(name="profile_image")
	@Lob
	private byte[] profileImg;
	// contains actual image stored as binary data (010101)
	// DB will auto convert to blob when retrieving & storing
	@Column(name="is_active")
	private Boolean isActive;
	// used to check if account is Activated or Deactivated
	@Column(name="is_approved")
	private Boolean isApproved;
	// used to check if account has been approved
	// upon any login, will change this value to be true
	// as employee will only get password from Amit if he approves
	// the registration. 
	// So can consider any logins to be valid
	// if isApproved is false, prevent running of forgetPassword method
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeID) {
		this.employeeId = employeeID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public byte[] getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(byte[] profileImg) {
		this.profileImg = profileImg;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}
	
}
