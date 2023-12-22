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
	 * �����q��(valid => false)�A(�Ѽ�:record_id)
	 **/
	public RecordRes cancel(int id);

	/**
	 * �ϥΪ̧R���q��O��(�Ѽ�:record_id) #�R�����v����
	 **/
	public RecordSearchRes delete(List<Integer> idList);

	/**
	 * �ϥΪ̬d�߭q��O��(�Ѽ�:user_id) #�d�߾��v����
	 **/
	public RecordSearchRes getRecordInfoByUserId(int id);

	/**
	 * �d�߰ӫ~�P�q�O��(�Ѽ�:product_id) #�d�߰ӫ~�P�q������ ��i��ƪ��ӫ~�W�٭n����
	 **/
	public RecordSearchRes getRecordInfoByProductId(int id);
<<<<<<< HEAD
	
	public RecordSearchRes delete(List<Integer> idList);
=======

>>>>>>> b5ea93e62384850c6a89db11dd00712137c68d3b
}
