package shelper.methods;

import java.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import common.utils.managers.LocalDriver;
import log.TestException;
import shelper.abstracts.Commands;
import shelper.enums.BrowserObject;
import shelper.interfaces.IBrowser;
import shelper.vobjects.Locator;

public class Browser extends Commands implements IBrowser {

	private void switchToDefaultContent() {
		LocalDriver.getDriver().switchTo().defaultContent();
	}

	private void switchToNewWindow() {
		for (String window : LocalDriver.getDriver().getWindowHandles()) {
			LocalDriver.getDriver().switchTo().window(window);
		}
	}

	@Override
	public void switchTo(BrowserObject object, WebElement element) throws TestException {
		LocalDriver.getDriver().switchTo().frame(element);
	}

	@Override
	public void switchTo(BrowserObject object, Locator locator, shelper.vobjects.By by) throws TestException {
		LocalDriver.getDriver().switchTo().frame(getElement(locator, by));
	}

	@Override
	public void switchTo(BrowserObject object, String name) throws TestException {
		LocalDriver.getDriver().switchTo().frame(name);
	}

	@Override
	public void close() throws TestException {
		String originalHandle = LocalDriver.getDriver().getWindowHandle();

		for (String handle : LocalDriver.getDriver().getWindowHandles()) {
			if (!handle.equals(originalHandle)) {
				LocalDriver.getDriver().switchTo().window(handle);
				LocalDriver.getDriver().close();
			}
		}

		LocalDriver.getDriver().switchTo().window(originalHandle);
	}

	@Override
	public void open() throws TestException {
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript("window.open();");
	}

	@Override
	public void navigateTo(String url) throws TestException {
		LocalDriver.getDriver().navigate().to(url);
	}

	@Override
	public void switchTo(BrowserObject object) throws TestException {
		switch (object) {
			case WINDOW:
				switchToNewWindow();
				break;
			case DEFAULTCONTENT:
				switchToDefaultContent();
				break;
			case ALERT:
				LocalDriver.getDriver().switchTo().alert().accept();
				break;
			default:
				throw new TestException("Please select an available browser object to switch to.");
		}
	}

	@Override
	public void waitForWindowCount(int i, int expectedCount) throws TestException {
		WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), i);

		wait.until((WebDriver driver) -> {
			Boolean result = false;
			int actualNumberofWindows = LocalDriver.getDriver().getWindowHandles().size();
			try {
				if (actualNumberofWindows == expectedCount) {
					result = true;
					return result;
				}
			} catch (StaleElementReferenceException ex) {
				return result;
			}
			return result;
		});
	}

	@Override
	public void switchTo(BrowserObject object, int i) throws TestException {
		Set<String> windows = LocalDriver.getDriver().getWindowHandles();
		List<String> handles = new ArrayList<>();
		for (String window : windows) {
			handles.add(window);
		}
		LocalDriver.getDriver().switchTo().window(handles.get(i));
	}

	@Override
	public void back() throws TestException {
		LocalDriver.getDriver().navigate().back();
	}

}