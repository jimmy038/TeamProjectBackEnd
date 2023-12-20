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
@Table(name = "record")
public class Record {

	// �q��id(���Wint AI)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "record_id")
	@JsonProperty("record_id")
	private int recordId;

	// �ʶR�B��ӫ~���
	@Column(name = "record_date")
	@JsonProperty("record_date")
	private LocalDateTime recordDate;

	// �ӫ~(�R�B��)�ƶq
	@Column(name = "product_count")
	@JsonProperty("product_count")
	private int productCount;

	// �ӫ~(�`)���B
	@Column(name = "product_amount")
	@JsonProperty("product_amount")
	private int productAmount;

	// �q�檬�A
	@Column(name = "status")
	private String status;

	// ���~�W��
	@Column(name = "product_name")
	@JsonProperty("product_name")
	private String productName;

	// �q������(�R�B��)
	@Column(name = "record_type")
	@JsonProperty("record_type")
	private String recordType;

	// ���O�����O�_����(�i�������->valid��false)
	@Column(name = "valid")
	private boolean valid;

	// ����user��ƪ�ID
	@Column(name = "user_id")
	@JsonProperty("user_id")
	private int userId;

	// ����product��ƪ�ID
	@Column(name = "product_id")
	@JsonProperty("product_id")
	private int productId;

	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Record(LocalDateTime recordDate, int productCount, int productAmount, String status, String productName,
			String recordType, boolean valid, int userId, int productId) {
		super();
		this.recordDate = recordDate;
		this.productCount = productCount;
		this.productAmount = productAmount;
		this.status = status;
		this.productName = productName;
		this.recordType = recordType;
		this.valid = valid;
		this.userId = userId;
		this.productId = productId;
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
