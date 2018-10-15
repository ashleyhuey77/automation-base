package shelper.methods;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import log.TestException;
import shelper.abstracts.Commands;
import shelper.interfaces.IEnter;
import shelper.vobjects.By;
import shelper.vobjects.Locator;

public class Enter extends Commands implements IEnter {

	@Override
	public void textInto(Locator locator, By by, String text) throws TestException {
		getElement(locator, by).sendKeys(text);
	}

	@Override
	public void textInto(WebElement element, String text) throws TestException {
		element.sendKeys(text);
	}

	@Override
	public void textInto(Locator locator, By by, Keys key) throws TestException {
		getElement(locator, by).sendKeys(key);
	}

	@Override
	public void textInto(WebElement element, Keys key) throws TestException {
		element.sendKeys(key);
	}

	@Override
	public void clear(Locator locator, By by) throws TestException {
		getElement(locator, by).clear();
	}

	@Override
	public void clear(WebElement element) throws TestException {
		element.clear();
	}

}