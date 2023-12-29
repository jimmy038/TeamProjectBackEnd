package com.example.PipiShrimp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PipiShrimp.entity.Comment;

@Repository
public interface CommentDao extends JpaRepository<Comment, Integer> {

 /**
  * 對應product_id，找出所有評論(參數:product_id)
  **/
 public List<Comment> findAllByProductId(int id);
}
