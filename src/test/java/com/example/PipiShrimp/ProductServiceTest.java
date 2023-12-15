package com.example.PipiShrimp;

import java.time.LocalDate;
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
		Product product = new Product("大麻", "吸食用藥物", LocalDate.of(2010, 1, 1), 3999, 10, 999, "保健食品", false, "", 16);
		ProductRes res = service.create(product);
		System.out.println("儲存結果: " + res.getRtnCode().getMessage());
		System.out.println("儲存產品:" + res.getProduct().getProductName());
	}

	@Test
	public void searchAllTest() {
		List<Product> res = service.getAllProductInfo().getProducts();
		for (Product item : res) {
			System.out.println("產品名稱: " + item.getProductName());
		}
	}

	@Test
	public void searchByNameTest() {
		List<Product> res = service.getProductByName("布丁").getProducts();
		for (Product item : res) {
			System.out.println("產品名稱: " + item.getProductName());
		}
	}

	@Test
	public void getProductByPriceTest() {
		List<Product> res = service.getProductByPrice().getProducts();
		for (Product item : res) {
			System.out.println("產品名稱: " + item.getProductName());
			System.out.println("產品名稱: " + item.getPrice());
			System.out.println("-----------------");
		}
	}

	@Test
	public void getProductByPriceDescTest() {
		List<Product> res = service.getProductByPriceDesc().getProducts();
		for (Product item : res) {
			System.out.println("產品名稱: " + item.getProductName());
			System.out.println("產品名稱: " + item.getPrice());
			System.out.println("-----------------");
		}
	}

	@Test
	public void daoTest() {
		List<Product> res = dao.searchProductByUserId(1);
		for (Product item : res) {
			System.out.println("產品名稱: " + item.getProductName());
			System.out.println("產品名稱: " + item.getPrice());
			System.out.println("-----------------");
		}
	}

	@Test
	public void deleteAllProduct() {
		dao.deleteAll();
		System.out.println("資料已全數清除");
	}
}
