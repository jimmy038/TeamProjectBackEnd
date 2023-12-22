package com.example.PipiShrimp.service.ifs;

import java.util.List;

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
	 * 使用者刪除訂單記錄(參數:record_id) #刪除歷史紀錄
	 **/
	public RecordSearchRes delete(List<Integer> idList);

	/**
	 * 使用者查詢訂單記錄(參數:user_id) #查詢歷史紀錄
	 **/
	public RecordSearchRes getRecordInfoByUserId(int id);

	/**
	 * 查詢商品銷量記錄(參數:product_id) #查詢商品銷量之類的 兩張資料表的商品名稱要對應
	 **/
	public RecordSearchRes getRecordInfoByProductId(int id);
<<<<<<< HEAD
	
	public RecordSearchRes delete(List<Integer> idList);
=======

>>>>>>> b5ea93e62384850c6a89db11dd00712137c68d3b
}
