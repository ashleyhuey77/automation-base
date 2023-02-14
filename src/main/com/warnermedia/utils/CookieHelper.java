package com.warnermedia.utils;

import java.util.Set;
import java.util.logging.Level;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Cookie;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;

@Slf4j
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
			log.info("{}{}Cookies have been managed successfully.{}", ConsoleDecoration.CYAN_TEXT.value, ConsoleDecoration.BLACK_BACKGROUND.value, ConsoleDecoration.RESET.value);
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
				log.info("{}{}An error occurred. Cookies were not created.{}", ConsoleDecoration.RED_TEXT.value, ConsoleDecoration.BLACK_BACKGROUND.value, ConsoleDecoration.RESET.value);
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
    								log.info("{}{}Added Cookie: {} {} {} {}{}",
											ConsoleDecoration.CYAN_TEXT.value, ConsoleDecoration.BLACK_BACKGROUND.value,
											cookie.getName(), cookie.getValue(), cookie.getDomain(), cookie.getPath(),
											ConsoleDecoration.RESET.value);
    							}
    						}
    					} else {
    						for (Cookie cookie : CookieManager.getCookies()) {
    							LocalDriver.getDriver().manage().addCookie(cookie);
								log.info("{}{}Added Cookie: {} {} {} {}{}",
										ConsoleDecoration.CYAN_TEXT.value, ConsoleDecoration.BLACK_BACKGROUND.value,
										cookie.getName(), cookie.getValue(), cookie.getDomain(), cookie.getPath(),
										ConsoleDecoration.RESET.value);
    						}
    					}
    				}
				//}
			} catch (Exception e) {
				log.info("{}{}An error occurred. Cookies were not created.{}",
						ConsoleDecoration.RED_TEXT.value, ConsoleDecoration.BLACK_BACKGROUND.value, ConsoleDecoration.RESET.value);
			}
			return this;
		}

	}

}
