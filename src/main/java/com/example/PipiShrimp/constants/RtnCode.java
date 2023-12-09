package com.example.PipiShrimp.constants; //constants�`�Ʊ`�q

//���~�T�����|�g�bRtnCode	
public enum RtnCode {

//	SUCCESSFUL�����\,HTTP���A�X,�D�n��200,400,401,403,404, 200�����\,�T�w��,�v����������401&403,404�T�w�N���䤣��,�ѤU�k����400
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
