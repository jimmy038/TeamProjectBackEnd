package com.example.PipiShrimp.service.ifs;


import com.example.PipiShrimp.entity.Record;
import com.example.PipiShrimp.vo.RecordRes;

public interface RecordService {
	/**
	 * �s�W�q��
	 **/
	public RecordRes create(Record record);
	
	/**
	 * �����q��(valid => false)�A(�Ѽ�:record_id)
	 **/
	public RecordRes cancel(int id);
}
