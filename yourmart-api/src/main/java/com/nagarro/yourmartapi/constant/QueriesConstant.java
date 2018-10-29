package com.nagarro.yourmartapi.constant;

public class QueriesConstant {

	private static final String ADMINTABLE = "com.nagarro.yourmartapi.models.Admin";

	public static final String SELECT_ALL_ADMINS = "SELECT admin.id,admin.username,admin.password FROM " + ADMINTABLE
			+ " as admin where admin.username=:username " + "and admin.password=:password";

	public static final String NEED_APPROVAL="NEED_APPROVAL";
	public static final String REJECTED="REJECTED";
	public static final String APPROVED="APPROVED";


	public static final int SUCCESS=200;
	public static final int SERVER_ERROR=500;
	public static final int UNAUTHORISED_CODE=403;
	public static final String UNAUTHORISED_MESSAGE="REQUEST IS PENDING. NEEDS APPROVAL!!";
	public static final String REJECTED_MESSAGE="REJECTED USER";

	public static final String SERVER_ERROR_MESSAGE="SERVER ERROR 500";
	public static final int PASSWORD_MATCH_ERROR_CODE=403;
	public static final String UNAUTHENTICATED_MESSAGE="UNAUTHENTICATED USER";
	public static final int UNAUTHENTICATED_CODE=401;


	public static final String PASSWORD_NOT_MATCH="PASSWORD DOESN'T MATCH";

	private static final String SELLERTABLE = "com.nagarro.yourmartapi.models.Seller";

	public static final String LOGIN_SELLER = "SELECT seller.id,seller.sellername,seller.sellerstatus FROM " + SELLERTABLE
			+ " as seller where seller.sellername=:username " + "and seller.sellerpassword=:password";


	


}
