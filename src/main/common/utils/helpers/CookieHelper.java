package common.utils.helpers;

import java.util.Set;
import java.util.logging.Level;
import org.openqa.selenium.Cookie;
import common.utils.managers.CookieManager;
import common.utils.managers.LocalDriver;
import common.utils.managers.LocalTest;
import log.Log;
import log.TestException;

public final class CookieHelper {

	private CookieHelper() {

	}

	public static CookiesStep newHelper() {
		return new CookiesSteps();
	}

	public interface CookiesStep {
		GetCookiesStep getCookies() throws TestException;
	}

	public interface GetCookiesStep {
		BuildStep setCookies() throws TestException;
	}

	public interface BuildStep {
		void build();
	}

	private static class CookiesSteps implements CookiesStep, GetCookiesStep, BuildStep {

		private Set<Cookie> currentCookies;

		@Override
		public void build() {
			Log.get().log(Level.INFO, "Cookies have been managed successfully.");
		}

		@Override
		public GetCookiesStep getCookies() throws TestException {
			try {
				//if (!LocalTest.getEnvironment().getOS().equalsIgnoreCase("linux")) {
    				if (CookieManager.getCookies() != null) {
    					currentCookies = LocalDriver.getDriver().manage().getCookies();
    				}
				//}
			} catch (Exception e) {
				Log.get().log(Level.INFO, "An error occurred. Cookies were not created.");
			}
			return this;
		}

		@Override
		public BuildStep setCookies() throws TestException {
			try {
				//if (!LocalTest.getEnvironment().getOS().equalsIgnoreCase("linux")) {
    				if (CookieManager.getCookies() != null) {
    					if (currentCookies != null) {
    						for (Cookie cookie : CookieManager.getCookies()) {
    							if (!currentCookies.contains(cookie)) {
    								LocalDriver.getDriver().manage().addCookie(cookie);
    								Log.get().log(Level.INFO, "Added Cookie: {0}", cookie.getName() + " "
    										+ cookie.getValue() + " " + cookie.getDomain() + " " + cookie.getPath());
    							}
    						}
    					} else {
    						for (Cookie cookie : CookieManager.getCookies()) {
    							LocalDriver.getDriver().manage().addCookie(cookie);
    							Log.get().log(Level.INFO, "Added Cookie: {0}", cookie.getName() + " " + cookie.getValue()
    									+ " " + cookie.getDomain() + " " + cookie.getPath());
    						}
    					}
    				}
				//}
			} catch (Exception e) {
				Log.get().log(Level.INFO, "An error occurred. Cookies were not created.");
			}
			return this;
		}

	}

}
