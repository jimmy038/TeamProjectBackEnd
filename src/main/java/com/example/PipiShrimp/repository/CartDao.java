package com.example.PipiShrimp.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.PipiShrimp.entity.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {

	/**
	 * 使用user_id找出所有對應的List(參數user_id)
	 **/
	public List<Cart> findAllByUserId(int id);

	/**
	 * 使用user_id, product_id找出對應的cart(參數product_id, user_id)
	 **/
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM cart"//
			+ " WHERE user_id = :userId"//
			+ " AND product_id = :productId", //
			nativeQuery = true)
	public void deleteCartById(@Param("userId") int userId,//
			@Param("productId") int proId);
}
