package com.example.PipiShrimp.constants; //constants�`�Ʊ`�q

//���~�T�����|�g�bRtnCode	
public enum RtnCode { 
	
//	SUCCESSFUL�����\,HTTP���A�X,�D�n��200,400,401,403,404, 200�����\,�T�w��,�v����������401&403,404�T�w�N���䤣��,�ѤU�k����400
	SUCCESSFUL(200,"Successful!!"),//  			
	PARAM_ERROR(400,"Param error!!"),//  
	ID_HAS_EXISTED(400,"ID Has Existed!!"),//
	DEPARTMENTS_NOT_FOUND(404,"DEPARTMENTS NOT FOUND!!"),//
	ID_NOT_FOUND(404,"ID Not Found!!"),//
	PASSWORD_ERROR(400,"Password Error!!"),//
	EMPLOYEE_CREATE_ERROR(400,"EMPLOYEE CREATE ERROR!!"),//
	PLEASE_LOGIN_FIRST(400,"Please Login First!!"),// �Х��n�J
	UNAUTHORIZATED(401,"Unauthorizted!!"),// �v������
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
