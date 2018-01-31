package unitTests.seleniumHelperTests;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import common.utils.TestUtils;
import common.utils.WebDriverListener;
import common.utils.managers.LocalDriver;
import common.utils.managers.SHelper;
import seleniumHelper.SeleniumHelper;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;

@Listeners(WebDriverListener.class)
public class ElementTests {
	
	@BeforeMethod
	public void beforeScenario()
	{
		SHelper.set(new SeleniumHelper());
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/externalLibraries/browsers/chromedriver");
        LocalDriver.getDriver().get("http://www.google.com");
	}
	
	@Test
	public void verifyFindWebElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test2><button id=Test class=testClass >Dont click this button</button></div>');");
		Locator locator = new Locator("Test2");
		By by = new By("id");
		Locator locator2 = new Locator("Test");
		By by2 = new By("id");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		WebElement result = SHelper.get().element().find(test, locator2, by2);
		Assert.assertTrue(result != null, "Web element is null");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyFindWebElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test2><button id=Test class=testClass >Dont click this button</button></div>');");
		Locator locator = new Locator("Test2");
		By by = new By("id");
		Locator locator2 = new Locator("blah");
		By by2 = new By("id");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		SHelper.get().element().find(test, locator2, by2);
	}
	
	@Test
	public void verifyFindWebElements() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test2><button id=Test class=testClass >Dont click this button</button><button id=Test class=testClass >Dont click this button</button></div>');");
		Locator locator = new Locator("Test2");
		By by = new By("id");
		Locator locator2 = new Locator("Test");
		By by2 = new By("id");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		List<WebElement> result = SHelper.get().element().findListOf(test, locator2, by2);
		Assert.assertTrue(result.size() == 2, "Web element is null");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyFindWebElements_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test2><button id=Test class=testClass >Dont click this button</button><button id=Test class=testClass >Dont click this button</button></div>');");
		Locator locator = new Locator("Test2");
		By by = new By("id");
		Locator locator2 = new Locator("blah");
		By by2 = new By("id");
		
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(600);
		SHelper.get().element().findListOf(test, locator2, by2);
	}
	
/*	@Test
	public void verifyIsElementDisplayedInThePage_PreDefinedWebElement_ExcpetionThrown()
	{
		WebElement test = null;
		
		Boolean result = SHelper.get().element().isDisplayed(test, -1);
		
		Assert.assertFalse(result);
	}*/
	
	@Test(expectedExceptions=Exception.class)
	public void verifyGetElements_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("#Test");
		
		SHelper.get().element().getListOf(locator, null);
	}
	
	@Test
	public void getWebElement_ID() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_CssSelector() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("button[id='Test']");
		By by = new By("css");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_Xpath() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("//*[@id='Test']");
		By by = new By("xpath");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_ClassName() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=className >Dont click this button</button>');");
		Locator locator = new Locator("className");
		By by = new By("class_name");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_TagName() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("button");
		By by = new By("tag_name");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_LinkText() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<a>Dont click this button</a>');");
		Locator locator = new Locator("Dont click this button");
		By by = new By("link_text");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_PartialLinkText() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<a>Dont click this button</a>');");
		Locator locator = new Locator("Dont click");
		By by = new By("partial_link_text");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_Name() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Locator locator = new Locator("testName");
		By by = new By("name");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void getWebElement_SelectorStringIsNull() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		By by = new By("xpath");
		Thread.sleep(500);
		SHelper.get().element().get(null, by);
	}
	
	@Test
	public void verifyIsAttributePresentInElement_AttributeIsPresent() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		
		Boolean result = SHelper.get().element().isAttributePresent(test, "class");
		
		Assert.assertTrue(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyIsAttributePresentInElement_AttributeIsNotPresent() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		
		Boolean result = SHelper.get().element().isAttributePresent(test, "test");
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyIsAttributePresentInElement_ExcpetionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		Boolean result = SHelper.get().element().isAttributePresent(test, "class");
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyIsElementPresent_ElementIsPresent() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		
		Boolean result = SHelper.get().element().isDisplayed(locator, by, 2);
		
		Assert.assertTrue(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyIsElementPresent_ElementIsNotPresent() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("NotHere");
		By by = new By("id");
		Thread.sleep(500);
		
		Boolean result = SHelper.get().element().isDisplayed(locator, by, 1);
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@AfterMethod
	public void afterScenario() {
		try {
			/*if(LocalDriver.getDriver() != null) {
				LocalDriver.getDriver().quit();
			}*/
		} catch (Exception e) {
/*			if(LocalDriver.getDriver() != null) {
				LocalDriver.getDriver().quit();
			}*/
		}
		finally {
/*			if(LocalDriver.getDriver() != null) {
				LocalDriver.getDriver().quit();
			}*/
		}
	}

}
