package com.example.PipiShrimp.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Cart;
import com.example.PipiShrimp.repository.CartDao;
import com.example.PipiShrimp.service.ifs.CartService;
import com.example.PipiShrimp.vo.CartRes;

//TODO �Ҧ��ާ@���������n�J�b���~��i��
@Service
public class CartServiceImpl implements CartService {

	/* org.slf4j.Logger */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CartDao dao;

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
	public CartRes delete(int id) {
		// �ˬdcart_id�O�_�s�b
		if (!dao.existsById(id)) {
			return new CartRes(RtnCode.CART_ID_NOT_FOUND);
		}

		// ���o�R���ӫ~��T�A�Ω�^�ǵ��ϥΪ�
		Cart res = dao.findById(id).get();

		try {
			dao.deleteById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new CartRes(RtnCode.CART_CREATE_FAILED);
		}

		return new CartRes(RtnCode.SUCCESSFUL, res);
	}
}
