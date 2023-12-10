package com.example.PipiShrimp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.PipiShrimp.entity.User;
import com.example.PipiShrimp.repository.UserDao;
import com.example.PipiShrimp.service.ifs.UserService;
import com.example.PipiShrimp.vo.UserRes;

@SpringBootTest
public class UserServiceTest {
	@Autowired
	private UserService service;

	@Autowired
	private UserDao dao;

	@Test
	public void loginTest() {
		// 成功新增
		User user = new User("ian", "ian0217@gmail.com", "ian8787gogo");
		UserRes res = service.signUp(user);
		System.out.println("執行結果:" + res.getRtnCode().getMessage());
		System.out.printf("姓名:%s 信箱:%s 密碼:%s\n", //
				res.getUser().getName(), res.getUser().getEmail(), res.getUser().getPwd());

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
	}

	@Test
	public void deleteAll() {
		dao.deleteAll();
	}
}
