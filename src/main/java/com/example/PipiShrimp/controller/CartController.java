package com.example.PipiShrimp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Cart;
import com.example.PipiShrimp.entity.User;
import com.example.PipiShrimp.repository.UserDao;
import com.example.PipiShrimp.service.ifs.CartService;
import com.example.PipiShrimp.vo.CartRes;
import com.example.PipiShrimp.vo.CartSearchRes;

//TODO ���n���n�J�~��ϥ�
@CrossOrigin
@RestController
public class CartController {

	@Autowired
	private CartService service;

	@Autowired
	private UserDao userDao;

	@PostMapping(value = "/cart/create")
	public CartRes create(@RequestBody Cart cart, HttpSession session) {
		User user = (User) session.getAttribute("user");
		// �P�_�O�_�n�J�A�u���n�J�̥i�H�s�W�ʪ����ӫ~
		if (user == null) {
			return new CartRes(RtnCode.LOGIN_FIRST);
		}

		if (!userDao.existsById(user.getId())) {
			return new CartRes(RtnCode.USER_ID_NOT_FOUND);
		}

		return service.create(cart);
	}

	@PostMapping(value = "/cart/delete")
	public CartSearchRes delete(@RequestParam List<Integer> idList, HttpSession session) {
		User user = (User) session.getAttribute("user");
		// �P�_�O�_�n�J�A�u���n�J�̥i�H�R���ʪ����ӫ~
		if (user == null) {
			return new CartSearchRes(RtnCode.LOGIN_FIRST);
		}

		if (!userDao.existsById(user.getId())) {
			return new CartSearchRes(RtnCode.USER_ID_NOT_FOUND);
		}

		return service.delete(idList);
	}

	@GetMapping(value = "/cart/get/user_id")
	public CartSearchRes getCartInfoByUserId(//
			@RequestParam(value = "id") int id, HttpSession session) {
		User user = (User) session.getAttribute("user");
		// �P�_�O�_�n�J�A�u���n�J�̥i�H�d���ʪ����ӫ~
		if (user == null) {
			return new CartSearchRes(RtnCode.LOGIN_FIRST);
		}

		if (!userDao.existsById(user.getId())) {
			return new CartSearchRes(RtnCode.USER_ID_NOT_FOUND);
		}

		return service.getCartInfoByUserId(id);
	}
}
