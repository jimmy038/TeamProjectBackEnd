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

	@Test
	public void createTest() {
<<<<<<< HEAD
	    Record record = new Record(
	        9,                             // recordId
	        27,                           // userId
	        107,                           // productId
	        "武弄組老師限量作品",                      // productName
	        5,                             // productCount
	        "吉娃娃",                     // consumerName
	        "台北市天龍國",                     // consumerAddress
	        "0987878888",                     // consumerPhone
	        "宅配",                         // shippingMethod
	        80,                            // shippingCost
	        "匯款",                      // paymentMethod
	        "中國信託",                      // remittanceTitle
	        "812-00000087888",                      // remittanceNumber
	        "好喜歡老師的作品!",                        // remarksColumn
	        10000,                           // productAmount
	        LocalDateTime.now(),           // recordDate
	        "運送中",                         // status
	        "購買",                         // recordType
	        true                           // valid
, 0, null
	    );

	    service.create(record);
	}
	@Test
	public void deleteAllRECORD() {
		dao.deleteAll();
		System.out.println("��嚙踐��蕭�嚙踐��謒�");
=======
		Record record = new Record(LocalDateTime.now(), 1, 999, "�w�e�F�ӫ~", //
				"���J�|", "food", true, 26, 28, 20);
		service.create(record);
>>>>>>> ian
	}

	@Test
	public void searchTest() {
		List<Record> records = dao.findAllByStartDate(LocalDate.of(2023, 12, 31));
		System.out.println("�}�l���============");
		for (Record record : records) {
			System.out.println(record.getRecordId());
			System.out.println(record.getProductName());
			System.out.println("==================");
		}

		System.out.println("============�������");
		records = dao.findAllByEndDate(LocalDate.of(2023, 12, 31));
		for (Record record : records) {
			System.out.println(record.getRecordId());
			System.out.println(record.getProductName());
			System.out.println("==================");
		}

		System.out.println("�}�l���============�������");
		records = dao.findAllByDate(LocalDate.of(2023, 12, 25), LocalDate.of(2023, 12, 31));
		for (Record record : records) {
			System.out.println(record.getRecordId());
			System.out.println(record.getProductName());
			System.out.println("==================");
		}

	}
}
