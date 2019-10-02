package com.warnermedia.selenium.click;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Commands;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;

public class JSClick extends Commands implements IClick {

	/**
	 * <summary> Method to click an element via
	 * javascript based on webelement type </summary>
	 * @param index
	 *            the index used to identify a
	 *            particular element in a list of
	 *            elements tat contain more than one
	 *            element with a similar selector
	 * 
	 * @return void
	 */
	private void clickViaJavascriptElementType(TestElement element, String index) {
		String click = "].click();";
		switch (element.by().value()) {
			case ID:
				((JavascriptExecutor) LocalDriver.getDriver())
						.executeScript("document.getElementById('" + element.locator().value() + "').click();");
				break;
			case CLASS_NAME:
				((JavascriptExecutor) LocalDriver.getDriver())
						.executeScript("document.getElementsByClassName('" + element.locator().value() + "')[" + index + click);
				break;
			case NAME:
				((JavascriptExecutor) LocalDriver.getDriver())
						.executeScript("document.getElementsByName('" + element.locator().value() + "')[" + index + click);
				break;
			case TAG_NAME:
				((JavascriptExecutor) LocalDriver.getDriver())
						.executeScript("document.getElementsByTagName('" + element.locator().value() + "')[" + index + click);
				break;
			case CSS:
				((JavascriptExecutor) LocalDriver.getDriver())
						.executeScript("document.querySelector('" + element.locator().value() + "').click();");
				break;
			default:
				break;
		}
	}

	@Override
	public void on(TestElement element) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
		checkCookiesAndAddRequiredOnesIfNecessary();
		clickViaJavascriptElementType(element, "0");
	}

	@Override
	public void on(WebElement element) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
		checkCookiesAndAddRequiredOnesIfNecessary();
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("arguments[0].click();", element);
	}

	@Override
	public void on(TestElement element, String index) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
		checkCookiesAndAddRequiredOnesIfNecessary();
		clickViaJavascriptElementType(element, index);
	}

	@Override
	public void on(TestElement element, int index) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
		checkCookiesAndAddRequiredOnesIfNecessary();
		clickViaJavascriptElementType(element, Integer.toString(index));
	}

}