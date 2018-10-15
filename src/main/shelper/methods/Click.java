package shelper.methods;

import java.util.List;
import org.openqa.selenium.WebElement;
import common.utils.managers.SHelper;
import log.TestException;
import shelper.abstracts.Commands;
import shelper.builders.WaitBuilder;
import shelper.enums.Wait;
import shelper.interfaces.IClick;
import shelper.vobjects.By;
import shelper.vobjects.Locator;

public class Click extends Commands implements IClick {

	@Override
	public void on(Locator locator, By by) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(locator, by);
		getElement(locator, by).click();
	}

	@Override
	public void on(WebElement element) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
		element.click();
	}

	@Override
	public void on(Locator locator, By by, String index) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(locator, by);
		List<WebElement> element = getElements(locator, by);
		element.get(Integer.parseInt(index)).click();
	}

	@Override
	public void on(Locator locator, By by, int index) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20));
		List<WebElement> element = getElements(locator, by);
		element.get(index).click();
	}

}