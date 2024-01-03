package com.example.PipiShrimp.service.Impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.User;
import com.example.PipiShrimp.repository.UserDao;
import com.example.PipiShrimp.service.ifs.UserService;
import com.example.PipiShrimp.utils.Mail;
import com.example.PipiShrimp.utils.RandomCode;
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
			// �H�q�l�l��q��(�ϥ�)
			new Thread(() -> {
				Mail.sentSignUpMail(user.getEmail());
			}).start();

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
			new Thread(() -> {
				Mail.sentLoginMail(req.getEmail());
			}).start();

			logger.info("login successful");
			// �M���K�X(���^��)
			user.setPwd("");

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new UserRes(RtnCode.SENT_EMAIL_FAILED);
		}
		return new UserRes(RtnCode.SUCCESSFUL, user);
	}

	@Override
	public UserRes getUserInfo(int id) {
		// �T�{id�O�_�s�b
		if (!userDao.existsById(id)) {
			return new UserRes(RtnCode.USER_ID_NOT_FOUND);
		}

		Optional<User> op = userDao.findById(id);
		// �T�{User�O�_����
		if (op.isEmpty()) {
			return new UserRes(RtnCode.USER_IS_EMPTY);
		}

		return new UserRes(RtnCode.SUCCESSFUL, op.get());
	}

	@Override
	public UserRes editUserInfo(User user) {
		// �T�{id�O�_�s�b
		if (!userDao.existsById(user.getId())) {
			return new UserRes(RtnCode.USER_ID_NOT_FOUND);
		}

		Optional<User> op = userDao.findById(user.getId());

		// �T�{User�O�_����
		if (op.isEmpty()) {
			return new UserRes(RtnCode.USER_IS_EMPTY);
		}

		// ������H�c�M�K�X
		if (!op.get().getEmail().matches(user.getEmail()) || //
				!encoder.matches(user.getPwd(), op.get().getPwd())) {
			return new UserRes(RtnCode.PARAM_ERROR);
		}

		// �[�K��s�JDB
		user.setPwd(encoder.encode(user.getPwd()));

		// ��s�Ӥ�
		op.get().setPhoto(user.getPhoto());

		try {
			userDao.save(user);
			// �M�űK�X(���^��)
			user.setPwd("");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new UserRes(RtnCode.USER_UPDATE_FAILED);
		}

		return new UserRes(RtnCode.SUCCESSFUL, user);
	}

	@Override
	public String getVerifyMail(String email) {
		if (!StringUtils.hasText(email)) {
			return "email is null";
		}

		String verCode = RandomCode.generateVerificationCode();

		// �o�e���ҽX
		new Thread(() -> {
			Mail.sentConfirmMail(email, verCode);
		}).start();

		return verCode;
	}

	@Override
	public UserRes addPoints(int id, int points) {
		if (!userDao.existsById(id)) {
			return new UserRes(RtnCode.PARAM_ERROR);
		}

		User user = userDao.findById(id).get();

		user.setPoints(user.getPoints() + points);

		try {
			userDao.save(user);
			user.setPwd("");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new UserRes(RtnCode.USER_UPDATE_FAILED);
		}

		return new UserRes(RtnCode.SUCCESSFUL, user);
	}

	@Override
	public UserRes addPoints(int id, String password, int points) {
		if (!userDao.existsById(id)) {
			return new UserRes(RtnCode.PARAM_ERROR);
		}

		User user = userDao.findById(id).get();
		if (!encoder.matches(password, user.getPwd())) {
			return new UserRes(RtnCode.PASSWORD_ERROR);
		}

		user.setPoints(user.getPoints() + points);

		try {
			userDao.save(user);
			user.setPwd("");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new UserRes(RtnCode.USER_UPDATE_FAILED);
		}

		return new UserRes(RtnCode.SUCCESSFUL, user);
	}

}
