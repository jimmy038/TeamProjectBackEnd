package com.example.PipiShrimp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PipiShrimp.entity.Comment;
import com.example.PipiShrimp.service.ifs.CommentService;
import com.example.PipiShrimp.vo.CommentRes;
import com.example.PipiShrimp.vo.CommentSearchRes;

@CrossOrigin
@RestController
public class CommentController {

 @Autowired
 private CommentService service;

 @PostMapping(value = "/comment/create")
 public CommentRes create(@RequestBody Comment comment) {
  return service.create(comment);
 }

 @GetMapping(value = "/comment/info")
 public CommentSearchRes getCommentInfo(@RequestParam int id) {
  return service.getCommentInfo(id);
 }

 @PostMapping(value = "/comment/like")
 public CommentRes addLike(@RequestParam int id) {
  return service.addLike(id);
 }
 
 @PostMapping(value = "/comment/dislike")
 public CommentRes addDislike(@RequestParam int id) {
  return service.addDislike(id);
 }
}