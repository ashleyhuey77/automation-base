package shelper.methods;

import org.openqa.selenium.WebElement;
import log.TestException;
import shelper.abstracts.Commands;
import shelper.interfaces.IText;
import shelper.vobjects.By;
import shelper.vobjects.Locator;

public class PageText extends Commands implements IText {
	@Override
	public String getFrom(Locator locator, By by, String... attribute) throws TestException {
		return getElement(locator, by).getText().trim();
	}

	@Override
	public String getFrom(WebElement element, String... attribute) throws TestException {
		return element.getText().trim();
	}

	@Override
	public Boolean isDisplayed(Locator locator, By by, String expectedText, String... attribute) throws TestException {
		Boolean result = false;
		String actualText = getElement(locator, by).getText();
		String modifiedActualText = actualText.replace("\r\n", " ");
		if (modifiedActualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
			result = true;
		}
		return result;
	}

	@Override
	public Boolean isDisplayed(WebElement element, String expectedText, String... attribute) throws TestException {
		Boolean result = false;
		String actualText = element.getText();
		String modifiedActualText = actualText.replace("\r\n", " ");
		if (modifiedActualText.toLowerCase().trim().contains(expectedText.toLowerCase().trim())) {
			result = true;
		}
		return result;
	}

}