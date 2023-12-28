package com.example.PipiShrimp.vo;

import java.util.List;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Maintenance;

public class MaintenanceRes {
	private RtnCode rtnCode;

	private List<Maintenance> mList;

	public MaintenanceRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MaintenanceRes(RtnCode rtnCode) {
		super();
		this.rtnCode = rtnCode;
	}

	public MaintenanceRes(RtnCode rtnCode, List<Maintenance> mList) {
		super();
		this.rtnCode = rtnCode;
		this.mList = mList;
	}

	public RtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(RtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}

	public List<Maintenance> getmList() {
		return mList;
	}

	public void setmList(List<Maintenance> mList) {
		this.mList = mList;
	}

}
