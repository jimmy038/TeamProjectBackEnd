package com.example.PipiShrimp.service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Record;
import com.example.PipiShrimp.repository.ProductDao;
import com.example.PipiShrimp.repository.RecordDao;
import com.example.PipiShrimp.repository.UserDao;
import com.example.PipiShrimp.service.ifs.RecordService;
import com.example.PipiShrimp.vo.RecordRes;
import com.example.PipiShrimp.vo.RecordSearchRes;

@Service
public class RecordServiceImpl implements RecordService {

	/* org.slf4j.Logger */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RecordDao dao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProductDao productDao;

	// TODO 發送信件給買家和賣家，訂單成立PRODUCT庫存變更
	// TODO 購買數 > product庫存數，無法購買
	@Override
	public RecordRes create(Record record) {

		// 確認參數是否正確填寫
		if (!StringUtils.hasText(record.getProductName()) || //
				!StringUtils.hasText(record.getStatus()) || //
				!StringUtils.hasText(record.getRecordType()) || //
				record.getProductCount() <= 0 || //
				record.getProductAmount() <= 0) {
			return new RecordRes(RtnCode.PARAM_ERROR);
		}

		try {
			dao.save(record);
			// TODO 發送信件給買家和賣家
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new RecordRes(RtnCode.RECORD_CREATE_FAILED);
		}

		return new RecordRes(RtnCode.SUCCESSFUL, record);
	}

	// TODO 發送信件給買家(取消成功)和賣家(客戶取消訂單)
	// TODO 訂單成立PRODUCT庫存變更
	@Override
	public RecordRes cancel(int id) {
		// 確認訂單是否存在
		if (!dao.existsById(id)) {
			return new RecordRes(RtnCode.RECORD_ID_NOT_FOUND);
		}
		// 取得訂單資訊
		Optional<Record> op = dao.findById(id);

		// 訂單資料為空
		if (op.isEmpty()) {
			return new RecordRes(RtnCode.RECORD_IS_EMPTY);
		}

		// 更改訂單狀態(valid => false)
		Record record = op.get();
		record.setValid(false);

		// 將更新的資料存入DB
		try {
			dao.save(record);
			// TODO 發送信件給買家和賣家
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new RecordRes(RtnCode.RECORD_CANCEL_FAILED);
		}

		return new RecordRes(RtnCode.SUCCESSFUL, record);
	}

	@Override
	public RecordSearchRes getRecordInfoByUserId(int id) {
		// 確認使用者是否存在
		if (!userDao.existsById(id)) {
			return new RecordSearchRes(RtnCode.USER_ID_NOT_FOUND);
		}

		List<Record> recordList = dao.findAllByUserId(id);

		// 有可能會沒有購買或賣商品的交易紀錄->不用防recordList為空，
		// 但要給一個空List，讓他不是null
		recordList = recordList.size() != 0 ? recordList : Collections.emptyList();

		return new RecordSearchRes(RtnCode.SUCCESSFUL, recordList);
	}

	@Override
	public RecordSearchRes getRecordInfoByProductId(int id) {
		// 確認使用者是否存在
		if (!productDao.existsById(id)) {
			return new RecordSearchRes(RtnCode.PRODUCT_ID_NOT_FOUND);
		}

		List<Record> recordList = dao.findAllByProductId(id);

		// 有可能會沒有購買或賣商品的交易紀錄->不用防recordList為空，
		// 但要給一個空List，讓他不是null
		recordList = recordList.size() != 0 ? recordList : Collections.emptyList();

		return new RecordSearchRes(RtnCode.SUCCESSFUL, recordList);
	}

}
