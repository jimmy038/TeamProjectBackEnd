package com.example.PipiShrimp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PipiShrimp.entity.Record;

@Repository
public interface RecordDao extends JpaRepository<Record, Integer> {
	/**
	 * 使用user_id找出所有對應的List(參數user_id) #買家user_id
	 **/
	public List<Record> findAllByUserId(int id);
	
	/**
	 * 使用user_id找出所有對應的List(參數user_id) #賣家user_id
	 **/
	public List<Record> findAllBySellerId(int id);
	
	/**
	 * 使用product_id找出所有對應的List(參數product_id)
	 **/
	public List<Record> findAllByProductId(int id);
}
