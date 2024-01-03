package com.example.PipiShrimp;

import static org.mockito.ArgumentMatchers.intThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.PipiShrimp.entity.User;
import com.example.PipiShrimp.repository.UserDao;
import com.example.PipiShrimp.service.ifs.UserService;
import com.example.PipiShrimp.utils.Mail;
import com.example.PipiShrimp.utils.RandomCode;
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
		// ���\�s�W
		User user = new User("ian", "ian0217@gmail.com", "ian8787gogo");
		UserRes res = service.signUp(user);
		System.out.println("���浲�G:" + res.getRtnCode().getMessage());

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

		// email�榡����
//		user = new User("jack", "jackl.com", "ian1234fff");
//		res = service.signUp(user);
//		System.out.println("���浲�G:" + res.getRtnCode().getMessage());
	}

	@Test
	public void loginTest() {
		UserReq req = new UserReq("ian0217@gmail.com", "ian8787gogo");
		// �b���K�X���T
		UserRes res = service.login(req, null);
		System.out.println("���浲�G:" + res.getRtnCode().getMessage());

		// email�����U
		req = new UserReq("fuck1234@gmail.com", "ian8787gogo");
		res = service.login(req, null);
		System.out.println("���浲�G:" + res.getRtnCode().getMessage());

		// �K�X���~
		req = new UserReq("ian0217@gmail.com", "ianGG3cm");
		res = service.login(req, null);
		System.out.println("���浲�G:" + res.getRtnCode().getMessage());

		// ����J���
		req = new UserReq("", "ianGG3cm");
		res = service.login(req, null);
		System.out.println("���浲�G:" + res.getRtnCode().getMessage());
	}

	@Test
	public void ranDomcode() {
		for (int i = 0; i < 10; i++) {
			String verificationCode = RandomCode.generateVerificationCode();
			System.out.println("���ҽX: " + verificationCode);
		}
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
