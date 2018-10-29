package com.nagarro.yourmartapi.dto;

public class SellerStatusDto 
{
private String status;
private int id;
private String token;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

}
