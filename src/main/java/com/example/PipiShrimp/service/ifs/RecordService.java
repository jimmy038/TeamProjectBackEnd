package com.example.PipiShrimp.service.ifs;

import java.util.List;

import com.example.PipiShrimp.entity.Record;
import com.example.PipiShrimp.vo.RecordRes;
import com.example.PipiShrimp.vo.RecordSearchRes;

public interface RecordService {
	/**
	 * 新增訂單(單筆)
	 **/
	public RecordRes create(Record record);
	
	/**
	 * 新增訂單(多筆) #結清購物車資料
	 **/
	public RecordSearchRes create(List<Record> records);

	/**
	 * 取消訂單(valid => false)，(參數:record_id)
	 **/
	public RecordRes cancel(int id);

	/**
	 * 訂單出貨(status => 已出貨)，(參數:record_id)
	 **/
	public RecordRes shipping(int id);

	/**
	 * 完成訂單(status => 已完成)，(參數:record_id)
	 **/
	public RecordRes completed(int id);

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

	/**
	 * 透過手續費明細取得訂單資料(參數:record_id) #點擊單筆手續費顯示資訊
	 **/
	public RecordRes getMaintenanceByRecordId(int id);

}
