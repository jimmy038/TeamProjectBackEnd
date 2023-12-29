package com.example.PipiShrimp.constants; //constants常數常量

//錯誤訊息都會寫在RtnCode	
public enum RtnCode {

//	SUCCESSFUL為成功,HTTP狀態碼,主要用200,400,401,403,404, 200為成功,固定的,權限有相關的401&403,404固定就為找不到,剩下歸類到400
	SUCCESSFUL(200, "Successful!!"), //
	PARAM_ERROR(400, "Param error!!"), //
	FILE_ERROR(400, "Param error!!"), //
	FOUND_TO_CHANGE_PASSWORD_PAGE(302,"Found to change_password_page"),//告知前端導向更改密碼頁面;
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
	USER_IS_EMPTY(400, "Use is empty!!"), //
	RECORD_DELETE_FAILED(400, "Record delete failed!!"), //
	USER_UPDATE_FAILED(400, "user update failed!!"), //
	RECORD_CAN_NOT_CANCEL(400, "Record can not cancel!!"), //
	RECORD_IS_CANCELED(400, "Record is canceled!!"), //
	RECORD_SHIPPING_FAILED(400, "Record shipping failed!!"), //
	RECORD_COMPLETED_FAILED(400, "Record completed failed!!"), //
	PRODUCT_IS_SHORTAGE(400, "Product is shortage!!"), //
	COMMENT_IS_EMPTY(400, "Comment is empty!!"), //
	COMMENT_ID_NOT_FOUND(400, "Comment is not found!!"), //
	COMMENT_UPDATE_FAILED(400, "Comment update failed!!"), //
	COMMENT_CREATE_FAILED(400, "Comment create failed!!"), //
	FORGOT_PASSWORD_ERROR(400, "Forgot password error!!"), //
	OLD_PASSWORD_AND_NEW_PASSWORD_ARE_IDENITCAL(400, "Old password and new password aare identitcal!!"), //
	CHANGE_PASSWORD_ERROR(400, "Change password error!!"), //

	
	
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
