package com.example.PipiShrimp.service.ifs;

<<<<<<< HEAD
import javax.servlet.http.HttpSession;
=======

>>>>>>> b5ea93e62384850c6a89db11dd00712137c68d3b

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
<<<<<<< HEAD
	public UserRes login(UserReq req, HttpSession session);
	public UserRes getUserInfo(int id);
=======
	public UserRes login(UserReq req);
	
	/**
	 * �d��user���
	 **/
	public UserRes getUserInfo(int id);
	
	/**
	 * �s��user���
	 **/
	public UserRes editUserInfo(User user);
>>>>>>> b5ea93e62384850c6a89db11dd00712137c68d3b

	public UserRes editUserInfo(User user);}
