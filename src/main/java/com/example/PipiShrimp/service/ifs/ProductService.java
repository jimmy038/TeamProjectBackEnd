package com.example.PipiShrimp.service.ifs;

import com.example.PipiShrimp.entity.Product;
import com.example.PipiShrimp.vo.ProductCreateRes;
import com.example.PipiShrimp.vo.ProductSearchRes;

public interface ProductService {

	/**
	 * 新增商品
	 **/
	public ProductCreateRes create(Product product);

	/**
	 * 取得商品資訊(所有產品)
	 **/
	public ProductSearchRes getAllProductInfo();

	/**
	 * 取得搜尋商品名稱的商品資訊(模糊搜尋)
	 **/
	public ProductSearchRes getProductByName(String productName);

	/**
	 * 取得商品資訊(依價錢排序 低 => 高)
	 **/
	public ProductSearchRes getProductByPrice();

	/**
	 * 取得商品資訊(依價錢排序 高 => 低)
	 **/
	public ProductSearchRes getProductByPriceDesc();

}
