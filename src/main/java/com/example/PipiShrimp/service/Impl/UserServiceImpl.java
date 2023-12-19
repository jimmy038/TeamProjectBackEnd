package com.example.PipiShrimp.service.Impl;



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

	/* org.slf4j.Logger */
	private Logger logger = LoggerFactory.getLogger(getClass());

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private UserDao userDao;

	// TODO �H�c�榡�P�_(regexp)
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
//		String patternEmail = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}";
//		if (!user.getEmail().matches(patternEmail)) {
//			return new UserRes(RtnCode.EMAIL_FORMAT_ERROR);
//		}

		// �ˬd�K�X�榡(�n���^��+�Ʀr�A�ܤ�8�r��)
		String patternPwd = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$";
		if (!user.getPwd().matches(patternPwd)) {
			return new UserRes(RtnCode.PASSWORD_FORMAT_ERROR);
		}
		// �K�X�[�K
		user.setPwd(encoder.encode(user.getPwd()));

		try {
			userDao.save(user);
			// �x�s��M�űK�X(���^��)
			user.setPwd("");
			// �H�q�l�l��q��
			Mail.sentSignUpMail(user.getEmail());

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new UserRes(RtnCode.USER_CREATE_FAILED);
		}

		return new UserRes(RtnCode.SUCCESSFUL, user);
	}

	@Override
	public UserRes login(UserReq req) {
		if (!StringUtils.hasText(req.getEmail()) || !StringUtils.hasText(req.getPwd())) {
			return new UserRes(RtnCode.PARAM_ERROR);
		}

		// email�����U
		if (!userDao.existsByEmail(req.getEmail())) {
			return new UserRes(RtnCode.EMAIL_NOT_FOUND);
		}

		User user = userDao.findByEmail(req.getEmail());
		// ��Ʈw����z��(��Ʈ���)
		if (user == null) {
			return new UserRes(RtnCode.DATABASE_IS_EMPTY);
		}

		// �K�X���ŦX�b��
		if (!encoder.matches(req.getPwd(), user.getPwd())) {
			return new UserRes(RtnCode.PASSWORD_ERROR);
		}

		try {
			// �o�e�n�J���\�q��
			Mail.sentLoginMail(req.getEmail());
			logger.info("login successful");
			// �M���K�X(���^��)
			user.setPwd("");

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new UserRes(RtnCode.SENT_EMAIL_FAILED);
		}
		return new UserRes(RtnCode.SUCCESSFUL, user);
	}

}
