package shelper.methods;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import log.TestException;
import shelper.abstracts.Commands;
import shelper.interfaces.IEnter;
import shelper.vobjects.TestElement;

public class Enter extends Commands implements IEnter {

	@Override
	public void textInto(TestElement element, String text) throws TestException {
		getElement(element).sendKeys(text);
	}

	@Override
	public void textInto(WebElement element, String text) throws TestException {
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
		getElement(element).clear();
	}

	@Override
	public void clear(WebElement element) throws TestException {
		element.clear();
	}

}