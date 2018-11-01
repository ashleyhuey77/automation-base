package shelper.methods;

import java.util.List;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.utils.managers.LocalDriver;
import log.TestException;
import shelper.abstracts.Commands;
import shelper.builders.WaitBuilder;
import shelper.interfaces.IWait;
import shelper.vobjects.TestElement;

public class NonPresentElement extends Commands implements IWait {

	protected int time = 0;

	public NonPresentElement(WaitBuilder build) throws TestException {
		LocalWaitBuilder builder = new LocalWaitBuilder(build);
		this.time = builder.time;
	}

	@Override
	public void on(TestElement element) throws TestException {
		verifyMaxWaitTimeIsNotZero(time);
		new WebDriverWait(LocalDriver.getDriver(), time)
				.until(ExpectedConditions.invisibilityOfElementLocated(getByValueBasedOnUserInput(element)));
	}

	@Override
	public void on(WebElement element) throws TestException {
		verifyMaxWaitTimeIsNotZero(time);
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);

		wait.until((WebDriver driver) -> {
			Boolean result = false;
			try {
				if (!element.isDisplayed()) {
					result = true;
					return result;
				}
			} catch (StaleElementReferenceException ex) {
				result = true;
				return result;
			}
			return result;
		});
	}

	@Override
	public void on(List<WebElement> element) throws TestException {
		// not used at this time
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