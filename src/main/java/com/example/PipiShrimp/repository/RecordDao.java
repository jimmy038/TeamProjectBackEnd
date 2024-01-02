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
	 * �ϥ�user_id��X�Ҧ�������List(�Ѽ�user_id)
	 **/
	public List<Record> findAllByUserId(int id);

	/**
	 * �ϥ�product_id��X�Ҧ�������List(�Ѽ�product_id)
	 **/
	public List<Record> findAllByProductId(int id);

	/**
	 * �ϥ�start_date��X�Ҧ��j(��)��start_date��List(�Ѽ�start_date)
	 **/
	@Query(value = "SELECT * FROM record"//
			+ " WHERE record_date >= :startDate"//
			+ " ORDER BY record_date DESC", //
			nativeQuery = true)
	public List<Record> findAllByStartDate(//
			@Param("startDate") LocalDate startDate);

	/**
	 * �ϥ�end_date��X�Ҧ��p(��)��end_date��List(�Ѽ�end_date)
	 **/
	@Query(value = "SELECT * FROM record"//
			+ " WHERE record_date <= :endDate"//
			+ " ORDER BY record_date DESC", //
			nativeQuery = true)
	public List<Record> findAllByEndDate(//
			@Param("endDate") LocalDate endDate);

	/**
	 * �ϥ�start_date, end_date��X�Ҧ��b�������List(�Ѽ�start_date, end_date)
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
