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
	    Record record = new Record(
	        9,                             // recordId
	        27,                           // userId
	        107,                           // productId
	        "�Z�˲զѮv���q�@�~",                      // productName
	        5,                             // productCount
	        "�N����",                     // consumerName
	        "�x�_�����s��",                     // consumerAddress
	        "0987878888",                     // consumerPhone
	        "�v�t",                         // shippingMethod
	        80,                            // shippingCost
	        "�״�",                      // paymentMethod
	        "����H�U",                      // remittanceTitle
	        "812-00000087888",                      // remittanceNumber
	        "�n���w�Ѯv���@�~!",                        // remarksColumn
	        10000,                           // productAmount
	        LocalDateTime.now(),           // recordDate
	        "�B�e��",                         // status
	        "�ʶR",                         // recordType
	        true                           // valid
, 0, null
	    );

	    service.create(record);
	}

}
