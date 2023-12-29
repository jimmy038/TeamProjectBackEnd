package com.example.PipiShrimp.vo;

import com.example.PipiShrimp.constants.RtnCode;
import com.example.PipiShrimp.entity.Comment;

public class CommentRes {

 private RtnCode rtnCode;

 private Comment comment;

 public CommentRes() {
  super();
  // TODO Auto-generated constructor stub
 }

 public CommentRes(RtnCode rtnCode) {
  super();
  this.rtnCode = rtnCode;
 }

 public CommentRes(RtnCode rtnCode, Comment comment) {
  super();
  this.rtnCode = rtnCode;
  this.comment = comment;
 }

 public RtnCode getRtnCode() {
  return rtnCode;
 }

 public void setRtnCode(RtnCode rtnCode) {
  this.rtnCode = rtnCode;
 }

 public Comment getComment() {
  return comment;
 }

 public void setComment(Comment comment) {
  this.comment = comment;
 }

}