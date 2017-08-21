package commonClasses.sharedPageClasses;

public class BaseGeneric {
	
	/** cssSelector */
	public static String usrNameTxtField = "#credentials-username";
	/** cssSelector */
	public static String logInBox = "div[class='qrk-topbar modal fade in']";
	/** cssSelector */
	public static String pwdTxtField = "#credentials-password";
	/** cssSelector */ public static String signInBtn = "#topbarLoggedoutBox > div > div > div.qrk-topbar.modal-footer > button";
	/** id */ public static String errorMessage = "credentials-loginError";
	/** xpath */ public static String errorText = "//*[@id='credentials-loginError']/text()[1]";
	/** cssSelector */ public static String dashboardLoadElement = "div[class='dashboard-app']";

}
