package com.example.PipiShrimp.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "cart")
public class Cart {

	// ����user��ƪ�ID�B�]�O�D��
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	@JsonProperty("cart_id")
	private int cartId;
	

	// �ӫ~�[�J�ʪ������
	@Column(name = "cart_date")
	@JsonProperty("cart_date")
	private LocalDateTime cartDate;

	// �ӫ~�ƶq
	@Column(name = "cart_count")
	@JsonProperty("cart_count")
	private int cartCount;

	// �ӫ~(�`)���B
	@Column(name = "cart_amount")
	@JsonProperty("cart_amount")
	private int productAmount;

	// ���~�W��
	@Column(name = "product_name")
	@JsonProperty("product_name")
	private String productName;
	
	// ����user��ƪ�ID
	@Column(name = "user_id")
	@JsonProperty("user_id")
	private int userId;

	// ����product��ƪ�ID
	@Column(name = "product_id")
	@JsonProperty("product_id")
	private int productId;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(int userId, LocalDateTime cartDate, int cartCount, int productAmount, String productName,
			int productId) {
		super();
		this.userId = userId;
		this.cartDate = cartDate;
		this.cartCount = cartCount;
		this.productAmount = productAmount;
		this.productName = productName;
		this.productId = productId;
	}

	public LocalDateTime getCartDate() {
		return cartDate;
	}

	public void setCartDate(LocalDateTime cartDate) {
		this.cartDate = cartDate;
	}

	public int getCartCount() {
		return cartCount;
	}

	public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
	}

	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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
