package com.warnermedia.selenium.page;

import org.openqa.selenium.JavascriptExecutor;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.selenium.shared.Commands;

public class Page extends Commands implements IPage {

	@Override
	public void scrollTo(Location location) throws TestException {
		JavascriptExecutor js = ((JavascriptExecutor) LocalDriver.getDriver());
		String hLocationString = "0";
		String wLocationString = "0";
		switch (location) {
			case TOP_OF_PAGE:
				hLocationString = "-document.body.scrollHeight";
				break;
			case BOTTOM_OF_PAGE:
				hLocationString = "document.body.scrollHeight";
				break;
			case RIGHT_OF_PAGE:
				wLocationString = "document.body.scrollWidth";
				break;
			case LEFT_OF_PAGE:
				wLocationString = "-document.body.scrollWidth";
				break;
			default:
				throw new TestException("Did not provide an accepted location on the page.");
		}

		js.executeScript("window.scrollTo(" + wLocationString + ", " + hLocationString + ")");

	}

	@Override
	public void refresh() throws TestException {
		//LocalDriver.getDriver().navigate().refresh();
		checkCookiesAndAddRequiredOnesIfNecessary();
		JavascriptExecutor js = ((JavascriptExecutor) LocalDriver.getDriver());
		js.executeScript("location.reload(true)");
	}

}