package com.example.PipiShrimp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PipiShrimp.entity.Record;

@Repository
public interface RecordDao extends JpaRepository<Record, Integer> {
	/**
	 * ㄏノuser_idт┮Τ癸莱List(把计user_id)
	 **/
	public List<Record> findAllByUserId(int id);
	
	/**
	 * ㄏノproduct_idт┮Τ癸莱List(把计product_id)
	 **/
	public List<Record> findAllByProductId(int id);
}
