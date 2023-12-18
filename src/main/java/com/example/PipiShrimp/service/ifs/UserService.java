package com.example.PipiShrimp.service.ifs;

import com.example.PipiShrimp.entity.User;
import com.example.PipiShrimp.vo.UserReq;
import com.example.PipiShrimp.vo.UserRes;

public interface UserService {

	/**
	 * �s�W�@��user��ƨ�DB #���U
	 **/
	public UserRes signUp(User user);

	/**
	 * �d��DB���O�_���ŦX����� #�n�J
	 **/
	public UserRes login(UserReq req);
	
	/**
	 * �ѰO�K�X**/
	public UserRes sentForgotPwd(int id, String email);

	/**
	 * ���K�X**/
	public UserRes changePwd(String email, String oldPwd, String newPwd);
	

}
