package com.example.PipiShrimp.service.ifs;

import com.example.PipiShrimp.entity.Cart;
import com.example.PipiShrimp.vo.CartRes;

public interface CartService {

	/**
	 * 新增購物車商品 #將商品加入購物車
	 **/
	public CartRes create(Cart cart);

	/**
	 * 清除購物車商品(參數:cart_id) #將購物車商品清除
	 **/
	public CartRes delete(int id);
}
