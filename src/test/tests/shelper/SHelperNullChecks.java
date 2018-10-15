package tests.shelper;

import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import common.utils.TestUtils;
import common.utils.WebDriverListener;
import common.utils.managers.LocalDriver;
import common.utils.managers.SHelper;
import shelper.SeleniumHelper;
import shelper.builders.WaitBuilder;
import shelper.enums.Condition;
import shelper.enums.Wait;
import shelper.vobjects.By;
import shelper.vobjects.Locator;

@Listeners(WebDriverListener.class)
public class SHelperNullChecks {
	
	@BeforeMethod
	public void beforeScenario()
	{
		SHelper.set(new SeleniumHelper());
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/externalLibraries/browsers/chromedriver");
        LocalDriver.getDriver().get("http://www.google.com");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyMaxTimeIsZero() throws Exception {
		try {
			SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(0)).on(new Locator("test"), new By(How.ID));
			Assert.fail("Expected exception was not thrown.");
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyMaxTimeThrowsException() throws Exception {
		try {
			SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(-4)).on(new Locator("test"), new By(How.ID));
			Assert.fail("Expected exception was not thrown.");
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyExpectedCountIsZero() throws Exception {
		try {
			SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, new WaitBuilder().forAMaxTimeOf(1).withACountOf(0)).on(new Locator("test"), new By(How.ID));
			Assert.fail("Expected exception was not thrown.");
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyExpectedCountThrowsException() throws Exception {
		try {
			SHelper.get().waitMethod(Wait.COUNT_OF_ELEMENTS, new WaitBuilder().forAMaxTimeOf(1).withACountOf(-8)).on(new Locator("test"), new By(How.ID));
			Assert.fail("Expected exception was not thrown.");
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyValueIsNull() throws Exception {
		try {
			SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder()
					.forAMaxTimeOf(1)
					.value(null))
					.on(new Locator("test"), new By(How.ID));
			Assert.fail("Expected exception was not thrown.");
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyAttributeIsZero() throws Exception {
		try {
			SHelper.get().waitMethod(Wait.PRESENCE_OF_ATTRIBUTE_TEXT, new WaitBuilder()
					.forAMaxTimeOf(1)
					.forAttribute(null)
					.value("Test"))
					.on(new Locator("test"), new By(How.ID));
			Assert.fail("Expected exception was not thrown.");
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyFailIfValueIsNotNull() throws Exception {
		try {
			SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(0).value("Testing")).on(new Locator("test"), new By(How.ID));
			Assert.fail("Expected exception was not thrown.");
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyFailIfConditionIsNotNull() throws Exception {
		try {
			SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(0).to(Condition.CONTAIN)).on(new Locator("test"), new By(How.ID));
			Assert.fail("Expected exception was not thrown.");
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyFailIfExpectedCountIsNotNull() throws Exception {
		try {
			SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(0).withACountOf(5)).on(new Locator("test"), new By(How.ID));
			Assert.fail("Expected exception was not thrown.");
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyFailIfAttributeIsNotNull() throws Exception {
		try {
			SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(0).forAttribute("test")).on(new Locator("test"), new By(How.ID));
			Assert.fail("Expected exception was not thrown.");
		} catch (Exception e) {
			throw e;
		}
	}

}
