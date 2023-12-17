package com.example.PipiShrimp.entity;

import java.time.LocalDate;
<<<<<<< HEAD
=======
import java.time.LocalDateTime;
>>>>>>> kamishu

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

	// 訂單id(產品int AI)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	@JsonProperty("product_id")
	private int productId;

	// 產品名稱
	@Column(name = "product_name")
	@JsonProperty("product_name")
	private String productName;

	// 產品描述
	@Column(name = "description")
	private String description;

	// 產品上架日期
	@Column(name = "release_date")
	@JsonProperty("release_date")
	private LocalDate releaseDate;

	// 產品價格
	@Column(name = "price")
	private int price;

	// ������摨怠����賊��
	@Column(name = "inventory")
	private int inventory;

	// ��������瑕�格�賊��
	@Column(name = "sale_count")
	@JsonProperty("sale_count")
	private int saleCount;

<<<<<<< HEAD
	// 商品規格(例如:服裝S、M、L)
	@Column(name = "specification")
	private String specification;

	// 商品類型
	@Column(name = "product_type")
	@JsonProperty("product_type")
	private String productType;

	// 商品是否上架(boolean)
	@Column(name = "shelves")
	private boolean shelves;

	// 用路徑存放圖片
	@Column(name = "photo")
	private String photo;

	// 對應user_id(外部鍵)
=======
	// ��������臬�虫�����(boolean)
	@Column(name = "shelves")
	private boolean shelves;

	//��甫son 64base
	@Column(name = "photo", columnDefinition = "LONGTEXT")  // 雿輻�冽迤蝖桃�����摰�銋�嚗�靘�憒� "LONGTEXT"嚗���寞�桀����������菟�����
	@Lob
	private String photo;

	
>>>>>>> kamishu
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

<<<<<<< HEAD
	// 沒有id、規格
	public Product(String productName, String description, LocalDate releaseDate, int price, int inventory,
			int saleCount, String productType, boolean shelves, String photo, int userId) {
=======
	
	

	
	public Product(int productId, String productName, String description, int price, int inventory, int saleCount,
			boolean shelves, String photo, int userId, LocalDate currentDate) {
>>>>>>> kamishu
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.releaseDate = releaseDate;
		this.price = price;
		this.inventory = inventory;
		this.saleCount = saleCount;
		this.productType = productType;
		this.shelves = shelves;
		this.photo = photo;
		this.userId = userId;
		this.uploadTime = currentDate;
	}

<<<<<<< HEAD
	// 沒有規格(用來更新商品資訊)
	public Product(int productId, String productName, String description, LocalDate releaseDate, int price,
			int inventory, int saleCount, String productType, boolean shelves, String photo, int userId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.releaseDate = releaseDate;
		this.price = price;
		this.inventory = inventory;
		this.saleCount = saleCount;
		this.productType = productType;
		this.shelves = shelves;
		this.photo = photo;
		this.userId = userId;
	}

	// 全部都有
	public Product(int productId, String productName, String description, LocalDate releaseDate, int price,
			int inventory, int saleCount, String specification, String productType, boolean shelves, String photo,
			int userId) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.releaseDate = releaseDate;
		this.price = price;
		this.inventory = inventory;
		this.saleCount = saleCount;
		this.specification = specification;
		this.productType = productType;
		this.shelves = shelves;
		this.photo = photo;
		this.userId = userId;
=======




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

>>>>>>> kamishu
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





	public LocalDate getUploadTime() {
		return uploadTime;
	}





	public void setUploadTime(LocalDate uploadTime) {
		this.uploadTime = uploadTime;
	}







	

}
