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
	
	@Column(name = "product_type")
	@JsonProperty("product_type")
	private String productType;
	
	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private int price;

	// ���澈摮���
	@Column(name = "inventory")
	private int inventory;

	// ��������
	@Column(name = "sale_count")
	@JsonProperty("sale_count")
	private int saleCount;

	// ����銝(boolean)
	@Column(name = "shelves")
	private boolean shelves;

	//�json 64base
	@Column(name = "photo", columnDefinition = "MEDIUMBLOB")  
	private byte[] photo;

	
	@Column(name = "user_id")
	@JsonProperty("user_id")
	private int userId;
	
	@Column(name = "upload_time")
	@JsonProperty("upload_time")
	private LocalDate uploadTime;
	
	@Column(name = "seller_name")
	@JsonProperty("seller_name")
	private String sellerName;
	

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	



	public Product(int productId, String productName, String productType, String description, int price, int inventory,
			int saleCount, boolean shelves, byte[] photo, int userId, LocalDate uploadTime, String sellerName) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productType = productType;
		this.description = description;
		this.price = price;
		this.inventory = inventory;
		this.saleCount = saleCount;
		this.shelves = shelves;
		this.photo = photo;
		this.userId = userId;
		this.uploadTime = uploadTime;
		this.sellerName = sellerName;
	}





	public String getSellerName() {
		return sellerName;
	}



	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}



	public Product(Product product, LocalDate currentDate) {
		super();
		this.productName = product.getProductName();
		this.productType = product.getProductType();
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



	public byte[] getPhoto() {
		return photo;
	}





	public void setPhoto(byte[] photo) {
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











	public String getProductType() {
		return productType;
	}











	public void setProductType(String productType) {
		this.productType = productType;
	}







	

}
