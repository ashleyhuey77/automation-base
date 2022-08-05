package com.warnermedia.selenium.click;

import com.warnermedia.utils.ex.ErrorCode;
import com.warnermedia.utils.ex.SeleniumException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Commands;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;

import java.time.Duration;

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
