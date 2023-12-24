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
	 * 取的所有商品資料
	 **/
	@Query(value = "SELECT * FROM product"//
			+ " ORDER BY product_id DESC", //
			nativeQuery = true)
	public List<Product> searchAllProduct();

	/**
	 * 商品名稱模糊搜尋
	 **/
	@Query(value = "SELECT * FROM product"//
			+ " WHERE LOWER(product_name)"//
			+ " LIKE LOWER(CONCAT('%',:name,'%'))"//
			+ " ORDER BY product_id DESC;", //
			nativeQuery = true)
	public List<Product> searchProductByName(@Param("name") String productName);

	/**
	 * 依價格排序商品(低 => 高)
	 **/
	@Query(value = "SELECT * FROM product"//
			+ " ORDER BY price", //
			nativeQuery = true)
	public List<Product> searchProductByPrice();

	/**
	 * 依價格排序商品(高 => 低)
	 **/
	@Query(value = "SELECT * FROM product"//
			+ " ORDER BY price DESC", //
			nativeQuery = true)
	public List<Product> searchProductByPriceDesc();
}
