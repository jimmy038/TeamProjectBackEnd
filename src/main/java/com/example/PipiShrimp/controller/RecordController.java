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

//TODO �Ҧ����\�ೣ�ݭn���n�J�~��ϥ�
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
		// �P�_�O�_�n�J�A�u���n�J�̥i�H�s�W�q��
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
		// �P�_�O�_�n�J�A�u���n�J�̥i�H�R���q��
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
		// �P�_�O�_�n�J�A�u���n�J�̥i�H�d�߭q�����
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
		// �P�_�O�_�n�J�A�u���n�J�̥i�H�d�߰ӫ~��T
		if (user == null) {
			return new RecordSearchRes(RtnCode.LOGIN_FIRST);
		}

		if (!userDao.existsById(user.getId())) {
			return new RecordSearchRes(RtnCode.USER_ID_NOT_FOUND);
		}

		return service.getRecordInfoByProductId(id);
	}
}
