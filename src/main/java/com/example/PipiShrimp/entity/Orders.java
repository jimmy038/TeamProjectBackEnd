package com.example.PipiShrimp.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "orders")//訂單
public class Orders {

	@Column(name = "order_id") 
	private String orderId;
	
	//對應使用者資料表ID ↓
	@Column(name = "user_id") 
	private String userId;
	
	@Column(name = "order_date") 
	private LocalDate orderDate;
	
	@Column(name = "total_amount") 
	private String totalAmount;
	
	@Column(name = "order_status") 
	private String orderStatus;

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(String orderId, String userId, LocalDate orderDate, String totalAmount, String orderStatus) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.orderStatus = orderStatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}
