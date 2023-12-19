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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private int cartId;

	// 商品加入購物車日期
	@Column(name = "cart_date")
	@JsonProperty("cart_date")
	private LocalDateTime cartDate;

	// 商品數量
	@Column(name = "cart_count")
	@JsonProperty("cart_count")
	private int cartCount;

	// 商品(總)金額
	@Column(name = "cart_amount")
	@JsonProperty("cart_amount")
	private int productAmount;

	@Column(name = "product_name")
	@JsonProperty("product_name")
	private String productName;

	// 對應user資料表ID ↓
	@Column(name = "user_id")
	@JsonProperty("user_id")
	private int userId;

	// 對應product資料表ID
	@Column(name = "product_id")
	@JsonProperty("product_id")
	private int productId;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(LocalDateTime cartDate, int cartCount, int productAmount, String productName, int userId,
			int productId) {
		super();
		this.cartDate = cartDate;
		this.cartCount = cartCount;
		this.productAmount = productAmount;
		this.productName = productName;
		this.userId = userId;
		this.productId = productId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
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
