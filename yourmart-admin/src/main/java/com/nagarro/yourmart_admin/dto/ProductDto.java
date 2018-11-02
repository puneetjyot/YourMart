package com.nagarro.yourmart_admin.dto;

import java.util.List;

public class ProductDto {

	int status;
	List<ProductResponseDto> data;
	String message;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<ProductResponseDto> getData() {
		return data;
	}
	public void setData(List<ProductResponseDto> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
