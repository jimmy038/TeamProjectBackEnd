package com.example.PipiShrimp.constants; //constants常數常量

//錯誤訊息都會寫在RtnCode	
public enum RtnCode {

//	SUCCESSFUL為成功,HTTP狀態碼,主要用200,400,401,403,404, 200為成功,固定的,權限有相關的401&403,404固定就為找不到,剩下歸類到400
	SUCCESSFUL(200, "Successful!!"), //
	PARAM_ERROR(400, "Param error!!"), //
	PRODUCT_CREATE_FAILED(400, "Product create failed!!"), //
	PRODUCT_IS_EMPTY(400, "Product is empty!!"), //
	PRODUCT_NOT_FOUND(404, "Product not found!!"), //
	EMAIL_IS_EXIST(400, "Email is exist!!"),//
	PASSWORD_FORMAT_ERROR(400, "Password format error!!"),//
	EMAIL_FORMAT_ERROR(400, "Email format error!!"),//
	USER_CREATE_FAILED(400, "User create failed!!"), //
	EMAIL_NOT_FOUND(400, "Email not found!!"), //
	PASSWORD_ERROR(400, "Password error!!"), //
	DATABASE_IS_EMPTY(400, "Database is empty!!"), //
	SENT_EMAIL_FAILED(400, "Sent email failed!!"), //
	PRODUCT_ID_NOT_FOUND(400, "Product id not found!!"), //
	PRODUCT_DELETE_FAILED(400, "Product delete failed!!"), //
	RECORD_CREATE_FAILED(400, "Record create failed!!"), //
	RECORD_ID_NOT_FOUND(400, "Record id not found!!"), //
	RECORD_IS_EMPTY(400, "Record is empty!!"), //
	RECORD_CANCEL_FAILED(400, "Record cancel failed!!"), //
	USER_ID_NOT_FOUND(400, "User id not found!!"), //
	CART_CREATE_FAILED(400, "Cart create failed!!"), //
	CART_ID_NOT_FOUND(400, "Cart id not found!!"), //
	CART_IS_EMPTY(400, "Cart is empty!!"), //
	FORGOT_PASSWORD_ERROR(400, "Forgot Password error!!"), // 
	OLD_PASSWORD_AND_NEW_PASSWORD_ARE_IDENITCAL(400, "Old Password And New Password Are Idenitcal!!"),//
	CHANGE_PASSWORD_ERROR(400,"Change Password Error!!"),//
	PLEASE_LOGIN_FIRST(400,"Please login first"),//
	;

	private int code;

	private String message;

	private RtnCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
