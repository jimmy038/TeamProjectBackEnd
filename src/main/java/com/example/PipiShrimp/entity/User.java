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
	

		@Column(name = "user_photo", columnDefinition = "LONGTEXT")  // 雿輻甇�蝖桃�������� "LONGTEXT"嚗�摰���
		@Lob
		private String userPhoto;
		@Column(name = "seller_name")
		@JsonProperty("seller_name")
		private String sellerName;
		
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
			String remittanceTitle, String remittanceNumber, String userPhoto, String sellerName) {
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

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	// ��s��ƪ�
	public User(int id, String name, String email, String pwd, String address, String phoneNumber, int level) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.level = level;
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
