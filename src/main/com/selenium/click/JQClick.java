package com.selenium.click;

import com.config.TestException;
import com.config.setup.browser.LocalDriver;
import com.selenium.TestElement;
import com.selenium.shared.Commands;
import com.utils.observers.StateManager;
import com.utils.ex.ErrorCode;
import com.utils.ex.SeleniumException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

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