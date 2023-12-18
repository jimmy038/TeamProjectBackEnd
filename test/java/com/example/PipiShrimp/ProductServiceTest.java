package com.example.PipiShrimp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.PipiShrimp.entity.Product;
import com.example.PipiShrimp.repository.ProductDao;
import com.example.PipiShrimp.service.ifs.ProductService;
import com.example.PipiShrimp.vo.ProductRes;

@SpringBootTest
public class ProductServiceTest {

	@Autowired
	private ProductService service;

	@Autowired
	private ProductDao dao;
	@Test
	public void createTest() {
	    LocalDate currentDate = LocalDate.now();

	    Product product1 = new Product(22200, "����", "�隞亦忽�����", 100, 25, 0, true, "", 1, currentDate);
	    ProductRes res1 = service.create(product1);
	    System.out.println("�摮���: " + res1.getRtnCode().getMessage());
	    System.out.println("�摮���:" + res1.getProduct().getProductName());

	    Product product2 = new Product(22, "����", "�靘����", 299, 25, 12, true, "", 1, currentDate);
	    ProductRes res2 = service.create(product2);
	    System.out.println("�摮���: " + res2.getRtnCode().getMessage());
	    System.out.println("�摮���:" + res2.getProduct().getProductName());

	    Product product3 = new Product(3, "憭折獄", "�憌��", 3999, 10, 999, false, "", 1, currentDate);
	    ProductRes res3 = service.create(product3);
	    System.out.println("�摮���: " + res3.getRtnCode().getMessage());
	    System.out.println("�摮���:" + res3.getProduct().getProductName());
	}

	@Test
	public void searchAllTest() {
		List<Product> res = service.getAllProductInfo().getProducts();
		for (Product item : res) {
			System.out.println("����迂: " + item.getProductName());
		}
	}

	@Test
	public void searchByNameTest() {
		List<Product> res = service.getProductByName("撣��").getProducts();
		for (Product item : res) {
			System.out.println("����迂: " + item.getProductName());
		}
	}

	@Test
	public void getProductByPriceTest() {
		List<Product> res = service.getProductByPrice().getProducts();
		for (Product item : res) {
			System.out.println("����迂: " + item.getProductName());
			System.out.println("����迂: " + item.getPrice());
			System.out.println("-----------------");
		}
	}

	@Test
	public void getProductByPriceDescTest() {
		List<Product> res = service.getProductByPriceDesc().getProducts();
		for (Product item : res) {
			System.out.println("����迂: " + item.getProductName());
			System.out.println("����迂: " + item.getPrice());
			System.out.println("-----------------");
		}
	}

	@Test
	public void deleteAllProduct() {
		dao.deleteAll();
		System.out.println("鞈�歇��皜");
	}
}
