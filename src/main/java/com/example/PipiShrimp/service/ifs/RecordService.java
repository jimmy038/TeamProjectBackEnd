package com.example.PipiShrimp.service.ifs;

import java.time.LocalDate;
import java.util.List;

import com.example.PipiShrimp.entity.Record;
import com.example.PipiShrimp.vo.RecordRes;
import com.example.PipiShrimp.vo.RecordSearchRes;

public interface RecordService {
	/**
	 * �s�W�q��(�浧)
	 **/
	public RecordRes create(Record record);

	/**
<<<<<<< HEAD
	 * �����q��(record_type => �����q��)�A(�Ѽ�:record_id)
=======
	 * �s�W�q��(�h��) #���M�ʪ������
	 **/
	public RecordSearchRes create(List<Record> records);

	/**
	 * �����q��(valid => false)�A(�Ѽ�:record_id)
>>>>>>> ian
	 **/
	public RecordRes cancel(int id);
	
	/**
	 * �q�檬�A�אּ�X�f��(status => �X�f��)�A(�Ѽ�:record_id)
	 **/
	public RecordRes shipping(int id);
	
	/**
	 * �q�檬�A�אּ�w����(status => �w����)�A(�Ѽ�:record_id)
	 **/
	public RecordRes completed(int id);

	/**
	 * �ϥΪ̬d�߭q��O��(�Ѽ�:user_id) #�d��(�ʶR)���v����
	 **/
	public RecordSearchRes getRecordInfoByUserId(int id);
	
	/**
	 * �ϥΪ̬d�߭q��O��(�Ѽ�:seller_id) #�d��(�P��)���v����
	 **/
	public RecordSearchRes getRecordInfoBySellerId(int id);

	/**
	 * �d�߰ӫ~�P�q�O��(�Ѽ�:product_id) #�d�߰ӫ~�P�q������ 
	 * ��i��ƪ��ӫ~�W�٭n����x ��� y��P�q ��g����
	 **/
	public RecordSearchRes getRecordInfoByProductId(int id);
<<<<<<< HEAD
	
	public RecordSearchRes delete(List<Integer> idList);
=======

	/**
	 * �ϥΪ̬d�߭q��O��(�Ѽ�:user_id,�}�l�B�������) #�d�߯S�w�ɶ����q��
	 **/
	public RecordSearchRes getRecordInfoByDate(int id, //
			LocalDate startDate, LocalDate endDate);

	/**
	 * �z�L����O���Ө��o�q����(�Ѽ�:record_id) #�I���浧����O��ܸ�T
	 **/
	public RecordRes getMaintenanceByRecordId(int id);

>>>>>>> ian
}
