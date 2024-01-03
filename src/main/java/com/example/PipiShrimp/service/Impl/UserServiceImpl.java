package com.example.PipiShrimp.service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

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
	
	private String generateRandomPassword() {
		 // 產生一個隨機數生成器
		Random random = new Random();
		 // 產生一個 StringBuilder 物件來儲存密碼
		StringBuilder pwd = new StringBuilder();
		// 產生字母和數字的字串    		  // 包含所有大小寫字母
		String englishCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";// 包含數字 0 到 9
		
		//使英文及數字交替排列成亂數
		for(int i = 0 ; i < 6 ; i++) {
			pwd.append(englishCharacters.charAt(random.nextInt(englishCharacters.length())));
			pwd.append(numbers.charAt(random.nextInt(numbers.length())));
		}
		
	    // 將密碼 的字元存入 List 中 ， List包含集合及繼承
		List<Character> chars = pwd.chars().mapToObj(e ->(char)e).collect(Collectors.toList());
		 // 將 List 中的字元進行洗牌，即打亂順序
		Collections.shuffle(chars);
		// 創建一個新的 StringBuilder 來存放洗牌後的密碼字串
	    StringBuilder shuffledPassword = new StringBuilder();
	    
	    // 將洗牌後的字元逐一添加到新的 StringBuilder 中，組成最後的密碼
	    for (Character character : chars) {
	        shuffledPassword.append(character);
	    }
	    // 回傳洗牌後的密碼字串
	    return shuffledPassword.toString();
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

<<<<<<< HEAD
		// 產生新密碼，並暫存起來
		String cachedRandomPwd = generateRandomPassword() ;
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
=======
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

>>>>>>> ian
}
