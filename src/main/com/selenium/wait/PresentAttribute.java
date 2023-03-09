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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PresentAttribute extends Commands implements IWait {

	protected Duration time;
	protected String attribute;

	public PresentAttribute(WaitBuilder build) throws TestException {
		LocalWaitBuilder builder = new LocalWaitBuilder(build);
		this.time = builder.time;
		this.attribute = builder.attribute;
	}

	@Override
	public void on(TestElement... element) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
			wait.until(ExpectedConditions.attributeToBeNotEmpty(getElement(element[0]), attribute));
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + time.getSeconds() + " seconds for element with locator " + Arrays.stream(element).findFirst().get().locator().value() + " to contain an attribute named " + attribute, ErrorCode.WAIT);
		}
	}

	@Override
	public void on(WebElement element) throws TestException {
		try {
			WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
			wait.until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
		} catch (Exception e) {
			throw new SeleniumException("The test waited " + time.getSeconds() + " seconds for the element to contain an attribute named " + attribute, ErrorCode.WAIT);
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