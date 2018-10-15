package shelper.methods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import common.utils.managers.LocalDriver;
import common.utils.managers.SHelper;
import log.TestException;
import shelper.abstracts.Commands;
import shelper.builders.WaitBuilder;
import shelper.enums.Wait;
import shelper.interfaces.IClick;
import shelper.vobjects.By;
import shelper.vobjects.Locator;

public class JSClick extends Commands implements IClick {

	/**
	 * <summary> Method to click an element via
	 * javascript based on webelement type </summary>
	 * 
	 * @param locator
	 *            the webelement selector string
	 *            necessary for the webelement to be
	 *            found
	 * @param index
	 *            the index used to identify a
	 *            particular element in a list of
	 *            elements tat contain more than one
	 *            element with a similar selector
	 * @param webelements
	 *            the locator type used to identify
	 *            webelements on the page. Based on
	 *            the values in the LocatorTypes enum.
	 * @return void
	 */
	private void clickViaJavascriptElementType(String locator, String index, How webelements) {
		String click = "].click();";
		switch (webelements) {
			case ID:
				((JavascriptExecutor) LocalDriver.getDriver())
						.executeScript("document.getElementById('" + locator + "').click();");
				break;
			case CLASS_NAME:
				((JavascriptExecutor) LocalDriver.getDriver())
						.executeScript("document.getElementsByClassName('" + locator + "')[" + index + click);
				break;
			case NAME:
				((JavascriptExecutor) LocalDriver.getDriver())
						.executeScript("document.getElementsByName('" + locator + "')[" + index + click);
				break;
			case TAG_NAME:
				((JavascriptExecutor) LocalDriver.getDriver())
						.executeScript("document.getElementsByTagName('" + locator + "')[" + index + click);
				break;
			case CSS:
				((JavascriptExecutor) LocalDriver.getDriver())
						.executeScript("document.querySelector('" + locator + "').click();");
				break;
			default:
				break;
		}
	}

	@Override
	public void on(Locator locator, By by) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(locator, by);
		clickViaJavascriptElementType(locator.value(), "0", by.value());
	}

	@Override
	public void on(WebElement element) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("arguments[0].click();", element);
	}

	@Override
	public void on(Locator locator, By by, String index) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(locator, by);
		clickViaJavascriptElementType(locator.value(), index, by.value());
	}

	@Override
	public void on(Locator locator, By by, int index) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(locator, by);
		clickViaJavascriptElementType(locator.value(), Integer.toString(index), by.value());
	}

}