package com.example.PipiShrimp.service.Impl;

import java.util.ArrayList;
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

//TODO �Ҧ��ާ@���������n�J�b���~��i��
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

		// �ˬd�Ѽ�
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
	public CartSearchRes delete(List<Integer> idList) {
		// �s��n�R����cart���
		List<Cart> cartList = new ArrayList<Cart>();

		// �ˬdcart_id�O�_�s�b
		for (int id : idList) {
			if (!dao.existsById(id)) {
				return new CartSearchRes(RtnCode.CART_ID_NOT_FOUND);
			}

			// ���o�R���ӫ~��T�A�Ω�^�ǵ��ϥΪ�
			Cart cart = dao.findById(id).get();
			cartList.add(cart);
		}

		try {
			dao.deleteAllById(idList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new CartSearchRes(RtnCode.CART_DELETE_FAILED);
		}

		return new CartSearchRes(RtnCode.SUCCESSFUL, cartList);
	}

	@Override
	public CartSearchRes getCartInfoByUserId(int id) {
		// �ˬduser_id�O�_�s�b
		if (!userDao.existsById(id)) {
			return new CartSearchRes(RtnCode.USER_ID_NOT_FOUND);
		}

		List<Cart> cartList = dao.findAllByUserId(id);

		// �p�G�ʪ����O�Ū��A�n���L�@��empty List�A����Onull
		cartList = cartList.size() != 0 ? cartList : Collections.emptyList();

		return new CartSearchRes(RtnCode.SUCCESSFUL, cartList);
	}
}
