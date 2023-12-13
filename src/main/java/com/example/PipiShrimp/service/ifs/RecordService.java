package com.example.PipiShrimp.service.ifs;


import com.example.PipiShrimp.entity.Record;
import com.example.PipiShrimp.vo.RecordRes;

public interface RecordService {
	/**
	 * 新增訂單
	 **/
	public RecordRes create(Record record);
	
	/**
	 * 取消訂單(valid => false)，(參數:record_id)
	 **/
	public RecordRes cancel(int id);
}
