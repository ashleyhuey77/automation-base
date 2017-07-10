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

import commonClasses.sharedUtils.TestUtils;
import commonClasses.sharedUtils.WebDriverListener;
import commonClasses.sharedUtils.managers.LocalDriver;
import commonClasses.sharedUtils.managers.SHelper;
import seleniumHelper.SeleniumHelper;

@Listeners(WebDriverListener.class)
public class ElementTests {
	
	@BeforeMethod
	public void beforeScenario()
	{
		SHelper.set(new SeleniumHelper());
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/ExternalLibraries/chromedriver");
        LocalDriver.getDriver().get("http://www.google.com");
	}
	
	@Test
	public void verifyFindWebElement() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test2><button id=Test class=testClass >Dont click this button</button></div>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test2", "id");
		WebElement result = SHelper.get().element().find(test, "Test", "id");
		Assert.assertTrue(result != null, "Web element is null");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyFindWebElement_ThrowsException() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test2><button id=Test class=testClass >Dont click this button</button></div>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test2", "id");
		SHelper.get().element().find(test, "blah", "id");
	}
	
	@Test
	public void verifyFindWebElements() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test2><button id=Test class=testClass >Dont click this button</button><button id=Test class=testClass >Dont click this button</button></div>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test2", "id");
		List<WebElement> result = SHelper.get().element().findListOf(test, "Test", "id");
		Assert.assertTrue(result.size() == 2, "Web element is null");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyFindWebElements_ThrowsException() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test2><button id=Test class=testClass >Dont click this button</button><button id=Test class=testClass >Dont click this button</button></div>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test2", "id");
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(600);
		SHelper.get().element().findListOf(test, "id", "blah");
	}
	
/*	@Test
	public void verifyIsElementDisplayedInThePage_PreDefinedWebElement_ExcpetionThrown()
	{
		WebElement test = null;
		
		Boolean result = SHelper.get().element().isDisplayed(test, -1);
		
		Assert.assertFalse(result);
	}*/
	
	@Test(expectedExceptions=Exception.class)
	public void verifyGetElements_ExceptionThrown() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		String test = "#Test";
		
		SHelper.get().element().getListOf(test, null);
	}
	
	@Test
	public void getWebElement_ID() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_CssSelector() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("button[id='Test']", "cssSelector");
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_Xpath() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("//*[@id='Test']", "xpath");
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_ClassName() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=className >Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("className", "ClassName");
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_TagName() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("button", "TagName");
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_LinkText() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<a>Dont click this button</a>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Dont click this button", "LinkText");
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_PartialLinkText() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<a>Dont click this button</a>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Dont click this button", "partialLinkText");
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_Name() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("testName", "name");
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_Default() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test", "hullabaloo");
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void getWebElement_SelectorStringIsNull() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().element().get(null, "hullabaloo");
	}
	
	@Test
	public void verifyIsAttributePresentInElement_AttributeIsPresent() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		Boolean result = SHelper.get().element().isAttributePresent(test, "class");
		
		Assert.assertTrue(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyIsAttributePresentInElement_AttributeIsNotPresent() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		Boolean result = SHelper.get().element().isAttributePresent(test, "test");
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyIsAttributePresentInElement_ExcpetionThrown() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test", "id");
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		Boolean result = SHelper.get().element().isAttributePresent(test, "class");
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyIsElementPresent_ElementIsPresent() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		
		Boolean result = SHelper.get().element().isDisplayed("Test", "id", 2);
		
		Assert.assertTrue(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyIsElementPresent_ElementIsNotPresent() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		
		Boolean result = SHelper.get().element().isDisplayed("NotHere", "id", 1);
		
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
