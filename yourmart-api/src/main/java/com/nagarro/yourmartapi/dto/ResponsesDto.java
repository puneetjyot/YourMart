package com.nagarro.yourmartapi.dto;

import com.nagarro.yourmartapi.utils.ResponseData;

public class ResponsesDto {
private int status;
	
	private ResponseData data;
	
	private String message;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ResponseData getData() {
		return data;
	}

	public void setData(ResponseData data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
