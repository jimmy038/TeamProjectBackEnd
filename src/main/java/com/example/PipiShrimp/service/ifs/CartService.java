package com.example.PipiShrimp.service.ifs;

import java.util.List;

import com.example.PipiShrimp.entity.Cart;
import com.example.PipiShrimp.vo.CartRes;
import com.example.PipiShrimp.vo.CartSearchRes;

public interface CartService {

	/**
	 * 新增購物車商品 #將商品加入購物車
	 **/
	public CartRes create(Cart cart);

	/**
	 * 清除購物車商品(參數:cart_id) #將購物車商品清除
	 **/
//	public CartRes delete(int id);
	
	/**
	 * 清除購物車(複數)商品(參數:cart_id) #將購物車商品清除 
	 **/
	public CartSearchRes delete(List<Integer> idList);

	/**
	 * 依照使用者id顯示對應的購物車資訊(參數:user_id) #user查看購物車
	 **/
	public CartSearchRes getCartInfoByUserId(int id);
}
