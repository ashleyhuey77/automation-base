package shelper.methods;

import java.util.List;
import java.util.Objects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.utils.Validator;
import common.utils.managers.LocalDriver;
import log.TestException;
import shelper.abstracts.Commands;
import shelper.builders.WaitBuilder;
import shelper.interfaces.IWait;
import shelper.vobjects.TestElement;

public class PresentAttribute extends Commands implements IWait {

	protected int time = 0;
	protected String attribute;

	public PresentAttribute(WaitBuilder build) throws TestException {
		LocalWaitBuilder builder = new LocalWaitBuilder(build);
		this.time = builder.time;
		this.attribute = builder.attribute;
	}

	@Override
	public void on(TestElement element) throws TestException {
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
		wait.until((WebDriver driver) -> {
			Boolean result = false;
			WebElement elementToBeTested = getElement(element);
			String attr = elementToBeTested.getAttribute(attribute);
			if (attr != null && !attr.equals("")) {
				result = true;
			}
			return result;
		});
	}

	@Override
	public void on(WebElement element) throws TestException {
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
		wait.until((WebDriver driver) -> {
			Boolean result = false;
			WebElement elementToBeTested = element;
			String attr = elementToBeTested.getAttribute(attribute);
			if (attr != null && !attr.equals("")) {
				result = true;
			}
			return result;
		});
	}

	@Override
	public void on(List<WebElement> element) throws TestException {
		throw new UnsupportedOperationException("The on(List<WebElement> element) method has not been implemented for the PresentAttribute class.");
	}

	public static class LocalWaitBuilder extends Commands {

		private int time;
		private String attribute;

		public LocalWaitBuilder(WaitBuilder base) throws TestException {
			this.time = base.baseTime;
			this.attribute = base.baseAttribute;
			Validator.of(base.baseAttribute).validate(Objects::nonNull, result -> base.baseAttribute != null, "Attribute is null. Add the 'forAttribute' method.")
											.validate(String::valueOf, result -> !result.isEmpty(), "Attribute is empty. Add a value to the 'forAttribute' method.").get();
			Validator.of(base.baseTime).validate(String::valueOf, result -> !result.equals("0"), "Time is null. Add the 'forAMaxTimeOf' method.").get();
			Validator.of(base.baseCondition).validate(Objects::isNull, result -> base.baseCondition == null, "Condition is not null. Remove the 'to' method.").get();
			Validator.of(base.baseExpectedTotalCount).validate(String::valueOf, result -> result.equals("0"), "Expected total count is not null. Remove the 'withACountOf' method.").get();
			Validator.of(base.baseValue).validate(Objects::nonNull, result -> base.baseValue == null, "Value is not null. Remove the 'value' method.").get();

		}

	}

}