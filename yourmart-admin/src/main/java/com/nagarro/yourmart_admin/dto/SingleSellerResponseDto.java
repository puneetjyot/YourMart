package com.nagarro.yourmart_admin.dto;

public class SingleSellerResponseDto {

	int id;
	SellerDetailsDto data;
	String message;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SellerDetailsDto getData() {
		return data;
	}
	public void setData(SellerDetailsDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
