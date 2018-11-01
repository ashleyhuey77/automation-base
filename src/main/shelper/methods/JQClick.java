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
import shelper.vobjects.TestElement;

public class JQClick extends Commands implements IClick {

	@Override
	public void on(TestElement element) throws TestException {
		String webElement = element.locator().value();
		try {
			SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
			((JavascriptExecutor) LocalDriver.getDriver()).executeScript("$('" + webElement + "').click();");
		} catch (Exception ex) {
			try {
				if (element.locator().value().contains("'")) {
					webElement = element.locator().value().replace("'", "");
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
	public void on(TestElement element, String index) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
		String webElement = null;
		if (element.locator().value().contains("'")) {
			webElement = element.locator().value().replace("'", "");
		} else {
			webElement = element.locator().value();
		}
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("$('" + webElement + "')[" + index + "].click();");
	}

	@Override
	public void on(TestElement element, int index) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
		String webElement = element.locator().value();
		if (element.locator().value().contains("'")) {
			webElement = element.locator().value().replace("'", "");
		}

		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("$('" + webElement + "')[" + Integer.toString(index) + "].click();");
	}

}