package com.warnermedia.selenium.wait;

import java.util.Objects;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Commands;
import com.warnermedia.utils.StateManager;
import com.warnermedia.utils.Validator;

public class PresentElement extends Commands implements IWait {

	protected int time = 0;

	public PresentElement(WaitBuilder build) throws TestException {
		LocalWaitBuilder builder = new LocalWaitBuilder(build);
		this.time = builder.time;
	}

	@Override
	public void on(TestElement element) throws TestException {
		if (StateManager.getState() != null) {
			StateManager.getState().checkState();
		}
		new WebDriverWait(LocalDriver.getDriver(), time)
				.until(ExpectedConditions.visibilityOfElementLocated(getByValueBasedOnUserInput(element)));
	}

	@Override
	public void on(WebElement element) throws TestException {
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);

		wait.until((WebDriver driver) -> {
			Boolean result = false;
			try {
				if (element.isDisplayed()) {
					result = true;
					return result;
				}
			} catch (StaleElementReferenceException ex) {
				return result;
			}
			return result;
		});
	}

	public static class LocalWaitBuilder extends Commands {
		private int time;

		public LocalWaitBuilder(WaitBuilder base) throws TestException {
			this.time = base.baseTime;
			Validator.of(base.baseTime).validate(String::valueOf, result -> !result.equals("0"), "Time is null. Add the 'forAMaxTimeOf' method.").get();
			Validator.of(base.baseValue).validate(Objects::nonNull, result -> base.baseValue == null, "Value is not null. Remove the 'value' method.").get();
			Validator.of(base.baseCondition).validate(Objects::isNull, result -> base.baseCondition == null, "Condition is not null. Remove the 'to' method.").get();
			Validator.of(base.baseExpectedTotalCount).validate(String::valueOf, result -> result.equals("0"), "Expected total count is not null. Remove the 'withACountOf' method.").get();
			Validator.of(base.baseAttribute).validate(Objects::nonNull, result -> base.baseAttribute == null, "Attribute is not null. Remove the 'forAttribute' method.").get();
		}

	}
}