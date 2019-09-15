package com.optimum.employeedashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="guest_login_account")
public class GuestLogin {

	// @GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name="guest_login_id")
	private int guestLoginId;
	// Primary key
	// initialized in DB table but not used
	
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="email")
	private String email;
	
	public int getGuestLoginId() {
		return guestLoginId;
	}
	public void setGuestLoginId(int guestLoginID) {
		this.guestLoginId = guestLoginID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	
}
