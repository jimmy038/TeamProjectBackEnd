package com.example.PipiShrimp.service.ifs;

import com.example.PipiShrimp.entity.Comment;
import com.example.PipiShrimp.vo.CommentRes;

public interface CommentService {
	/**
	 * �s�W����
	 **/
	public CommentRes create(Comment comment);
}
