package com.example.PipiShrimp.service.ifs;

import java.time.LocalDate;
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
<<<<<<< HEAD
	 * 取消訂單(record_type => 取消訂單)，(參數:record_id)
=======
	 * 新增訂單(多筆) #結清購物車資料
	 **/
	public RecordSearchRes create(List<Record> records);

	/**
	 * 取消訂單(valid => false)，(參數:record_id)
>>>>>>> ian
	 **/
	public RecordRes cancel(int id);
	
	/**
	 * 訂單狀態改為出貨中(status => 出貨中)，(參數:record_id)
	 **/
	public RecordRes shipping(int id);
	
	/**
	 * 訂單狀態改為已完成(status => 已完成)，(參數:record_id)
	 **/
	public RecordRes completed(int id);

	/**
	 * 使用者查詢訂單記錄(參數:user_id) #查詢(購買)歷史紀錄
	 **/
	public RecordSearchRes getRecordInfoByUserId(int id);
	
	/**
	 * 使用者查詢訂單記錄(參數:seller_id) #查詢(銷售)歷史紀錄
	 **/
	public RecordSearchRes getRecordInfoBySellerId(int id);

	/**
	 * 查詢商品銷量記錄(參數:product_id) #查詢商品銷量之類的 
	 * 兩張資料表的商品名稱要對應x 日期 y放銷量 月週報表
	 **/
	public RecordSearchRes getRecordInfoByProductId(int id);
<<<<<<< HEAD
	
	public RecordSearchRes delete(List<Integer> idList);
=======

	/**
	 * 使用者查詢訂單記錄(參數:user_id,開始、結束日期) #查詢特定時間的訂單
	 **/
	public RecordSearchRes getRecordInfoByDate(int id, //
			LocalDate startDate, LocalDate endDate);

	/**
	 * 透過手續費明細取得訂單資料(參數:record_id) #點擊單筆手續費顯示資訊
	 **/
	public RecordRes getMaintenanceByRecordId(int id);

>>>>>>> ian
}
