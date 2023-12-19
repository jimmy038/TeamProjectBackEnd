package com.example.PipiShrimp.vo;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.User;

public class UserRes {
	private RtnCode rtnCode;

	private User user;

	public UserRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRes(RtnCode rtnCode) {
		super();
		this.rtnCode = rtnCode;
	}

	public UserRes(RtnCode rtnCode, User user) {
		super();
		this.rtnCode = rtnCode;
		this.user = user;
	}

	public RtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(RtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
