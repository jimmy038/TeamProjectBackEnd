package com.example.PipiShrimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.PipiShrimp.repository.CartDao;
import com.example.PipiShrimp.service.ifs.CartService;

@SpringBootTest
public class CartServiceTest {

	@Autowired
	private CartService service;

	@Autowired
	private CartDao dao;

}
