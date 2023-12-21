package com.example.PipiShrimp.controller;

<<<<<<< HEAD
import java.util.List;

<<<<<<< HEAD
import javax.servlet.http.HttpSession;

=======
>>>>>>> ccb53b51ab44c1d0c7b76bcaa39d540e26c9b224
=======
>>>>>>> ian
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PipiShrimp.entity.Cart;
import com.example.PipiShrimp.service.ifs.CartService;
import com.example.PipiShrimp.vo.CartRes;
import com.example.PipiShrimp.vo.CartSearchRes;

//TODO 都要先登入才能使用
@CrossOrigin
@RestController
public class CartController {

	@Autowired
	private CartService service;

	@PostMapping(value = "/cart/create")
	public CartRes create(@RequestBody Cart cart) {

		return service.create(cart);
	}

<<<<<<< HEAD
	@PostMapping(value = "/cart/delete")
	public CartSearchRes delete(@RequestParam List<Integer> idList) {

		return service.delete(idList);
=======
	@PostMapping(value = "cart/delete")
	public CartRes delete(@RequestParam(value = "id") int id) {
		return service.delete(id);
>>>>>>> ccb53b51ab44c1d0c7b76bcaa39d540e26c9b224
	}

	@GetMapping(value = "/cart/get/user_id")
	public CartSearchRes getCartInfoByUserId(//
			@RequestParam(value = "id") int id) {

		return service.getCartInfoByUserId(id);
	}
}
