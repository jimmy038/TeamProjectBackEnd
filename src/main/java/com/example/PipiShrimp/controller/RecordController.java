package com.example.PipiShrimp.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Record;
import com.example.PipiShrimp.entity.User;
import com.example.PipiShrimp.repository.UserDao;
import com.example.PipiShrimp.service.ifs.RecordService;
import com.example.PipiShrimp.vo.RecordRes;
import com.example.PipiShrimp.vo.RecordSearchRes;

//TODO 所有的功能都需要先登入才能使用
@CrossOrigin
@RestController
public class RecordController {

	@Autowired
	private RecordService service;

	@Autowired
	private UserDao userDao;

	@PostMapping(value = "/record/create")
	public RecordRes create(@RequestBody Record record, HttpSession session) {
		User user = (User) session.getAttribute("user");
		// 判斷是否登入，只有登入者可以新增訂單
		if (user == null) {
			return new RecordRes(RtnCode.LOGIN_FIRST);
		}

		if (!userDao.existsById(user.getId())) {
			return new RecordRes(RtnCode.USER_ID_NOT_FOUND);
		}

		return service.create(record);
	}

	@PostMapping(value = "/record/cancel")
	public RecordRes cancel(@RequestParam(value = "id") int id, HttpSession session) {
		User user = (User) session.getAttribute("user");
		// 判斷是否登入，只有登入者可以刪除訂單
		if (user == null) {
			return new RecordRes(RtnCode.LOGIN_FIRST);
		}

		if (!userDao.existsById(user.getId())) {
			return new RecordRes(RtnCode.USER_ID_NOT_FOUND);
		}

		return service.cancel(id);
	}

	@PostMapping(value = "/record/delete")
	public RecordSearchRes delete(@RequestParam List<Integer> idList) {
		return service.delete(idList);
	}

	@GetMapping(value = "/record/get/user_id")
	public RecordSearchRes getRecordInfoByUserId(//
			@RequestParam(value = "id") int id, HttpSession session) {
		User user = (User) session.getAttribute("user");
		// 判斷是否登入，只有登入者可以查詢訂單紀錄
		if (user == null) {
			return new RecordSearchRes(RtnCode.LOGIN_FIRST);
		}

		if (!userDao.existsById(user.getId())) {
			return new RecordSearchRes(RtnCode.USER_ID_NOT_FOUND);
		}

		return service.getRecordInfoByUserId(id);
	}

	@GetMapping(value = "/record/get/product_id")
	public RecordSearchRes getRecordInfoByProductId(//
			@RequestParam(value = "id") int id, HttpSession session) {
		User user = (User) session.getAttribute("user");
		// 判斷是否登入，只有登入者可以查詢商品資訊
		if (user == null) {
			return new RecordSearchRes(RtnCode.LOGIN_FIRST);
		}

		if (!userDao.existsById(user.getId())) {
			return new RecordSearchRes(RtnCode.USER_ID_NOT_FOUND);
		}

		return service.getRecordInfoByProductId(id);
	}
}
