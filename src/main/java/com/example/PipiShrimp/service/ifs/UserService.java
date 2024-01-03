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
	 * �d��user���
	 **/
	public UserRes getUserInfo(int id);

	/**
	 * �s��user���
	 **/
	public UserRes editUserInfo(User user);

	/**
	 * �H�e�x���I�����ҽX
	 **/
	public String getVerifyMail(String email);

	/**
	 * �x���I�� (�Ѽ�:user_id, pwd, point)
	 **/
	public UserRes addPoints(int id, String password, int points);

}
