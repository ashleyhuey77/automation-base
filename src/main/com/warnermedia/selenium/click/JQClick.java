package com.warnermedia.selenium.click;

import com.warnermedia.utils.ex.ErrorCode;
import com.warnermedia.utils.ex.SeleniumException;
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
import org.testng.annotations.Test;

import java.time.Duration;

public class JQClick extends Commands implements IClick {

	@Override
	public void on(TestElement element) throws TestException {
		if (StateManager.getState() != null) {
			StateManager.getState().checkState();
		}
		String webElement = element.locator().value();
		try {
			((JavascriptExecutor) LocalDriver.getDriver()).executeScript("$('" + webElement + "').click();");
		} catch (Exception ex) {
			try {
				if (element.locator().value().contains("'")) {
					webElement = element.locator().value().replace("'", "");
				}
				try {
					((JavascriptExecutor) LocalDriver.getDriver()).executeScript("$('" + webElement + "').click();");
				} catch (Exception e) {
					throw new SeleniumException("A javascript error occurred when trying to click on element " + webElement + " via jquery.", ErrorCode.CLICK);
				}
			} catch (TestException e) {
				throw ex;
			}
		}
	}

	@Override
	public void on(WebElement element) throws TestException {
		if (StateManager.getState() != null) {
			StateManager.getState().checkState();
		}
		try {
			((JavascriptExecutor) LocalDriver.getDriver()).executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			throw new SeleniumException("A javascript error occurred when trying to click on the element via jquery.", ErrorCode.CLICK);
		}
	}

	@Override
	public void on(TestElement element, String index) throws TestException {
		if (StateManager.getState() != null) {
			StateManager.getState().checkState();
		}
		String webElement = null;
		if (element.locator().value().contains("'")) {
			webElement = element.locator().value().replace("'", "");
		} else {
			webElement = element.locator().value();
		}
		try {
			((JavascriptExecutor) LocalDriver.getDriver()).executeScript("$('" + webElement + "')[" + index + "].click();");
		} catch (Exception e) {
			throw new SeleniumException("A javascript error occurred when trying to click on element " + webElement + " via jquery.", ErrorCode.CLICK);
		}
	}

	@Override
	public void on(TestElement element, int index) throws TestException {
		if (StateManager.getState() != null) {
			StateManager.getState().checkState();
		}

		String webElement = element.locator().value();
		if (element.locator().value().contains("'")) {
			webElement = element.locator().value().replace("'", "");
		}
		try {
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("$('" + webElement + "')[" + Integer.toString(index) + "].click();");
		} catch (Exception e) {
			throw new SeleniumException("A javascript error occurred when trying to click on element " + webElement + " via jquery.", ErrorCode.CLICK);
		}
	}

}