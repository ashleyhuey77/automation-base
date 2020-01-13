package com.warnermedia.selenium.enter;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import com.warnermedia.config.TestException;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Commands;
import com.warnermedia.utils.StateManager;

public class Enter extends Commands implements IEnter {

	@Override
	public void textInto(TestElement element, String text) throws TestException {
		if (StateManager.getState() != null) {
			StateManager.getState().checkState();
		}
		getElement(element).sendKeys(text);
	}

	@Override
	public void textInto(WebElement element, String text) throws TestException {
		if (StateManager.getState() != null) {
			StateManager.getState().checkState();
		}
		element.sendKeys(text);
	}

	@Override
	public void textInto(TestElement element, Keys key) throws TestException {
		getElement(element).sendKeys(key);
	}

	@Override
	public void textInto(WebElement element, Keys key) throws TestException {
		element.sendKeys(key);
	}

	@Override
	public void clear(TestElement element) throws TestException {
		if (StateManager.getState() != null) {
			StateManager.getState().checkState();
		}
		getElement(element).clear();
	}

	@Override
	public void clear(WebElement element) throws TestException {
		if (StateManager.getState() != null) {
			StateManager.getState().checkState();
		}
		element.clear();
	}

}