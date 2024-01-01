package com.example.PipiShrimp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PipiShrimp.entity.Feedback;
import com.example.PipiShrimp.service.ifs.FeedbackService;
import com.example.PipiShrimp.vo.FeedBackRes;

@CrossOrigin
@RestController
public class FeedBackController {
	
	@Autowired
	private FeedbackService feedbackService;
	
	@PostMapping(value = "/feedBack/sendFeedBack")
	public FeedBackRes sendFeedBack(@RequestBody Feedback feedBack){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("qtp1520@gmail.com"); // 開發者 mail 信箱
		message.setSubject("新的意見回饋");
		message.setText("姓名: " + feedBack.getName() + "\n聯絡電話: " + feedBack.getPhone() + "\nE-mail: "
				+ feedBack.getEmail() + "\n意見與回饋: " + feedBack.getFeedback());

		return feedbackService.sendFeedBack(feedBack);
	}
	
	@GetMapping(value = "/feedBack/getFeedBackInfo")
	public FeedBackRes getFeedBackInfo(@RequestParam int id) {
		return feedbackService.getFeedBackInfo(id);	
	}
	
	@GetMapping(value = "/feedBack/getAllFeedBackInfo")
	public FeedBackRes getAllFeedBackInfo() {
		return feedbackService.getAllFeedBackInfo();	
	}

}
