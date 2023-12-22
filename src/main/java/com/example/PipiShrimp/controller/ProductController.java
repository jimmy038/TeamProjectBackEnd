package com.example.PipiShrimp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PipiShrimp.entity.Product;
import com.example.PipiShrimp.service.ifs.ProductService;
import com.example.PipiShrimp.vo.ProductRes;
import com.example.PipiShrimp.vo.ProductSearchRes;

@CrossOrigin
@RestController
public class ProductController {

	@Autowired
	private ProductService service;
//剩圖片
	@PostMapping(value = "/product/create")
	public ProductRes create(@RequestBody Product product) {
		// TODO 只有登入者可以新增商品
		return service.create(product);
	}
//搞定
	@DeleteMapping(value = "/product/delete")
	public ProductRes delete(@RequestParam(value = "id") int id) {
	    // TODO 只有登入者可以刪除商品
	    return service.delete(id);
	}
	
	//每個帳號顯示不同商品

	@GetMapping(value = "/product/get/info/user_id")
	 public ProductSearchRes getProductInfoByUserId(//
	   @RequestParam(value = "id") int id) {

	  return service.getProductInfoByUserId(id);
	 }
	/**
	 * 取得單一商品資訊(參數使用product_id)
	 **/
	@GetMapping(value = "/product/get/info")
	public ProductRes getProductInfo(//
			@RequestParam(value = "id") int id) {
		return service.getProductInfo(id);
	}

	@GetMapping(value = "/product/get")
	public ProductSearchRes getAllProductInfo() {
		return service.getAllProductInfo();
	}

	//上面收尋功能
	@GetMapping(value = "/product/search")
	public ProductSearchRes getProductByName(//
			@RequestParam(required = false) String productName) {
		return service.getProductByName(productName);
	}
//種類
	@GetMapping(value = "/product/price/sort")
	public ProductSearchRes getProductByPrice() {
		return service.getProductByPrice();
	}
//先不用
	@GetMapping(value = "/product/price/sort/desc")
	public ProductSearchRes getProductByPriceDesc() {
		return service.getProductByPriceDesc();
	}
}
