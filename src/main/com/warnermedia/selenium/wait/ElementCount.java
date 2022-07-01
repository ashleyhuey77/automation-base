package com.warnermedia.selenium.wait;

import java.time.Duration;
import java.util.Objects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Commands;
import com.warnermedia.utils.Validator;

public class ElementCount extends Commands implements IWait {

	protected Duration time;
	protected int expectedTotalCount = 0;

	public ElementCount(WaitBuilder build) throws TestException {
		LocalWaitBuilder builder = new LocalWaitBuilder(build);
		this.time = builder.time;
		this.expectedTotalCount = builder.expectedTotalCount;
	}

	@Override
	public void on(TestElement element) throws TestException {
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
		wait.until((WebDriver driver) -> {
			Boolean result = false;
			try {
				/** refresh can't live in this method. If refresh
			 	is necessary, then a new loop will need to be
			 	created that contains the refresh
			 	The list of web elements needs to be refreshed
			 	every time the page is refreshed or this method
			 	will not work correctly.
			 	SHelper.get().page().refresh(); **/
				SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
						new WaitBuilder()
						.forAMaxTimeOf(Duration.ofSeconds(3)))
				.on(element);
				int actualElementCount = getElements(element).size();
				if (actualElementCount == expectedTotalCount) {
					result = true;
				}
			} catch (Exception e) {
				//Log.get().log(Level.WARNING, e.getMessage(), e);
			}
			return result;
		});
	}

	@Override
	public void on(WebElement element) throws TestException {
		throw new UnsupportedOperationException("The on(WebElement element) method has not been implemented for wait on element count.");
	}

	public static class LocalWaitBuilder extends Commands {
		private Duration time;
		private int expectedTotalCount;

		public LocalWaitBuilder(WaitBuilder base) throws TestException {
			this.time = base.baseTime;
			this.expectedTotalCount = base.baseExpectedTotalCount;
			//Validator.of(base.baseTime).validate(String::valueOf, result -> !result.equals("0"), "Time is null. Add the 'forAMaxTimeOf' method.").get();
			Validator.of(base.baseExpectedTotalCount).validate(String::valueOf, result -> !result.equals("0"), "Expected Total Count is null. Add the 'withACountOf' method.").get();
			Validator.of(base.baseValue).validate(Objects::nonNull, result -> base.baseValue == null, "Value is not null. Remove the 'value' method.").get();
			Validator.of(base.baseCondition).validate(Objects::isNull, result -> base.baseCondition == null, "Condition is not null. Remove the 'to' method.").get();
			Validator.of(base.baseAttribute).validate(Objects::nonNull, result -> base.baseAttribute == null, "Attribute is not null. Remove the 'forAttribute' method.").get();
		}

	}
}