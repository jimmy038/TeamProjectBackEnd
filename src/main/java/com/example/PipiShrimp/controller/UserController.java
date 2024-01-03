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
import com.example.PipiShrimp.service.ifs.UserService;
import com.example.PipiShrimp.vo.UserReq;
import com.example.PipiShrimp.vo.UserRes;

@CrossOrigin
@RestController
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping(value = "/user/sign_up")
	public UserRes signUp(@RequestBody User user) {
		return service.signUp(user);
	}

	@PostMapping(value = "/user/login")
	public UserRes login(@RequestBody UserReq req, //
			HttpSession session) {
		// 如果session內沒有資料，要求登入帳號
		if (session.getAttribute(req.getEmail()) == null) {
			UserRes result = service.login(req);

			// 儲存使用者資料到session
//			session.setAttribute("user", result.getUser());
			return result;
		}
		return null;
	}

	@PostMapping(value = "/user/logout")
	public UserRes logout(HttpSession session) {
		// 清除session資料
		session.invalidate();
		return new UserRes(RtnCode.SUCCESSFUL);
	}

	@GetMapping(value = "/user/info")
	public UserRes getUserInfo(@RequestParam(name = "id") int id) {

		return service.getUserInfo(id);
	}

	@PostMapping(value = "/user/edit")
	public UserRes editUserInfo(@RequestBody User user) {
		return service.editUserInfo(user);
	}

	@GetMapping(value = "/verify/sent")
	public String getVerifyMail(@RequestParam(name = "mail") String email) {
		return service.getVerifyMail(email);
	}

	@PostMapping(value = "/user/point/edit")
	public UserRes addPoints(@RequestParam(name = "id") int id, //
			@RequestParam(name = "points") int points) {
		return service.addPoints(id, points);
	}

}
