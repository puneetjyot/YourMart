package com.nagarro.yourmart_admin.dto;

public class SingleProductResponseDto {

	int Status;
	ProductResponseDto data;
	String message;
	
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public ProductResponseDto getData() {
		return data;
	}
	public void setData(ProductResponseDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
