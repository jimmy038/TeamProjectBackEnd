package com.example.PipiShrimp.vo;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Product;

import java.util.List;

public class ProductSearchRes {
	private RtnCode rtnCode;

	private List<Product> products;

	public ProductSearchRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductSearchRes(RtnCode rtnCode) {
		super();
		this.rtnCode = rtnCode;
	}

	public ProductSearchRes(RtnCode rtnCode, List<Product> products) {
		super();
		this.rtnCode = rtnCode;
		this.products = products;
	}

	public RtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(RtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
