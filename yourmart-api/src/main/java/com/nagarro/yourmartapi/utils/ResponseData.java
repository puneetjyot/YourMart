package com.nagarro.yourmartapi.utils;

public class ResponseData {

	private int id;
	private String username;
	private String token;
	
	public ResponseData(int id,String username,String token) {

		this.id=id;
		this.username=username;
		this.token=token;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getToken() {
		return token;
	}
	
}
