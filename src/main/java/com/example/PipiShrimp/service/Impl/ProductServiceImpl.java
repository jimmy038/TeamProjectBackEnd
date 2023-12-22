package com.example.PipiShrimp.service.Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
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

	@Autowired
	private UserDao userDao;
	//TODO �摮��撘�
	 @Override
	    public ProductRes create(Product product) {
	        if (!StringUtils.hasText(product.getProductName()) || //
	                !StringUtils.hasText(product.getDescription()) ||
	                product.getPrice() <= 0 || //
	                product.getInventory() < 0 //
	        ) {
	            return new ProductRes(RtnCode.PARAM_ERROR);
	        }

	        // 憭�ase64蝻�����
	        String base64Image = product.getPhoto();
	        if (base64Image != null && !base64Image.isEmpty()) {
	            try {
	                byte[] imageBytes =  DatatypeConverter.parseBase64Binary(base64Image);

	                // TODO: 撠��蝏����摨��蝟餌�葉
	                // 餈��挽����銝芰鈭��������� DAO
	                // 霂瑟�摰��餈��
	                // imageService.saveImage(imageBytes);
	                // 霈曄蔭銝��
	                product.setUploadTime(LocalDate.now());
	            } catch (IllegalArgumentException e) {
	                // 憭�ase64閫����秤
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
		// �銝閰脣��d
		if (!proDao.existsById(id)) {
			return new ProductRes(RtnCode.PRODUCT_ID_NOT_FOUND);
		}

		// �������������蝯虫蝙���
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
	public ProductRes getProductInfo(int id) {
		if (!proDao.existsById(id)) {
			return new ProductRes(RtnCode.PRODUCT_ID_NOT_FOUND);
		}

		Optional<Product> op = proDao.findById(id);
		// 鞈�蝛�(鞈�����)
		if (op.isEmpty()) {
			return new ProductRes(RtnCode.DATABASE_IS_EMPTY);
		}
		return new ProductRes(RtnCode.SUCCESSFUL, op.get());
	}

	@Override
	public ProductSearchRes getAllProductInfo() {
		List<Product> products = proDao.searchAllProduct();
		// product鞈�澈�蝛�
		if (products.size() == 0) {
			return new ProductSearchRes(RtnCode.PRODUCT_IS_EMPTY);
		}

		return new ProductSearchRes(RtnCode.SUCCESSFUL, proDao.searchAllProduct());
	}

	@Override
	public ProductSearchRes getProductByName(String productName) {
		List<Product> products = proDao.searchProductByName(productName);
<<<<<<< HEAD
		// ����頛詨->憿舐內��
=======
<<<<<<< HEAD
		// 搜尋欄未輸入->顯示全部
=======
		// �j�M�楼��J=>��ܥ���
>>>>>>> ian
>>>>>>> b5ea93e62384850c6a89db11dd00712137c68d3b
		if (!StringUtils.hasText(productName)) {
			getAllProductInfo();
		}

<<<<<<< HEAD
		// �銝������
=======
<<<<<<< HEAD
		// 找不到搜尋商品
=======
		// �䤣��j�M�ӫ~ => ���@�ӪŰ}�C
>>>>>>> ian
>>>>>>> b5ea93e62384850c6a89db11dd00712137c68d3b
		if (products.size() == 0) {
			products = Collections.emptyList();
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
	@Override
	 public ProductSearchRes getProductInfoByUserId(int id) {
	  // user_id不存在
	  if (!userDao.existsById(id)) {
	   return new ProductSearchRes(RtnCode.USER_ID_NOT_FOUND);
	  }

	  List<Product> res = proDao.searchProductByUserId(id);
	  // 如果商品為空 => 給一個空List
	  res = res.size() != 0 ? res : Collections.emptyList();

	  return new ProductSearchRes(RtnCode.SUCCESSFUL, res);
	 }

}
