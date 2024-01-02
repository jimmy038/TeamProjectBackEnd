package com.example.PipiShrimp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
				"雞蛋糕", "food", true, 26, 28, 20);
		service.create(record);
	}

	@Test
	public void searchTest() {
		List<Record> records = dao.findAllByStartDate(LocalDate.of(2023, 12, 31));
		System.out.println("開始日期============");
		for (Record record : records) {
			System.out.println(record.getRecordId());
			System.out.println(record.getProductName());
			System.out.println("==================");
		}

		System.out.println("============結束日期");
		records = dao.findAllByEndDate(LocalDate.of(2023, 12, 31));
		for (Record record : records) {
			System.out.println(record.getRecordId());
			System.out.println(record.getProductName());
			System.out.println("==================");
		}

		System.out.println("開始日期============結束日期");
		records = dao.findAllByDate(LocalDate.of(2023, 12, 25), LocalDate.of(2023, 12, 31));
		for (Record record : records) {
			System.out.println(record.getRecordId());
			System.out.println(record.getProductName());
			System.out.println("==================");
		}

	}
}
