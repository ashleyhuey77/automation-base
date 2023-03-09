package com.selenium.click;

import com.config.TestException;
import com.config.setup.browser.LocalDriver;
import com.selenium.TestElement;
import com.selenium.shared.Commands;
import com.utils.ex.ErrorCode;
import com.utils.ex.SeleniumException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class RightClick extends Commands implements IClick {

	@Override
	public void on(TestElement element) throws TestException {
		try {
			Actions action = new Actions(LocalDriver.getDriver());
			action.contextClick(getElement(element)).build().perform();
		} catch (Exception e) {
			throw new SeleniumException("An error occurred when trying to right click on the element.", ErrorCode.CLICK);
		}
	}

	@Override
	public void on(WebElement element) throws TestException {
		try {
			Actions action = new Actions(LocalDriver.getDriver());
			action.contextClick(element).build().perform();
		} catch (Exception e) {
			throw new SeleniumException("An error occurred when trying to right click on the element.", ErrorCode.CLICK);
		}
	}

	@Override
	public void on(TestElement element, String index) throws TestException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void on(TestElement element, int index) throws TestException {
		throw new UnsupportedOperationException();
	}

}
