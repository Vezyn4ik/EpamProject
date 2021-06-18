package company;

/**
 * Path holder (jsp pages, controller commands).
 * 
 */
public final class Path {
	
	// pages
	public static final String PAGE_LOGIN = "/login.jsp";
	public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
	public static final String TO_REGISTER = "/WEB-INF/jsp/register.jsp";
	public static final String ACCOUNT = "/WEB-INF/jsp/profile.jsp";
	public static final String PAYMENTS="/WEB-INF/jsp/payments.jsp";
	public static final String TO_MAKE_PAYMENT="/WEB-INF/jsp/make_payment.jsp";
	public static final String USER_DATA="/WEB-INF/jsp/admin/user_data.jsp";
	public static final String USERS_LIST ="/WEB-INF/jsp/admin/list_users.jsp";
	public static final String APPLICATIONS ="/WEB-INF/jsp/admin/unblocking_applications.jsp";
	// commands
	public static final String COMMAND_USER_ACCOUNT = "/controller?command=userAccount";

}