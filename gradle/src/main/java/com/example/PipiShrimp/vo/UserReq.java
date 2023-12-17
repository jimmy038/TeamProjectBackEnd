package com.example.PipiShrimp.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/* login¨Ï¥Î */
public class UserReq {
	private String email;

	@JsonProperty("password")
	private String pwd;

	public UserReq(String email, String pwd) {
		super();
		this.email = email;
		this.pwd = pwd;
	}

	public UserReq() {
		super();
		// TODO Auto-generated constructor stub
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
}
