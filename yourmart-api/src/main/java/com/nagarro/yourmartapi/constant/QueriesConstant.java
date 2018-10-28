package com.nagarro.yourmartapi.constant;

public class QueriesConstant {
	
	
	
private static final String TABLE="com.nagarro.yourmartapi.models.Admin";
	
	public static final String SELECT_ALL_ADMINS="SELECT admin.id,admin.username,admin.password FROM "+TABLE+" as admin where admin.username=:username "
													+ "and admin.password=:password"; 
}
