package com.example.PipiShrimp.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangePasswordReq {
	
	private String email;
	
	@JsonProperty("old_password")
	private String oldPwd;
	
	@JsonProperty("new_password")
	private String newPwd;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	
}

