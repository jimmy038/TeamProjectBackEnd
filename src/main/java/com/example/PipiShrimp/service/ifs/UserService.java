package com.example.PipiShrimp.service.ifs;

<<<<<<< HEAD
import javax.servlet.http.HttpSession;

=======
>>>>>>> ian
import com.example.PipiShrimp.entity.User;
import com.example.PipiShrimp.vo.UserReq;
import com.example.PipiShrimp.vo.UserRes;

public interface UserService {

	/**
	 * 嚙編嚙磕嚙瑾嚙踝蕭user嚙踝蕭嚙瘩B #嚙踝蕭嚙磊
	 **/
	public UserRes signUp(User user);

	/**
<<<<<<< HEAD
	 * 嚙範嚙踝蕭DB嚙踝蕭嚙瞌嚙稻嚙踝蕭嚙褐合嚙踝蕭嚙踝蕭嚙� #嚙緯嚙皚
=======
	 * �d��DB���O�_���ŦX����� #�n�J
	 **/
	public UserRes login(UserReq req);

	/**
	 * �d��user���
>>>>>>> ian
	 **/
	public UserRes login(UserReq req, HttpSession session);
	public UserRes getUserInfo(int id);

<<<<<<< HEAD
	public UserRes editUserInfo(User user);
	
=======
>>>>>>> ian
	/**
	 * 忘記密碼**/
	public UserRes sentForgotPwd(String email);
	

	/**
	 * 更改密碼**/
	public UserRes changePwd(String email, String oldPwd, String newPwd);

	/**
	 * �H�e�x���I�����ҽX
	 **/
	public String getVerifyMail(String email);

	/**
	 * �x���I�� (�Ѽ�:user_id, point)
	 **/
	public UserRes addPoints(int id, int points);

	/**
	 * �x���I�� (�Ѽ�:user_id, pwd, point)
	 **/
	public UserRes addPoints(int id, String password, int points);

}
