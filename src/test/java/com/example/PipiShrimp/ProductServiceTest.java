package com.example.PipiShrimp;

import java.time.LocalDate;
<<<<<<< HEAD
=======
import java.time.LocalDateTime;
>>>>>>> kamishu
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
<<<<<<< HEAD
		Product product = new Product("�j��", "�l�����Ī�", LocalDate.of(2010, 1, 1), 3999, 10, 999, "�O�����~", false, "", 16);
		ProductRes res = service.create(product);
		System.out.println("�x�s���G: " + res.getRtnCode().getMessage());
		System.out.println("�x�s���~:" + res.getProduct().getProductName());
=======
	    LocalDate currentDate = LocalDate.now();

	    Product product1 = new Product(1, "拖鞋", "可以穿的拖鞋", 100, 25, 0, true, "", 1, currentDate);
	    ProductRes res1 = service.create(product1);
	    System.out.println("儲存結果: " + res1.getRtnCode().getMessage());
	    System.out.println("儲存產品:" + res1.getProduct().getProductName());

	    Product product2 = new Product(2, "掃把", "用來掃地的", 299, 25, 12, true, "", 1, currentDate);
	    ProductRes res2 = service.create(product2);
	    System.out.println("儲存結果: " + res2.getRtnCode().getMessage());
	    System.out.println("儲存產品:" + res2.getProduct().getProductName());

	    Product product3 = new Product(3, "大麻", "吸食用藥物", 3999, 10, 999, false, "", 1, currentDate);
	    ProductRes res3 = service.create(product3);
	    System.out.println("儲存結果: " + res3.getRtnCode().getMessage());
	    System.out.println("儲存產品:" + res3.getProduct().getProductName());
>>>>>>> kamishu
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
			System.out.println("���~�W��: " + item.getProductName());
			System.out.println("���~�W��: " + item.getPrice());
			System.out.println("-----------------");
		}
	}

	@Test
	public void deleteAllProduct() {
		dao.deleteAll();
		System.out.println("資料已全數清除");
	}
}
