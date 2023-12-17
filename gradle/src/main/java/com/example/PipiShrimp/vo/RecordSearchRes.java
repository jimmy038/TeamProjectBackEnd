package com.example.PipiShrimp.vo;

import java.util.List;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Record;

public class RecordSearchRes {
	private RtnCode rtnCode;

	private List<Record> recordList;

	public RecordSearchRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecordSearchRes(RtnCode rtnCode) {
		super();
		this.rtnCode = rtnCode;
	}

	public RecordSearchRes(RtnCode rtnCode, List<Record> recordList) {
		super();
		this.rtnCode = rtnCode;
		this.recordList = recordList;
	}

	public RtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(RtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}

	public List<Record> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<Record> recordList) {
		this.recordList = recordList;
	}

}
