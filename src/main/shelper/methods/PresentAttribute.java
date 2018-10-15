package shelper.methods;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.utils.managers.LocalDriver;
import log.TestException;
import shelper.abstracts.Commands;
import shelper.builders.WaitBuilder;
import shelper.enums.Condition;
import shelper.interfaces.IWait;
import shelper.vobjects.By;
import shelper.vobjects.Locator;

public class PresentAttribute extends Commands implements IWait {

	protected int time = 0;
	protected Condition condition;
	protected String value;
	protected int expectedTotalCount = 0;
	protected String attribute;

	public PresentAttribute(WaitBuilder build) throws TestException {
		LocalWaitBuilder builder = new LocalWaitBuilder(build);
		this.time = builder.time;
		this.attribute = builder.attribute;
	}

	@Override
	public void on(Locator locator, By by) throws TestException {
		verifyAttributeIsNotNull(attribute);
		verifyMaxWaitTimeIsNotZero(time);
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
		wait.until((WebDriver driver) -> {
			Boolean result = false;
			WebElement elementToBeTested = getElement(locator, by);
			String attr = elementToBeTested.getAttribute(attribute);
			if (attr != null && !attr.equals("")) {
				result = true;
			}
			return result;
		});
	}

	@Override
	public void on(WebElement element) throws TestException {
		verifyAttributeIsNotNull(attribute);
		verifyMaxWaitTimeIsNotZero(time);
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
		//unimplemented so far
		throw new UnsupportedOperationException();
	}

	public static class LocalWaitBuilder extends Commands {

		private int time;
		private String attribute;

		public LocalWaitBuilder(WaitBuilder base) throws TestException {
			this.time = base.baseTime;
			this.attribute = base.baseAttribute;
			failIfConditionIsNotNull(base.baseCondition);
			failIfExpectedCountIsNotZero(base.baseExpectedTotalCount);
			failIfValueIsNotNull(base.baseValue);
		}

	}

}