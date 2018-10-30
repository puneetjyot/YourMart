package com.nagarro.yourmartapi.exceptionhandling;

public class SellerStatusInvalidExceptions extends Throwable

{
	String message;
	public SellerStatusInvalidExceptions() {
	
		
	}
	public String getMessage(String message) {
		this.message=message;
		return message;
	}
}
