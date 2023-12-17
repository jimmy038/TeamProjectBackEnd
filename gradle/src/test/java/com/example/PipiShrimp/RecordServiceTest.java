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

	@Test
	public void createTest() {
		Record record = new Record(LocalDateTime.now(), 10, 50, "已送達商品", //
				"茶葉蛋", "buy", true, 5, 5);
		service.create(record);
	}
}
