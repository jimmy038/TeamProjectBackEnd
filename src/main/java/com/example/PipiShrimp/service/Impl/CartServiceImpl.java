package com.example.PipiShrimp.service.Impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Cart;
import com.example.PipiShrimp.repository.CartDao;
import com.example.PipiShrimp.repository.UserDao;
import com.example.PipiShrimp.service.ifs.CartService;
import com.example.PipiShrimp.vo.CartRes;
import com.example.PipiShrimp.vo.CartSearchRes;

//TODO 所有操作都必須先登入帳號才能進行
@Service
public class CartServiceImpl implements CartService {

	/* org.slf4j.Logger */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CartDao dao;

	@Autowired
	private UserDao userDao;

	@Override
	public CartRes create(Cart cart) {

		// 檢查參數
		if (!StringUtils.hasText(cart.getProductName()) || //
				cart.getCartCount() < 0 || cart.getProductAmount() <= 0) {
			return new CartRes(RtnCode.PARAM_ERROR);
		}

		try {
			dao.save(cart);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new CartRes(RtnCode.CART_CREATE_FAILED);
		}

		return new CartRes(RtnCode.SUCCESSFUL, cart);
	}

	@Override
	public CartRes delete(int id) {
		// 檢查cart_id是否存在
		if (!dao.existsById(id)) {
			return new CartRes(RtnCode.CART_ID_NOT_FOUND);
		}

		// 取得刪除商品資訊，用於回傳給使用者
		Cart res = dao.findById(id).get();

		try {
			dao.deleteById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new CartRes(RtnCode.CART_CREATE_FAILED);
		}

		return new CartRes(RtnCode.SUCCESSFUL, res);
	}

	@Override
	public CartSearchRes getCartInfoByUserId(int id) {
		// 檢查user_id是否存在
		if (!userDao.existsById(id)) {
			return new CartSearchRes(RtnCode.USER_ID_NOT_FOUND);
		}

		List<Cart> cartList = dao.findAllByUserId(id);

		// 如果購物車是空的，要給他一個empty List，不能是null
		cartList = cartList.size() != 0 ? cartList : Collections.emptyList();

		return new CartSearchRes(RtnCode.SUCCESSFUL, cartList);
	}
}
