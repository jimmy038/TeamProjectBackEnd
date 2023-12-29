package com.example.PipiShrimp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "comment")
public class Comment {

 // µû½×id(»¼¼Wint AI)
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "comment_id")
 @JsonProperty("comment_id")
 private int commentId;

 @Column(name = "user_name")
 @JsonProperty("user_name")
 private String userName;

 @Column(name = "star")
 private int star;

 @Column(name = "comment")
 private String comment;

 @Column(name = "like_count")
 @JsonProperty("like_count")
 private int likeCount;

 @Column(name = "dislike_count")
 @JsonProperty("dislike_count")
 private int dislikeCount;

 @Column(name = "user_id")
 @JsonProperty("user_id")
 private int userId;

 @Column(name = "product_id")
 @JsonProperty("product_id")
 private int productId;

 public Comment() {
  super();
  // TODO Auto-generated constructor stub
 }

 public Comment( String userName, int star, String comment, int likeCount, int dislikeCount,
   int userId, int productId) {
  super();
  this.userName = userName;
  this.star = star;
  this.comment = comment;
  this.likeCount = likeCount;
  this.dislikeCount = dislikeCount;
  this.userId = userId;
  this.productId = productId;
 }

 public int getCommentId() {
  return commentId;
 }

 public void setCommentId(int commentId) {
  this.commentId = commentId;
 }

 public String getUserName() {
  return userName;
 }

 public void setUserName(String userName) {
  this.userName = userName;
 }

 public int getStar() {
  return star;
 }

 public void setStar(int star) {
  this.star = star;
 }

 public String getComment() {
  return comment;
 }

 public void setComment(String comment) {
  this.comment = comment;
 }

 public int getLikeCount() {
  return likeCount;
 }

 public void setLikeCount(int likeCount) {
  this.likeCount = likeCount;
 }

 public int getDislikeCount() {
  return dislikeCount;
 }

 public void setDislikeCount(int dislikeCount) {
  this.dislikeCount = dislikeCount;
 }

 public int getUserId() {
  return userId;
 }

 public void setUserId(int userId) {
  this.userId = userId;
 }

 public int getProductId() {
  return productId;
 }

 public void setProductId(int productId) {
  this.productId = productId;
 }

}