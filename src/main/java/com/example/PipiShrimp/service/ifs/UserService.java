package com.example.PipiShrimp.service.ifs;

import javax.servlet.http.HttpSession;

import com.example.PipiShrimp.entity.User;
import com.example.PipiShrimp.vo.UserReq;
import com.example.PipiShrimp.vo.UserRes;

public interface UserService {

	/**
	 * 嚙編嚙磕嚙瑾嚙踝蕭user嚙踝蕭嚙瘩B #嚙踝蕭嚙磊
	 **/
	public UserRes signUp(User user);

	/**
	 * 嚙範嚙踝蕭DB嚙踝蕭嚙瞌嚙稻嚙踝蕭嚙褐合嚙踝蕭嚙踝蕭嚙� #嚙緯嚙皚
	 **/
	public UserRes login(UserReq req, HttpSession session);
	public UserRes getUserInfo(int id);

	public UserRes editUserInfo(User user);
	
	/**
	 * 忘記密碼**/
	public UserRes sentForgotPwd(String email);
	

	/**
	 * 更改密碼**/
	public UserRes changePwd(String email, String oldPwd, String newPwd);

}
