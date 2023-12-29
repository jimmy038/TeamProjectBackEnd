package com.example.PipiShrimp.vo;

import java.util.List;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Comment;

public class CommentSearchRes {

 private RtnCode rtnCode;

 private List<Comment> commentList;

 public CommentSearchRes() {
  super();
  // TODO Auto-generated constructor stub
 }

 public CommentSearchRes(RtnCode rtnCode) {
  super();
  this.rtnCode = rtnCode;
 }

 public CommentSearchRes(RtnCode rtnCode, List<Comment> commentList) {
  super();
  this.rtnCode = rtnCode;
  this.commentList = commentList;
 }

 public RtnCode getRtnCode() {
  return rtnCode;
 }

 public void setRtnCode(RtnCode rtnCode) {
  this.rtnCode = rtnCode;
 }

 public List<Comment> getCommentList() {
  return commentList;
 }

 public void setCommentList(List<Comment> commentList) {
  this.commentList = commentList;
 }

}
