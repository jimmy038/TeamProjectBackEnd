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
//�ѹϤ�
	@PostMapping(value = "/product/create")
	public ProductRes create(@RequestBody Product product) {

		return service.create(product);
	}
<<<<<<< HEAD

	@PostMapping(value = "/product/delete")
	public ProductRes delete(@RequestParam(value = "id") int id) {

		return service.delete(id);
	}

	@GetMapping(value = "/product/get/info/user_id")
	public ProductSearchRes getProductInfoByUserId(//
			@RequestParam(value = "id") int id) {

		return service.getProductInfoByUserId(id);
	}

=======
//�d�w
	@DeleteMapping(value = "/product/delete")
	public ProductRes delete(@RequestParam(value = "id") int id) {
	    // TODO �u���n�J�̥i�H�R���ӫ~
	    return service.delete(id);
	}
	
	
	/**
	 * ���o��@�ӫ~��T(�Ѽƨϥ�product_id)
	 **/
>>>>>>> ccb53b51ab44c1d0c7b76bcaa39d540e26c9b224
	@GetMapping(value = "/product/get/info")
	public ProductRes getProductInfo(//
			@RequestParam(value = "id") int id) {
		return service.getProductInfo(id);
	}

	@GetMapping(value = "/product/get")
	public ProductSearchRes getAllProductInfo() {
		return service.getAllProductInfo();
	}

	//�W�����M�\��
	@GetMapping(value = "/product/search")
	public ProductSearchRes getProductByName(//
			@RequestParam(value = "product", required = false) String productName) {
		return service.getProductByName(productName);
	}
//����
	@GetMapping(value = "/product/price/sort")
	public ProductSearchRes getProductByPrice() {
		return service.getProductByPrice();
	}
//������
	@GetMapping(value = "/product/price/sort/desc")
	public ProductSearchRes getProductByPriceDesc() {
		return service.getProductByPriceDesc();
	}
}
