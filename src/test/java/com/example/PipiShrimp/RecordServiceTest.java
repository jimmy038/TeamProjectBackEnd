package com.example.PipiShrimp;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.PipiShrimp.entity.Record;
import com.example.PipiShrimp.repository.RecordDao;
import com.example.PipiShrimp.service.ifs.RecordService;

@SpringBootTest
public class RecordServiceTest {

	@Autowired
	private RecordService service;

	@Autowired
	private RecordDao dao;

//	LocalDateTime recordDate, int productCount, int productAmount, String status, String productName,
//	String recordType, boolean valid, int userId, int sellerId, int productId
	@Test
	public void createTest() {
		Record record = new Record(LocalDateTime.now(), 1, 999, "已送達商品", //
				"T91步槍的子彈", "food", true, 26, 28,20);
		service.create(record);
	}
}
