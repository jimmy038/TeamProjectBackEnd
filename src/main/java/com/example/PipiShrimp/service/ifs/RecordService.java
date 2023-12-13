package com.example.PipiShrimp.service.ifs;

import com.example.PipiShrimp.entity.Record;
import com.example.PipiShrimp.vo.RecordRes;
import com.example.PipiShrimp.vo.RecordSearchRes;

public interface RecordService {
	/**
	 * 新增訂單
	 **/
	public RecordRes create(Record record);

	/**
	 * 取消訂單(valid => false)，(參數:record_id)
	 **/
	public RecordRes cancel(int id);

	/**
	 * 使用者查詢訂單記錄(參數:user_id) #查詢歷史紀錄
	 **/
	public RecordSearchRes getRecordInfoByUserId(int id);

	/**
	 * 查詢商品銷量記錄(參數:product_id) #查詢商品銷量之類的 
	 * 兩張資料表的商品名稱要對應
	 **/
	public RecordSearchRes getRecordInfoByProductId(int id);
}
