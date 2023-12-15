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
		Product product = new Product("�j��", "�l�����Ī�", LocalDate.of(2010, 1, 1), 3999, 10, 999, "�O�����~", false, "", 16);
		ProductRes res = service.create(product);
		System.out.println("�x�s���G: " + res.getRtnCode().getMessage());
		System.out.println("�x�s���~:" + res.getProduct().getProductName());
	}

	@Test
	public void searchAllTest() {
		List<Product> res = service.getAllProductInfo().getProducts();
		for (Product item : res) {
			System.out.println("���~�W��: " + item.getProductName());
		}
	}

	@Test
	public void searchByNameTest() {
		List<Product> res = service.getProductByName("���B").getProducts();
		for (Product item : res) {
			System.out.println("���~�W��: " + item.getProductName());
		}
	}

	@Test
	public void getProductByPriceTest() {
		List<Product> res = service.getProductByPrice().getProducts();
		for (Product item : res) {
			System.out.println("���~�W��: " + item.getProductName());
			System.out.println("���~�W��: " + item.getPrice());
			System.out.println("-----------------");
		}
	}

	@Test
	public void getProductByPriceDescTest() {
		List<Product> res = service.getProductByPriceDesc().getProducts();
		for (Product item : res) {
			System.out.println("���~�W��: " + item.getProductName());
			System.out.println("���~�W��: " + item.getPrice());
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
		System.out.println("��Ƥw���ƲM��");
	}
}
