package com.example.PipiShrimp.service.Impl;

import java.time.LocalDateTime;
import java.util.Optional;

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

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService {

	/* org.slf4j.Logger */
	private Logger logger = LoggerFactory.getLogger(getClass());

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private UserDao userDao;

	// TODO 信箱格式判斷(regexp)
	@Override // 註冊
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
			// 寄電子郵件通知
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

		// email未註冊
		if (!userDao.existsByEmail(req.getEmail())) {
			return new UserRes(RtnCode.EMAIL_NOT_FOUND);
		}

		User user = userDao.findByEmail(req.getEmail());
		// 防止資料庫資料爆炸(資料消失)
		if (user == null) {
			return new UserRes(RtnCode.DATABASE_IS_EMPTY);
		}

		// 密碼不符合帳號
		if (!encoder.matches(req.getPwd(), user.getPwd())) {
			return new UserRes(RtnCode.PASSWORD_ERROR);
		}

		try {
			// 發送登入成功通知
			Mail.sentLoginMail(req.getEmail());
			logger.info("login successful");
			// 清除密碼(不回傳)
			user.setPwd("");
			return new UserRes(RtnCode.SUCCESSFUL, user);

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new UserRes(RtnCode.SENT_EMAIL_FAILED);
		}
	}

	@Override /** 忘記密碼 **/
	public UserRes sentForgotPwd(int id, String email) {
		if (id < 0 && !StringUtils.hasText(email)) {
			return new UserRes(RtnCode.PARAM_ERROR);
		}
		// 設置初始值，避免後續空指針
		User user = null;
		// 假設ID有值
		if (id > 0) {
			Optional<User> op = userDao.findById(id);
			// 判斷ID如果為空回傳USER_ID_NOT_FOUND
			if (op.isEmpty()) {
				return new UserRes(RtnCode.USER_ID_NOT_FOUND);
			}
			user = op.get();
		} else {
			user = userDao.findByEmail(email);
			//假設 user撈回來的email沒東西時，回EMAIL_NOT_FOUND
			if (user == null) {
				return new UserRes(RtnCode.EMAIL_NOT_FOUND);
			}
		}
		// RandomString.make產生包含大小寫英文數字都有的亂數，產生10碼的亂數的密碼
		// 產生新密碼↓
		String randomPwd = RandomString.make(10);	
		// 發送忘記密碼通知mail
		Mail.sentForgotPwdMail(email);
		user.setPwd(encoder.encode(randomPwd)); 
		try {
			userDao.save(user);
			logger.info("ForgotPassword sent successful");
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new UserRes(RtnCode.FORGOT_PASSWORD_ERROR);
		}
		return new UserRes(RtnCode.SUCCESSFUL, user);
	}

	@Override /** 更改密碼 **/
	public UserRes changePwd(String email, String oldPwd, String newPwd) {
		if(!StringUtils.hasText(email) || !StringUtils.hasText(oldPwd)|| !StringUtils.hasText(newPwd)) {
			return new UserRes(RtnCode.PARAM_ERROR);
		}
		//判斷新的密碼不能與舊密碼相同
		if(oldPwd.equals(newPwd)) {
			return new UserRes(RtnCode.OLD_PASSWORD_AND_NEW_PASSWORD_ARE_IDENITCAL);
		}
		
		User user = userDao.findByEmail(email);
		
		if(!encoder.matches(oldPwd,user.getPwd() )) {
			return new UserRes(RtnCode.PASSWORD_ERROR);
		}
		//設定新密碼為加密後的密碼
		user.setPwd(encoder.encode(newPwd));
		try {
			userDao.save(user);
			// 儲存後清空密碼(不回傳)
			user.setPwd("");
			logger.info("ChangePssword sent successful");
			// 發送更改密碼通知mail
			Mail.sentChangePwdMail(email);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new UserRes(RtnCode.CHANGE_PASSWORD_ERROR);
		}
		return new UserRes(RtnCode.SUCCESSFUL);
	}
	
}
