package com.example.PipiShrimp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.User;
import com.example.PipiShrimp.service.ifs.UserService;
import com.example.PipiShrimp.vo.ChangePasswordReq;
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
			session.setAttribute("user", result.getUser());
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
	
	@PostMapping(value = "/user/sentForgotPwd")
	public UserRes sentForgotPwd(@RequestBody int id, String email) {
		return service.sentForgotPwd(id, email);
	}
	
	@PostMapping(value = "/user/changePwd")
	public UserRes changePwd(@RequestBody ChangePasswordReq req, HttpSession session) {
		//要更改密碼請先登入
		if(session.getAttribute(req.getEmail()) == null ) {
			return new UserRes(RtnCode.PLEASE_LOGIN_FIRST);
		}
		return service.changePwd(req.getEmail(), req.getOldPwd(), req.getNewPwd());
	}

}
