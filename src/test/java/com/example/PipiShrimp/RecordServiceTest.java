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
	        "æ­¦å¼„çµ„è€å¸«é™é‡ä½œå“",                      // productName
	        5,                             // productCount
	        "å‰å¨ƒå¨ƒ",                     // consumerName
	        "å°åŒ—å¸‚å¤©é¾åœ‹",                     // consumerAddress
	        "0987878888",                     // consumerPhone
	        "å®…é…",                         // shippingMethod
	        80,                            // shippingCost
	        "åŒ¯æ¬¾",                      // paymentMethod
	        "ä¸­åœ‹ä¿¡è¨—",                      // remittanceTitle
	        "812-00000087888",                      // remittanceNumber
	        "å¥½å–œæ­¡è€å¸«çš„ä½œå“!",                        // remarksColumn
	        10000,                           // productAmount
	        LocalDateTime.now(),           // recordDate
	        "é‹é€ä¸­",                         // status
	        "è³¼è²·",                         // recordType
	        true                           // valid
, 0, null
	    );

	    service.create(record);
	}
	@Test
	public void deleteAllRECORD() {
		dao.deleteAll();
		System.out.println("ï¿½ï¿½ïŽï”¢åš™è¸ï¿½îš¦ï¿½ï‹³è•­ï¿½ï…¼åš™è¸î´±ï¿½ï¿½î°´ïˆšè¬’ï¿½");
=======
		Record record = new Record(LocalDateTime.now(), 1, 999, "¤w°e¹F°Ó«~", //
				"Âû³J¿|", "food", true, 26, 28, 20);
		service.create(record);
>>>>>>> ian
	}

	@Test
	public void searchTest() {
		List<Record> records = dao.findAllByStartDate(LocalDate.of(2023, 12, 31));
		System.out.println("¶}©l¤é´Á============");
		for (Record record : records) {
			System.out.println(record.getRecordId());
			System.out.println(record.getProductName());
			System.out.println("==================");
		}

		System.out.println("============µ²§ô¤é´Á");
		records = dao.findAllByEndDate(LocalDate.of(2023, 12, 31));
		for (Record record : records) {
			System.out.println(record.getRecordId());
			System.out.println(record.getProductName());
			System.out.println("==================");
		}

		System.out.println("¶}©l¤é´Á============µ²§ô¤é´Á");
		records = dao.findAllByDate(LocalDate.of(2023, 12, 25), LocalDate.of(2023, 12, 31));
		for (Record record : records) {
			System.out.println(record.getRecordId());
			System.out.println(record.getProductName());
			System.out.println("==================");
		}

	}
}
