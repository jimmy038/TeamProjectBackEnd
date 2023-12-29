package com.example.PipiShrimp.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private int cartId;

	//  ӫ~ [ J ʪ      
	@Column(name = "cart_date")
	@JsonProperty("cart_date")
	private LocalDateTime cartDate;

	//  ӫ~ ƶq
	@Column(name = "cart_count")
	@JsonProperty("cart_count")
	private int cartCount;

	//  ӫ~( `)   B
	@Column(name = "cart_amount")
	@JsonProperty("cart_amount")
	private int productAmount;

	@Column(name = "product_name")
	@JsonProperty("product_name")
	private String productName;

	//     user  ƪ ID   
	@Column(name = "user_id")
	@JsonProperty("user_id")
	private int userId;

	//     product  ƪ ID
	@Column(name = "product_id")
	@JsonProperty("product_id")
	private int productId;
	
	//用json 64base
	@Column(name = "photo", columnDefinition = "MEDIUMBLOB")  // 使用正确的列定义，例如 "LONGTEXT"，根据实际情况选择

	private byte[] photo;
	
	@Column(name = "product_type")
	@JsonProperty("product_type")
	private String productType;
	
	// 商品庫存數量
	@Column(name = "inventory")
	private int inventory;
	

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Cart(int cartId, LocalDateTime cartDate, int cartCount, int productAmount, String productName, int userId,
			int productId, byte[] photo, String productType, int inventory) {
		super();
		this.cartId = cartId;
		this.cartDate = cartDate;
		this.cartCount = cartCount;
		this.productAmount = productAmount;
		this.productName = productName;
		this.userId = userId;
		this.productId = productId;
		this.photo = photo;
		this.productType = productType;
		this.inventory = inventory;
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

	

	public byte[] getPhoto() {
		return photo;
	}



	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}



	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	
}
