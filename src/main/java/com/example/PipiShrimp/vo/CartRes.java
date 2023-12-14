package com.example.PipiShrimp.vo;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Cart;

public class CartRes {
	private RtnCode rtnCode;

	private Cart cart;

	public CartRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartRes(RtnCode rtnCode) {
		super();
		this.rtnCode = rtnCode;
	}

	public CartRes(RtnCode rtnCode, Cart cart) {
		super();
		this.rtnCode = rtnCode;
		this.cart = cart;
	}

	public RtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(RtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

}
