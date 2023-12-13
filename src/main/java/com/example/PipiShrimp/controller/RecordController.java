package com.example.PipiShrimp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PipiShrimp.entity.Record;
import com.example.PipiShrimp.service.ifs.RecordService;
import com.example.PipiShrimp.vo.RecordRes;
import com.example.PipiShrimp.vo.RecordSearchRes;

//TODO �Ҧ����\�ೣ�ݭn���n�J�~��ϥ�
@CrossOrigin
@RestController
public class RecordController {

	@Autowired
	private RecordService service;

	// TODO �ݭn�n�J�~��ާ@
	@PostMapping(value = "/record/create")
	public RecordRes create(@RequestBody Record record) {
		return service.create(record);
	}

	// TODO �ݭn�n�J�~��ާ@
	@PostMapping(value = "/record/cancel")
	public RecordRes cancel(@RequestParam(value = "id") int id) {
		return service.cancel(id);
	}

	@GetMapping(value = "/record/get/user_id")
	public RecordSearchRes getRecordInfoByUserId(//
			@RequestParam(value = "id") int id) {
		return service.getRecordInfoByUserId(id);
	}
}
