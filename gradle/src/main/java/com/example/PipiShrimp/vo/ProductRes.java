package com.example.PipiShrimp.vo;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Product;


public class ProductRes {
	private RtnCode rtnCode;

	private Product product;

	public ProductRes() {
		super();
	}

	public ProductRes(RtnCode rtnCode) {
		super();
		this.rtnCode = rtnCode;
	}

	public ProductRes(RtnCode rtnCode, Product product) {
		super();
		this.rtnCode = rtnCode;
		this.product = product;
	}

	public RtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(RtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
