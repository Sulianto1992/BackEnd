package com.optimum.employeedashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="guest_details")
public class Guest {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name="guest_id")
	private int guestId;
	// primary key generated in DB table
	@Column(name="nric")
	private String nric;
	@Column(name="name")
	private String name;
	@Column(name="email")
	private String email;
	@Column(name="mobile_no")
	private String mobileNo;
	@Column(name="education_level")
	private String educationLevel;
	@Column(name="gpa")
	private float gpa;
	@Column(name="graduation_year")
	private int graduationYear;
	
	public int getGuestId() {
		return guestId;
	}
	public void setGuestId(int guestID) {
		this.guestId = guestID;
	}
	public String getNric() {
		return nric;
	}
	public void setNric(String nric) {
		this.nric = nric;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}
	public float getGpa() {
		return gpa;
	}
	public void setGpa(float gpa) {
		this.gpa = gpa;
	}
	public int getGraduationYear() {
		return graduationYear;
	}
	public void setGraduationYear(int graduationYear) {
		this.graduationYear = graduationYear;
	}	

}
