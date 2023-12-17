package com.example.PipiShrimp.entity;

import java.time.LocalDate;
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
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int productId;

	@Column(name = "product_name")
	@JsonProperty("product_name")
	private String productName;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private int price;

	// 商品庫存數量
	@Column(name = "inventory")
	private int inventory;

	// 商品銷售數量
	@Column(name = "sale_count")
	@JsonProperty("sale_count")
	private int saleCount;

	// 商品是否上架(boolean)
	@Column(name = "shelves")
	private boolean shelves;

	//用json 64base
	@Column(name = "photo", columnDefinition = "LONGTEXT")  // 使用正确的列定义，例如 "LONGTEXT"，根据实际情况选择
	@Lob
	private String photo;

	
	@Column(name = "user_id")
	@JsonProperty("user_id")
	private int userId;
	
	@Column(name = "upload_time")
	@JsonProperty("upload_time")
	private LocalDate uploadTime;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

	
	public Product(int productId, String productName, String description, int price, int inventory, int saleCount,
			boolean shelves, String photo, int userId, LocalDate currentDate) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.inventory = inventory;
		this.saleCount = saleCount;
		this.shelves = shelves;
		this.photo = photo;
		this.userId = userId;
		this.uploadTime = currentDate;
	}





	public Product(Product product, LocalDate currentDate) {
		super();
		this.productName = product.getProductName();
		this.description = product.getDescription();
		this.price = product.getPrice();
		this.inventory = product.getInventory();
		this.saleCount = product.getSaleCount();
		this.shelves = product.isShelves();
		this.photo = product.getPhoto();
		this.userId = product.getUserId();

	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public int getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}

	public boolean isShelves() {
		return shelves;
	}

	public void setShelves(boolean shelves) {
		this.shelves = shelves;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}





	public LocalDate getUploadTime() {
		return uploadTime;
	}





	public void setUploadTime(LocalDate uploadTime) {
		this.uploadTime = uploadTime;
	}







	

}
