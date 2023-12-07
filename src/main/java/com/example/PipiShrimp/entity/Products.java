package com.example.PipiShrimp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.mysql.cj.jdbc.Blob;

@Entity
@Table(name = "products")//產品
public class Products {

	@Column(name = "products_id") 
	private String productsId;
	
	@Column(name = "product_name") 
	private String  productName;
	
	@Column(name = "description") 
	private String  description;
	
	@Column(name = "price") 
	private String  price;
	
	@Column(name = "inventory") 
	private String  inventory;
	
	//Blob 存放二進位制資料的資料型態
	@Column(name = "photo") 
	private Blob   photo;

	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Products(String productsId, String productName, String description, String price, String inventory,
			Blob photo) {
		super();
		this.productsId = productsId;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.inventory = inventory;
		this.photo = photo;
	}

	public String getProductsId() {
		return productsId;
	}

	public void setProductsId(String productsId) {
		this.productsId = productsId;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getInventory() {
		return inventory;
	}

	public void setInventory(String inventory) {
		this.inventory = inventory;
	}

	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}
	
}
