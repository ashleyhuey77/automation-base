package com.warnermedia.selenium.click;

import java.util.List;
import org.openqa.selenium.WebElement;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Commands;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;

public class Click extends Commands implements IClick {

	@Override
	public void on(TestElement element) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
		//checkCookiesAndAddRequiredOnesIfNecessary();
		getElement(element).click();
	}

	@Override
	public void on(WebElement element) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
		//checkCookiesAndAddRequiredOnesIfNecessary();
		element.click();
	}

	@Override
	public void on(TestElement element, String index) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
		//checkCookiesAndAddRequiredOnesIfNecessary();
		List<WebElement> element1 = getElements(element);
		element1.get(Integer.parseInt(index)).click();
	}

	@Override
	public void on(TestElement element, int index) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20));
		//checkCookiesAndAddRequiredOnesIfNecessary();
		List<WebElement> element1 = getElements(element);
		element1.get(index).click();
	}

}