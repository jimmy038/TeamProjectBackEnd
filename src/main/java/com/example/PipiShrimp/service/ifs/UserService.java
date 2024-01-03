package com.example.PipiShrimp.service.ifs;

import com.example.PipiShrimp.entity.User;
import com.example.PipiShrimp.vo.UserReq;
import com.example.PipiShrimp.vo.UserRes;

public interface UserService {

	/**
	 * 穝糤掸user戈DB #爹
	 **/
	public UserRes signUp(User user);

	/**
	 * 琩高DBず琌Τ才戈 #祅
	 **/
	public UserRes login(UserReq req);

	/**
	 * 琩高user戈
	 **/
	public UserRes getUserInfo(int id);

	/**
	 * 絪胯user戈
	 **/
	public UserRes editUserInfo(User user);

	/**
	 * 盚癳纗翴计喷靡絏
	 **/
	public String getVerifyMail(String email);

	/**
	 * 纗翴计 (把计:user_id, point)
	 **/
	public UserRes addPoints(int id, int points);

	/**
	 * 纗翴计 (把计:user_id, pwd, point)
	 **/
	public UserRes addPoints(int id, String password, int points);

}
