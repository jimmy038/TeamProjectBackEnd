package com.example.PipiShrimp.service.ifs;

import com.example.PipiShrimp.entity.Product;
import com.example.PipiShrimp.vo.ProductRes;
import com.example.PipiShrimp.vo.ProductSearchRes;

public interface ProductService {
	
	
	//每個帳號顯示不同商品
	public ProductSearchRes getProductInfoByUserId(int id);
	/**
	 * 新增商品&編輯商品
	 **/
	public ProductRes create(Product product);

	/**
	 * 刪除單筆商品(參數使用product_id)
	 **/
	public ProductRes delete(int id);

	/**
	 * 取得單一商品資訊(參數使用product_id)
	 **/
	public ProductRes getProductInfo(int id);

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
