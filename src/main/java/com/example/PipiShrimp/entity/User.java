package com.example.PipiShrimp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	@JsonProperty("password")
	private String pwd;

	@Column(name = "address")
	private String address;

	@Column(name = "phone_number")
	@JsonProperty("phone_number")
	private String phoneNumber;

	@Column(name = "level")
	private int level;
	
	@Column(name = "remittance_title")
	@JsonProperty("remittance_title")
	private String remittanceTitle;
	
	@Column(name = "remittance_number")
	@JsonProperty("remittance_number")
	private String remittanceNumber;
	

	@Column(name = "user_photo", columnDefinition = "MEDIUMBLOB")
	private byte[] userPhoto;
		
		@Column(name = "seller_name")
		@JsonProperty("seller_name")
		private String sellerName;
		
		@Column(name = "points")
		private int points;
		@Column(name = "reset_password") 	//判斷是否要更改密碼		
		private boolean resetPwd = false;

		public boolean isResetPwd() { 
				return resetPwd;
			}

		public void setResetPwd(boolean resetPwd) {
				this.resetPwd = resetPwd;
			}

		
		
	public User() {
		super();
	}

	// Test用的建構方法
	public User(String name, String email, String pwd) {
		super();
		this.name = name;
		this.email = email;
		this.pwd = pwd;
	}

	

	

	

	

	



	public User(int id, String name, String email, String pwd, String address, String phoneNumber, int level,
			String remittanceTitle, String remittanceNumber, byte[] userPhoto, String sellerName, int points,
			boolean resetPwd) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.level = level;
		this.remittanceTitle = remittanceTitle;
		this.remittanceNumber = remittanceNumber;
		this.userPhoto = userPhoto;
		this.sellerName = sellerName;
		this.points = points;
		this.resetPwd = resetPwd;
	}

	public User(User user) {
		super();
		this.name = user.getName();
		this.email = user.getEmail();
		this.pwd = user.getPwd();
		this.address = user.getAddress();
		this.phoneNumber = user.getPhoneNumber();
		this.level = user.getLevel();
	}
	
	

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	

	public String getRemittanceTitle() {
		return remittanceTitle;
	}

	public void setRemittanceTitle(String remittanceTitle) {
		this.remittanceTitle = remittanceTitle;
	}

	public String getRemittanceNumber() {
		return remittanceNumber;
	}

	public void setRemittanceNumber(String remittanceNumber) {
		this.remittanceNumber = remittanceNumber;
	}

	

	public byte[] getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(byte[] userPhoto) {
		this.userPhoto = userPhoto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
