package tests.shelper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import common.utils.TestUtils;
import common.utils.WebDriverListener;
import common.utils.managers.LocalDriver;
import common.utils.managers.SHelper;
import shelper.SeleniumHelper;
import shelper.enums.Via;
import shelper.vobjects.By;
import shelper.vobjects.Locator;

@Listeners(WebDriverListener.class)
public class ClickTests {
	
	@BeforeMethod
	public void beforeScenario()
	{
		SHelper.set(new SeleniumHelper());
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/externalLibraries/browsers/chromedriver");
        LocalDriver.getDriver().get("http://www.google.com");
	}
	
	@Test
	public void verifyClickViaJQuery() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Locator locator = new Locator("#Test");
		By by = new By("css");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on(locator, by);
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
		Locator locator = new Locator("input[id='Test']");
		By by = new By("css");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on(locator, by);
	}
	
	@Test
	public void verifyClickViaJQuery_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(700);
		WebElement element = SHelper.get().element().get(locator, by);
		
		SHelper.get().click(Via.JQUERY).on(element);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClickViaJQuery_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(700);
		WebElement element = SHelper.get().element().get(locator, by);
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
		Locator locator = new Locator("#Test");
		By by = new By("css");
		
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on(locator, by, 0);
	}
	
	@Test
	public void verifyClickViaJQuery_IntIndex_SpecialChar() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Locator locator = new Locator("input[id='Test']");
		By by = new By("css");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on(locator, by, 0);
	}
	
	@Test
	public void verifyClickViaJQuery_IntIndex_IndexIsNumeric() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Locator locator = new Locator("#Test");
		By by = new By("css");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on(locator, by, 0);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClickViaJQuery_IndexIsNumeric_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Locator locator = new Locator("input");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on(locator, null, -2);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyClickViaJQuery_IndexIsNull() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Locator locator = new Locator("#Test");
		By by = new By("css");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on(locator, by, null);
	}
	
	@Test
	public void verifyClickViaJQuery_StringIndex() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Locator locator = new Locator("#Test");
		By by = new By("css");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on(locator, by, "0");
	}
	
	@Test
	public void verifyClickViaJQuery_StringIndex_SpecialChar() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Locator locator = new Locator("input[id='Test']");
		By by = new By("css");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on(locator, by, "0");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClickViaJQuery_IndexIsString_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Locator locator = new Locator("input");
		Thread.sleep(700);
		
		SHelper.get().click(Via.JQUERY).on(locator, null, "-2");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyClick_ThrowsWebDriverException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("#Ha");
		By by = new By("css");
	
		SHelper.get().click(Via.SELENIUM).on(locator, by);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClick_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("#Ha");
	
		SHelper.get().click(Via.SELENIUM).on(locator, null);
	}
	
	@Test
	public void verifyClick() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
	
		SHelper.get().click(Via.SELENIUM).on(locator, by);
	}
	
	@Test
	public void verifyClick_PredefinedWebElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		WebElement element = SHelper.get().element().get(locator, by);
	
		SHelper.get().click(Via.SELENIUM).on(element);
	}
	
	@Test
	public void verifyClickWithStringIndex() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
	
		SHelper.get().click(Via.SELENIUM).on(locator, by, "0");
	}
	
	@Test
	public void verifyClickWithIntIndex() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
	
		SHelper.get().click(Via.SELENIUM).on(locator, by, 0);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClickWithStringIndex_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
	
		SHelper.get().click(Via.SELENIUM).on(locator, by, "7");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClickWithIntIndex_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
	
		SHelper.get().click(Via.SELENIUM).on(locator, by, 7);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyClick_PredefinedWebElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		Thread.sleep(500);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
	
		SHelper.get().click(Via.SELENIUM).on(test);
	}
	
	@Test
	public void verifyClickViaJavascript_ID() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on(locator, by);
	}
	
	@Test
	public void verifyClickViaJavascript_CSSSELECTOR() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Locator locator = new Locator("#Test");
		By by = new By("css");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on(locator, by);
	}
	
	@Test
	public void verifyClickViaJavascript_CLASSNAME() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("testClass");
		By by = new By("class_name");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on(locator, by);
	}
	
	@Test
	public void verifyClickViaJavascript_TAGNAME() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("button");
		By by = new By("tag_name");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on(locator, by);
	}
	
	@Test
	public void verifyClickViaJavascript_NAME() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Locator locator = new Locator("testName");
		By by = new By("name");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on(locator, by);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClickViaJavascript_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("#Test");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on(locator, null);
	}
	
	@Test
	public void verifyClickViaJavascript_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		WebElement element = SHelper.get().element().get(locator, by);
		
		SHelper.get().click(Via.JAVASCRIPT).on(element);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyClickViaJavascript_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		WebElement element = SHelper.get().element().get(locator, by);
		SHelper.get().page().refresh();
		
		SHelper.get().click(Via.JAVASCRIPT).on(element);
	}
	
	@Test
	public void verifyClickViaJavascript_StringIndex_ID() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on(locator, by, "0");
	}
	
	@Test
	public void verifyClickViaJavascript_StringIndex_CSSSELECTOR() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Locator locator = new Locator("#Test");
		By by = new By("css");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on(locator, by, "0");
	}
	
	@Test
	public void verifyClickViaJavascript_StringIndex_CLASSNAME() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("testClass");
		By by = new By("class_name");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on(locator, by, "0");
	}
	
	@Test
	public void verifyClickViaJavascript_StringIndex_TAGNAME() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("button");
		By by = new By("tag_name");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on(locator, by, "0");
	}
	
	@Test
	public void verifyClickViaJavascript_StringIndex_NAME() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Locator locator = new Locator("testName");
		By by = new By("name");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on(locator, by, "0");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClickViaJavascript_StringIndex_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("#Test");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on(locator, null, "0");
	}
	
	@Test
	public void verifyClickViaJavascript_IntIndex_ID() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on(locator, by, 0);
	}
	
	@Test
	public void verifyClickViaJavascript_IntIndex_CSSSELECTOR() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Locator locator = new Locator("#Test");
		By by = new By("css");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on(locator, by, 0);
	}
	
	@Test
	public void verifyClickViaJavascript_IntIndex_CLASSNAME() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("testClass");
		By by = new By("class_name");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on(locator, by, 0);
	}
	
	@Test
	public void verifyClickViaJavascript_IntIndex_TAGNAME() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("button");
		By by = new By("tag_name");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on(locator, by, 0);
	}
	
	@Test
	public void verifyClickViaJavascript_IntIndex_NAME() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Locator locator = new Locator("testName");
		By by = new By("name");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on(locator, by, 0);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClickViaJavascript_IntIndex_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		Locator locator = new Locator("#Test");
		SHelper.get().click(Via.JAVASCRIPT).on(locator, null, 0);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyClickViaJavascriptElementType_ExceptionThrown() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("testClass");
		By by = new By("class_name");
		Thread.sleep(500);
		SHelper.get().click(Via.JAVASCRIPT).on(locator, by, 2);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyRightClick_ThrowsWebDriverException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("#Ha");
		By by = new By("css");
	
		SHelper.get().click(Via.ALTERNATE).on(locator, by);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyRightClick_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("#Ha");
	
		SHelper.get().click(Via.ALTERNATE).on(locator, null);
	}
	
	@Test
	public void verifyRightClick() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
	
		SHelper.get().click(Via.ALTERNATE).on(locator, by);
	}
	
	@Test
	public void verifyRightClick_PredefinedWebElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		WebElement element = SHelper.get().element().get(locator, by);
	
		SHelper.get().click(Via.ALTERNATE).on(element);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyRightClick_PredefinedWebElement_ThrowsWebDriverException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
	
		SHelper.get().click(Via.ALTERNATE).on(null);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyRightClick_PredefinedWebElement_ThrowsWebDriverException2() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		WebElement element = SHelper.get().element().get(locator, by);
		SHelper.get().page().refresh();
		Thread.sleep(500);
	
		SHelper.get().click(Via.ALTERNATE).on(element);
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
