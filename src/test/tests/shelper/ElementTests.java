package tests.shelper;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.driver.WebDriverListener;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.SeleniumHelper;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.utils.TestUtils;

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
	public void verifyFindWebElement_predefined() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test2><button id=Test class=testClass >Dont click this button</button></div>');");
		Locator locator = new Locator("Test2");
		By by = new By("id");
		TestElement testing = new TestElement(locator, by);
		System.out.println(testing.locator().value() + " " + testing.by().value());
		Locator locator2 = new Locator("Test");
		By by2 = new By("id");
		TestElement element = new TestElement(locator2, by2);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(testing);
		WebElement result = SHelper.get().element().find(test, element);
		Assert.assertTrue(result != null, "Web element is null");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyFindWebElement_predefined_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test2><button id=Test class=testClass >Dont click this button</button></div>');");
		Locator locator = new Locator("Test2");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Locator locator2 = new Locator("blah");
		By by2 = new By("id");
		TestElement element2 = new TestElement(locator2, by2);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		SHelper.get().element().find(test, element2);
	}
	
	@Test
	public void verifyFindWebElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test2><button id=Test class=testClass >Dont click this button</button></div>');");
		Locator locator = new Locator("Test2");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Locator locator2 = new Locator("Test");
		By by2 = new By("id");
		TestElement element2 = new TestElement(locator2, by2);
		Thread.sleep(500);
		WebElement result = SHelper.get().element().find(element, element2);
		Assert.assertTrue(result != null, "Web element is null");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyFindWebElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test2><button id=Test class=testClass >Dont click this button</button></div>');");
		Locator locator = new Locator("Test2");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Locator locator2 = new Locator("blah");
		By by2 = new By("id");
		TestElement element2 = new TestElement(locator2, by2);
		Thread.sleep(500);
		SHelper.get().element().find(element, element2);
	}
	
	@Test
	public void verifyFindWebElements_predefined() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test2><button id=Test class=testClass >Dont click this button</button><button id=Test class=testClass >Dont click this button</button></div>');");
		Locator locator = new Locator("Test2");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Locator locator2 = new Locator("Test");
		By by2 = new By("id");
		TestElement element2 = new TestElement(locator2, by2);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		List<WebElement> result = SHelper.get().element().findListOf(test, element2);
		Assert.assertTrue(result.size() == 2, "Web element is null");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyFindWebElements_predefined_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test2><button id=Test class=testClass >Dont click this button</button><button id=Test class=testClass >Dont click this button</button></div>');");
		Locator locator = new Locator("Test2");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Locator locator2 = new Locator("blah");
		By by2 = new By("id");
		TestElement element2 = new TestElement(locator2, by2);
		
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(600);
		SHelper.get().element().findListOf(test, element2);
	}
	
	@Test
	public void verifyFindWebElements() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test2><button id=Test class=testClass >Dont click this button</button><button id=Test class=testClass >Dont click this button</button></div>');");
		Locator locator = new Locator("Test2");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Locator locator2 = new Locator("Test");
		By by2 = new By("id");
		TestElement element2 = new TestElement(locator2, by2);
		Thread.sleep(500);
		List<WebElement> result = SHelper.get().element().findListOf(element, element2);
		Assert.assertTrue(result.size() == 2, "Web element is null");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyFindWebElements_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test2><button id=Test class=testClass >Dont click this button</button><button id=Test class=testClass >Dont click this button</button></div>');");
		Locator locator = new Locator("Test2");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Locator locator2 = new Locator("blah");
		By by2 = new By("id");
		TestElement element2 = new TestElement(locator2, by2);
		
		Thread.sleep(500);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(600);
		SHelper.get().element().findListOf(element, element2);
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
		TestElement element = new TestElement(locator, new By(""));
		
		SHelper.get().element().getListOf(element);
	}
	
	@Test
	public void getWebElement_ID() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_CssSelector() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("button[id='Test']");
		By by = new By("css");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_Xpath() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("//*[@id='Test']");
		By by = new By("xpath");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_ClassName() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=className >Dont click this button</button>');");
		Locator locator = new Locator("className");
		By by = new By("class_name");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_TagName() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("button");
		By by = new By("tag_name");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_LinkText() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<a>Dont click this button</a>');");
		Locator locator = new Locator("Dont click this button");
		By by = new By("link_text");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_PartialLinkText() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<a>Dont click this button</a>');");
		Locator locator = new Locator("Dont click");
		By by = new By("partial_link_text");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test
	public void getWebElement_Name() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Locator locator = new Locator("testName");
		By by = new By("name");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		
		Assert.assertTrue(test != null, "Web element is null");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void getWebElement_SelectorStringIsNull() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		By by = new By("xpath");
		TestElement element = new TestElement(new Locator(null), by);
		Thread.sleep(500);
		SHelper.get().element().get(element);
	}
	
	@Test
	public void verifyIsAttributePresentInElement_predefined_AttributeIsPresent() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		
		Boolean result = SHelper.get().element().isAttributePresent(test, "class");
		
		Assert.assertTrue(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyIsAttributePresentInElement_predefined_AttributeIsNotPresent() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		
		Boolean result = SHelper.get().element().isAttributePresent(test, "test");
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyIsAttributePresentInElement_predefined_ExcpetionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		Boolean result = SHelper.get().element().isAttributePresent(test, "class");
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyIsAttributePresentInElement_AttributeIsPresent() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		
		Boolean result = SHelper.get().element().isAttributePresent(element, "class");
		
		Assert.assertTrue(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyIsAttributePresentInElement_AttributeIsNotPresent() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		
		Boolean result = SHelper.get().element().isAttributePresent(element, "test");
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyIsAttributePresentInElement_ExcpetionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		Boolean result = SHelper.get().element().isAttributePresent(element, "class");
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyIsElementPresent_ElementIsPresent() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		
		Boolean result = SHelper.get().element().isDisplayed(element, Duration.ofSeconds(2));
		
		Assert.assertTrue(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyIsElementPresent_ElementIsNotPresent() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("NotHere");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		
		Boolean result = SHelper.get().element().isDisplayed(element, Duration.ofSeconds(1));
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@SuppressWarnings("unused")
	@Test(expectedExceptions=Exception.class)
	public void verifyIsElementPresent_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator(null);
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		
		Boolean result = SHelper.get().element().isDisplayed(element,Duration.ofSeconds(2));
	}
	
	@Test
	public void verifyIsElementEnabled_ElementIsEnabled() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		
		Boolean result = SHelper.get().element().isEnabled(element);
		
		Assert.assertTrue(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyIsElementEnabled_ElementIsNotEnabled() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass disabled>Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		
		Boolean result = SHelper.get().element().isEnabled(element);
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@SuppressWarnings("unused")
	@Test(expectedExceptions=Exception.class)
	public void verifyIsElementEnabled_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass disabled>Dont click this button</button>');");
		Locator locator = new Locator(null);
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		
		Boolean result = SHelper.get().element().isEnabled(element);
	}
	
	@Test
	public void verifyIsElementEnabled_predefined_ElementIsEnabled() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		WebElement test = SHelper.get().element().get(element);
		Thread.sleep(500);
		
		Boolean result = SHelper.get().element().isEnabled(test);
		
		Assert.assertTrue(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyIsElementEnabled_predefined_ElementIsNotEnabled() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass disabled>Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		WebElement test = SHelper.get().element().get(element);
		Thread.sleep(500);
		
		Boolean result = SHelper.get().element().isEnabled(test);
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@SuppressWarnings("unused")
	@Test(expectedExceptions=Exception.class)
	public void verifyIsElementEnabled_predefined_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass disabled>Dont click this button</button>');");
		Locator locator = new Locator(null);
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		WebElement test = SHelper.get().element().get(element);
		Thread.sleep(500);
		
		Boolean result = SHelper.get().element().isEnabled(test);
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
