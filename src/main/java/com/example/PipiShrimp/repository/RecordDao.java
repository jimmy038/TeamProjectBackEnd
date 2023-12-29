package com.example.PipiShrimp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PipiShrimp.entity.Record;

@Repository
public interface RecordDao extends JpaRepository<Record, Integer> {
	/**
	 * �ϥ�user_id��X�Ҧ�������List(�Ѽ�user_id) #�R�auser_id
	 **/
	public List<Record> findAllByUserId(int id);
	
	/**
	 * �ϥ�user_id��X�Ҧ�������List(�Ѽ�user_id) #��auser_id
	 **/
	public List<Record> findAllBySellerId(int id);
	
	/**
	 * �ϥ�product_id��X�Ҧ�������List(�Ѽ�product_id)
	 **/
	public List<Record> findAllByProductId(int id);
}
