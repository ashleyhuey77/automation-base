package shelper.methods;

import java.util.List;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.errorprone.annotations.DoNotCall;
import common.utils.managers.LocalDriver;
import log.TestException;
import shelper.abstracts.Commands;
import shelper.interfaces.IElement;
import shelper.vobjects.By;
import shelper.vobjects.Locator;

public class Element extends Commands implements IElement {

	@Override
	public WebElement get(Locator locator, By by) throws TestException {
		return getElement(locator, by);
	}

	@Override
	public List<WebElement> getListOf(Locator locator, By by) throws TestException {
		return getElements(locator, by);
	}

	@Override
	public Boolean isDisplayed(Locator locator, By by, int i) throws TestException {
		try {
			new WebDriverWait(LocalDriver.getDriver(), i)
					.until(ExpectedConditions.presenceOfElementLocated(getByValueBasedOnUserInput(locator, by)));
			return true;
		} catch (WebDriverException ex) {
			return false;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	@DoNotCall
	public Boolean isDisplayed(WebElement element, int i) throws TestException {
		// waitForPresenceOfElementLocated(element, i);
		return true;
	}

	@Override
	public WebElement find(WebElement firstElement, Locator locator, By by) throws TestException {
		return firstElement.findElement(getByValueBasedOnUserInput(locator, by));
	}

	@Override
	public WebElement find(Locator firstElocator, By firstEBy, Locator locator, By by) throws TestException {
		return getElement(firstElocator, firstEBy).findElement(getByValueBasedOnUserInput(locator, by));
	}

	@Override
	public List<WebElement> findListOf(WebElement firstElement, Locator locator, By by) throws TestException {
		return firstElement.findElements(getByValueBasedOnUserInput(locator, by));
	}

	@Override
	public List<WebElement> findListOf(Locator firstElocator, By firstEBy, Locator locator, By by) throws TestException {
		return getElement(firstElocator, firstEBy).findElements(getByValueBasedOnUserInput(locator, by));
	}

	@Override
	public Boolean isAttributePresent(WebElement element, String attribute) throws TestException {
		Boolean result = false;
		try {
			String value = element.getAttribute(attribute);
			if (value != null) {
				result = true;
			}
		} catch (Exception e) {
			return result;
		}

		return result;
	}

	@Override
	public Boolean isAttributePresent(Locator locator, By by, String attribute) throws TestException {
		Boolean result = false;
		try {
			String value = getElement(locator, by).getAttribute(attribute);
			if (value != null) {
				result = true;
			}
		} catch (Exception e) {
			return result;
		}

		return result;
	}

	@Override
	public Boolean isEnabled(Locator locator, By by) throws TestException {
		Boolean result = false;
		WebElement element = getElement(locator, by);
		if (element.isEnabled()) {
			result = true;
		}
		return result;
	}

	@Override
	public Boolean isEnabled(WebElement element) throws TestException {
		Boolean result = false;
		WebElement webElement = element;
		if (webElement.isEnabled()) {
			result = true;
		}
		return result;
	}

}