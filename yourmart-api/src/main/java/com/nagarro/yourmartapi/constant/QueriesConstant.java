package com.nagarro.yourmartapi.constant;

public class QueriesConstant {

	private static final String ADMINTABLE = "com.nagarro.yourmartapi.models.Admin";

	public static final String SELECT_ALL_ADMINS = "SELECT admin.id,admin.username,admin.password FROM " + ADMINTABLE
			+ " as admin where admin.username=:username " + "and admin.password=:password";

	public static final String NEED_APPROVAL="NEED_APPROVAL";
	public static final int SUCCESS=200;
	public static final int SERVER_ERROR=500;
	public static final String SERVER_ERROR_MESSAGE="Server Error 500";
	public static final int PASSWORD_MATCH_ERROR_CODE=403;

	public static final String PASSWORD_NOT_MATCH="PASSWORD DOESN'T MATCH";


	


}
