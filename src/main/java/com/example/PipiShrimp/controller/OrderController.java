package com.example.PipiShrimp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PipiShrimp.entity.Record;
import com.example.PipiShrimp.service.ifs.RecordService;
import com.example.PipiShrimp.vo.RecordRes;

@CrossOrigin
@RestController
public class OrderController {

	@Autowired
	private RecordService service;

	// TODO 需要登入才能操作
	@PostMapping(value = "/record/create")
	public RecordRes create(@RequestBody Record record) {
		return service.create(record);
	}

	// TODO 需要登入才能操作
	@PostMapping(value = "/record/cancel")
	public RecordRes cancel(@RequestParam(value = "id") int id) {
		return service.cancel(id);
	}
}
