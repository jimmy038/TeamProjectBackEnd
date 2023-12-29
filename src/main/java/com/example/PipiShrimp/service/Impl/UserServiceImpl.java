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

import net.bytebuddy.utility.RandomString;

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
			// 創建一個多執行續，開執行續讓他跑寄EMAIL，發送登入成功通知郵件給用戶。這樣可以在背景中執行發送郵件的操作，不會阻塞主線程的執行。
			String  userEmail = req.getEmail();
			new Thread(() -> {
				Mail.sentLoginMail(userEmail);
			}).start();
			logger.info("login successful");
			// 清除密碼(不回傳)
			user.setPwd("");
			//判斷如果為要更改密碼的回傳302(FOUND)重新導向給前端，讓前端依RtnCode判斷導向更改忘記密碼頁面
			if(user.isResetPwd() == true) {
				return new UserRes(RtnCode.FOUND_TO_CHANGE_PASSWORD_PAGE);
			}
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

		// 更新點數
		user.setPoints(op.get().getPoints() + user.getPoints());

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
	@Override /** 忘記密碼 **/
	public UserRes sentForgotPwd(String email) {
		if (!StringUtils.hasText(email)) {
			return new UserRes(RtnCode.PARAM_ERROR);
		}
		// 設置初始值，避免後續空指針
		User user = null;
		user = userDao.findByEmail(email);
		// 假設 user撈回來的email沒東西時，回EMAIL_NOT_FOUND
		if (user == null) {
			return new UserRes(RtnCode.EMAIL_NOT_FOUND);
		}

		// 產生新密碼，並暫存起來
		String cachedRandomPwd = RandomString.make(12);
		// 將生成的亂碼設定到使用者物件中，設為使用者一次性密碼暫存
		user.setPwd(encoder.encode(cachedRandomPwd)); // 使用先前暫存的亂碼
		user.setResetPwd(true); // 將判斷是否需要更改密碼狀態的欄位改為true
		userDao.save(user);
		try {
			new Thread(() -> {
				// 發送忘記密碼通知mail時，使用存入資料庫的亂碼
				Mail.sentForgotPwdMail(email, cachedRandomPwd); // 傳遞用戶的亂碼
			}).start();
			logger.info("ForgotPassword sent successful");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new UserRes(RtnCode.FORGOT_PASSWORD_ERROR);
		}
		return new UserRes(RtnCode.SUCCESSFUL, user);
	}
	
	@Override /** 更改密碼 **/
	public UserRes changePwd(String email, String oldPwd, String newPwd) {
		if (!StringUtils.hasText(email) || !StringUtils.hasText(oldPwd) || !StringUtils.hasText(newPwd)) {
			return new UserRes(RtnCode.PARAM_ERROR);
		}
		// 設置初始值，避免後續空指針
		User user = null;
		user = userDao.findByEmail(email);

		if (user == null) {
			return new UserRes(RtnCode.EMAIL_NOT_FOUND);
		}

		// 取得一次性密碼，做後續判斷
		String cachedRandomPwd = user.getPwd();

		// 如果存在一次性密碼，將其作為舊密碼，否則使用原密碼進行比對驗證
		String oldPassword = StringUtils.hasText(cachedRandomPwd) ? cachedRandomPwd : user.getPwd();

		// 判斷新的密碼不能與舊密碼相同
		if (oldPassword.equals(newPwd)) {
			return new UserRes(RtnCode.OLD_PASSWORD_AND_NEW_PASSWORD_ARE_IDENITCAL);
		}

		try {
			// 設定新密碼為加密後的密碼
			user.setPwd(encoder.encode(newPwd));
			user.setResetPwd(false); // 將判斷是否需要更改密碼狀態的欄位改為false
			userDao.save(user);
			// 儲存後清空密碼(不回傳)
			user.setPwd("");
			// 發送更改密碼成功通知mail
			new Thread(() -> {
				Mail.sentChangePwdMail(email);
			}).start();
			logger.info("ChangePssword sent successful");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new UserRes(RtnCode.CHANGE_PASSWORD_ERROR);
		}
		return new UserRes(RtnCode.SUCCESSFUL);
	}
}
