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

	// TODO 信箱格式判斷(regexp)
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
//		String patternEmail = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}";
//		if (!user.getEmail().matches(patternEmail)) {
//			return new UserRes(RtnCode.EMAIL_FORMAT_ERROR);
//		}

		// 檢查密碼格式(要有英文+數字，至少8字元)
		String patternPwd = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$";
		if (!user.getPwd().matches(patternPwd)) {
			return new UserRes(RtnCode.PASSWORD_FORMAT_ERROR);
		}
		// 密碼加密
		user.setPwd(encoder.encode(user.getPwd()));

		try {
			userDao.save(user);
			// 儲存後清空密碼(不回傳)
			user.setPwd("");
			// 寄電子郵件通知(使用)
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

		// email未註冊
		if (!userDao.existsByEmail(req.getEmail())) {
			return new UserRes(RtnCode.EMAIL_NOT_FOUND);
		}

		User user = userDao.findByEmail(req.getEmail());
		// 資料庫資料爆炸(資料消失)
		if (user == null) {
			return new UserRes(RtnCode.DATABASE_IS_EMPTY);
		}

		// 密碼不符合帳號
		if (!encoder.matches(req.getPwd(), user.getPwd())) {
			return new UserRes(RtnCode.PASSWORD_ERROR);
		}

		try {
			// 發送登入成功通知
			new Thread(() -> {
				Mail.sentLoginMail(req.getEmail());
			}).start();

			logger.info("login successful");
			// 清除密碼(不回傳)
			user.setPwd("");

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new UserRes(RtnCode.SENT_EMAIL_FAILED);
		}
		return new UserRes(RtnCode.SUCCESSFUL, user);
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
		op.get().setPhoto(user.getPhoto());

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

	@Override
	public String getVerifyMail(String email) {
		if (!StringUtils.hasText(email)) {
			return "email is null";
		}

		String verCode = RandomCode.generateVerificationCode();

		// 發送驗證碼
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
