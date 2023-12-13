package com.example.PipiShrimp.service.ifs;

import com.example.PipiShrimp.entity.Record;
import com.example.PipiShrimp.vo.RecordRes;
import com.example.PipiShrimp.vo.RecordSearchRes;

public interface RecordService {
	/**
	 * 穝糤璹虫
	 **/
	public RecordRes create(Record record);

	/**
	 * 璹虫(valid => false)(把计:record_id)
	 **/
	public RecordRes cancel(int id);

	/**
	 * ㄏノ琩高璹虫癘魁(把计:user_id) #琩高菌魁
	 **/
	public RecordSearchRes getRecordInfoByUserId(int id);

	/**
	 * 琩高坝珇綪秖癘魁(把计:product_id) #琩高坝珇綪秖ぇ摸
	 **/
	public RecordSearchRes getRecordInfoByProductId(int id);
}
