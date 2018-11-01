package com.nagarro.yourmart_admin.dto;

import java.util.List;

public class SellerDto {

	int status;
	List<SellerDetailsDto> data;
	String message;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<SellerDetailsDto> getData() {
		return data;
	}
	public void setData(List<SellerDetailsDto> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
