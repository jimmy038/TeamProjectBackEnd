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
		Product product = new Product("大麻", "吸食用藥物", LocalDate.of(2010, 1, 1), 3999, 10, 999, "保健食品", false, "", 16);
		ProductRes res = service.create(product);
		System.out.println("儲存結果: " + res.getRtnCode().getMessage());
		System.out.println("儲存產品:" + res.getProduct().getProductName());
=======
	    LocalDate currentDate = LocalDate.now();

	    Product product1 = new Product(1, "������", "��臭誑蝛輻��������", 100, 25, 0, true, "", 1, currentDate);
	    ProductRes res1 = service.create(product1);
	    System.out.println("��脣��蝯����: " + res1.getRtnCode().getMessage());
	    System.out.println("��脣����Ｗ��:" + res1.getProduct().getProductName());

	    Product product2 = new Product(2, "������", "��其�������啁��", 299, 25, 12, true, "", 1, currentDate);
	    ProductRes res2 = service.create(product2);
	    System.out.println("��脣��蝯����: " + res2.getRtnCode().getMessage());
	    System.out.println("��脣����Ｗ��:" + res2.getProduct().getProductName());

	    Product product3 = new Product(3, "憭折獄", "��賊����刻�亦��", 3999, 10, 999, false, "", 1, currentDate);
	    ProductRes res3 = service.create(product3);
	    System.out.println("��脣��蝯����: " + res3.getRtnCode().getMessage());
	    System.out.println("��脣����Ｗ��:" + res3.getProduct().getProductName());
>>>>>>> kamishu
	}

	@Test
	public void searchAllTest() {
		List<Product> res = service.getAllProductInfo().getProducts();
		for (Product item : res) {
			System.out.println("��Ｗ�����蝔�: " + item.getProductName());
		}
	}

	@Test
	public void searchByNameTest() {
		List<Product> res = service.getProductByName("撣�銝�").getProducts();
		for (Product item : res) {
			System.out.println("��Ｗ�����蝔�: " + item.getProductName());
		}
	}

	@Test
	public void getProductByPriceTest() {
		List<Product> res = service.getProductByPrice().getProducts();
		for (Product item : res) {
			System.out.println("��Ｗ�����蝔�: " + item.getProductName());
			System.out.println("��Ｗ�����蝔�: " + item.getPrice());
			System.out.println("-----------------");
		}
	}

	@Test
	public void getProductByPriceDescTest() {
		List<Product> res = service.getProductByPriceDesc().getProducts();
		for (Product item : res) {
			System.out.println("��Ｗ�����蝔�: " + item.getProductName());
			System.out.println("��Ｗ�����蝔�: " + item.getPrice());
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
		System.out.println("鞈����撌脣�冽�豢�����");
	}
}
