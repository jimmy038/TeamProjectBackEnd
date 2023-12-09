package com.example.PipiShrimp;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.PipiShrimp.entity.Product;
import com.example.PipiShrimp.repository.ProductDao;
import com.example.PipiShrimp.service.ifs.ProductService;
import com.example.PipiShrimp.vo.ProductCreateRes;

@SpringBootTest
public class ProductServiceTest {

	@Autowired
	private ProductService service;

	@Autowired
	private ProductDao dao;

	@Test
	public void createTest() {
		Product product = new Product("��c", "�i�H�諸��c", 100, 25, 0, true, "", 1);
		ProductCreateRes res = service.create(product);
		System.out.println("�x�s���G: " + res.getRtnCode().getMessage());
		System.out.println("�x�s���~:" + res.getProduct().getProductName());

		product = new Product("����", "�Ψӱ��a��", 299, 25, 12, true, "", 1);
		res = service.create(product);
		System.out.println("�x�s���G: " + res.getRtnCode().getMessage());
		System.out.println("�x�s���~:" + res.getProduct().getProductName());

		product = new Product("�j��", "�l�����Ī�", 3999, 10, 999, false, "", 1);
		res = service.create(product);
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
	public void deleteAllProduct() {
		dao.deleteAll();
		System.out.println("��Ƥw���ƲM��");
	}
}
