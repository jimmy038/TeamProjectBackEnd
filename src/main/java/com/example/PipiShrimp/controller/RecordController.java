package com.example.PipiShrimp.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

//TODO 所有的功能都需要先登入才能使用
@CrossOrigin
@RestController
public class RecordController {

	@Autowired
	private RecordService service;

	@PostMapping(value = "/record/create")
	public RecordRes create(@RequestBody Record record) {

		return service.create(record);
	}

	@PostMapping(value = "/records/create")
	public RecordSearchRes create(@RequestBody List<Record> records) {

		return service.create(records);
	}

	@PostMapping(value = "/record/cancel")
	public RecordRes cancel(@RequestParam(value = "id") int id) {

		return service.cancel(id);
	}

	@PostMapping(value = "/record/delete")
	public RecordSearchRes delete(@RequestParam List<Integer> idList) {
		return service.delete(idList);
	}

	@GetMapping(value = "/record/get/user_id")
	public RecordSearchRes getRecordInfoByUserId(//
			@RequestParam(value = "id") int id) {

		return service.getRecordInfoByUserId(id);
	}

	@GetMapping(value = "/record/get/product_id")
	public RecordSearchRes getRecordInfoByProductId(//
			@RequestParam(value = "id") int id) {

		return service.getRecordInfoByProductId(id);
	}

	@GetMapping(value = "/record/get/date")
	public RecordSearchRes getRecordInfoByDate(//
			@RequestParam(value = "id") int id, //
			@RequestParam(required = false, value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, //
			@RequestParam(required = false, value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
		return service.getRecordInfoByDate(id, startDate, endDate);
	}

	@GetMapping(value = "/record/get/record_id")
	public RecordRes getMaintenanceByRecordId(//
			@RequestParam(value = "id") int id) {

		return service.getMaintenanceByRecordId(id);
	}
}
