package com.nagarro.yourmart_admin.dto;

import java.util.List;

public class CategoryResponseDto {

	int status;
	List<String> data;
	String message;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<String> getData() {
		return data;
	}
	public void setData(List<String> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
