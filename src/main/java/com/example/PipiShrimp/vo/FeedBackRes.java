package com.example.PipiShrimp.vo;

import java.util.List;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Feedback;

public class FeedBackRes {
	
	private RtnCode rtnCode;
	
	private List<Feedback> feedbackList; 

	public FeedBackRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FeedBackRes(RtnCode rtnCode, List<Feedback> feedbackList) {
		super();
		this.rtnCode = rtnCode;
		this.feedbackList = feedbackList;
	}
	
	
	public FeedBackRes(RtnCode rtnCode) {
		super();
		this.rtnCode = rtnCode;
	}

	public RtnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(RtnCode rtnCode) {
		this.rtnCode = rtnCode;
	}

	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}

	
}

