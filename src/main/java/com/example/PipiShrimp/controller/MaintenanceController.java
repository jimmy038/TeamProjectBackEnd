package com.example.PipiShrimp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PipiShrimp.service.ifs.MaintenanceService;
import com.example.PipiShrimp.vo.MaintenanceRes;

@CrossOrigin
@RestController
public class MaintenanceController {
	@Autowired
	private MaintenanceService service;

	@GetMapping(value = "/maintenance/get")
	public MaintenanceRes getAllInfo() {
		return service.getAllInfo();
	}
}
