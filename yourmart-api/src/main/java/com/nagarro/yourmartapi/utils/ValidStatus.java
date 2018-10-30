package com.nagarro.yourmartapi.utils;

public class ValidStatus 
{
	public static boolean isValidStatus(String status) {
		
		if(status.equals("REJECTED")||status.equals("APPROVED")||status.equals("NEED_APPROVAL")) {
			return true;
		}
		
		return false;
	}
}
