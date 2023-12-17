package com.example.PipiShrimp.service.ifs;

import com.example.PipiShrimp.entity.Cart;
import com.example.PipiShrimp.vo.CartRes;
import com.example.PipiShrimp.vo.CartSearchRes;

public interface CartService {

	/**
	 * �s�W�ʪ����ӫ~ #�N�ӫ~�[�J�ʪ���
	 **/
	public CartRes create(Cart cart);

	/**
	 * �M���ʪ����ӫ~(�Ѽ�:cart_id) #�N�ʪ����ӫ~�M��
	 **/
	public CartRes delete(int id);

	/**
	 * �̷ӨϥΪ�id��ܹ������ʪ�����T(�Ѽ�:user_id) #user�d���ʪ���
	 **/
	public CartSearchRes getCartInfoByUserId(int id);
}
