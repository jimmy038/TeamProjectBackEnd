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

		// �T�{�ѼƬO�_���T��g
		if (!StringUtils.hasText(record.getProductName()) || //
				!StringUtils.hasText(record.getStatus()) || //
				!StringUtils.hasText(record.getRecordType()) || //
				record.getProductCount() <= 0 || //
				record.getProductAmount() <= 0) {
			return new RecordRes(RtnCode.PARAM_ERROR);
		}

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

	@Transactional
	@Override
	public RecordSearchRes create(List<Record> records) {
		// �P�_�q��O�_����
		if (records.size() == 0 || records == null) {
			return new RecordSearchRes(RtnCode.RECORD_IS_EMPTY);
		}

		// �ˬd�Ѽ�
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

		// ���ouserId
		int userId = 0;
		for (Record record : records) {
			userId = record.getUserId();
		}

		// �s�W���q��� => �R���ʪ��� #��user_id + product_id
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

	// TODO �o�e�H�󵹶R�a(�������\)�M��a(�Ȥ�����q��)
	@Override
	public RecordRes cancel(int id) {
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

		// ���q�檬�A(valid => false)
		Record record = op.get();
		record.setValid(false);

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
		try {
			dao.save(record);
			// TODO �o�e�H�󵹶R�a�M��a
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
		// �T�{�ϥΪ̬O�_�s�b
		if (!userDao.existsById(id)) {
			return new RecordSearchRes(RtnCode.USER_ID_NOT_FOUND);
		}

		List<Record> recordList = dao.findAllByUserId(id);

		// ���i��|�S���ʶR�ν�ӫ~���������->���Ψ�recordList���šA
		// ���n���@�Ӫ�List�A���L���Onull
		recordList = recordList.size() != 0 ? recordList : Collections.emptyList();

		return new RecordSearchRes(RtnCode.SUCCESSFUL, recordList);
	}

	@Override
	public RecordSearchRes getRecordInfoByProductId(int id) {
		// �T�{�ϥΪ̬O�_�s�b
		if (!productDao.existsById(id)) {
			return new RecordSearchRes(RtnCode.PRODUCT_ID_NOT_FOUND);
		}

		List<Record> recordList = dao.findAllByProductId(id);

		// ���i��|�S���ʶR�ν�ӫ~���������->���Ψ�recordList���šA
		// ���n���@�Ӫ�List�A���L���Onull
		recordList = recordList.size() != 0 ? recordList : Collections.emptyList();

		return new RecordSearchRes(RtnCode.SUCCESSFUL, recordList);
	}

	@Override
	public RecordSearchRes getRecordInfoByDate(int id, //
			LocalDate startDate, LocalDate endDate) {
		List<Record> recordList = getRecordInfoByUserId(id).getRecordList();
		// �p�G�S��J�ɶ� => ��ܫ��wuser�����q��
		if (startDate == null && endDate == null) {
			return new RecordSearchRes(RtnCode.SUCCESSFUL, recordList);
		}

		// �}�l�B�������������J
		if (startDate != null && endDate != null) {
			recordList = dao.findAllByDate(startDate, endDate);
			return new RecordSearchRes(RtnCode.SUCCESSFUL, recordList);
		}

		// �u���}�l���
		if (startDate != null && endDate == null) {
			recordList = dao.findAllByStartDate(startDate);
			return new RecordSearchRes(RtnCode.SUCCESSFUL, recordList);
		}

		// �u���������
		if (startDate == null && endDate != null) {
			recordList = dao.findAllByEndDate(endDate);
			return new RecordSearchRes(RtnCode.SUCCESSFUL, recordList);
		}

		// �}�l���<<<������� ���b
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
