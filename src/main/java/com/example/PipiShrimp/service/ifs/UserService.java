package com.example.PipiShrimp.service.ifs;

import com.example.PipiShrimp.entity.User;
import com.example.PipiShrimp.vo.UserReq;
import com.example.PipiShrimp.vo.UserRes;

public interface UserService {

	/**
	 * 新增一筆user資料到DB #註冊
	 **/
	public UserRes signUp(User user);

	/**
	 * 查詢DB內是否有符合的資料 #登入
	 **/
	public UserRes login(UserReq req);
	
	/**
	 * 忘記密碼**/
	public UserRes sentForgotPwd(int id, String email);

	/**
	 * 更改密碼**/
	public UserRes changePwd(String email, String oldPwd, String newPwd);
	

}
