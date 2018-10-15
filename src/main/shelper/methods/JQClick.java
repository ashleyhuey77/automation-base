package shelper.methods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import common.utils.managers.LocalDriver;
import common.utils.managers.SHelper;
import log.TestException;
import shelper.abstracts.Commands;
import shelper.builders.WaitBuilder;
import shelper.enums.Wait;
import shelper.interfaces.IClick;
import shelper.vobjects.By;
import shelper.vobjects.Locator;

public class JQClick extends Commands implements IClick {

	@Override
	public void on(Locator locator, By by) throws TestException {
		String webElement = locator.value();
		try {
			SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(locator, by);
			((JavascriptExecutor) LocalDriver.getDriver()).executeScript("$('" + webElement + "').click();");
		} catch (Exception ex) {
			try {
				if (locator.value().contains("'")) {
					webElement = locator.value().replace("'", "");
				}

				((JavascriptExecutor) LocalDriver.getDriver()).executeScript("$('" + webElement + "').click();");
			} catch (Exception e) {
				throw ex;
			}
		}
	}

	@Override
	public void on(WebElement element) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("arguments[0].click();", element);
	}

	@Override
	public void on(Locator locator, By by, String index) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(locator, by);
		String webElement = null;
		if (locator.value().contains("'")) {
			webElement = locator.value().replace("'", "");
		} else {
			webElement = locator.value();
		}
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("$('" + webElement + "')[" + index + "].click();");
	}

	@Override
	public void on(Locator locator, By by, int index) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(locator, by);
		String webElement = locator.value();
		if (locator.value().contains("'")) {
			webElement = locator.value().replace("'", "");
		}

		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("$('" + webElement + "')[" + Integer.toString(index) + "].click();");
	}

}