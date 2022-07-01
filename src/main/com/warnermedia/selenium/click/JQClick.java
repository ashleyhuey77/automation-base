package com.warnermedia.selenium.click;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Commands;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;
import com.warnermedia.utils.StateManager;

import java.time.Duration;

public class JQClick extends Commands implements IClick {

	@Override
	public void on(TestElement element) throws TestException {
		String webElement = element.locator().value();
		try {
			if (StateManager.getState() != null) {
				StateManager.getState().checkState();
			}
			SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(20))).on(element);
			//checkCookiesAndAddRequiredOnesIfNecessary();
			((JavascriptExecutor) LocalDriver.getDriver()).executeScript("$('" + webElement + "').click();");
		} catch (Exception ex) {
			try {
				if (element.locator().value().contains("'")) {
					webElement = element.locator().value().replace("'", "");
				}
				//checkCookiesAndAddRequiredOnesIfNecessary();
				((JavascriptExecutor) LocalDriver.getDriver()).executeScript("$('" + webElement + "').click();");
			} catch (Exception e) {
				throw ex;
			}
		}
	}

	@Override
	public void on(WebElement element) throws TestException {
		if (StateManager.getState() != null) {
			StateManager.getState().checkState();
		}
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(20))).on(element);
		//checkCookiesAndAddRequiredOnesIfNecessary();
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("arguments[0].click();", element);
	}

	@Override
	public void on(TestElement element, String index) throws TestException {
		if (StateManager.getState() != null) {
			StateManager.getState().checkState();
		}
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(20))).on(element);
		//checkCookiesAndAddRequiredOnesIfNecessary();
		String webElement = null;
		if (element.locator().value().contains("'")) {
			webElement = element.locator().value().replace("'", "");
		} else {
			webElement = element.locator().value();
		}
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("$('" + webElement + "')[" + index + "].click();");
	}

	@Override
	public void on(TestElement element, int index) throws TestException {
		if (StateManager.getState() != null) {
			StateManager.getState().checkState();
		}
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(20))).on(element);
		//checkCookiesAndAddRequiredOnesIfNecessary();
		String webElement = element.locator().value();
		if (element.locator().value().contains("'")) {
			webElement = element.locator().value().replace("'", "");
		}

		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("$('" + webElement + "')[" + Integer.toString(index) + "].click();");
	}

}