package com.example.PipiShrimp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PipiShrimp.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	/**
	 * �P�_�H�c�O�_�w�g���U�L 
	 **/
	public boolean existsByEmail(String email);
	
	/**
	 * ��X�ŦX�H�c��User���
	 **/
	public User findByEmail(String email);
}
