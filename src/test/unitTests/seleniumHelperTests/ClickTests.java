package unitTests.seleniumHelperTests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commonClasses.sharedUtils.TestUtils;
import commonClasses.sharedUtils.WebDriverListener;
import commonClasses.sharedUtils.managers.LocalDriver;
import commonClasses.sharedUtils.managers.SHelper;
import seleniumHelper.SeleniumHelper;
import seleniumHelper.enums.Via;

@Listeners(WebDriverListener.class)
public class ClickTests {
	
	@BeforeMethod
	public void beforeScenario()
	{
		SHelper.set(new SeleniumHelper());
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/ExternalLibraries/chromedriver");
        LocalDriver.getDriver().get("http://www.google.com");
	}
	
	@Test
	public void verifyClickViaJQuery_IndexIsNotNull() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on("input", null, "0");
	}
	
	@Test
	public void verifyClickViaJQuery_IndexIsNumeric() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on("input", null, 0);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClickViaJQuery_IndexIsNumeric_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on("input", null, -2);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyClickViaJQuery_IndexIsNull() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on("#Test", null, null);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyClick_ThrowsWebDriverException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		String test = "#Ha";
	
		SHelper.get().click(Via.SELENIUM).on(test, "cssSelector");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClick_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		String test = "#Ha";
	
		SHelper.get().click(Via.SELENIUM).on(test, null);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyClick_PredefinedWebElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test", "id");
		Thread.sleep(500);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
	
		SHelper.get().click(Via.SELENIUM).on(test);
	}
	
	@Test
	public void verifyClickViaJavascript_ID() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("Test", "id", null);
	}
	
	@Test
	public void verifyClickViaJavascript_CSSSELECTOR() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("#Test", "cssselector", null);
	}
	
	@Test
	public void verifyClickViaJavascript_CLASSNAME() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("testClass", "classname", "0");
	}
	
	@Test
	public void verifyClickViaJavascript_TAGNAME() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("button", "tagname", "0");
	}
	
	@Test
	public void verifyClickViaJavascript_NAME() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("testName", "name", "0");
	}
	
	@Test
	public void verifyClickViaJavascript_DEFAULT() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("Test", "xpath", null);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClickViaJavascript_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("blah", "id", null);
	}
	
	@AfterMethod
	public void afterScenario() {
/*		try {
			if(LocalDriver.getDriver() != null) {
				LocalDriver.getDriver().quit();
			}
		} catch (Exception e) {
			if(LocalDriver.getDriver() != null) {
				LocalDriver.getDriver().quit();
			}
		}
		finally {
			if(LocalDriver.getDriver() != null) {
				LocalDriver.getDriver().quit();
			}
		}*/
	}

}
