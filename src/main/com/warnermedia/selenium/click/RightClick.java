package com.warnermedia.selenium.click;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Commands;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;

import java.time.Duration;

public class RightClick extends Commands implements IClick {

	@Override
	public void on(TestElement element) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(20))).on(element);

		Actions action = new Actions(LocalDriver.getDriver());
		action.contextClick(getElement(element)).build().perform();
	}

	@Override
	public void on(WebElement element) throws TestException {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(20))).on(element);

		Actions action = new Actions(LocalDriver.getDriver());
		action.contextClick(element).build().perform();
	}

	@Override
	public void on(TestElement element, String index) throws TestException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void on(TestElement element, int index) throws TestException {
		throw new UnsupportedOperationException();
	}

}
