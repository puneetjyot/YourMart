package com.nagarro.yourmart_admin.dto;

public class AdminResponseDto {

	int status;
	AdminResponseDataDto data;
	String message;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public AdminResponseDataDto getData() {
		return data;
	}
	public void setData(AdminResponseDataDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
