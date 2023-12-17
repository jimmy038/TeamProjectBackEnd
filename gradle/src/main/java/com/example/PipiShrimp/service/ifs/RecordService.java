package com.example.PipiShrimp.service.ifs;

import com.example.PipiShrimp.entity.Record;
import com.example.PipiShrimp.vo.RecordRes;
import com.example.PipiShrimp.vo.RecordSearchRes;

public interface RecordService {
	/**
	 * �s�W�q��
	 **/
	public RecordRes create(Record record);

	/**
	 * �����q��(valid => false)�A(�Ѽ�:record_id)
	 **/
	public RecordRes cancel(int id);

	/**
	 * �ϥΪ̬d�߭q��O��(�Ѽ�:user_id) #�d�߾��v����
	 **/
	public RecordSearchRes getRecordInfoByUserId(int id);

	/**
	 * �d�߰ӫ~�P�q�O��(�Ѽ�:product_id) #�d�߰ӫ~�P�q������ 
	 * ��i��ƪ��ӫ~�W�٭n����
	 **/
	public RecordSearchRes getRecordInfoByProductId(int id);
}
