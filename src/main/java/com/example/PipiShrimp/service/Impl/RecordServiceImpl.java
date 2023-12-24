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
import com.example.PipiShrimp.entity.Maintenance;
import com.example.PipiShrimp.entity.Product;
import com.example.PipiShrimp.entity.Record;
import com.example.PipiShrimp.entity.User;
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

<<<<<<< HEAD
	@Autowired
	private MaintenanceDao maintenanceDao;

=======
	// TODO 發送信件給買家和賣家，訂單成立PRODUCT庫存變更
	// TODO 購買數 > product庫存數，無法購買
>>>>>>> 4f8cb30f04fb6a7c1560c35271114125a67650fb
	@Override
	public RecordRes create(Record record) {
	    // 确认参数是否正确填写
	    if (!StringUtils.hasText(record.getProductName()) ||
	        !StringUtils.hasText(record.getStatus()) ||
	        !StringUtils.hasText(record.getRecordType()) ||
	        record.getProductCount() <= 0 ||
	        record.getProductAmount() <= 0) {
	        logger.error("Invalid parameters provided for record creation.");
	        return new RecordRes(RtnCode.PARAM_ERROR);
	    }

	    try {
	        dao.save(record);

<<<<<<< HEAD
		// ���o�ӫ~��T
		Optional<Product> op = productDao.findById(record.getProductId());
		if (op.isEmpty()) {
			return new RecordRes(RtnCode.PRODUCT_IS_EMPTY);
		}

		Product product = op.get();
		// �T�{�ʶR�ƶq <= �w�s
		if (product.getInventory() < record.getProductCount()) {
			return new RecordRes(RtnCode.PRODUCT_IS_SHORTAGE);
		}

		// �ӫ~�w�s - �ʶR��
		product.setInventory(product.getInventory() - record.getProductCount());

		try {
			productDao.save(product);
			dao.save(record);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new RecordRes(RtnCode.RECORD_CREATE_FAILED);
		}

		// TODO �o�e�H�󵹽�a

		return new RecordRes(RtnCode.SUCCESSFUL, record);
	}

	// TODO �o�e�H�󵹶R�a(�������\)�M��a(�Ȥ�����q��)
=======
	        // TODO: 在这里添加发送邮件给买家和卖家的逻辑

	        logger.info("Record created successfully. ID: {}", record.getRecordId());
	    } catch (Exception e) {
	        logger.error("Error creating record: {}", e.getMessage(), e);
	        return new RecordRes(RtnCode.RECORD_CREATE_FAILED);
	    }

	    return new RecordRes(RtnCode.SUCCESSFUL, record);
	}

	// TODO 發送信件給買家(取消成功)和賣家(客戶取消訂單)
	// TODO 訂單成立PRODUCT庫存變更
>>>>>>> 4f8cb30f04fb6a7c1560c35271114125a67650fb
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

<<<<<<< HEAD
		// ���o�ӫ~��T
		Optional<Product> proOp = productDao.findById(record.getProductId());
		if (proOp.isEmpty()) {
			return new RecordRes(RtnCode.PRODUCT_IS_EMPTY);
		}

		Product product = proOp.get();

		// �ӫ~�w�s + �ʶR��
		product.setInventory(product.getInventory() + record.getProductCount());

		// �N��s����Ʀs�JDB
		try {
			productDao.save(product);
			dao.save(record);
			// TODO �o�e�H�󵹶R�a�M��a
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new RecordRes(RtnCode.RECORD_CANCEL_FAILED);
		}

		return new RecordRes(RtnCode.SUCCESSFUL, record);
	}

	@Override
	public RecordRes shipping(int id) {
		// �T�{�q��O�_�s�b
		if (!dao.existsById(id)) {
			return new RecordRes(RtnCode.RECORD_ID_NOT_FOUND);
		}
		// ���o�q���T
		Optional<Record> op = dao.findById(id);

		// �q���Ƭ���
		if (op.isEmpty()) {
			return new RecordRes(RtnCode.RECORD_IS_EMPTY);
		}

		// ���q�檬�A(status => "�X�f��")
		Record record = op.get();
		if (record.getStatus().matches("�����q��")) {
			return new RecordRes(RtnCode.RECORD_IS_CANCELED);
		}

		record.setStatus("�X�f��");

		// �N��s����Ʀs�JDB
=======
		// 將更新的資料存入DB
>>>>>>> 4f8cb30f04fb6a7c1560c35271114125a67650fb
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
		// �T�{�q��O�_�s�b
		if (!dao.existsById(id)) {
			return new RecordRes(RtnCode.RECORD_ID_NOT_FOUND);
		}
		// ���o�q���T
		Optional<Record> op = dao.findById(id);

		// �q���Ƭ���
		if (op.isEmpty()) {
			return new RecordRes(RtnCode.RECORD_IS_EMPTY);
		}

		// ���q�檬�A(status => "�w����")
		Record record = op.get();
		if (record.getStatus().matches("�����q��")) {
			return new RecordRes(RtnCode.RECORD_IS_CANCELED);
		}

		// ���o�ӫ~��T(�W�[�ӫ~�P���)
		Optional<Product> proOp = productDao.findById(record.getProductId());

		if (proOp.isEmpty()) {
			return new RecordRes(RtnCode.PRODUCT_IS_EMPTY);
		}

		Product product = proOp.get();

		// ���o���O�̸�T(�����I��)
		Optional<User> buyerOp = userDao.findById(record.getUserId());

		if (buyerOp.isEmpty()) {
			return new RecordRes(RtnCode.USER_IS_EMPTY);
		}

		User buyer = buyerOp.get();

		// ���o��a��T(�W�[�I��)
		Optional<User> sellerOp = userDao.findById(record.getSellerId());

		if (sellerOp.isEmpty()) {
			return new RecordRes(RtnCode.USER_IS_EMPTY);
		}

		User seller = sellerOp.get();

		// ���q�檬�A
		record.setStatus("�w����");

		// �q�槹����A�q�ө��3%����O
		Maintenance maintenance = new Maintenance(record.getRecordId(), //
				(int) Math.floor(record.getProductAmount() * 0.07));

		// �W�[�ӫ~�P���
		product.setSaleCount(product.getSaleCount() + record.getProductCount());

		// �������O���I��
		buyer.setPoints(buyer.getPoints() - record.getProductAmount());

		// ��a�I�ƼW�[(�`���B-����O)
		seller.setPoints(seller.getPoints() + (record.getProductAmount() - maintenance.getMaintenanceCost()));

		// �N��s����Ʀs�JDB
		try {
			dao.save(record);
			maintenanceDao.save(maintenance);
			productDao.save(product);
			userDao.save(buyer);
			userDao.save(seller);
			// TODO �o�e�H�󵹶R�a�M��a
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new RecordRes(RtnCode.RECORD_COMPLETED_FAILED);
		}

		return new RecordRes(RtnCode.SUCCESSFUL, record);
	}

	@Override
	public RecordSearchRes delete(List<Integer> idList) {
		// �T�{�q��id�O�_�s�b
		for (int id : idList) {
			if (!dao.existsById(id)) {
				return new RecordSearchRes(RtnCode.USER_ID_NOT_FOUND);
			}

		}

		// �s��R��record���
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
}
