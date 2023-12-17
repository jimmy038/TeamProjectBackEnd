package com.example.PipiShrimp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PipiShrimp.entity.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {

	/**
	 * �ϥ�user_id��X�Ҧ�������List(�Ѽ�user_id)
	 **/
	public List<Cart> findAllByUserId(int id);
}
