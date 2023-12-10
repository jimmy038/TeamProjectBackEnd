package com.example.PipiShrimp.service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Product;
import com.example.PipiShrimp.repository.ProductDao;
import com.example.PipiShrimp.service.ifs.ProductService;
import com.example.PipiShrimp.vo.ProductCreateRes;
import com.example.PipiShrimp.vo.ProductSearchRes;

@Service
public class ProductServiceImpl implements ProductService {

	/* org.slf4j.Logger */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ProductDao proDao;

	@Override
	public ProductCreateRes create(Product product) {
		if (!StringUtils.hasText(product.getProductName()) || //
				!StringUtils.hasText(product.getDescription()) || //
				product.getPrice() <= 0 || //
				product.getInventory() < 0 //
		) {
			return new ProductCreateRes(RtnCode.PARAM_ERROR);
		}

		try {
			proDao.save(product);

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ProductCreateRes(RtnCode.PRODUCT_CREATE_FAILED);
		}
		return new ProductCreateRes(RtnCode.SUCCESSFUL, product);
	}

	@Override
	public ProductSearchRes getAllProductInfo() {
		List<Product> products = proDao.searchAllProduct();
		// product資料庫為空
		if (products.size() == 0) {
			return new ProductSearchRes(RtnCode.PRODUCT_IS_EMPTY);
		}

		return new ProductSearchRes(RtnCode.SUCCESSFUL, proDao.searchAllProduct());
	}

	@Override
	public ProductSearchRes getProductByName(String productName) {
		List<Product> products = proDao.searchProductByName(productName);
		// 搜尋欄未輸入->顯示全部
		if (!StringUtils.hasText(productName)) {
			getAllProductInfo();
		}

		// 找不到搜尋商品
		if (products.size() == 0) {
			return new ProductSearchRes(RtnCode.PRODUCT_NOT_FOUND);
		}

		return new ProductSearchRes(RtnCode.SUCCESSFUL, products);
	}

	@Override
	public ProductSearchRes getProductByPrice() {
		return new ProductSearchRes(RtnCode.SUCCESSFUL, proDao.searchProductByPrice());
	}

	@Override
	public ProductSearchRes getProductByPriceDesc() {
		return new ProductSearchRes(RtnCode.SUCCESSFUL, proDao.searchProductByPriceDesc());
	}

}
