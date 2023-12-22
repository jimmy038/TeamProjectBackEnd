package com.example.PipiShrimp.service.Impl;

import java.util.Optional;

import javax.servlet.http.HttpSession;

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

	    if (!encoder.matches(req.getPwd(), user.getPwd())) {
	        return new UserRes(RtnCode.PASSWORD_ERROR);
	    }

	    try {
	        Mail.sentLoginMail(req.getEmail());
	        logger.info("login successful");

	        // 撠�ID摮� Session 銝�
	        session.setAttribute("userId", user.getId());  // ��挽 user 撖寡情���銝芸�蛹 "id" ����扯”蝷箇�ID
//	        // 霈曄蔭 Session 摮��銝� 5 ����
//	        session.setMaxInactiveInterval(5 * 60);
	        user.setPwd("");
	        return new UserRes(RtnCode.SUCCESSFUL, user);
	    } catch (Exception e) {
	        logger.error(e.getMessage());
	        return new UserRes(RtnCode.SENT_EMAIL_FAILED);
	    }
	}
	@Override
	 public UserRes getUserInfo(int id) {
	  // 確認id是否存在
	  if (!userDao.existsById(id)) {
	   return new UserRes(RtnCode.USER_ID_NOT_FOUND);
	  }

	  Optional<User> op = userDao.findById(id);
	  // 確認User是否為空
	  if (op.isEmpty()) {
	   return new UserRes(RtnCode.USER_IS_EMPTY);
	  }
	  
	  return new UserRes(RtnCode.SUCCESSFUL, op.get());
	 }
	@Override
	 public UserRes editUserInfo(User user) {
	  // 確認id是否存在
	  if (!userDao.existsById(user.getId())) {
	   return new UserRes(RtnCode.USER_ID_NOT_FOUND);
	  }

	  Optional<User> op = userDao.findById(user.getId());

	  // 確認User是否為空
	  if (op.isEmpty()) {
	   return new UserRes(RtnCode.USER_IS_EMPTY);
	  }

	  // 不能更改信箱和密碼
	  if (!op.get().getEmail().matches(user.getEmail()) || //
	    !encoder.matches(user.getPwd(), op.get().getPwd())) {
	   return new UserRes(RtnCode.PARAM_ERROR);
	  }

	  // 加密後存入DB
	  user.setPwd(encoder.encode(user.getPwd()));

	  // 更新照片
	  op.get().setUserPhoto(user.getUserPhoto());

	  try {
	   userDao.save(user);
	   // 清空密碼(不回傳)
	   user.setPwd("");
	  } catch (Exception e) {
	   logger.error(e.getMessage());
	   return new UserRes(RtnCode.USER_UPDATE_FAILED);
	  }

	  return new UserRes(RtnCode.SUCCESSFUL, user);
	 }
}
