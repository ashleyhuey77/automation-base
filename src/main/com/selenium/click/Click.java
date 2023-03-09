package com.selenium.click;

import java.util.List;

import com.config.TestException;
import com.selenium.TestElement;
import com.selenium.shared.Commands;
import com.utils.observers.StateManager;
import com.utils.ex.ErrorCode;
import com.utils.ex.SeleniumException;
import org.openqa.selenium.WebElement;

public class Click extends Commands implements IClick {

	@Override
	public void on(TestElement element) throws TestException {
		if (StateManager.getState() != null) {
			StateManager.getState().checkState();
		}
		try {
			getElement(element).click();
		} catch (TestException e) {
			if (e.getMessage().contains("Error Code 5:")) {
				throw e;
			} else {
				throw new SeleniumException("The element was found on the page, but something might have happened while attempting to click. Like the page refreshed. Or an unexpected pop up appeared. Check to see what is interfering with the click.", ErrorCode.CLICK);
			}
		}
	}

	@Override
	public void on(WebElement element) throws TestException {
		if (StateManager.getState() != null) {
			StateManager.getState().checkState();
		}
		try {
			element.click();
		} catch (Exception e) {
			throw new SeleniumException("The element was found on the page, but it's not an element that can be clicked on.", ErrorCode.CLICK);
		}
	}

	@Override
	public void on(TestElement element, String index) throws TestException {
		if (StateManager.getState() != null) {
			StateManager.getState().checkState();
		}
		try {
			List<WebElement> element1 = getElements(element);
			element1.get(Integer.parseInt(index)).click();
		} catch (TestException e) {
			if (e.getMessage().contains("Error Code 5:")) {
				throw e;
			} else {
				throw new SeleniumException("The elements were found on the page, but it's not an element that can be clicked on.", ErrorCode.CLICK);
			}
		}
	}

	@Override
	public void on(TestElement element, int index) throws TestException {
		if (StateManager.getState() != null) {
			StateManager.getState().checkState();
		}
		try {
			List<WebElement> element1 = getElements(element);
			element1.get(index).click();
		} catch (TestException e) {
			if (e.getMessage().contains("Error Code 5:")) {
				throw e;
			} else {
				throw new SeleniumException("The elements were found on the page, but it's not an element that can be clicked on.", ErrorCode.CLICK);
			}
		}
	}

}