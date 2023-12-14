package com.example.PipiShrimp.vo;

import java.util.List;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Cart;

public class CartSearchRes {
	private RtnCode rtnCode;

	private List<Cart> cartList;

	public CartSearchRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartSearchRes(RtnCode rtnCode) {
		super();
		this.rtnCode = rtnCode;
	}

	public CartSearchRes(RtnCode rtnCode, List<Cart> cartList) {
		super();
		this.rtnCode = rtnCode;
		this.cartList = cartList;
	}

	public RtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(RtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}

	public List<Cart> getCartList() {
		return cartList;
	}

	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}

}
