package com.example.PipiShrimp.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int orderId;

	@Column(name = "order_date")
	private LocalDateTime orderDate;

	// 金額
	@Column(name = "amount")
	private int amount;

	// 訂單狀態
	@Column(name = "status")
	private String status;

	@Column(name = "product_name")
	private String productName;

	// 訂單類型(買、賣)
	@Column(name = "type")
	private String type;

	// 對應使用者資料表ID ↓
	@Column(name = "user_id")
	private int userId;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(LocalDateTime orderDate, int amount, String status, String productName, String type, int userId) {
		super();
		this.orderDate = orderDate;
		this.amount = amount;
		this.status = status;
		this.productName = productName;
		this.type = type;
		this.userId = userId;
	}

	public Order(Order order) {
		super();
		this.orderDate = order.getOrderDate();
		this.amount = order.getAmount();
		this.status = order.getStatus();
		this.productName = order.getProductName();
		this.type = order.getType();
		this.userId = order.getUserId();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
