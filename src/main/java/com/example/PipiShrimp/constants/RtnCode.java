package com.example.PipiShrimp.constants; //constants�`�Ʊ`�q

//���~�T�����|�g�bRtnCode	
public enum RtnCode {

//	SUCCESSFUL�����\,HTTP���A�X,�D�n��200,400,401,403,404, 200�����\,�T�w��,�v����������401&403,404�T�w�N���䤣��,�ѤU�k����400
	SUCCESSFUL(200, "Successful!!"), //
	PARAM_ERROR(400, "Param error!!"), //
	FILE_ERROR(400, "Param error!!"), //

	PRODUCT_CREATE_FAILED(400, "Product create failed!!"), //
	PRODUCT_IS_EMPTY(400, "Product is empty!!"), //
	PRODUCT_NOT_FOUND(404, "Product not found!!"), //
	EMAIL_IS_EXIST(400, "Email is exist!!"), //
	PASSWORD_FORMAT_ERROR(400, "Password format error!!"), //
	EMAIL_FORMAT_ERROR(400, "Email format error!!"), //
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
<<<<<<< HEAD
	CART_DELETE_FAILED(400, "Cart delete failed!!"), //
	LOGIN_FIRST(400, "Login first!!"), //
<<<<<<< HEAD
=======
>>>>>>> ccb53b51ab44c1d0c7b76bcaa39d540e26c9b224
=======
	USER_IS_EMPTY(400, "User is empty!!"), //
	COMMENT_CREATE_FAILED(400, "Comment create failed!!"), //
	RECORD_DELETE_FAILED(400, "Record delete failed!!"), //
	COMMENT_ID_NOT_FOUND(400, "Comment id not found!!"), //
	COMMENT_IS_EMPTY(400, "Comment is empty!!"), //
	COMMENT_UPDATE_FAILED(400, "Comment update failed!!"), //
	USER_UPDATE_FAILED(400, "User update failed!!"), //
>>>>>>> ian
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
