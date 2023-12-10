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
		// ���\�s�W
		User user = new User("ian", "ian0217@gmail.com", "ian8787gogo");
		UserRes res = service.signUp(user);
		System.out.println("���浲�G:" + res.getRtnCode().getMessage());
		System.out.printf("�m�W:%s �H�c:%s �K�X:%s\n", //
				res.getUser().getName(), res.getUser().getEmail(), res.getUser().getPwd());

		// ��Ƭ���
		user = new User("", "ian@gmail.com", "");
		res = service.signUp(user);
		System.out.println("���浲�G:" + res.getRtnCode().getMessage());

		// �H�c�w�s�b
		user = new User("jack", "ian0217@gmail.com", "ian1234fff");
		res = service.signUp(user);
		System.out.println("���浲�G:" + res.getRtnCode().getMessage());

		// �K�X�榡����
		user = new User("jack", "jack@gmail.com", "12346789");
		res = service.signUp(user);
		System.out.println("���浲�G:" + res.getRtnCode().getMessage());
	}

	@Test
	public void deleteAll() {
		dao.deleteAll();
	}
}
