package com.example.PipiShrimp.service.ifs;

import org.springframework.stereotype.Service;

import com.example.PipiShrimp.entity.Users;
import com.example.PipiShrimp.vo.UsersResponse;

@Service
public interface UsersService {

	//�s�W���U�b��
	public UsersResponse create(Users users);
	
	
	
}
