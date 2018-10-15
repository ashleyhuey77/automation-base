package shelper.methods;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.utils.managers.LocalDriver;
import log.TestException;
import shelper.abstracts.Commands;
import shelper.builders.WaitBuilder;
import shelper.interfaces.IWait;
import shelper.vobjects.By;
import shelper.vobjects.Locator;

public class ClickableElement extends Commands implements IWait {

	protected int time = 0;

	public ClickableElement(WaitBuilder build) throws TestException {
		LocalWaitBuilder builder = new LocalWaitBuilder(build);
		this.time = builder.time;
	}

	@Override
	public void on(Locator locator, By by) throws TestException {
		verifyMaxWaitTimeIsNotZero(time);
		new WebDriverWait(LocalDriver.getDriver(), time)
				.until(ExpectedConditions.elementToBeClickable(getByValueBasedOnUserInput(locator, by)));
	}

	@Override
	public void on(WebElement element) throws TestException {
		verifyMaxWaitTimeIsNotZero(time);
		new WebDriverWait(LocalDriver.getDriver(), time).until(ExpectedConditions.elementToBeClickable(element));
	}

	@Override
	public void on(List<WebElement> element) throws TestException {
		throw new UnsupportedOperationException();
	}

	public static class LocalWaitBuilder extends Commands {
		private int time;

		public LocalWaitBuilder(WaitBuilder base) throws TestException {
			this.time = base.baseTime;
			failIfValueIsNotNull(base.baseValue);
			failIfConditionIsNotNull(base.baseCondition);
			failIfExpectedCountIsNotZero(base.baseExpectedTotalCount);
			failIfAttributeIsNotNull(base.baseAttribute);
		}

	}
}