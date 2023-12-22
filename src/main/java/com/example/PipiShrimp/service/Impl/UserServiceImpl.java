package com.example.PipiShrimp.service.Impl;

import java.util.Optional;

<<<<<<< HEAD
import javax.servlet.http.HttpSession;

=======
>>>>>>> b5ea93e62384850c6a89db11dd00712137c68d3b
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Mail;
import com.example.PipiShrimp.entity.User;
import com.example.PipiShrimp.repository.UserDao;
import com.example.PipiShrimp.service.ifs.UserService;
import com.example.PipiShrimp.vo.UserReq;
import com.example.PipiShrimp.vo.UserRes;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private UserDao userDao;

	@Override
	public UserRes signUp(User user) {
		if (!StringUtils.hasText(user.getName()) || //
				!StringUtils.hasText(user.getEmail()) || //
				!StringUtils.hasText(user.getPwd())//
		) {
			return new UserRes(RtnCode.PARAM_ERROR);
		}

		if (userDao.existsByEmail(user.getEmail())) {
			return new UserRes(RtnCode.EMAIL_IS_EXIST);
		}

		

		String patternPwd = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$";
		if (!user.getPwd().matches(patternPwd)) {
			return new UserRes(RtnCode.PASSWORD_FORMAT_ERROR);
		}
		
		user.setPwd(encoder.encode(user.getPwd()));

		try {
			userDao.save(user);
			
			user.setPwd("");
			
			Mail.sentSignUpMail(user.getEmail());

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new UserRes(RtnCode.USER_CREATE_FAILED);
		}

		return new UserRes(RtnCode.SUCCESSFUL, user);
	}

	@Override
	public UserRes login(UserReq req, HttpSession session) {
	    if (!StringUtils.hasText(req.getEmail()) || !StringUtils.hasText(req.getPwd())) {
	        return new UserRes(RtnCode.PARAM_ERROR);
	    }

	    if (!userDao.existsByEmail(req.getEmail())) {
	        return new UserRes(RtnCode.EMAIL_NOT_FOUND);
	    }

	    User user = userDao.findByEmail(req.getEmail());

	    if (user == null) {
	        return new UserRes(RtnCode.DATABASE_IS_EMPTY);
	    }

<<<<<<< HEAD
	    if (!encoder.matches(req.getPwd(), user.getPwd())) {
	        return new UserRes(RtnCode.PASSWORD_ERROR);
	    }

	    try {
	        Mail.sentLoginMail(req.getEmail());
	        logger.info("login successful");

	        // æ’ ï‰îœ…ï¿½ï˜IDæ‘®î¦¶î¾›ï¿½î¯­ Session éŠï¿½
	        session.setAttribute("userId", user.getId());  // ï¿½ï¿½ï‹¬æŒ½ user æ’–å¯¡æƒ…ï¿½ï¿½ï¢ï¿½éŠèŠ¸ï¿½ïš–è›¹ "id" ï¿½ï¿½ï„’ï¿½îµ¥ï¿½æ‰¯â€è·ç®‡îœ…ï¿½ï˜ID
//	        // éœˆæ›„è”­ Session æ‘®î¦¶î¾›ï¿½î£ªï¿½î£¨éŠï¿½ 5 ï¿½ï¿½ï‰ï¿½ï¿½
//	        session.setMaxInactiveInterval(5 * 60);
	        user.setPwd("");
	        return new UserRes(RtnCode.SUCCESSFUL, user);
	    } catch (Exception e) {
	        logger.error(e.getMessage());
	        return new UserRes(RtnCode.SENT_EMAIL_FAILED);
	    }
=======
		try {
			// µo°eµn¤J¦¨¥\³qª¾
			Mail.sentLoginMail(req.getEmail());
			logger.info("login successful");
			// ²M°£±K½X(¤£¦^¶Ç)
			user.setPwd("");

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new UserRes(RtnCode.SENT_EMAIL_FAILED);
		}
		return new UserRes(RtnCode.SUCCESSFUL, user);
	}

	@Override
	public UserRes getUserInfo(int id) {
		// ½T»{id¬O§_¦s¦b
		if (!userDao.existsById(id)) {
			return new UserRes(RtnCode.USER_ID_NOT_FOUND);
		}

		Optional<User> op = userDao.findById(id);
		// ½T»{User¬O§_¬°ªÅ
		if (op.isEmpty()) {
			return new UserRes(RtnCode.USER_IS_EMPTY);
		}

		return new UserRes(RtnCode.SUCCESSFUL, op.get());
	}

	@Override
	public UserRes editUserInfo(User user) {

		// ½T»{User¬O§_¬°ªÅ
		if (user == null) {
			return new UserRes(RtnCode.USER_IS_EMPTY);
		}

		// TODO ¤£¯à§ó§ï«H½c©M±K½X

		try {
			userDao.save(user);
			// ²MªÅ±K½X(¤£¦^¶Ç)
			user.setPwd("");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new UserRes(RtnCode.USER_UPDATE_FAILED);
		}

		return new UserRes(RtnCode.SUCCESSFUL, user);
>>>>>>> b5ea93e62384850c6a89db11dd00712137c68d3b
	}
	@Override
	 public UserRes getUserInfo(int id) {
	  // ç¢ºèªidæ˜¯å¦å­˜åœ¨
	  if (!userDao.existsById(id)) {
	   return new UserRes(RtnCode.USER_ID_NOT_FOUND);
	  }

	  Optional<User> op = userDao.findById(id);
	  // ç¢ºèªUseræ˜¯å¦ç‚ºç©º
	  if (op.isEmpty()) {
	   return new UserRes(RtnCode.USER_IS_EMPTY);
	  }
	  
	  return new UserRes(RtnCode.SUCCESSFUL, op.get());
	 }
	@Override
	 public UserRes editUserInfo(User user) {
	  // ç¢ºèªidæ˜¯å¦å­˜åœ¨
	  if (!userDao.existsById(user.getId())) {
	   return new UserRes(RtnCode.USER_ID_NOT_FOUND);
	  }

	  Optional<User> op = userDao.findById(user.getId());

	  // ç¢ºèªUseræ˜¯å¦ç‚ºç©º
	  if (op.isEmpty()) {
	   return new UserRes(RtnCode.USER_IS_EMPTY);
	  }

	  // ä¸èƒ½æ›´æ”¹ä¿¡ç®±å’Œå¯†ç¢¼
	  if (!op.get().getEmail().matches(user.getEmail()) || //
	    !encoder.matches(user.getPwd(), op.get().getPwd())) {
	   return new UserRes(RtnCode.PARAM_ERROR);
	  }

	  // åŠ å¯†å¾Œå­˜å…¥DB
	  user.setPwd(encoder.encode(user.getPwd()));

	  // æ›´æ–°ç…§ç‰‡
	  op.get().setUserPhoto(user.getUserPhoto());

	  try {
	   userDao.save(user);
	   // æ¸…ç©ºå¯†ç¢¼(ä¸å›å‚³)
	   user.setPwd("");
	  } catch (Exception e) {
	   logger.error(e.getMessage());
	   return new UserRes(RtnCode.USER_UPDATE_FAILED);
	  }

	  return new UserRes(RtnCode.SUCCESSFUL, user);
	 }
}
