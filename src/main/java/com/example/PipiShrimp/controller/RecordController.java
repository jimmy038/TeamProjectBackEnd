package com.example.PipiShrimp.controller;

import java.util.List;

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

//TODO 所有的功能都需要先登入才能使用
@CrossOrigin
@RestController
public class RecordController {

	@Autowired
	private RecordService service;

	// TODO 需要登入才能操作
	@PostMapping(value = "/record/create")
	public RecordRes create(@RequestBody Record record) {
		return service.create(record);
	}

	// 取消訂單買家賣家用
	@PostMapping(value = "/record/cancel")
	public RecordRes cancel(@RequestParam(value = "id") int id) {
		return service.cancel(id);
	}

	// 訂單狀態改為已出貨賣家用
	@PostMapping(value = "/record/shipping")
	public RecordRes shipping(@RequestParam(value = "id") int id) {
		return service.shipping(id);
	}

	// 訂單狀態改為已完成買家用確認收貨
	@PostMapping(value = "/record/completed")
	public RecordRes completed(@RequestParam(value = "id") int id) {
		return service.completed(id);
	}

//顯示購買清單
	@GetMapping(value = "/record/get/user_id")
	public RecordSearchRes getRecordInfoByUserId(//
			@RequestParam(value = "id") int id) {
		return service.getRecordInfoByUserId(id);
	}

	// 顯示下標訂單
	@GetMapping(value = "/record/get/seller_id")
	public RecordSearchRes getRecordInfoBySellerId(//
			@RequestParam(value = "id") int id) {
		return service.getRecordInfoBySellerId(id);
	}

	@GetMapping(value = "/record/get/product_id")
	public RecordSearchRes getRecordInfoByProductId(//
			@RequestParam(value = "id") int id) {
		return service.getRecordInfoByProductId(id);
	}

	@PostMapping(value = "/record/delete")
	public RecordSearchRes delete(@RequestParam List<Integer> idList) {
		return service.delete(idList);
	}
}
