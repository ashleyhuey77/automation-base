package com.warnermedia.selenium.element;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.errorprone.annotations.DoNotCall;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Commands;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;

public class Element extends Commands implements IElement {

	@Override
	public WebElement get(TestElement element) throws TestException {
		return getElement(element);
	}

	@Override
	public List<WebElement> getListOf(TestElement element) throws TestException {
		return getElements(element);
	}

	@Override
	public Boolean isDisplayed(TestElement element, Duration i) throws TestException {
		try {
			new WebDriverWait(LocalDriver.getDriver(), i)
					.until(ExpectedConditions.presenceOfElementLocated(getByValueBasedOnUserInput(element)));
			return true;
		} catch (WebDriverException ex) {
			return false;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	@DoNotCall
	public Boolean isDisplayed(WebElement element, Duration i) throws TestException {
		// waitForPresenceOfElementLocated(element, i);
		return true;
	}

	@Override
	public WebElement find(WebElement firstElement, TestElement secondElement) throws TestException {
		return firstElement.findElement(getByValueBasedOnUserInput(secondElement));
	}

	@Override
	public WebElement find(TestElement firstElement, TestElement secondElement) throws TestException {
		return getElement(firstElement).findElement(getByValueBasedOnUserInput(secondElement));
	}

	@Override
	public List<WebElement> findListOf(WebElement firstElement, TestElement secondElement) throws TestException {
		return firstElement.findElements(getByValueBasedOnUserInput(secondElement));
	}

	@Override
	public List<WebElement> findListOf(TestElement firstElement, TestElement secondElement) throws TestException {
		return getElement(firstElement).findElements(getByValueBasedOnUserInput(secondElement));
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
	public Boolean isAttributePresent(TestElement element, String attribute) throws TestException {
		Boolean result = false;
		try {
			String value = getElement(element).getAttribute(attribute);
			if (value != null) {
				result = true;
			}
		} catch (Exception e) {
			return result;
		}

		return result;
	}

	@Override
	public Boolean isEnabled(TestElement element) throws TestException {
		Boolean result = false;
		WebElement el = getElement(element);
		if (el.isEnabled()) {
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

	@Override
	public Boolean isClickable(TestElement element) throws TestException {
		Boolean result = false;
		try {
			SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(1))).on(element);
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	@Override
	public Boolean isClickable(WebElement element) throws TestException {
		Boolean result = false;
		try {
			SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(1))).on(element);
			result = true;
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

}