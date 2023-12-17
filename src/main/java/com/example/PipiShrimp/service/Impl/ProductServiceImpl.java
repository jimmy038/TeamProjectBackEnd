package com.example.PipiShrimp.service.Impl;

<<<<<<< HEAD
import java.util.Collections;
=======
import java.time.LocalDate;
import java.time.LocalDateTime;
>>>>>>> kamishu
import java.util.List;
import java.util.Optional;

import javax.xml.bind.DatatypeConverter;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Product;
import com.example.PipiShrimp.repository.ProductDao;
import com.example.PipiShrimp.repository.UserDao;
import com.example.PipiShrimp.service.ifs.ProductService;
import com.example.PipiShrimp.vo.ProductRes;
import com.example.PipiShrimp.vo.ProductSearchRes;

@Service
public class ProductServiceImpl implements ProductService {

	/* org.slf4j.Logger */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ProductDao proDao;

<<<<<<< HEAD
	@Autowired
	private UserDao userDao;

	// TODO �x�s�Ϥ��覡
	@Override
	public ProductRes create(Product product) {
		if (!StringUtils.hasText(product.getProductName()) || //
				!StringUtils.hasText(product.getDescription()) || //
				product.getPrice() <= 0 || //
				product.getInventory() < 0 //
		) {
			return new ProductRes(RtnCode.PARAM_ERROR);
		}
=======
	//TODO 儲存圖片方式
	 @Override
	    public ProductRes create(Product product) {
	        if (!StringUtils.hasText(product.getProductName()) || //
	                !StringUtils.hasText(product.getDescription()) ||
	                product.getPrice() <= 0 || //
	                product.getInventory() < 0 //
	        ) {
	            return new ProductRes(RtnCode.PARAM_ERROR);
	        }
>>>>>>> kamishu

	        // 处理Base64编码的图像数据
	        String base64Image = product.getPhoto();
	        if (base64Image != null && !base64Image.isEmpty()) {
	            try {
	                byte[] imageBytes =  DatatypeConverter.parseBase64Binary(base64Image);

	                // TODO: 将字节数组保存到数据库或存储系统中
	                // 这里假设您有一个用于保存图像的服务或 DAO
	                // 请根据实际情况进行调整
	                // imageService.saveImage(imageBytes);
	                // 设置上传时间
	                product.setUploadTime(LocalDate.now());
	            } catch (IllegalArgumentException e) {
	                // 处理Base64解码错误
	                e.printStackTrace();
	                return new ProductRes(RtnCode.FILE_ERROR);
	            }
	        }

	        try {
	            proDao.save(product);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ProductRes(RtnCode.PRODUCT_CREATE_FAILED);
	        }

	        return new ProductRes(RtnCode.SUCCESSFUL, product);
	    }
	

	@Override
	public ProductRes delete(int id) {
		// 找不到該商品id
		if (!proDao.existsById(id)) {
			return new ProductRes(RtnCode.PRODUCT_ID_NOT_FOUND);
		}

		// 取得刪除商品資訊，用於回傳給使用者
		Product res = proDao.findById(id).get();

		try {
			proDao.deleteById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ProductRes(RtnCode.PRODUCT_DELETE_FAILED);
		}

		return new ProductRes(RtnCode.SUCCESSFUL, res);
	}

	@Override
	public ProductSearchRes getProductInfoByUserId(int id) {
		// user_id���s�b
		if (!userDao.existsById(id)) {
			return new ProductSearchRes(RtnCode.USER_ID_NOT_FOUND);
		}

		List<Product> res = proDao.searchProductByUserId(id);
		// �p�G�ӫ~���� => ���@�Ӫ�List
		res = res.size() != 0 ? res : Collections.emptyList();

		return new ProductSearchRes(RtnCode.SUCCESSFUL, res);
	}

	@Override
	public ProductRes getProductInfo(int id) {
		if (!proDao.existsById(id)) {
			return new ProductRes(RtnCode.PRODUCT_ID_NOT_FOUND);
		}

		Optional<Product> op = proDao.findById(id);
		// 資料為空(資料有問題)
		if (op.isEmpty()) {
			return new ProductRes(RtnCode.DATABASE_IS_EMPTY);
		}
		return new ProductRes(RtnCode.SUCCESSFUL, op.get());
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
