package com.example.PipiShrimp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "user")
public class User {

	// �ϥΪ�id(���Wint AI)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	// �ϥΪ̦W��
	@Column(name = "name")
	private String name;

	// �ϥΪ̫H�c
	@Column(name = "email")
	private String email;

	// �ϥΪ̱K�X
	@Column(name = "password")
	@JsonProperty("password")
	private String pwd;

	// �a�}
	@Column(name = "address")
	private String address;

	// �q�ܸ��X
	@Column(name = "phone_number")
	@JsonProperty("phone_number")
	private String phoneNumber;

	// �v��(�ϥΪ̡B�޲z�̤�����)
	@Column(name = "level")
	private int level;

	public User() {
		super();
	}

	// Test�Ϊ��غc��k
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
