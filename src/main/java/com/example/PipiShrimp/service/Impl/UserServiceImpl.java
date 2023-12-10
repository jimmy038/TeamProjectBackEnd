package com.example.PipiShrimp.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.User;
import com.example.PipiShrimp.repository.UserDao;
import com.example.PipiShrimp.service.ifs.UserService;
import com.example.PipiShrimp.vo.UserRes;

@Service
public class UserServiceImpl implements UserService {

	/* org.slf4j.Logger */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserDao userDao;

	// TODO 信箱格式判斷(regexp)、寄送信建
	@Override
	public UserRes signUp(User user) {
		// 至少要輸入 name、email、password
		if (!StringUtils.hasText(user.getName()) || //
				!StringUtils.hasText(user.getEmail()) || //
				!StringUtils.hasText(user.getPwd())//
		) {
			return new UserRes(RtnCode.PARAM_ERROR);
		}

		// 信箱已註冊過
		if (userDao.existsByEmail(user.getEmail())) {
			return new UserRes(RtnCode.EMAIL_IS_EXIST);
		}

		// 檢查信箱格式
//		String patternEmail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
//		if (!user.getPwd().matches(patternEmail)) {
//			return new UserRes(RtnCode.EMAIL_FORMAT_ERROR);
//		}

		// 檢查密碼格式
		String patternPwd = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$";
		if (!user.getPwd().matches(patternPwd)) {
			return new UserRes(RtnCode.PASSWORD_FORMAT_ERROR);
		}

		try {
			userDao.save(user);
			// 儲存後清空密碼(不回傳)
			user.setPwd("");

			// 寄電子郵件通知
			// TODO
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new UserRes(RtnCode.USER_CREATE_FAILED);
		}

		return new UserRes(RtnCode.SUCCESSFUL, user);
	}

}
