package common.base;

public class BaseGeneric {
	
	private BaseGeneric() {
		throw new IllegalStateException("Base Generic class");
	}
	
	/** cssSelector */
	public static final String USER_NAME_TEXT_FIELD = "#credentials-username";
	/** cssSelector */
	public static final String LOG_IN_BOX = "div[class='qrk-topbar modal fade in']";
	/** cssSelector */
	public static final String PWD_TEXT_FIELD = "#credentials-password";
	/** cssSelector */ public static final String SIGN_IN_BTN = "#topbarLoggedoutBox > div > div > div.qrk-topbar.modal-footer > button";
	/** id */ public static final String ERROR_MSG = "credentials-loginError";
	/** xpath */ public static final String ERROR_TEXT = "//*[@id='credentials-loginError']/text()[1]";
	/** cssSelector */ public static final String DASHBOARD_LOAD_ELEMENT = "div[class='dashboard-app']";

}
