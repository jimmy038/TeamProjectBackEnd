package com.example.PipiShrimp.service.ifs;

import com.example.PipiShrimp.entity.Comment;
import com.example.PipiShrimp.vo.CommentRes;
import com.example.PipiShrimp.vo.CommentSearchRes;

public interface CommentService {
	/**
	 * 新增評論
	 **/
	public CommentRes create(Comment comment);

	/**
	 * 依據商品id查看對商品的評論(參數:product_id)
	 **/
	public CommentSearchRes getCommentInfo(int id);

}
