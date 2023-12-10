package com.example.PipiShrimp.service.ifs;

import com.example.PipiShrimp.entity.Product;
import com.example.PipiShrimp.vo.ProductCreateRes;
import com.example.PipiShrimp.vo.ProductSearchRes;

public interface ProductService {

	/**
	 * �s�W�ӫ~
	 **/
	public ProductCreateRes create(Product product);

	/**
	 * ���o�ӫ~��T(�Ҧ����~)
	 **/
	public ProductSearchRes getAllProductInfo();

	/**
	 * ���o�j�M�ӫ~�W�٪��ӫ~��T(�ҽk�j�M)
	 **/
	public ProductSearchRes getProductByName(String productName);

	/**
	 * ���o�ӫ~��T(�̻����Ƨ� �C => ��)
	 **/
	public ProductSearchRes getProductByPrice();

	/**
	 * ���o�ӫ~��T(�̻����Ƨ� �� => �C)
	 **/
	public ProductSearchRes getProductByPriceDesc();

}
