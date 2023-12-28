package com.example.PipiShrimp.service.Impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Maintenance;
import com.example.PipiShrimp.repository.MaintenanceDao;
import com.example.PipiShrimp.service.ifs.MaintenanceService;
import com.example.PipiShrimp.vo.MaintenanceRes;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

	@Autowired
	private MaintenanceDao dao;

	@Override
	public MaintenanceRes getAllInfo() {
		List<Maintenance> mList = dao.findAll();

		// 若List為空，給一個空List
		mList = mList.size() != 0 ? mList : Collections.emptyList();

		//反轉List
		Collections.reverse(mList);
		
		return new MaintenanceRes(RtnCode.SUCCESSFUL, mList);
	}

}
