package tests.shelper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.config.SHelper;
import com.config.TestException;
import com.config.setup.browser.LocalDriver;
import com.config.setup.browser.WebDriverListener;
import com.selenium.By;
import com.selenium.Locator;
import com.selenium.SeleniumHelper;
import com.selenium.TestElement;
import com.selenium.wait.Condition;
import com.selenium.wait.Wait;
import com.selenium.wait.WaitBuilder;
import com.utils.TestUtils;

import java.time.Duration;

@Listeners(WebDriverListener.class)
public class Wait_Clickable_tests {

	Locator locator = new Locator("Test");
	By by = new By(How.ID);
	TestElement element = new TestElement(locator, by);

	@BeforeMethod
	public void beforeScenario() {
		SHelper.set(new SeleniumHelper());
		System.setProperty("webdriver.chrome.driver",
				TestUtils.getRelativePath() + "/externalLibraries/browsers/chromedriver");
		LocalDriver.getDriver().get("http://www.google.com");
	}

	@Test
	public void verifyWaitForElementToBeClickable() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(2))).on(element);

	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForElementToBeClickable_ThrowsException() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test>Button</button>');");
		Locator locator2 = new Locator("NotHere");
		By by2 = new By("id");
		TestElement element2 = new TestElement(locator2, by2);
		Thread.sleep(300);

		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(2))).on(element2);

	}

	@Test
	public void verifyWaitForElementToBeClickable_PredefinedWebElement() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		WebElement button = SHelper.get().element().get(element);

		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(2))).on(button);

	}

	@Test(expectedExceptions = WebDriverException.class)
	public void verifyWaitForElementToBeClickable__PredefinedWebElement_ThrowsException() throws Exception {
		((JavascriptExecutor) LocalDriver.getDriver())
				.executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		WebElement button = SHelper.get().element().get(element);
		SHelper.get().page().refresh();
		Thread.sleep(900);

		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(2))).on(button);

	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForClickableInvalidWaitTime_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder())
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForClickableNonNullAttribute_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder()
				.forAMaxTimeOf(Duration.ofSeconds(2))
				.forAttribute("Test"))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForClickableNonNullValue_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder()
				.forAMaxTimeOf(Duration.ofSeconds(2))
				.value("Test"))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForClickableNonNullCondition_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder()
				.forAMaxTimeOf(Duration.ofSeconds(2))
				.to(Condition.CONTAIN))
				.on(element);
	}
	
	@Test(expectedExceptions=TestException.class)
	public void verifyWaitForClickableNonNullTotalCount_ExceptionThrown() throws Exception {
		SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder()
				.forAMaxTimeOf(Duration.ofSeconds(2))
				.withACountOf(2))
				.on(element);
	}
	
	@AfterMethod
	public void afterScenario() {
		
	}

}
