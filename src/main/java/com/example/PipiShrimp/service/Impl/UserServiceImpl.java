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
<<<<<<< HEAD
			// ±Hπq§l∂l•Û≥q™æ(®œ•Œ)
			new Thread(() -> {
				Mail.sentSignUpMail(user.getEmail());
			}).start();
=======
			
			Mail.sentSignUpMail(user.getEmail());
>>>>>>> 4f8cb30f04fb6a7c1560c35271114125a67650fb

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

	        // Êí†ÔâéÓúÖÔøΩÔçòIDÊëÆÓ¶∂ÓæõÔøΩÓØ≠ Session ÈäùÔøΩ
	        session.setAttribute("userId", user.getId());  // ÔøΩÔøΩÔã¨ÊåΩ user ÊíñÂØ°ÊÉÖÔøΩÔøΩÔê¢ÔøΩÈäùËä∏ÔøΩÔöñËõπ "id" ÔøΩÔøΩÔÑíÔøΩÓµ•ÔøΩÊâØ‚ÄùËù∑ÁÆáÓúÖÔøΩÔçòID
//	        // ÈúàÊõÑËî≠ Session ÊëÆÓ¶∂ÓæõÔøΩÓ£™ÔøΩÓ£®ÈäùÔøΩ 5 ÔøΩÔøΩÔâêÔøΩÔøΩ
//	        session.setMaxInactiveInterval(5 * 60);
	        user.setPwd("");
	        return new UserRes(RtnCode.SUCCESSFUL, user);
	    } catch (Exception e) {
	        logger.error(e.getMessage());
	        return new UserRes(RtnCode.SENT_EMAIL_FAILED);
	    }
=======
		try {
			// µo∞eµn§J¶®•\≥q™æ
			new Thread(() -> {
				Mail.sentLoginMail(req.getEmail());
			}).start();

			logger.info("login successful");
			// ≤M∞£±KΩX(§£¶^∂«)
			user.setPwd("");

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new UserRes(RtnCode.SENT_EMAIL_FAILED);
		}
		return new UserRes(RtnCode.SUCCESSFUL, user);
	}

	@Override
	public UserRes getUserInfo(int id) {
		// ΩTª{id¨Oß_¶s¶b
		if (!userDao.existsById(id)) {
			return new UserRes(RtnCode.USER_ID_NOT_FOUND);
		}

		Optional<User> op = userDao.findById(id);
		// ΩTª{User¨Oß_¨∞™≈
		if (op.isEmpty()) {
			return new UserRes(RtnCode.USER_IS_EMPTY);
		}

		return new UserRes(RtnCode.SUCCESSFUL, op.get());
	}

	@Override
	public UserRes editUserInfo(User user) {
		// ΩTª{id¨Oß_¶s¶b
		if (!userDao.existsById(user.getId())) {
			return new UserRes(RtnCode.USER_ID_NOT_FOUND);
		}

		Optional<User> op = userDao.findById(user.getId());

		// ΩTª{User¨Oß_¨∞™≈
		if (op.isEmpty()) {
			return new UserRes(RtnCode.USER_IS_EMPTY);
		}

		// §£Ø‡ßÛßÔ´HΩc©M±KΩX
		if (!op.get().getEmail().matches(user.getEmail()) || //
				!encoder.matches(user.getPwd(), op.get().getPwd())) {
			return new UserRes(RtnCode.PARAM_ERROR);
		}

		// •[±K´·¶s§JDB
		user.setPwd(encoder.encode(user.getPwd()));

		// ßÛ∑s∑”§˘
		op.get().setPhoto(user.getPhoto());

		try {
			userDao.save(user);
			// ≤M™≈±KΩX(§£¶^∂«)
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
	  // Á¢∫Ë™çidÊòØÂê¶Â≠òÂú®
	  if (!userDao.existsById(id)) {
	   return new UserRes(RtnCode.USER_ID_NOT_FOUND);
	  }

	  Optional<User> op = userDao.findById(id);
	  // Á¢∫Ë™çUserÊòØÂê¶ÁÇ∫Á©∫
	  if (op.isEmpty()) {
	   return new UserRes(RtnCode.USER_IS_EMPTY);
	  }
	  
	  return new UserRes(RtnCode.SUCCESSFUL, op.get());
	 }
	@Override
	 public UserRes editUserInfo(User user) {
	  // Á¢∫Ë™çidÊòØÂê¶Â≠òÂú®
	  if (!userDao.existsById(user.getId())) {
	   return new UserRes(RtnCode.USER_ID_NOT_FOUND);
	  }

	  Optional<User> op = userDao.findById(user.getId());

	  // Á¢∫Ë™çUserÊòØÂê¶ÁÇ∫Á©∫
	  if (op.isEmpty()) {
	   return new UserRes(RtnCode.USER_IS_EMPTY);
	  }

	  // ‰∏çËÉΩÊõ¥Êîπ‰ø°ÁÆ±ÂíåÂØÜÁ¢º
	  if (!op.get().getEmail().matches(user.getEmail()) || //
	    !encoder.matches(user.getPwd(), op.get().getPwd())) {
	   return new UserRes(RtnCode.PARAM_ERROR);
	  }

	  // Âä†ÂØÜÂæåÂ≠òÂÖ•DB
	  user.setPwd(encoder.encode(user.getPwd()));

	  // Êõ¥Êñ∞ÁÖßÁâá
	  op.get().setUserPhoto(user.getUserPhoto());

	  try {
	   userDao.save(user);
	   // Ê∏ÖÁ©∫ÂØÜÁ¢º(‰∏çÂõûÂÇ≥)
	   user.setPwd("");
	  } catch (Exception e) {
	   logger.error(e.getMessage());
	   return new UserRes(RtnCode.USER_UPDATE_FAILED);
	  }

	  return new UserRes(RtnCode.SUCCESSFUL, user);
	 }
}
