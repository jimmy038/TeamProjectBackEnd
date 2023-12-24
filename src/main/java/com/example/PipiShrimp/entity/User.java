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
	// @Lob¥Î¨Ó¼Ð°OÄÝ©ó¤j«¬¤å¥ó(Lob)
	@Lob
	// columnDefinition©w¸q¸ê®Æ®wªº¸ê®Æ«¬ºA
	@Column(name = "photo", columnDefinition = "MEDIUMBLOB")
	private byte[] photo;

	// ¨Ï¥ÎªÌ¦WºÙ
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
	
	@Column(name = "remittance_title")
	@JsonProperty("remittance_title")
	private String remittanceTitle;
	
	@Column(name = "remittance_number")
	@JsonProperty("remittance_number")
	private String remittanceNumber;
	

<<<<<<< HEAD
	// ·|­ûÂI¼Æ
	@Column(name = "points")
	private int points;

=======
		@Column(name = "user_photo", columnDefinition = "LONGTEXT")  // é›¿è¼»îœ…ç”‡ï¿½è–æ¡ƒï¿½ï„’ï¿½î¤™ï¿½î«¯ï¿½ï­ï¿½ï—¹ï¿½ï•ï¿½ï¿½ "LONGTEXT"åš—ï—»î¹²ï¿½ï™ æ‘°îµ¨ï¿½ï†°ï¿½ï†¯ïˆœï¿½ï¤ï”¡
		@Lob
		private String userPhoto;
		@Column(name = "seller_name")
		@JsonProperty("seller_name")
		private String sellerName;
		
>>>>>>> 4f8cb30f04fb6a7c1560c35271114125a67650fb
	public User() {
		super();
	}

<<<<<<< HEAD
	// µù¥U¥Îªº«Øºc¤èªk
=======
	// Testç”¨çš„å»ºæ§‹æ–¹æ³•
>>>>>>> 4f8cb30f04fb6a7c1560c35271114125a67650fb
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

	// §ó·suser¸ê®Æªí®É¨Ï¥Î
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
