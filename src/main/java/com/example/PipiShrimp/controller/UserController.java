package com.example.PipiShrimp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.User;
import com.example.PipiShrimp.repository.UserDao;
import com.example.PipiShrimp.service.ifs.UserService;
import com.example.PipiShrimp.vo.UserReq;
import com.example.PipiShrimp.vo.UserRes;

@CrossOrigin
@RestController
public class UserController {
	@Autowired
	private UserService service;

	@Autowired
	private UserDao userDao;

	@PostMapping(value = "/user/sign_up")
	public UserRes signUp(@RequestBody User user) {
		return service.signUp(user);
	}

	@PostMapping(value = "/user/login")
	public UserRes login(@RequestBody UserReq req, //
			HttpSession session) {
		// �p�Gsession���S����ơA�n�D�n�J�b��
		if (session.getAttribute(req.getEmail()) == null) {
			UserRes result = service.login(req);

			// �x�s�ϥΪ̸�ƨ�session
			session.setAttribute("user", result.getUser());
			return result;
		}
		return null;
	}

	@PostMapping(value = "/user/logout")
	public UserRes logout(HttpSession session) {
		// �M��session���
		session.invalidate();
		return new UserRes(RtnCode.SUCCESSFUL);
	}

	@GetMapping(value = "/user/info")
	public UserRes getUserInfo(@RequestParam(name = "id") int id, //
			HttpSession session) {

		User user = (User) session.getAttribute("user");
		// �P�_�O�_�n�J�A�u���n�J�̥i�H�d��user���
		if (user == null) {
			return new UserRes(RtnCode.LOGIN_FIRST);
		}

		if (!userDao.existsById(user.getId())) {
			return new UserRes(RtnCode.USER_ID_NOT_FOUND);
		}

		return service.getUserInfo(id);
	}

}
