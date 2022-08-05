package com.warnermedia.selenium.wait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;

import com.warnermedia.utils.ex.ErrorCode;
import com.warnermedia.utils.ex.SeleniumException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Commands;
import com.warnermedia.utils.Validator;

public class ClickableElement extends Commands implements IWait {

	protected Duration time;

	public ClickableElement(WaitBuilder build) throws TestException {
		LocalWaitBuilder builder = new LocalWaitBuilder(build);
		this.time = builder.time;
	}

	@Override
	public void on(TestElement... element) throws TestException {
		try {
			new WebDriverWait(LocalDriver.getDriver(), time)
					.until(ExpectedConditions.elementToBeClickable(getByValueBasedOnUserInput(Arrays.stream(element).findFirst().get())));
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + time.getSeconds() + " seconds for element with locator " + Arrays.stream(element).findFirst().get().locator().value() + " to be clickable.", ErrorCode.WAIT);
		}
	}

	@Override
	public void on(WebElement element) throws TestException {
		try {
			new WebDriverWait(LocalDriver.getDriver(), time).until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + time.getSeconds() + " seconds for the element to be clickable.", ErrorCode.WAIT);
		}
	}

	public static class LocalWaitBuilder extends Commands {
		private Duration time;

		public LocalWaitBuilder(WaitBuilder base) throws TestException {
			this.time = base.baseTime;
			//Validator.of(base.baseTime).validate(String::valueOf, result -> !result.equals("0"), "Time is null. Add the 'forAMaxTimeOf' method.").get();
			Validator.of(base.baseValue).validate(Objects::nonNull, result -> base.baseValue == null, "Value is not null. Remove the 'value' method.").get();
			Validator.of(base.baseCondition).validate(Objects::isNull, result -> base.baseCondition == null, "Condition is not null. Remove the 'to' method.").get();
			Validator.of(base.baseExpectedTotalCount).validate(String::valueOf, result -> result.equals("0"), "Expected total count is not null. Remove the 'withACountOf' method.").get();
			Validator.of(base.baseAttribute).validate(Objects::nonNull, result -> base.baseAttribute == null, "Attribute is not null. Remove the 'forAttribute' method.").get();
		}

	}
}