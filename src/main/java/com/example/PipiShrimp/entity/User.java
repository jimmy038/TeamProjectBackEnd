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

<<<<<<< HEAD
=======
	// @Lob用來標記屬於大型文件(Lob)
	@Lob
	// columnDefinition定義資料庫的資料型態
	@Column(name = "photo", columnDefinition = "MEDIUMBLOB")
	private byte[] photo;

	// 使用者名稱
>>>>>>> ian
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

	// 會員點數
	@Column(name = "points")
	private int points;

	public User() {
		super();
	}

	// 註冊用的建構方法
	public User(String name, String email, String pwd) {
		super();
		this.name = name;
		this.email = email;
		this.pwd = pwd;
	}

	public User(String name, String email, String pwd, String address, String phoneNumber, int level) {
		super();
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.level = level;
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

	// 更新user資料表時使用
	public User(int id, byte[] photo, String name, String email, String pwd, String address, String phoneNumber) {
		super();
		this.id = id;
		this.photo = photo;
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public User(int id, byte[] photo, String name, String email, String pwd, String address, String phoneNumber,
			int level) {
		super();
		this.id = id;
		this.photo = photo;
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.level = level;
	}

	public User(int id, byte[] photo, String name, String email, String pwd, String address, String phoneNumber,
			int level, int points) {
		super();
		this.id = id;
		this.photo = photo;
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.level = level;
		this.points = points;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}
