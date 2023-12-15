package com.example.PipiShrimp.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "product")
public class Product {

	// �q��id(���~int AI)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	@JsonProperty("product_id")
	private int productId;

	// ���~�W��
	@Column(name = "product_name")
	@JsonProperty("product_name")
	private String productName;

	// ���~�y�z
	@Column(name = "description")
	private String description;

	// ���~�W�[���
	@Column(name = "release_date")
	@JsonProperty("release_date")
	private LocalDate releaseDate;

	// ���~����
	@Column(name = "price")
	private int price;

	// �ӫ~�w�s�ƶq
	@Column(name = "inventory")
	private int inventory;

	// �ӫ~�P��ƶq
	@Column(name = "sale_count")
	@JsonProperty("sale_count")
	private int saleCount;

	// �ӫ~�O�_�W�[(boolean)
	@Column(name = "shelves")
	private boolean shelves;

	// �θ��|�s��Ϥ�
	@Column(name = "photo")
	private String photo;

	// ����user_id(�~����)
	@Column(name = "user_id")
	@JsonProperty("user_id")
	private int userId;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String productName, String description, LocalDate releaseDate, int price, int inventory,
			int saleCount, boolean shelves, String photo, int userId) {
		super();
		this.productName = productName;
		this.description = description;
		this.releaseDate = releaseDate;
		this.price = price;
		this.inventory = inventory;
		this.saleCount = saleCount;
		this.shelves = shelves;
		this.photo = photo;
		this.userId = userId;
	}

	public Product(Product product) {
		super();
		this.productName = product.getProductName();
		this.description = product.getDescription();
		this.releaseDate = product.getReleaseDate();
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

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
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

}
