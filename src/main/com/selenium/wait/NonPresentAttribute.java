package com.selenium.wait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;

import com.config.TestException;
import com.config.setup.browser.LocalDriver;
import com.selenium.TestElement;
import com.selenium.shared.Commands;
import com.utils.Validator;
import com.utils.ex.ErrorCode;
import com.utils.ex.SeleniumException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NonPresentAttribute extends Commands implements IWait {

	protected Duration time;
	protected String attribute;

	public NonPresentAttribute(WaitBuilder build) throws TestException {
		LocalWaitBuilder builder = new LocalWaitBuilder(build);
		this.time = builder.time;
		this.attribute = builder.attribute;
	}

	@Override
	public void on(TestElement... element) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
			wait.until((WebDriver driver) -> {
				Boolean result = false;
				WebElement elementToBeTested = null;
				try {
					elementToBeTested = getElement(Arrays.stream(element).findFirst().get());
				} catch (TestException e) {
					return false;
				}
				String value = elementToBeTested.getAttribute(attribute);
				if (value == null || value.equals("")) {
					result = true;
				}
				return result;
			});
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + time.getSeconds() + " seconds for element with locator " + Arrays.stream(element).findFirst().get().locator().value() + " to no longer contain attribute " + attribute, ErrorCode.WAIT);
		}
	}

	@Override
	public void on(WebElement element) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
			wait.until((WebDriver driver) -> {
				Boolean result = false;
				WebElement elementToBeTested = element;
				String value = elementToBeTested.getAttribute(attribute);
				if (value == null || value.equals("")) {
					result = true;
				}
				return result;
			});
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + time.getSeconds() + " seconds for the element to no longer contain attribute " + attribute, ErrorCode.WAIT);
		}
	}

	public static class LocalWaitBuilder extends Commands {
		private Duration time;
		private String attribute;

		public LocalWaitBuilder(WaitBuilder base) throws TestException {
			this.time = base.baseTime;
			this.attribute = base.baseAttribute;
			Validator.of(base.baseAttribute).validate(Objects::nonNull, result -> base.baseAttribute != null, "Attribute is null. Add the 'forAttribute' method.")
											.validate(String::valueOf, result -> !result.isEmpty(), "Attribute is empty. Add a value to the 'forAttribute' method.").get();
			//Validator.of(base.baseTime).validate(String::valueOf, result -> !result.equals("0"), "Time is null. Add the 'forAMaxTimeOf' method.").get();
			Validator.of(base.baseCondition).validate(Objects::isNull, result -> base.baseCondition == null, "Condition is not null. Remove the 'to' method.").get();
			Validator.of(base.baseExpectedTotalCount).validate(String::valueOf, result -> result.equals("0"), "Expected total count is not null. Remove the 'withACountOf' method.").get();
			Validator.of(base.baseValue).validate(Objects::nonNull, result -> base.baseValue == null, "Value is not null. Remove the 'value' method.").get();
		}

	}
}