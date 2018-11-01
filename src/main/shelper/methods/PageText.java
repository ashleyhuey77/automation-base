package shelper.methods;

import org.openqa.selenium.WebElement;
import log.TestException;
import shelper.abstracts.Commands;
import shelper.interfaces.IText;
import shelper.vobjects.TestElement;

public class PageText extends Commands implements IText {
	@Override
	public String getFrom(TestElement element, String... attribute) throws TestException {
		return getElement(element).getText().trim();
	}

	@Override
	public String getFrom(WebElement element, String... attribute) throws TestException {
		return element.getText().trim();
	}

	@Override
	public Boolean isDisplayed(TestElement element, String expectedText, String... attribute) throws TestException {
		Boolean result = false;
		String actualText = getElement(element).getText();
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