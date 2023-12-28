package com.example.PipiShrimp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSessionIdListener;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.PipiShrimp.entity.Cart;
import com.example.PipiShrimp.repository.CartDao;
import com.example.PipiShrimp.service.ifs.CartService;

@SpringBootTest
public class CartServiceTest {

	@Autowired
	private CartService service;

	@Autowired
	private CartDao dao;

	@Test
	public void deleteTest() {
		List<Integer> idList = new ArrayList<Integer>(Arrays.asList(1,3));
		List<Cart> res = service.delete(idList).getCartList();
		for (Cart cart : res) {
			System.out.println("§R°£¸ê®Æ: " + cart.getProductName());
		}
	}

}
