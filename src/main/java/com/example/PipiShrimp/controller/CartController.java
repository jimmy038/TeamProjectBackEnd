package com.example.PipiShrimp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PipiShrimp.entity.Cart;
import com.example.PipiShrimp.service.ifs.CartService;
import com.example.PipiShrimp.vo.CartRes;

@CrossOrigin
@RestController
public class CartController {

	@Autowired
	private CartService service;

	@PostMapping(value = "/cart/create")
	public CartRes create(@RequestBody Cart cart) {
		return service.create(cart);
	}

	@PostMapping(value = "cart/delete")
	public CartRes delete(@RequestParam(value = "id") int id) {
		return service.delete(id);
	}
}
