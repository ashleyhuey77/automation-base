package com.warnermedia.selenium.click;

import java.util.List;
import com.warnermedia.utils.ex.ErrorCode;
import com.warnermedia.utils.ex.SeleniumException;
import org.openqa.selenium.WebElement;
import com.warnermedia.config.TestException;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Commands;
import com.warnermedia.utils.StateManager;

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