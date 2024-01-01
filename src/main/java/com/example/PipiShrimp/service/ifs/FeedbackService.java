package com.example.PipiShrimp.service.ifs;

import com.example.PipiShrimp.entity.Feedback;
import com.example.PipiShrimp.vo.FeedBackRes;

public interface FeedbackService {

	//儲存使用者填寫的回饋資料
	public FeedBackRes sendFeedBack(Feedback feedBack);
	
	//依id查找對應單筆
	public FeedBackRes getFeedBackInfo(int id);
	
	//找全部
	public FeedBackRes getAllFeedBackInfo();

}