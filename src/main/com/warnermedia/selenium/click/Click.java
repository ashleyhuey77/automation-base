package com.warnermedia.selenium.click;

import java.util.List;

import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.utils.JSWaiter;
import org.openqa.selenium.WebElement;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Commands;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;
import com.warnermedia.utils.StateManager;

public class Click extends Commands implements IClick {

	@Override
	public void on(TestElement element) throws TestException {
		if (StateManager.getState() != null) {
			StateManager.getState().checkState();
		}
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
		//checkCookiesAndAddRequiredOnesIfNecessary();
		getElement(element).click();
		JSWaiter waiter = new JSWaiter(LocalDriver.getDriver());
		waiter.waitForAllRequests();
	}

	@Override
	public void on(WebElement element) throws TestException {
		if (StateManager.getState() != null) {
			StateManager.getState().checkState();
		}
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
		//checkCookiesAndAddRequiredOnesIfNecessary();
		element.click();
		JSWaiter waiter = new JSWaiter(LocalDriver.getDriver());
		waiter.waitForAllRequests();
	}

	@Override
	public void on(TestElement element, String index) throws TestException {
		if (StateManager.getState() != null) {
			StateManager.getState().checkState();
		}
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
		//checkCookiesAndAddRequiredOnesIfNecessary();
		List<WebElement> element1 = getElements(element);
		element1.get(Integer.parseInt(index)).click();
		JSWaiter waiter = new JSWaiter(LocalDriver.getDriver());
		waiter.waitForAllRequests();
	}

	@Override
	public void on(TestElement element, int index) throws TestException {
		if (StateManager.getState() != null) {
			StateManager.getState().checkState();
		}
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20));
		//checkCookiesAndAddRequiredOnesIfNecessary();
		List<WebElement> element1 = getElements(element);
		element1.get(index).click();
		JSWaiter waiter = new JSWaiter(LocalDriver.getDriver());
		waiter.waitForAllRequests();
	}

}