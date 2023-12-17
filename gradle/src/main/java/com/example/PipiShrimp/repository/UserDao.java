package com.example.PipiShrimp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PipiShrimp.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	/**
	 * 判斷信箱是否已經註冊過
	 **/
	public boolean existsByEmail(String email);
	
	/**
	 * 找出符合信箱的User資料
	 **/
	public User findByEmail(String email);
}
