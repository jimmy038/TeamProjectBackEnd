package com.example.PipiShrimp.service.ifs;

import com.example.PipiShrimp.entity.Comment;
import com.example.PipiShrimp.vo.CommentRes;
import com.example.PipiShrimp.vo.CommentSearchRes;

public interface CommentService {
	/**
	 * �s�W����
	 **/
	public CommentRes create(Comment comment);

	/**
	 * �̾ڰӫ~id�d�ݹ�ӫ~������(�Ѽ�:product_id)
	 **/
	public CommentSearchRes getCommentInfo(int id);
	
	/**
	 * �R��������(user��id = comment��user_id) 
	 **/
	public CommentRes delete(Comment comment);
}
