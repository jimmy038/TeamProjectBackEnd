package com.example.PipiShrimp.service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Comment;
import com.example.PipiShrimp.repository.CommentDao;
import com.example.PipiShrimp.repository.ProductDao;
import com.example.PipiShrimp.service.ifs.CommentService;
import com.example.PipiShrimp.vo.CommentRes;
import com.example.PipiShrimp.vo.CommentSearchRes;

@Service
public class CommentServiceImpl implements CommentService {

	/* org.slf4j.Logger */
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CommentDao dao;

	@Autowired
	private ProductDao proDao;

	@Override
	public CommentRes create(Comment comment) {
		if (!StringUtils.hasText(comment.getComment()) 
//				|| !StringUtils.hasText(comment.getUserName())
				
				) {
			return new CommentRes(RtnCode.PARAM_ERROR);
		}

		try {
			Comment res = dao.save(comment);
			return new CommentRes(RtnCode.SUCCESSFUL, res);

		} catch (Exception e) {
			logger.error(e.getMessage());
			System.out.println("錯誤:" + e);
			return new CommentRes(RtnCode.COMMENT_CREATE_FAILED);
		}
	}

	@Override
	public CommentSearchRes getCommentInfo(int id) {
		// 確認商品id是否存在
		if (!proDao.existsById(id)) {
			return new CommentSearchRes(RtnCode.PRODUCT_ID_NOT_FOUND);
		}

		List<Comment> commentList = dao.findAllByProductId(id);

		// 如果List是空的要給一個空List
		commentList = commentList.size() != 0 ? commentList : Collections.emptyList();

		return new CommentSearchRes(RtnCode.SUCCESSFUL, commentList);
	}

	@Override
	public CommentRes addLike(int id) {
		if (!dao.existsById(id)) {
			return new CommentRes(RtnCode.COMMENT_ID_NOT_FOUND);
		}

		Optional<Comment> op = dao.findById(id);

		if (op.isEmpty()) {
			return new CommentRes(RtnCode.COMMENT_IS_EMPTY);
		}

		Comment comment = op.get();
		int count = comment.getLikeCount();
		int plus = count += 1;
		comment.setLikeCount(plus);

		// 儲存更新like_count資料
		try {
			dao.save(comment);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new CommentRes(RtnCode.COMMENT_UPDATE_FAILED);
		}

		return new CommentRes(RtnCode.SUCCESSFUL, comment);
	}

	@Override
	public CommentRes addDislike(int id) {
		if (!dao.existsById(id)) {
			return new CommentRes(RtnCode.COMMENT_ID_NOT_FOUND);
		}

		Optional<Comment> op = dao.findById(id);

		if (op.isEmpty()) {
			return new CommentRes(RtnCode.COMMENT_IS_EMPTY);
		}

		Comment comment = op.get();
		int count = comment.getDislikeCount();
		int plus = count += 1;
		comment.setDislikeCount(plus);

		// 儲存更新like_count資料
		try {
			dao.save(comment);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new CommentRes(RtnCode.COMMENT_UPDATE_FAILED);
		}

		return new CommentRes(RtnCode.SUCCESSFUL, comment);
	}
}