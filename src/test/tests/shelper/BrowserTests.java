package tests.shelper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.driver.WebDriverListener;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.browser.BrowserObject;
import com.warnermedia.selenium.shared.Via;
import pages.TestInitialization;

@Listeners(WebDriverListener.class)
public class BrowserTests extends TestInitialization {

	public void goToGoogle() {
		LocalDriver.getDriver().get("http://www.google.com");
	}

	@Test
	public void verifySwitchToDefaultContent() throws Exception {
		goToGoogle();
		SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
		Assert.assertTrue(LocalDriver.getDriver().getWindowHandles().size() == 1);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifySwitchToDefaultContent_ThrowsException() throws Exception {
		goToGoogle();
		LocalDriver.getDriver().close();
		SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
	}

	@Test
	public void verifySwitchToNewWindow() throws Exception {
		goToGoogle();
		SHelper.get().browser().open();
		Thread.sleep(500);
		SHelper.get().browser().switchTo(BrowserObject.WINDOW);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifySwitchToUndefinedBrowserObject() throws Exception {
		goToGoogle();
		SHelper.get().browser().open();
		Thread.sleep(500);
		SHelper.get().browser().switchTo(BrowserObject.TAB);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifySwitchToNewWindow_ThrowsException() throws Exception {
		goToGoogle();
		LocalDriver.getDriver().close();
		SHelper.get().browser().switchTo(BrowserObject.WINDOW);
	}

	@Test
	public void verifySwitchToIFrame() throws Exception {
		goToGoogle();
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript(
				"document.write('<iframe id=\"Test\" src=\"demo_iframe.htm\" height=\"200\" width=\"300\"></iframe>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(300);

		SHelper.get().browser().switchTo(BrowserObject.FRAME, element);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifySwitchToIFrame_ThrowsException() throws Exception {
		goToGoogle();
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript(
				"document.write('<iframe id=\"Test\" src=\"demo_iframe.htm\" height=\"200\" width=\"300\"></iframe>');");
		Locator locator = new Locator("NotHere");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(300);

		SHelper.get().browser().switchTo(BrowserObject.FRAME, element);
	}

	@Test
	public void verifySwitchToIFrame_PredefinedElement() throws Exception {
		goToGoogle();
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript(
				"document.write('<iframe id=\"Test\" src=\"demo_iframe.htm\" height=\"200\" width=\"300\"></iframe>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);

		SHelper.get().browser().switchTo(BrowserObject.FRAME, test);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifySwitchToIFrame_PredefinedElement_ThrowsException() throws Exception {
		goToGoogle();
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript(
				"document.write('<iframe id=\"Test\" src=\"demo_iframe.htm\" height=\"200\" width=\"300\"></iframe>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(element);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);

		SHelper.get().browser().switchTo(BrowserObject.FRAME, test);
	}

	@Test
	public void verifySwitchToFrameByName() throws Exception {
		goToGoogle();
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript(
				"document.write('<iframe name=\"Test\" src=\"demo_iframe.htm\" height=\"200\" width=\"300\"></iframe>');");
		Thread.sleep(300);

		SHelper.get().browser().switchTo(BrowserObject.FRAME, "Test");
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifySwitchToFrameByName_ThrowsException() throws Exception {
		goToGoogle();
		((JavascriptExecutor) LocalDriver.getDriver()).executeScript(
				"document.write('<iframe name=\"Test\" src=\"demo_iframe.htm\" height=\"200\" width=\"300\"></iframe>');");
		Thread.sleep(300);

		SHelper.get().browser().switchTo(BrowserObject.FRAME, "NotHere");
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyOpenNewTab_ThrowsException() throws Exception {
		goToGoogle();
		LocalDriver.getDriver().close();
		SHelper.get().browser().open();
	}

	@Test
	public void verifyCloseOpenTabs() throws Exception {
		goToGoogle();
		Thread.sleep(500);
		SHelper.get().browser().open();
		Thread.sleep(500);
		SHelper.get().browser().open();
		Thread.sleep(500);
		SHelper.get().browser().open();
		Thread.sleep(500);
		SHelper.get().browser().open();
		Thread.sleep(500);

		SHelper.get().browser().switchTo(BrowserObject.DEFAULTCONTENT);
		SHelper.get().browser().close();
		SHelper.get().browser().waitForWindowCount(30, 1);
		Assert.assertTrue(LocalDriver.getDriver().getWindowHandles().size() == 1);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyCloseOpenTabs_ThrowsException() throws Exception {
		goToGoogle();
		LocalDriver.getDriver().close();
		SHelper.get().browser().close();
	}

	@Test
	public void verifyNavigateToUrl() throws Exception {
		goToGoogle();
		SHelper.get().browser().navigateTo("https://www.facebook.com/");
		Thread.sleep(400);

		Assert.assertEquals("https://www.facebook.com/",
				LocalDriver.getDriver().getCurrentUrl());
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyNavigateToUrl_ThrowsException() throws Exception {
		goToGoogle();
		LocalDriver.getDriver().close();
		SHelper.get().browser().navigateTo("Test");
	}

	@Test
	public void verifyWaitForWindowCount() throws Exception {
		goToGoogle();
		SHelper.get().browser().waitForWindowCount(1, 1);
	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForWindowCount_ThrowsException() throws Exception {
		goToGoogle();
		SHelper.get().browser().waitForWindowCount(1, 2);
	}

	@Test
	public void verifySwitchToSpecificWindow() throws Exception {
		goToGoogle();
		SHelper.get().browser().open();
		Thread.sleep(500);
		SHelper.get().browser().open();
		Thread.sleep(500);
		SHelper.get().browser().open();
		Thread.sleep(500);
		SHelper.get().browser().open();
		Thread.sleep(500);
		SHelper.get().browser().open();
		Thread.sleep(500);
		SHelper.get().browser().open();
		Thread.sleep(500);

		SHelper.get().browser().switchTo(BrowserObject.WINDOW, 0);
	}

	@Test(expectedExceptions = Exception.class)
	public void verifySwitchToSpecificWindow_ThrowException() throws Exception {
		goToGoogle();
		SHelper.get().browser().open();
		Thread.sleep(500);

		SHelper.get().browser().switchTo(BrowserObject.WINDOW, 8);
	}

	@Test
	public void verifySwitchToAlertAndAccept() throws Exception {
		goToGoogle();
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<p>Click the button to display an alert box.</p>"
						+ "<button id=Test onclick=myFunction()>Try it</button>" + "<script>"
						+ "function myFunction() {" + "    alert(\"Hello! I am an alert box!\");" + "}"
						+ "</script>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(700);
		SHelper.get().click(Via.SELENIUM).on(element);
		Thread.sleep(500);
		SHelper.get().browser().switchTo(BrowserObject.ALERT);
	}

	@Test
	public void verifyBrowserBackFunctionality() throws Exception {
		goToGoogle();
		SHelper.get().browser().navigateTo("https://www.facebook.com/");
		Thread.sleep(400);
		SHelper.get().browser().back();
	}

	@Test(expectedExceptions = Exception.class)
	public void verifyBack_ThrowsException() throws Exception {
		goToGoogle();
		SHelper.get().browser().navigateTo("https://www.facebook.com/");
		Thread.sleep(400);
		LocalDriver.getDriver().close();
		SHelper.get().browser().navigateTo("Test");
	}

}
