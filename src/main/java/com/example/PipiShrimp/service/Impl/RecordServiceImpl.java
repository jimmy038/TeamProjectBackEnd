package com.example.PipiShrimp.service.Impl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Maintenance;
import com.example.PipiShrimp.entity.Product;
import com.example.PipiShrimp.entity.Record;
import com.example.PipiShrimp.entity.User;
import com.example.PipiShrimp.repository.CartDao;
import com.example.PipiShrimp.repository.MaintenanceDao;
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

	@Autowired
	private CartDao cartDao;

	@Autowired
	private MaintenanceDao maintenanceDao;

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

		// 取得商品資訊
		Optional<Product> op = productDao.findById(record.getProductId());
		if (op.isEmpty()) {
			return new RecordRes(RtnCode.PRODUCT_IS_EMPTY);
		}

		Product product = op.get();
		// 確認購買數量 <= 庫存
		if (product.getInventory() < record.getProductCount()) {
			return new RecordRes(RtnCode.PRODUCT_IS_SHORTAGE);
		}

		// 商品庫存 - 購買數
		product.setInventory(product.getInventory() - record.getProductCount());

		try {
			productDao.save(product);
			dao.save(record);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new RecordRes(RtnCode.RECORD_CREATE_FAILED);
		}

		// TODO 發送信件給賣家

		return new RecordRes(RtnCode.SUCCESSFUL, record);
	}

	@Transactional
	@Override
	public RecordSearchRes create(List<Record> records) {
		// 判斷訂單是否為空
		if (records.size() == 0 || records == null) {
			return new RecordSearchRes(RtnCode.RECORD_IS_EMPTY);
		}

		// 檢查參數
		for (Record record : records) {
			if (!StringUtils.hasText(record.getProductName()) || //
					!StringUtils.hasText(record.getStatus()) || //
					!StringUtils.hasText(record.getRecordType()) || //
					record.getProductCount() <= 0 || //
					record.getProductAmount() <= 0) {
				return new RecordSearchRes(RtnCode.PARAM_ERROR);
			}
		}

		try {
			dao.saveAll(records);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new RecordSearchRes(RtnCode.RECORD_CREATE_FAILED);
		}

		// 取得userId
		int userId = 0;
		for (Record record : records) {
			userId = record.getUserId();
		}

		// 新增完訂單後 => 刪除購物車 #用user_id + product_id
		for (Record record : records) {
			int id = record.getProductId();

			try {
				cartDao.deleteCartById(userId, id);
			} catch (Exception e) {
				logger.error(e.getMessage());
				return new RecordSearchRes(RtnCode.CART_DELETE_FAILED);
			}
		}

		return new RecordSearchRes(RtnCode.SUCCESSFUL);
	}

	// TODO 發送信件給買家(取消成功)和賣家(客戶取消訂單)
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

		// 取得商品資訊
		Optional<Product> proOp = productDao.findById(record.getProductId());
		if (proOp.isEmpty()) {
			return new RecordRes(RtnCode.PRODUCT_IS_EMPTY);
		}

		Product product = proOp.get();

		// 商品庫存 + 購買數
		product.setInventory(product.getInventory() + record.getProductCount());

		// 將更新的資料存入DB
		try {
			productDao.save(product);
			dao.save(record);
			// TODO 發送信件給買家和賣家
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new RecordRes(RtnCode.RECORD_CANCEL_FAILED);
		}

		return new RecordRes(RtnCode.SUCCESSFUL, record);
	}

	@Override
	public RecordRes shipping(int id) {
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

		// 更改訂單狀態(status => "出貨中")
		Record record = op.get();
		if (record.getStatus().matches("取消訂單")) {
			return new RecordRes(RtnCode.RECORD_IS_CANCELED);
		}

		record.setStatus("出貨中");

		// 將更新的資料存入DB
		try {
			dao.save(record);
			// TODO 發送信件給買家和賣家
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new RecordRes(RtnCode.RECORD_SHIPPING_FAILED);
		}

		return new RecordRes(RtnCode.SUCCESSFUL, record);
	}

	@Override
	public RecordRes completed(int id) {
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

		// 更改訂單狀態(status => "已完成")
		Record record = op.get();
		if (record.getStatus().matches("取消訂單")) {
			return new RecordRes(RtnCode.RECORD_IS_CANCELED);
		}

		// 取得商品資訊(增加商品銷售數)
		Optional<Product> proOp = productDao.findById(record.getProductId());

		if (proOp.isEmpty()) {
			return new RecordRes(RtnCode.PRODUCT_IS_EMPTY);
		}

		Product product = proOp.get();

		// 取得消費者資訊(扣除點數)
		Optional<User> buyerOp = userDao.findById(record.getUserId());

		if (buyerOp.isEmpty()) {
			return new RecordRes(RtnCode.USER_IS_EMPTY);
		}

		User buyer = buyerOp.get();

		// 取得賣家資訊(增加點數)
		Optional<User> sellerOp = userDao.findById(record.getSellerId());

		if (sellerOp.isEmpty()) {
			return new RecordRes(RtnCode.USER_IS_EMPTY);
		}

		User seller = sellerOp.get();

		// 更改訂單狀態
		record.setStatus("已完成");

		// 訂單完成後，電商抽取3%手續費
		Maintenance maintenance = new Maintenance(record.getRecordId(), //
				(int) Math.floor(record.getProductAmount() * 0.07));

		// 增加商品銷售數
		product.setSaleCount(product.getSaleCount() + record.getProductCount());

		// 扣除消費者點數
		buyer.setPoints(buyer.getPoints() - record.getProductAmount());

		// 賣家點數增加(總金額-手續費)
		seller.setPoints(seller.getPoints() + (record.getProductAmount() - maintenance.getMaintenanceCost()));

		// 將更新的資料存入DB
		try {
			dao.save(record);
			maintenanceDao.save(maintenance);
			productDao.save(product);
			userDao.save(buyer);
			userDao.save(seller);
			// TODO 發送信件給買家和賣家
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new RecordRes(RtnCode.RECORD_COMPLETED_FAILED);
		}

		return new RecordRes(RtnCode.SUCCESSFUL, record);
	}

	@Override
	public RecordSearchRes delete(List<Integer> idList) {
		// 確認訂單id是否存在
		for (int id : idList) {
			if (!dao.existsById(id)) {
				return new RecordSearchRes(RtnCode.USER_ID_NOT_FOUND);
			}

		}

		// 存放刪除record資料
		List<Record> records = dao.findAllById(idList);

		try {
			dao.deleteAllById(idList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new RecordSearchRes(RtnCode.RECORD_DELETE_FAILED);
		}

		return new RecordSearchRes(RtnCode.SUCCESSFUL, records);
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

	@Override
	public RecordSearchRes getRecordInfoByDate(int id, //
			LocalDate startDate, LocalDate endDate) {
		List<Record> recordList = getRecordInfoByUserId(id).getRecordList();
		// 如果沒輸入時間 => 顯示指定user全部訂單
		if (startDate == null && endDate == null) {
			return new RecordSearchRes(RtnCode.SUCCESSFUL, recordList);
		}

		// 開始、結束日期都有輸入
		if (startDate != null && endDate != null) {
			recordList = dao.findAllByDate(startDate, endDate);
			return new RecordSearchRes(RtnCode.SUCCESSFUL, recordList);
		}

		// 只有開始日期
		if (startDate != null && endDate == null) {
			recordList = dao.findAllByStartDate(startDate);
			return new RecordSearchRes(RtnCode.SUCCESSFUL, recordList);
		}

		// 只有結束日期
		if (startDate == null && endDate != null) {
			recordList = dao.findAllByEndDate(endDate);
			return new RecordSearchRes(RtnCode.SUCCESSFUL, recordList);
		}

		// 開始日期<<<結束日期 防呆
		return new RecordSearchRes(RtnCode.PARAM_ERROR);
	}

	@Override
	public RecordRes getMaintenanceByRecordId(int id) {
		if (!dao.existsById(id)) {
			return new RecordRes(RtnCode.RECORD_ID_NOT_FOUND);
		}

		Optional<Record> op = dao.findById(id);
		if (op.isEmpty()) {
			return new RecordRes(RtnCode.RECORD_IS_EMPTY);
		}

		return new RecordRes(RtnCode.SUCCESSFUL, op.get());
	}

}
