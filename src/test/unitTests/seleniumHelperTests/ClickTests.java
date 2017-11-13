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
	public void verifyClickViaJQuery() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on("#Test", "cssSelector");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClickViaJQuery_TrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on(null, null);
	}
	
	@Test
	public void verifyClickViaJQuery_ContainsSpecialChar() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on("input[id='Test']", "cssSelector");
	}
	
	@Test
	public void verifyClickViaJQuery_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(700);
		WebElement element = SHelper.get().element().get("Test", "id");
		
		SHelper.get().click(Via.JQUERY).on(element);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClickViaJQuery_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(700);
		WebElement element = SHelper.get().element().get("Test", "id");
		SHelper.get().page().refresh();
		Thread.sleep(900);
		
		SHelper.get().click(Via.JQUERY).on(element);
	}
	
	@Test
	public void verifyClickViaJQuery_IntIndex() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on("#Test", "cssSelector", 0);
	}
	
	@Test
	public void verifyClickViaJQuery_IntIndex_SpecialChar() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on("input[id='Test']", "cssSelector", 0);
	}
	
	@Test
	public void verifyClickViaJQuery_IntIndex_IndexIsNumeric() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on("#Test", "cssSelector", 0);
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
		
		SHelper.get().click(Via.JQUERY).on("#Test", "cssSelector", null);
	}
	
	@Test
	public void verifyClickViaJQuery_StringIndex() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on("#Test", "cssSelector", "0");
	}
	
	@Test
	public void verifyClickViaJQuery_StringIndex_SpecialChar() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on("input[id='Test']", "cssSelector", "0");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClickViaJQuery_IndexIsString_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on("input", null, "-2");
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
	
	@Test
	public void verifyClick() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		String test = "Test";
	
		SHelper.get().click(Via.SELENIUM).on(test, "id");
	}
	
	@Test
	public void verifyClick_PredefinedWebElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		WebElement element = SHelper.get().element().get("Test", "id");
	
		SHelper.get().click(Via.SELENIUM).on(element);
	}
	
	@Test
	public void verifyClickWithStringIndex() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		String test = "Test";
	
		SHelper.get().click(Via.SELENIUM).on(test, "id", "0");
	}
	
	@Test
	public void verifyClickWithIntIndex() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		String test = "Test";
	
		SHelper.get().click(Via.SELENIUM).on(test, "id", 0);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClickWithStringIndex_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		String test = "Test";
	
		SHelper.get().click(Via.SELENIUM).on(test, "id", "7");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClickWithIntIndex_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		String test = "Test";
	
		SHelper.get().click(Via.SELENIUM).on(test, "id", 7);
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
		SHelper.get().click(Via.JAVASCRIPT).on("Test", "id");
	}
	
	@Test
	public void verifyClickViaJavascript_CSSSELECTOR() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("#Test", "cssselector");
	}
	
	@Test
	public void verifyClickViaJavascript_CLASSNAME() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("testClass", "classname");
	}
	
	@Test
	public void verifyClickViaJavascript_TAGNAME() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("button", "tagname");
	}
	
	@Test
	public void verifyClickViaJavascript_NAME() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("testName", "name");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClickViaJavascript_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("#Test", null);
	}
	
	@Test
	public void verifyClickViaJavascript_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		WebElement element = SHelper.get().element().get("Test", "id");
		
		SHelper.get().click(Via.JAVASCRIPT).on(element);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyClickViaJavascript_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		WebElement element = SHelper.get().element().get("Test", "id");
		SHelper.get().page().refresh();
		
		SHelper.get().click(Via.JAVASCRIPT).on(element);
	}
	
	@Test
	public void verifyClickViaJavascript_StringIndex_ID() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("Test", "id", "0");
	}
	
	@Test
	public void verifyClickViaJavascript_StringIndex_CSSSELECTOR() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("#Test", "cssselector", "0");
	}
	
	@Test
	public void verifyClickViaJavascript_StringIndex_CLASSNAME() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("testClass", "classname", "0");
	}
	
	@Test
	public void verifyClickViaJavascript_StringIndex_TAGNAME() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("button", "tagname", "0");
	}
	
	@Test
	public void verifyClickViaJavascript_StringIndex_NAME() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("testName", "name", "0");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClickViaJavascript_StringIndex_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("#Test", null, "0");
	}
	
	@Test
	public void verifyClickViaJavascript_IntIndex_ID() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("Test", "id", 0);
	}
	
	@Test
	public void verifyClickViaJavascript_IntIndex_CSSSELECTOR() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("#Test", "cssselector", 0);
	}
	
	@Test
	public void verifyClickViaJavascript_IntIndex_CLASSNAME() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("testClass", "classname", 0);
	}
	
	@Test
	public void verifyClickViaJavascript_IntIndex_TAGNAME() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("button", "tagname", 0);
	}
	
	@Test
	public void verifyClickViaJavascript_IntIndex_NAME() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("testName", "name", 0);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClickViaJavascript_IntIndex_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("#Test", null, 0);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClickViaJavascriptElementType_ExceptionThrown() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on("testClass", "className", 2);
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
