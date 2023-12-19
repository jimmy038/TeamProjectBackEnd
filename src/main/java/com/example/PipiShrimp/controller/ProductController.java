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
import com.example.PipiShrimp.entity.Product;
import com.example.PipiShrimp.entity.User;
import com.example.PipiShrimp.repository.UserDao;
import com.example.PipiShrimp.service.ifs.ProductService;
import com.example.PipiShrimp.vo.ProductRes;
import com.example.PipiShrimp.vo.ProductSearchRes;

@CrossOrigin
@RestController
public class ProductController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProductService service;

	@PostMapping(value = "/product/create")
	public ProductRes create(@RequestBody Product product, //
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		// �P�_�O�_�n�J�A�u���n�J�̥i�H�s�W�ӫ~
		if (user == null) {
			return new ProductRes(RtnCode.LOGIN_FIRST);
		}

		if (!userDao.existsById(user.getId())) {
			return new ProductRes(RtnCode.USER_ID_NOT_FOUND);
		}
		return service.create(product);
	}

	@PostMapping(value = "/product/delete")
	public ProductRes delete(@RequestParam(value = "id") int id, //
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		// �P�_�O�_�n�J�A�u���n�J�̥i�H�s�W�ӫ~
		if (user == null) {
			return new ProductRes(RtnCode.LOGIN_FIRST);
		}

		if (!userDao.existsById(user.getId())) {
			return new ProductRes(RtnCode.USER_ID_NOT_FOUND);
		}
		return service.delete(id);
	}

	@GetMapping(value = "/product/get/info/user_id")
	public ProductSearchRes getProductInfoByUserId(//
			@RequestParam(value = "id") int id) {
		// TODO �u���n�J�̥i�H�d�ݦۤv���ӫ~
		return service.getProductInfoByUserId(id);
	}

	@GetMapping(value = "/product/get/info")
	public ProductRes getProductInfo(//
			@RequestParam(value = "id") int id) {
		return service.getProductInfo(id);
	}

	@GetMapping(value = "/product/get")
	public ProductSearchRes getAllProductInfo() {
		return service.getAllProductInfo();
	}

	@GetMapping(value = "/product/search")
	public ProductSearchRes getProductByName(//
			@RequestParam(required = false) String productName) {
		return service.getProductByName(productName);
	}

	@GetMapping(value = "/product/price/sort")
	public ProductSearchRes getProductByPrice() {
		return service.getProductByPrice();
	}

	@GetMapping(value = "/product/price/sort/desc")
	public ProductSearchRes getProductByPriceDesc() {
		return service.getProductByPriceDesc();
	}
}
