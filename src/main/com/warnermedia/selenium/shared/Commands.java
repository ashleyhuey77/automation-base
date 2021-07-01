package com.warnermedia.selenium.shared;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.utils.CookieHelper;

public class Commands {

	/**
	 * <summary> method to get the by value based on
	 * user input </summary>
	 * 
	 * @return By
	 */
	public org.openqa.selenium.By getByValue(TestElement element) {
		switch (element.by().value()) {
			case ID:
				return org.openqa.selenium.By.id(element.locator().value());
			case CSS:
				return org.openqa.selenium.By.cssSelector(element.locator().value());
			case XPATH:
				return org.openqa.selenium.By.xpath(element.locator().value());
			case CLASS_NAME:
				return org.openqa.selenium.By.className(element.locator().value());
			case TAG_NAME:
				return org.openqa.selenium.By.tagName(element.locator().value());
			case LINK_TEXT:
				return org.openqa.selenium.By.linkText(element.locator().value());
			case PARTIAL_LINK_TEXT:
				return org.openqa.selenium.By.partialLinkText(element.locator().value());
			case NAME:
				return org.openqa.selenium.By.name(element.locator().value());
			default:
				return null;

		}
	}

	/**
	 * <summary> Method to call the by switch
	 * statement and return the correct by value based
	 * on user input </summary>
	 * @param element TODO
	 * 
	 * @return By
	 */
	protected org.openqa.selenium.By getByValueBasedOnUserInput(TestElement element) {
		return getByValue(element);
	}

	/**
	 * <summary> Element that is being defined based
	 * on the html string parameter and the By value
	 * that indicates which type of element is being
	 * defined </summary>
	 * @param element TODO
	 * 
	 * @return WebElement
	 */
	public WebElement getElement(TestElement element) throws TestException {
		try {
			WebDriver driver = LocalDriver.getDriver();
			WebElement el = driver.findElement(getByValueBasedOnUserInput(element));
			return el;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * <summary> method to return a list of
	 * webElements </summary>
	 * @param element TODO
	 * 
	 * @return List<\WebElement>
	 */
	public List<WebElement> getElements(TestElement element) throws TestException {
		try {
			return LocalDriver.getDriver().findElements(getByValueBasedOnUserInput(element));
		} catch (Exception e) {
			throw e;
		}
	}
	
	protected void checkCookiesAndAddRequiredOnesIfNecessary() throws TestException {
		CookieHelper.newHelper().getCookies().setCookies().build();
	}

}