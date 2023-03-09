package com.selenium.enter;

import com.config.TestException;
import com.config.setup.browser.LocalDriver;
import com.selenium.TestElement;
import com.selenium.shared.Commands;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class JSEnter extends Commands implements IEnter {
	
	private void enterViaJavascriptElementType(TestElement element, String value) {
		String enter = "').value = \"";
		String close = "\";";
		switch (element.by().value()) {
			case ID:
				((JavascriptExecutor) LocalDriver.getDriver())
						.executeScript("document.getElementById('" + element.locator().value() + enter + value + close);
				break;
			case CLASS_NAME:
				((JavascriptExecutor) LocalDriver.getDriver())
						.executeScript("document.getElementsByClassName('" + element.locator().value() + enter + value + close);
				break;
			case NAME:
				((JavascriptExecutor) LocalDriver.getDriver())
						.executeScript("document.getElementsByName('" + element.locator().value() + enter + value + close);
				break;
			case TAG_NAME:
				((JavascriptExecutor) LocalDriver.getDriver())
						.executeScript("document.getElementsByTagName('" + element.locator().value() + enter + value + close);
				break;
			case CSS:
				((JavascriptExecutor) LocalDriver.getDriver())
						.executeScript("document.querySelector('" + element.locator().value() + enter + value + close);
				break;
			default:
				break;
		}
	}

	@Override
	public void textInto(TestElement element, String text) throws TestException {
		enterViaJavascriptElementType(element, text);
	}

	@Override
	public void textInto(WebElement element, String text) throws TestException {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("arguments[0].value = \"" + text + "\";", element);
	}

	@Override
	public void textInto(TestElement element, Keys key) throws TestException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void textInto(WebElement element, Keys key) throws TestException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear(TestElement element) throws TestException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear(WebElement element) throws TestException {
		// TODO Auto-generated method stub
		
	}

}
