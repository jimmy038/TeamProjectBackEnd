package com.example.PipiShrimp.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "record")
public class Record {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "record_id")
	@JsonProperty("record_id")
	private int recordId;

	// 對應user資料表ID ↓
	@Column(name = "user_id")
	@JsonProperty("user_id")
	private int userId;

	// 對應product資料表ID
	@Column(name = "product_id")
	@JsonProperty("product_id")
	private int productId;

	@Column(name = "product_name")
	@JsonProperty("product_name")
	private String productName;

	// 商品(買、賣)數量
	@Column(name = "product_count")
	@JsonProperty("product_count")
	private int productCount;

	@Column(name = "consumer_name")
	@JsonProperty("consumer_name")
	private String consumerName;

	@Column(name = "consumer_address")
	@JsonProperty("consumer_address")
	private String consumerAddress;

	@Column(name = "consumer_phone")
	@JsonProperty("consumer_phone")
	private String consumerPhone;

	@Column(name = "shipping_method")
	@JsonProperty("shipping_method")
	private String shippingMethod;

	@Column(name = "shipping_cost")
	@JsonProperty("shipping_cost")
	private int shippingCost;

	@Column(name = "payment_method")
	@JsonProperty("payment_method")
	private String paymentMethod;

	@Column(name = "remittance_title")
	@JsonProperty("remittance_title")
	private String remittanceTitle;

	@Column(name = "remittance_number")
	@JsonProperty("remittance_number")
	private String remittanceNumber;

	@Column(name = "remarks_column")
	@JsonProperty("remarks_column")
	private String remarksColumn;

	// 商品(總)金額
	@Column(name = "product_amount")
	@JsonProperty("product_amount")
	private int productAmount;
	// 購買、賣商品日期
	@Column(name = "record_date")
	@JsonProperty("record_date")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime recordDate;

	// 訂單狀態
	@Column(name = "status")
	private String status;

	// 訂單類型(買、賣)
	@Column(name = "record_type")
	@JsonProperty("record_type")
	private String recordType;

	// 消費紀錄是否有效(可取消交易->valid為false)
	@Column(name = "valid")
	private boolean valid;
	
	@Column(name = "seller_id")
	@JsonProperty("seller_id")
	private int sellerId;
	
	@Column(name = "seller_name")
	@JsonProperty("seller_name")
	private String sellerName;

	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	


	public Record(int recordId, int userId, int productId, String productName, int productCount, String consumerName,
			String consumerAddress, String consumerPhone, String shippingMethod, int shippingCost, String paymentMethod,
			String remittanceTitle, String remittanceNumber, String remarksColumn, int productAmount,
			LocalDateTime recordDate, String status, String recordType, boolean valid, int sellerId,
			String sellerName) {
		super();
		this.recordId = recordId;
		this.userId = userId;
		this.productId = productId;
		this.productName = productName;
		this.productCount = productCount;
		this.consumerName = consumerName;
		this.consumerAddress = consumerAddress;
		this.consumerPhone = consumerPhone;
		this.shippingMethod = shippingMethod;
		this.shippingCost = shippingCost;
		this.paymentMethod = paymentMethod;
		this.remittanceTitle = remittanceTitle;
		this.remittanceNumber = remittanceNumber;
		this.remarksColumn = remarksColumn;
		this.productAmount = productAmount;
		this.recordDate = recordDate;
		this.status = status;
		this.recordType = recordType;
		this.valid = valid;
		this.sellerId = sellerId;
		this.sellerName = sellerName;
	}






	public int getSellerId() {
		return sellerId;
	}






	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}






	public String getSellerName() {
		return sellerName;
	}






	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}






	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getRemarksColumn() {
		return remarksColumn;
	}

	public void setRemarksColumn(String remarksColumn) {
		this.remarksColumn = remarksColumn;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getConsumerAddress() {
		return consumerAddress;
	}

	public void setConsumerAddress(String consumerAddress) {
		this.consumerAddress = consumerAddress;
	}

	public String getConsumerPhone() {
		return consumerPhone;
	}

	public void setConsumerPhone(String consumerPhone) {
		this.consumerPhone = consumerPhone;
	}

	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	

	public int getShippingCost() {
		return shippingCost;
	}



	public void setShippingCost(int shippingCost) {
		this.shippingCost = shippingCost;
	}



	public String getRemittanceTitle() {
		return remittanceTitle;
	}

	public void setRemittanceTitle(String remittanceTitle) {
		this.remittanceTitle = remittanceTitle;
	}

	public String getRemittanceNumber() {
		return remittanceNumber;
	}

	public void setRemittanceNumber(String remittanceNumber) {
		this.remittanceNumber = remittanceNumber;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public LocalDateTime getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(LocalDateTime recordDate) {
		this.recordDate = recordDate;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
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

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
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
