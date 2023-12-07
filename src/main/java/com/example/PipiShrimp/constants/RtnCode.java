package com.example.PipiShrimp.constants; //constants常數常量

//錯誤訊息都會寫在RtnCode	
public enum RtnCode { 
	
//	SUCCESSFUL為成功,HTTP狀態碼,主要用200,400,401,403,404, 200為成功,固定的,權限有相關的401&403,404固定就為找不到,剩下歸類到400
	SUCCESSFUL(200,"Successful!!"),//  			
	PARAM_ERROR(400,"Param error!!"),//  
	ID_HAS_EXISTED(400,"ID Has Existed!!"),//
	DEPARTMENTS_NOT_FOUND(404,"DEPARTMENTS NOT FOUND!!"),//
	ID_NOT_FOUND(404,"ID Not Found!!"),//
	PASSWORD_ERROR(400,"Password Error!!"),//
	EMPLOYEE_CREATE_ERROR(400,"EMPLOYEE CREATE ERROR!!"),//
	PLEASE_LOGIN_FIRST(400,"Please Login First!!"),// 請先登入
	UNAUTHORIZATED(401,"Unauthorizted!!"),// 權限不足
	CHANGE_PASSWORD_ERROR(400,"Change Password Error!!"),//
	OLD_PASSWORD_AND_NEW_PASSWORD_ARE_IDENITCAL(400,"Old Password And New Password Are Idenitcal"),//
	FORGOT_PASSWORD_ERROR(400,"Forgot Password Error!!"),//
	AUTH_CODE_NOT_MATCHED(400,"Auth Code Not Matched!!"),//
	AUTH_CODE_EXPIRED(400,"Auth Code Auth Code Expired!!"),//
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
