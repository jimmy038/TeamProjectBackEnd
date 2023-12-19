package com.example.PipiShrimp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.PipiShrimp.entity.Mail;
import com.example.PipiShrimp.entity.User;
import com.example.PipiShrimp.repository.UserDao;
import com.example.PipiShrimp.service.ifs.UserService;
import com.example.PipiShrimp.vo.UserReq;
import com.example.PipiShrimp.vo.UserRes;

@SpringBootTest
public class UserServiceTest {
	@Autowired
	private UserService service;

	@Autowired
	private UserDao dao;

	@Test
	public void signUpTest() {
		// 成功新增
		User user = new User("ian", "ian0217@gmail.com", "ian8787gogo");
		UserRes res = service.signUp(user);
		System.out.println("執行結果:" + res.getRtnCode().getMessage());

		// 資料為空
		user = new User("", "ian@gmail.com", "");
		res = service.signUp(user);
		System.out.println("執行結果:" + res.getRtnCode().getMessage());

		// 信箱已存在
		user = new User("jack", "ian0217@gmail.com", "ian1234fff");
		res = service.signUp(user);
		System.out.println("執行結果:" + res.getRtnCode().getMessage());

		// 密碼格式不符
		user = new User("jack", "jack@gmail.com", "12346789");
		res = service.signUp(user);
		System.out.println("執行結果:" + res.getRtnCode().getMessage());

		// email格式不符
//		user = new User("jack", "jackl.com", "ian1234fff");
//		res = service.signUp(user);
//		System.out.println("執行結果:" + res.getRtnCode().getMessage());
	}

	@Test
	public void loginTest() {
		UserReq req = new UserReq("ian0217@gmail.com", "ian8787gogo");
		// 帳號密碼正確
		UserRes res = service.login(req);
		System.out.println("執行結果:" + res.getRtnCode().getMessage());

		// email未註冊
		req = new UserReq("fuck1234@gmail.com", "ian8787gogo");
		res = service.login(req);
		System.out.println("執行結果:" + res.getRtnCode().getMessage());

		// 密碼錯誤
		req = new UserReq("ian0217@gmail.com", "ianGG3cm");
		res = service.login(req);
		System.out.println("執行結果:" + res.getRtnCode().getMessage());

		// 未輸入資料
		req = new UserReq("", "ianGG3cm");
		res = service.login(req);
		System.out.println("執行結果:" + res.getRtnCode().getMessage());
	}

//	@Test
//	public void sentMailTest() {
//		Mail.sentSignUpMail();
//	}

//	@Test
//	public void deleteAll() {
//		dao.deleteAll();
//	}
}
