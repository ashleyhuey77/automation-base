package shelper.methods;

import java.util.List;
import java.util.logging.Level;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.utils.managers.LocalDriver;
import common.utils.managers.SHelper;
import log.Log;
import log.TestException;
import shelper.abstracts.Commands;
import shelper.builders.WaitBuilder;
import shelper.interfaces.IWait;
import shelper.vobjects.By;
import shelper.vobjects.Locator;

public class ElementCount extends Commands implements IWait {

	protected int time = 0;
	protected int expectedTotalCount = 0;

	public ElementCount(WaitBuilder build) throws TestException {
		LocalWaitBuilder builder = new LocalWaitBuilder(build);
		this.time = builder.time;
		this.expectedTotalCount = builder.expectedTotalCount;
	}

	@Override
	public void on(Locator locator, By by) throws TestException {
		verifyExpectedCountIsNotZero(expectedTotalCount);
		verifyMaxWaitTimeIsNotZero(time);
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
		wait.until((WebDriver driver) -> {
			Boolean result = false;
			try {
				SHelper.get().page().refresh();
				int actualElementCount = getElements(locator, by).size();
				if (actualElementCount == expectedTotalCount) {
					result = true;
				}
			} catch (Exception e) {
				Log.get().log(Level.WARNING, e.getMessage(), e);
			}
			return result;
		});
	}

	@Override
	public void on(WebElement element) throws TestException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void on(List<WebElement> element) throws TestException {
		verifyExpectedCountIsNotZero(expectedTotalCount);
		verifyMaxWaitTimeIsNotZero(time);
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
		wait.until((WebDriver driver) -> {
			Boolean result = false;
			// refresh can't live in this method. If refresh
			// is necessary, then a new loop will need to be
			// created that contains the refresh
			// The list of web elements needs to be refreshed
			// every time the page is refreshed or this method
			// will not work correctly.
			// SHelper.get().page().refresh();
			int actualElementCount = element.size();
			if (actualElementCount == expectedTotalCount) {
				result = true;
			}
			return result;
		});
	}

	public static class LocalWaitBuilder extends Commands {
		private int time;
		private int expectedTotalCount;

		public LocalWaitBuilder(WaitBuilder base) throws TestException {
			this.time = base.baseTime;
			this.expectedTotalCount = base.baseExpectedTotalCount;
			failIfValueIsNotNull(base.baseValue);
			failIfConditionIsNotNull(base.baseCondition);
			failIfAttributeIsNotNull(base.baseAttribute);
		}

	}
}