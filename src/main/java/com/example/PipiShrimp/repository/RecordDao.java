package com.example.PipiShrimp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	/**
	 * ㄏノstart_dateт┮Τ(单)start_dateList(把计start_date)
	 **/
	@Query(value = "SELECT * FROM record"//
			+ " WHERE record_date >= :startDate"//
			+ " ORDER BY record_date DESC", //
			nativeQuery = true)
	public List<Record> findAllByStartDate(//
			@Param("startDate") LocalDate startDate);

	/**
	 * ㄏノend_dateт┮Τ(单)end_dateList(把计end_date)
	 **/
	@Query(value = "SELECT * FROM record"//
			+ " WHERE record_date <= :endDate"//
			+ " ORDER BY record_date DESC", //
			nativeQuery = true)
	public List<Record> findAllByEndDate(//
			@Param("endDate") LocalDate endDate);

	/**
	 * ㄏノstart_date, end_dateт┮Τら戳丁List(把计start_date, end_date)
	 **/
	@Query(value = "SELECT * FROM record"//
			+ " WHERE record_date >= :startDate"//
			+ " AND record_date <= :endDate"//
			+ " ORDER BY record_date DESC;", //
			nativeQuery = true)
	public List<Record> findAllByDate(//
			@Param("startDate") LocalDate startDate, //
			@Param("endDate") LocalDate endDate);
}
