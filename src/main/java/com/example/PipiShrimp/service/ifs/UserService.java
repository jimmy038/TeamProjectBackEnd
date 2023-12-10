package com.example.PipiShrimp.service.ifs;

import com.example.PipiShrimp.entity.User;
import com.example.PipiShrimp.vo.UserRes;

public interface UserService {

	/**
	 * 新增一筆user資料到DB #註冊
	 **/
	public UserRes signUp(User user);

}
