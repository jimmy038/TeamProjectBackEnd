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

	// TODO �H�c�榡�P�_(regexp)�B�H�e�H��
	@Override
	public UserRes signUp(User user) {
		// �ܤ֭n��J name�Bemail�Bpassword
		if (!StringUtils.hasText(user.getName()) || //
				!StringUtils.hasText(user.getEmail()) || //
				!StringUtils.hasText(user.getPwd())//
		) {
			return new UserRes(RtnCode.PARAM_ERROR);
		}

		// �H�c�w���U�L
		if (userDao.existsByEmail(user.getEmail())) {
			return new UserRes(RtnCode.EMAIL_IS_EXIST);
		}

		// �ˬd�H�c�榡
//		String patternEmail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
//		if (!user.getPwd().matches(patternEmail)) {
//			return new UserRes(RtnCode.EMAIL_FORMAT_ERROR);
//		}

		// �ˬd�K�X�榡
		String patternPwd = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$";
		if (!user.getPwd().matches(patternPwd)) {
			return new UserRes(RtnCode.PASSWORD_FORMAT_ERROR);
		}

		try {
			userDao.save(user);
			// �x�s��M�űK�X(���^��)
			user.setPwd("");

			// �H�q�l�l��q��
			// TODO
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new UserRes(RtnCode.USER_CREATE_FAILED);
		}

		return new UserRes(RtnCode.SUCCESSFUL, user);
	}

}
