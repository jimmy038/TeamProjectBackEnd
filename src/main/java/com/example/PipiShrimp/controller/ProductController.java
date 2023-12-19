package com.example.PipiShrimp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Product;
import com.example.PipiShrimp.entity.User;
import com.example.PipiShrimp.repository.UserDao;
import com.example.PipiShrimp.service.ifs.ProductService;
import com.example.PipiShrimp.vo.ProductRes;
import com.example.PipiShrimp.vo.ProductSearchRes;

@CrossOrigin
@RestController
public class ProductController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProductService service;
//剩圖片
	@PostMapping(value = "/product/create")
	public ProductRes create(@RequestBody Product product, //
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		// 判斷是否登入，只有登入者可以新增商品
		if (user == null) {
			return new ProductRes(RtnCode.LOGIN_FIRST);
		}

		if (!userDao.existsById(user.getId())) {
			return new ProductRes(RtnCode.USER_ID_NOT_FOUND);
		}
		return service.create(product);
	}
<<<<<<< HEAD

	@PostMapping(value = "/product/delete")
	public ProductRes delete(@RequestParam(value = "id") int id, //
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		// 判斷是否登入，只有登入者可以刪除商品
		if (user == null) {
			return new ProductRes(RtnCode.LOGIN_FIRST);
		}

		if (!userDao.existsById(user.getId())) {
			return new ProductRes(RtnCode.USER_ID_NOT_FOUND);
		}
		return service.delete(id);
	}

	@GetMapping(value = "/product/get/info/user_id")
	public ProductSearchRes getProductInfoByUserId(//
			@RequestParam(value = "id") int id, //
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		// 判斷是否登入，只有登入者可以取得商品資訊
		if (user == null) {
			return new ProductSearchRes(RtnCode.LOGIN_FIRST);
		}

		if (!userDao.existsById(user.getId())) {
			return new ProductSearchRes(RtnCode.USER_ID_NOT_FOUND);
		}
		return service.getProductInfoByUserId(id);
	}

=======
//搞定
	@DeleteMapping(value = "/product/delete")
	public ProductRes delete(@RequestParam(value = "id") int id) {
	    // TODO 只有登入者可以刪除商品
	    return service.delete(id);
	}
	
	
	/**
	 * 取得單一商品資訊(參數使用product_id)
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
