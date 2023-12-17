package com.example.PipiShrimp.vo;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Record;

public class RecordRes {
	private RtnCode rtnCode;

	private Record record;

	public RecordRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecordRes(RtnCode rtnCode) {
		super();
		this.rtnCode = rtnCode;
	}

	public RecordRes(RtnCode rtnCode, Record record) {
		super();
		this.rtnCode = rtnCode;
		this.record = record;
	}

	public RtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(RtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

}
