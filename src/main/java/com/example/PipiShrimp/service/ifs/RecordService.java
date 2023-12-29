package com.example.PipiShrimp.service.ifs;

import java.util.List;

import com.example.PipiShrimp.entity.Record;
import com.example.PipiShrimp.vo.RecordRes;
import com.example.PipiShrimp.vo.RecordSearchRes;

public interface RecordService {
	/**
	 * �s�W�q��
	 **/
	public RecordRes create(Record record);

	/**
	 * �����q��(record_type => �����q��)�A(�Ѽ�:record_id)
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
	
	public RecordSearchRes delete(List<Integer> idList);
}
