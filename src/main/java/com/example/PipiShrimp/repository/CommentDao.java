package com.example.PipiShrimp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PipiShrimp.entity.Comment;

@Repository
public interface CommentDao extends JpaRepository<Comment, Integer> {

	/**
	 * ����product_id�A��X�Ҧ�����(�Ѽ�:product_id)
	 **/
	public List<Comment> findAllByProductId(int id);
}
