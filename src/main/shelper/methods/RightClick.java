package shelper.methods;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import common.utils.managers.LocalDriver;
import common.utils.managers.SHelper;
import log.TestException;
import shelper.abstracts.Commands;
import shelper.builders.WaitBuilder;
import shelper.enums.Wait;
import shelper.interfaces.IClick;
import shelper.vobjects.By;
import shelper.vobjects.Locator;

public class RightClick extends Commands implements IClick {

	@Override
	public void on(Locator locator, By by) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(locator, by);

		Actions action = new Actions(LocalDriver.getDriver());
		action.contextClick(getElement(locator, by)).build().perform();
	}

	@Override
	public void on(WebElement element) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);

		Actions action = new Actions(LocalDriver.getDriver());
		action.contextClick(element).build().perform();
	}

	@Override
	public void on(Locator locator, By by, String index) throws TestException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void on(Locator locator, By by, int index) throws TestException {
		throw new UnsupportedOperationException();
	}

}
