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
	 * åš™ç·¨åš™ç£•åš™ç‘¾åš™è¸è•­useråš™è¸è•­ïš¸åš™ç˜©B #åš™è¸è•­åš™ç£Š
	 **/
	public UserRes signUp(User user);

	/**
<<<<<<< HEAD
	 * åš™ç¯„åš™è¸è•­DBåš™è¸è•­åš™çŒåš™ç¨»åš™è¸è•­åš™è¤åˆåš™è¸è•­åš™è¸è•­åš™ï¿½ #åš™ç·¯åš™çšš
=======
	 * ¬d¸ßDB¤º¬O§_¦³²Å¦Xªº¸ê®Æ #µn¤J
	 **/
	public UserRes login(UserReq req);

	/**
	 * ¬d¸ßuser¸ê®Æ
>>>>>>> ian
	 **/
	public UserRes login(UserReq req, HttpSession session);
	public UserRes getUserInfo(int id);

<<<<<<< HEAD
	public UserRes editUserInfo(User user);
	
=======
>>>>>>> ian
	/**
	 * å¿˜è¨˜å¯†ç¢¼**/
	public UserRes sentForgotPwd(String email);
	

	/**
	 * æ›´æ”¹å¯†ç¢¼**/
	public UserRes changePwd(String email, String oldPwd, String newPwd);

	/**
	 * ±H°eÀx­ÈÂI¼ÆÅçÃÒ½X
	 **/
	public String getVerifyMail(String email);

	/**
	 * Àx­ÈÂI¼Æ (°Ñ¼Æ:user_id, point)
	 **/
	public UserRes addPoints(int id, int points);

	/**
	 * Àx­ÈÂI¼Æ (°Ñ¼Æ:user_id, pwd, point)
	 **/
	public UserRes addPoints(int id, String password, int points);

}
