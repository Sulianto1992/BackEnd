package com.optimum.employeedashboard.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="admin_accounts")
public class Admin {

	
		// @GeneratedValue(strategy=GenerationType.AUTO)
		@Id
		@Column(name="admin_id")
		private int adminId;
		// primary key
		@Column(name="password")
		private String password;
		@Column(name="name")
		private String name;
		@Column(name="email")
		private String email;
		@Column(name="mobile_no")
		private String mobileNo;
		@Column(name="profile_image")
		@Lob
		//@Lob annotaion used to specify it is a blob
		// 2 varieties of LOB
		// CLOB is used for text daya
		// BLOB is used for binary data (image,audio,video)
		private byte[] profileImg;
		// contains actual image stored as binary data (010101)
		// DB will auto convert to blob when retrieving & storing
		
		public int getAdminId() {
			return adminId;
		}
		public void setAdminId(int adminId) {
			this.adminId = adminId;
		}
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
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
		public byte[] getProfileImg() {
			return profileImg;
		}
		public void setProfileImg(byte[] profileImg) {
			this.profileImg = profileImg;
		}	

}
