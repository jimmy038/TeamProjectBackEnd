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
	 * ���g(�Ѽ�:comment_id)
	 **/
	public CommentRes addLike(int id);
	
	/**
	 * ���g(�Ѽ�:comment_id)
	 **/
	public CommentRes addDislike(int id);
}
