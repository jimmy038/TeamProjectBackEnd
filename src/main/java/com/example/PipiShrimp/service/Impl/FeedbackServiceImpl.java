package com.example.PipiShrimp.service.Impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Feedback;
import com.example.PipiShrimp.entity.Mail;
import com.example.PipiShrimp.repository.FeedbackDao;
import com.example.PipiShrimp.service.ifs.FeedbackService;
import com.example.PipiShrimp.vo.FeedBackRes;

@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	private FeedbackDao feedbackDao;
	
	/* org.slf4j.Logger */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Override //儲存用戶意見回饋資料
	public FeedBackRes sendFeedBack(Feedback feedBack) {
		// 至少要輸入 name、phone、email、feedback
		if(!StringUtils.hasText(feedBack.getName()) || !StringUtils.hasText(feedBack.getPhone())
			|| !StringUtils.hasText(feedBack.getEmail()) || !StringUtils.hasText(feedBack.getFeedback())) {
			return new FeedBackRes(RtnCode.PARAM_ERROR);
		}
		try {
			feedbackDao.save(feedBack);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new FeedBackRes(RtnCode.EMAIL_FORMAT_ERROR);
		}
		return new FeedBackRes(RtnCode.SUCCESSFUL);
	}


	@Override  //依ID 撈使用者填寫的回饋資料
	public FeedBackRes getFeedBackInfo(int id) {
		Optional<Feedback> op = feedbackDao.findById(id); 
		if(!op.isPresent()){
			return new FeedBackRes(RtnCode.FEEDBACK_ID_NOT_FOUND);
		}	
		Feedback feedback = op.get();			
		return new FeedBackRes(RtnCode.SUCCESSFUL,List.of(feedback));
	}


	@Override  //撈全部
	public FeedBackRes getAllFeedBackInfo() {
		List<Feedback> feedbackList = feedbackDao.findAll();
		if(feedbackList.isEmpty()) {
			return new FeedBackRes(RtnCode.FEEDBACK_ID_NOT_FOUND);
		}else {
			return new FeedBackRes(RtnCode.SUCCESSFUL,feedbackList);
		}
	}

}