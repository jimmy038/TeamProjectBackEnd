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

	// TODO �o�e�H�󵹶R�a�M��a�A�q�榨��PRODUCT�w�s�ܧ�
	// TODO �ʶR�� > product�w�s�ơA�L�k�ʶR
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

		try {
			dao.save(record);
			// TODO �o�e�H�󵹶R�a�M��a
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new RecordRes(RtnCode.RECORD_CREATE_FAILED);
		}

		return new RecordRes(RtnCode.SUCCESSFUL, record);
	}

	// TODO �o�e�H�󵹶R�a(�������\)�M��a(�Ȥ�����q��)
	// TODO �q�榨��PRODUCT�w�s�ܧ�
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

		// �N��s����Ʀs�JDB
		try {
			dao.save(record);
			// TODO �o�e�H�󵹶R�a�M��a
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new RecordRes(RtnCode.RECORD_CANCEL_FAILED);
		}

		return new RecordRes(RtnCode.SUCCESSFUL, record);
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

}
