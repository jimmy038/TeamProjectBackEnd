package com.example.PipiShrimp.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Comment;
import com.example.PipiShrimp.repository.CommentDao;
import com.example.PipiShrimp.service.ifs.CommentService;
import com.example.PipiShrimp.vo.CommentRes;

@Service
public class CommentServiceImpl implements CommentService {

	/* org.slf4j.Logger */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CommentDao dao;

	@Override
	public CommentRes create(Comment comment) {
		if (!StringUtils.hasText(comment.getComment()) || //
				!StringUtils.hasText(comment.getUserName())) {
			return new CommentRes(RtnCode.PARAM_ERROR);
		}

		try {
			Comment res = dao.save(comment);
			return new CommentRes(RtnCode.SUCCESSFUL, res);

		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println("¿ù»~:" + e);
			return new CommentRes(RtnCode.COMMENT_CREATE_FAILED);
		}
	}

}
