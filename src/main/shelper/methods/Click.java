package shelper.methods;

import java.util.List;
import org.openqa.selenium.WebElement;
import common.utils.managers.SHelper;
import log.TestException;
import shelper.abstracts.Commands;
import shelper.builders.WaitBuilder;
import shelper.enums.Wait;
import shelper.interfaces.IClick;
import shelper.vobjects.TestElement;

public class Click extends Commands implements IClick {

	@Override
	public void on(TestElement element) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
		checkCookiesAndAddRequiredOnesIfNecessary();
		getElement(element).click();
	}

	@Override
	public void on(WebElement element) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
		checkCookiesAndAddRequiredOnesIfNecessary();
		element.click();
	}

	@Override
	public void on(TestElement element, String index) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
		checkCookiesAndAddRequiredOnesIfNecessary();
		List<WebElement> element1 = getElements(element);
		element1.get(Integer.parseInt(index)).click();
	}

	@Override
	public void on(TestElement element, int index) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20));
		checkCookiesAndAddRequiredOnesIfNecessary();
		List<WebElement> element1 = getElements(element);
		element1.get(index).click();
	}

}