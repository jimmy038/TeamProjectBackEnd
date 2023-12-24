package com.example.PipiShrimp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.PipiShrimp.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
	
	
	@Query(value = "SELECT * FROM product"//
			   + " WHERE user_id = :id"//
			   + " ORDER BY product_id DESC", //
			   nativeQuery = true)
			 public List<Product> searchProductByUserId(@Param("id") int id);
	/**
	 * �����Ҧ��ӫ~���
	 **/
	@Query(value = "SELECT * FROM product"//
			+ " ORDER BY product_id DESC", //
			nativeQuery = true)
	public List<Product> searchAllProduct();

	/**
	 * �ӫ~�W�ټҽk�j�M
	 **/
	@Query(value = "SELECT * FROM product"//
			+ " WHERE LOWER(product_name)"//
			+ " LIKE LOWER(CONCAT('%',:name,'%'))"//
			+ " ORDER BY product_id DESC;", //
			nativeQuery = true)
	public List<Product> searchProductByName(@Param("name") String productName);

	/**
	 * �̻���Ƨǰӫ~(�C => ��)
	 **/
	@Query(value = "SELECT * FROM product"//
			+ " ORDER BY price", //
			nativeQuery = true)
	public List<Product> searchProductByPrice();

	/**
	 * �̻���Ƨǰӫ~(�� => �C)
	 **/
	@Query(value = "SELECT * FROM product"//
			+ " ORDER BY price DESC", //
			nativeQuery = true)
	public List<Product> searchProductByPriceDesc();
}
