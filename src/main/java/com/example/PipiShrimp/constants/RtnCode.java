package com.example.PipiShrimp.constants; //constants常數常量

//錯誤訊息都會寫在RtnCode	
public enum RtnCode {

//	SUCCESSFUL為成功,HTTP狀態碼,主要用200,400,401,403,404, 200為成功,固定的,權限有相關的401&403,404固定就為找不到,剩下歸類到400
	SUCCESSFUL(200, "Successful!!"), //
	PARAM_ERROR(400, "Param error!!"), //
	PRODUCT_CREATE_FAILED(400, "Product create failed!!"),//
	PRODUCT_IS_EMPTY(400, "Product is empty!!"),//
	PRODUCT_NOT_FOUND(404, "Product not found!!"),//
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
