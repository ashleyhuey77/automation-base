package unitTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import commonClasses.sharedUtils.ExtensionMethods;
import commonClasses.sharedUtils.LocalDriverManager;
import commonClasses.sharedUtils.TestUtils;
import seleniumHelper.seleniumHelper.LookUp;
import seleniumHelper.seleniumHelper.SeleniumHelper;

public class SeleniumHelperTests {
	
	private WebDriver browser;
	SeleniumHelper sHelp;
	
	@Before
	public void before()
	{
		browser = LocalDriverManager.getDriver();
		sHelp = new SeleniumHelper();
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/ExternalLibraries/chromedriver");
        browser = new ChromeDriver();
        browser.get("http://www.google.com");
	}

	@Test(expected=WebDriverException.class)
	public void verifyClearAllTextByBackspacing_ExceptionThrown() {
			((JavascriptExecutor)browser).executeScript("document.write('<input id=Test value=Testing></input>');");
			String test = "#Ha";
		
			sHelp.clearAllTextByBackspacing(test, "cssSelector");
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyClearAllTextByBackspacing_ElementPredefined_ExceptionThrown() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test value=Testing></input>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Test", "id");
		Thread.sleep(500);
		browser.navigate().refresh();
		Thread.sleep(500);
	
		sHelp.clearAllTextByBackspacing(test);
	}
	
	@Test
	public void verifyGetTextFromWebElement_PreDefinedElement_WebElementIsNull() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test value=Testing></input>');");
		Thread.sleep(500);
		WebElement test = null;
		Thread.sleep(500);
		
		String value = sHelp.getTextFromWebElement(test);
		
		assertTrue("A value was returned. Expected the value to return null", ExtensionMethods.isNullOrBlank(value));
	}
	
	@Test//(expected=Exception.class)
	public void verifyIsElementDisplayedInThePage_PreDefinedWebElement_ExcpetionThrown()
	{
		WebElement test = null;
		
		sHelp.isElementDisplayedInThePage(test, -1);
	}
	
	@Test
	public void validateEnums()
	{
		LookUp l = new LookUp();
		System.out.println(l);
		System.out.println(LookUp.IdentifyBy.Id);
		System.out.println(LookUp.IdentifyBy.valueOf(LookUp.IdentifyBy.Id.toString()));
		System.out.println(LookUp.IdentifyBy.CssSelector);
		System.out.println(LookUp.IdentifyBy.valueOf(LookUp.IdentifyBy.CssSelector.toString()));
		System.out.println(LookUp.IdentifyBy.Name);
		System.out.println(LookUp.IdentifyBy.valueOf(LookUp.IdentifyBy.Name.toString()));
		System.out.println(LookUp.IdentifyBy.Xpath);
		System.out.println(LookUp.IdentifyBy.valueOf(LookUp.IdentifyBy.Xpath.toString()));
		System.out.println(LookUp.IdentifyBy.ClassName);
		System.out.println(LookUp.IdentifyBy.valueOf(LookUp.IdentifyBy.ClassName.toString()));
		System.out.println(LookUp.IdentifyBy.TagName);
		System.out.println(LookUp.IdentifyBy.valueOf(LookUp.IdentifyBy.TagName.toString()));
		System.out.println(LookUp.IdentifyBy.LinkText);
		System.out.println(LookUp.IdentifyBy.valueOf(LookUp.IdentifyBy.LinkText.toString()));
		System.out.println(LookUp.IdentifyBy.PartialLinkText);
		System.out.println(LookUp.IdentifyBy.valueOf(LookUp.IdentifyBy.PartialLinkText.toString()));
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyWaitForElementToLoad_ExceptionThrown()
	{
		sHelp.waitForElementToLoad("Test", "id", 1);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifySendKeys_ExceptionThrown()
	{
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test value=Testing></input>');");
		String test = "#Ha";
		
		sHelp.sendKeys(test, "cssSelector", "Testing");
	}
	
	@Test(expected=WebDriverException.class)
	public void verifySendKeys_ElementPreDefined_ExceptionThrown() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test value=Testing></input>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Test", "id");
		Thread.sleep(500);
		browser.navigate().refresh();
		Thread.sleep(500);
		
		sHelp.sendKeys(test, "Testing");
	}
	
	@Test(expected=Exception.class)
	public void verifyGetElements_ExceptionThrown() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test value=Testing></input>');");
		String test = "#Test";
		
		sHelp.getElements(test, null);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyClear_ExceptionThrown()
	{
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test value=Testing></input>');");
		String test = "#Ha";
		
		sHelp.clear(test, "cssSelector");
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyClear_ElementPreDefined_ExceptionThrown() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test value=Testing></input>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Test", "id");
		Thread.sleep(500);
		browser.navigate().refresh();
		Thread.sleep(500);
		
		sHelp.clear(test);
	}
	
	@Test
	public void verifyClickViaJQuery_SelectorStringDoesNotContainChar() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test value=Testing></input>');");
		((JavascriptExecutor)browser).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(500);
		
		sHelp.clickViaJQuery("#Test", null);
	}
	
	@Test
	public void verifyGetTextInTextFieldViaJavascript_CharsInCssSelectorString() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test value=Testing></input>');");
		Thread.sleep(500);
		
		String result = sHelp.getTextInTextBoxViaJavascript("input[id='Test']", "cssSelector", false, null);
		
		assertEquals("The two values do not match", result, "Testing");
	}
	
	@Test
	public void verifyGetTextInTextFieldViaJavascript_Id() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test value=Testing></input>');");
		Thread.sleep(500);
		
		String result = sHelp.getTextInTextBoxViaJavascript("Test", "id", false, null);
		
		assertEquals("The two values do not match", result, "Testing");
	}
	
	@Test
	public void verifyGetTextInTextFieldViaJavascript_ClassName() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test class=className value=Testing></input>');");
		Thread.sleep(500);
		
		String result = sHelp.getTextInTextBoxViaJavascript("className", "className", true, "0");
		
		assertEquals("The two values do not match", result, "Testing");
	}
	
	@Test
	public void verifyGetTextInTextFieldViaJavascript_TagName() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test value=Testing></input>');");
		Thread.sleep(500);
		
		String result = sHelp.getTextInTextBoxViaJavascript("input", "tagName", true, "0");
		
		assertEquals("The two values do not match", result, "Testing");
	}
	
	@Test
	public void verifyGetTextInTextFieldViaJavascript_Name() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test name=name value=Testing></input>');");
		Thread.sleep(500);
		
		String result = sHelp.getTextInTextBoxViaJavascript("name", "name", true, "0");
		
		assertEquals("The two values do not match", result, "Testing");
	}
	
	@Test
	public void verifyGetTextInTextFieldViaJavascript_DefaultCase() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test name=name value=Testing></input>');");
		Thread.sleep(500);
		
		String result = sHelp.getTextInTextBoxViaJavascript("Test", "xpath", false, null);
		
		assertEquals("The two values do not match", result, "Testing");
	}
	
	@Test
	public void verifyClickViaJQuery_IndexIsNotNull() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)browser).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(700);
		
		sHelp.clickViaJQuery("input", "0");
	}
	
	@Test
	public void verifyClickViaJQuery_IndexIsNull() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test class=Testing value=Testing></input>');");
		((JavascriptExecutor)browser).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(700);
		
		sHelp.clickViaJQuery("#Test", null);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyClick_ThrowsWebDriverException()
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		String test = "#Ha";
	
		sHelp.click(test, "cssSelector");
	}
	
	@Test(expected=Exception.class)
	public void verifyClick_ThrowsException()
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		String test = "#Ha";
	
		sHelp.click(test, null);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyClick_PredefinedWebElement_ThrowsException() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Test", "id");
		Thread.sleep(500);
		browser.navigate().refresh();
		Thread.sleep(500);
	
		sHelp.click(test);
	}
	
	@Test
	public void getWebElement_ID() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Test", "id");
		
		assertTrue("Web element is null", test != null);
	}
	
	@Test
	public void getWebElement_CssSelector() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("button[id='Test']", "cssSelector");
		
		assertTrue("Web element is null", test != null);
	}
	
	@Test
	public void getWebElement_Xpath() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("//*[@id='Test']", "xpath");
		
		assertTrue("Web element is null", test != null);
	}
	
	@Test
	public void getWebElement_ClassName() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=className >Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("className", "ClassName");
		
		assertTrue("Web element is null", test != null);
	}
	
	@Test
	public void getWebElement_TagName() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test>Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("button", "TagName");
		
		assertTrue("Web element is null", test != null);
	}
	
	@Test
	public void getWebElement_LinkText() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<a>Dont click this button</a>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Dont click this button", "LinkText");
		
		assertTrue("Web element is null", test != null);
	}
	
	@Test
	public void getWebElement_PartialLinkText() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<a>Dont click this button</a>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Dont click this button", "partialLinkText");
		
		assertTrue("Web element is null", test != null);
	}
	
	@Test
	public void getWebElement_Name() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("testName", "name");
		
		assertTrue("Web element is null", test != null);
	}
	
	@Test
	public void getWebElement_Default() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Test", "hullabaloo");
		
		assertTrue("Web element is null", test != null);
	}
	
	@Test(expected=Exception.class)
	public void getWebElement_SelectorStringIsNull() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		sHelp.getElement(null, "hullabaloo");
	}
	
	@Test
	public void verifyClickViaJavascript_ID() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		sHelp.clickViaJavascript("Test", "id", null);
	}
	
	@Test
	public void verifyClickViaJavascript_CSSSELECTOR() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		sHelp.clickViaJavascript("#Test", "cssselector", null);
	}
	
	@Test
	public void verifyClickViaJavascript_CLASSNAME() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		sHelp.clickViaJavascript("testClass", "classname", "0");
	}
	
	@Test
	public void verifyClickViaJavascript_TAGNAME() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		sHelp.clickViaJavascript("button", "tagname", "0");
	}
	
	@Test
	public void verifyClickViaJavascript_NAME() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test name=testName >Dont click this button</button>');");
		Thread.sleep(500);
		sHelp.clickViaJavascript("testName", "name", "0");
	}
	
	@Test
	public void verifyClickViaJavascript_DEFAULT() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		sHelp.clickViaJavascript("Test", "xpath", null);
	}
	
	@Test(expected=Exception.class)
	public void verifyClickViaJavascript_ExceptionThrown() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		sHelp.clickViaJavascript("blah", "id", null);
	}
	
	@Test
	public void verifyDoesAttributeContainTheExpectedValue_AttributeContainsValue() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		
		Boolean result = sHelp.doesAttributeContainTheExpectedValue("Test", "id", "class", "testClass");
		
		assertEquals("The boolean results do not match", result, true);
		
	}
	
	@Test
	public void verifyDoesAttributeContainTheExpectedValue_AttributeDoesNotContainValue() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		
		Boolean result = sHelp.doesAttributeContainTheExpectedValue("Test", "id", "class", "not correct");
		
		assertEquals("The boolean results do not match", result, false);
	}
	
	@Test
	public void verifyDoesAttributeContainTheExpectedValue_NullValue() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test >Dont click this button</button>');");
		Thread.sleep(500);
		
		Boolean result = sHelp.doesAttributeContainTheExpectedValue("Test", "id", "class", "testClass");
		
		assertEquals("The boolean results do not match", result, false);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyDoesAttributeContainTheExpectedValue_ExceptionThrown() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		
		sHelp.doesAttributeContainTheExpectedValue("testing", "id", "class", "testClass");
	}
	
	@Test
	public void verifyFindWebElement() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=Test2><button id=Test class=testClass >Dont click this button</button></div>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Test2", "id");
		WebElement result = sHelp.findWebElement(test, "id", "Test");
		assertTrue("Web element is null", result != null);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyFindWebElement_ThrowsException() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=Test2><button id=Test class=testClass >Dont click this button</button></div>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Test2", "id");
		sHelp.findWebElement(test, "id", "blah");
	}
	
	@Test
	public void verifyFindWebElements() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=Test2><button id=Test class=testClass >Dont click this button</button><button id=Test class=testClass >Dont click this button</button></div>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Test2", "id");
		List<WebElement> result = sHelp.findWebElements(test, "id", "Test");
		assertTrue("Web element is null", result.size() == 2);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyFindWebElements_ThrowsException() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=Test2><button id=Test class=testClass >Dont click this button</button><button id=Test class=testClass >Dont click this button</button></div>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Test2", "id");
		browser.navigate().refresh();
		Thread.sleep(600);
		sHelp.findWebElements(test, "id", "blah");
	}
	
	@Test
	public void verifyGetWebElementAttribute() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		String result = sHelp.getWebElementAttribute("Test", "id", "class");
		assertEquals("The attribute values do not match", result, "testClass");
	}
	
	@Test
	public void verifyGetWebElementAttribute_AttributeNotPresent() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		String result = sHelp.getWebElementAttribute("Test", "id", "test");
		assertTrue("The attribute values do not match", ExtensionMethods.isNullOrBlank(result));
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyGetWebElementAttribute_ExceptionThrown() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		sHelp.getWebElementAttribute("NotHere", "id", "test");
	}
	
	@Test
	public void verifyGetWebElementAttribute_ElementPredefined() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Test", "id");
		String result = sHelp.getWebElementAttribute(test, "class");
		assertEquals("The attribute values do not match", result, "testClass");
	}
	
	@Test
	public void verifyGetWebElementAttribute_ElementPredefined_AttributeNotPresent() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Test", "id");
		String result = sHelp.getWebElementAttribute(test, "test");
		assertTrue("The attribute values do not match", ExtensionMethods.isNullOrBlank(result));
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyGetWebElementAttribute_ElementPredefined_ExceptionThrown() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Test", "id");
		browser.navigate().refresh();
		Thread.sleep(500);
		sHelp.getWebElementAttribute(test, "test");
	}
	
	@Test
	public void verifyGetWidthOfWebElement() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		
		int result = sHelp.getWidthOfWebElement("Test", "id");
		
		assertEquals("width result values do not match", result, 127);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyGetWidthOfWebElement_ExceptionThrown() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		
		sHelp.getWidthOfWebElement("derp", "id");
	}
	
	@Test
	public void verifyIsAnyTextDisplayedInElement_ElementContainsText() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=Test class=testClass>Some Text</div>');");
		Thread.sleep(500);
		
		Boolean result = sHelp.isAnyTextDisplayedInElement("Test", "id");
		
		assertEquals("The boolean results do not match", result, true);
	}
	
	@Test
	public void verifyIsAnyTextDisplayedInElement_ElementDoesntContainText() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=Test class=testClass></div>');");
		Thread.sleep(500);
		
		Boolean result = sHelp.isAnyTextDisplayedInElement("Test", "id");
		
		assertEquals("The boolean results do not match", result, false);
	}
	
	@Test
	public void verifyIsAttributePresentInElement_AttributeIsPresent() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Test", "id");
		
		Boolean result = sHelp.isAttributePresentInElement(test, "class");
		
		assertEquals("The boolean results do not match", result, true);
	}
	
	@Test
	public void verifyIsAttributePresentInElement_AttributeIsNotPresent() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Test", "id");
		
		Boolean result = sHelp.isAttributePresentInElement(test, "test");
		
		assertEquals("The boolean results do not match", false, result);
	}
	
	@Test
	public void verifyIsAttributePresentInElement_ExcpetionThrown() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Test", "id");
		browser.navigate().refresh();
		Thread.sleep(500);
		
		Boolean result = sHelp.isAttributePresentInElement(test, "class");
		
		assertEquals("The boolean results do not match", result, false);
	}
	
	@Test
	public void verifyIsElementPresent_ElementIsPresent() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		
		Boolean result = sHelp.isElementPresent("Test", "id");
		
		assertEquals("The boolean results do not match", result, true);
	}
	
	@Test
	public void verifyIsElementPresent_ElementIsNotPresent() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		
		Boolean result = sHelp.isElementPresent("NotHere", "id");
		
		assertEquals("The boolean results do not match", result, false);
	}
	
	@Test
	public void verfifyIsTextDisplayedInSpecifiedElement_TextIsDisplayed() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=Test class=testClass >Testing123</div>');");
		Thread.sleep(500);
		
		Boolean result = sHelp.isTextDisplayedInSpecifiedElement("Test", "id", "Testing123");
		
		assertEquals("The boolean results do not match", true, result);
	}
	
	@Test
	public void verfifyIsTextDisplayedInSpecifiedElement_TextIsNotDisplayed() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=Test class=testClass >Testing123</div>');");
		Thread.sleep(500);
		
		Boolean result = sHelp.isTextDisplayedInSpecifiedElement("Test", "id", "AlternateFacts");
		
		assertEquals("The boolean results do not match", false, result);
	}
	
	@Test(expected=Exception.class)
	public void verfifyIsTextDisplayedInSpecifiedElement_ThrowsException() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=Test class=testClass >Testing123</div>');");
		Thread.sleep(500);
		
		sHelp.isTextDisplayedInSpecifiedElement("Test", "id", null);
	}
	
	@Test
	public void verfifyIsTextDisplayedInSpecifiedElement_PredefinedElement_TextIsDisplayed() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=Test class=testClass >Testing123</div>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Test", "id");
		
		Boolean result = sHelp.isTextDisplayedInSpecifiedElement(test, "Testing123");
		
		assertEquals("The boolean results do not match", true, result);
	}
	
	@Test
	public void verfifyIsTextDisplayedInSpecifiedElement_PredefinedElement_TextIsNotDisplayed() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=Test class=testClass >Testing123</div>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Test", "id");
		
		Boolean result = sHelp.isTextDisplayedInSpecifiedElement(test, "AlternateFacts");
		
		assertEquals("The boolean results do not match", false, result);
	}
	
	@Test(expected=Exception.class)
	public void verfifyIsTextDisplayedInSpecifiedElement_PredefinedElement_ThrowsException() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=Test class=testClass >Testing123</div>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Test", "id");
		
		sHelp.isTextDisplayedInSpecifiedElement(test, null);
	}
	
	@Test
	public void verifyMouseOver() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Test", "id");
		
		sHelp.mouseOver(test);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyMouseOver_ExceptionThrown() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Thread.sleep(500);
		WebElement test = sHelp.getElement("Test", "id");
		browser.navigate().refresh();
		Thread.sleep(500);
		
		sHelp.mouseOver(test);
	}
	
	@Test
	public void verifyMoveToElement() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Thread.sleep(500);
		
		sHelp.moveToElement("Test", "id");
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyMoveToElement_ThrowsException() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		
		sHelp.moveToElement("NotHere", "id");
	}
	
	@Test
	public void verifyNavigateToUrl() throws InterruptedException
	{
		sHelp.navigateToUrl("https://www.facebook.com/");
		Thread.sleep(400);
		
		assertEquals("The url values do not match", "https://www.facebook.com/", browser.getCurrentUrl());
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyNavigateToUrl_ThrowsException() throws InterruptedException
	{
		browser.close();
		sHelp.navigateToUrl("Test");
	}
	
	@Test
	public void verifyOpenANewTab()
	{
		sHelp.openANewTab();
		assertTrue("Total tabs open is not correct", browser.getWindowHandles().size() == 2);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyOpenANewTab_ThrowsException()
	{
		browser.close();
		sHelp.openANewTab();
	}
	
	@Test
	public void verifyRefreshThePage()
	{
		sHelp.refreshThePage();
		assertTrue("Total tabs open is not correct", browser.getWindowHandles().size() == 1);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyRefreshThePage_ThrowsException()
	{
		browser.close();
		sHelp.refreshThePage();
	}
	
	@Test
	public void verifySwitchToDefaultContent()
	{
		sHelp.switchToDefaultContent();
		assertTrue("Total tabs open is not correct", browser.getWindowHandles().size() == 1);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifySwitchToDefaultContent_ThrowsException()
	{
		browser.close();
		sHelp.switchToDefaultContent();
	}
	
	@Test
	public void verifyScrollToBottomOfPage() throws Exception
	{
		sHelp.scrollToBottomOfPage();
		assertTrue("Total tabs open is not correct", browser.getWindowHandles().size() == 1);
	}
	
	@Test(expected=Exception.class)
	public void verifyScrollToBottomOfPage_ThrowsException() throws Exception
	{
		browser.close();
		sHelp.scrollToBottomOfPage();
	}
	
	@Test
	public void verifyScrollToElement() throws Exception
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Thread.sleep(300);
		sHelp.scrollToElement("Test", "id");
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyScrollToElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Thread.sleep(300);
		sHelp.scrollToElement("NotHere", "id");
	}
	
	@Test
	public void verifyScrollToElement_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Thread.sleep(300);
		WebElement test = sHelp.getElement("Test", "id");
		sHelp.scrollToElement(test);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyScrollToElement_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Thread.sleep(300);
		WebElement test = sHelp.getElement("Test", "id");
		browser.navigate().refresh();
		Thread.sleep(400);
		sHelp.scrollToElement(test);
	}
	
	@Test
	public void verifySelectOptionFromDropDownMenu() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<select id=Test class=testClass ><option name=test>Test1</option><option>Test2</option><option>Test3</option><option>Test4</option><option>Test5</option></select>');");
		Thread.sleep(300);
		
		sHelp.selectOptionFromDropDownMenu("Test", "id", "Test2");
	}
	
	@Test(expected=WebDriverException.class)
	public void verifySelectOptionFromDropDownMenu_ExceptionThrown() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<select id=Test class=testClass ><option name=test>Test1</option><option>Test2</option><option>Test3</option><option>Test4</option><option>Test5</option></select>');");
		Thread.sleep(300);
		
		sHelp.selectOptionFromDropDownMenu("notHere", "name", "Test2");
	}
	
	@Test
	public void verifySwitchToIFrame() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<iframe id=\"Test\" src=\"demo_iframe.htm\" height=\"200\" width=\"300\"></iframe>');");
		Thread.sleep(300);
		
		sHelp.switchToIFrame("Test", "id");
	}
	
	@Test(expected=WebDriverException.class)
	public void verifySwitchToIFrame_ThrowsException() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<iframe id=\"Test\" src=\"demo_iframe.htm\" height=\"200\" width=\"300\"></iframe>');");
		Thread.sleep(300);
		
		sHelp.switchToIFrame("NotHere", "id");
	}
	
	@Test
	public void verifySwitchToIFrame_PredefinedElement() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<iframe id=\"Test\" src=\"demo_iframe.htm\" height=\"200\" width=\"300\"></iframe>');");
		Thread.sleep(300);
		WebElement test = sHelp.getElement("Test", "id");
		
		sHelp.switchToIFrame(test);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifySwitchToIFrame_PredefinedElement_ThrowsException() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<iframe id=\"Test\" src=\"demo_iframe.htm\" height=\"200\" width=\"300\"></iframe>');");
		Thread.sleep(300);
		WebElement test = sHelp.getElement("Test", "id");
		browser.navigate().refresh();
		Thread.sleep(500);
		
		sHelp.switchToIFrame(test);
	}
	
	@Test
	public void verifySwitchToNewWindow() throws InterruptedException
	{
		sHelp.openANewTab();
		Thread.sleep(500);
		sHelp.switchToNewWindow();
	}
	
	@Test(expected=WebDriverException.class)
	public void verifySwitchToNewWindow_ThrowsException() throws InterruptedException
	{
		browser.close();
		sHelp.switchToNewWindow();
	}
	
	@Test
	public void verifyWaitForAttributeToContainACertainValue() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		sHelp.waitForAttributeToContainACertainValue("Test", "id", "class", "someClassValue", 2);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyWaitForAttributeToContainACertainValue_ExceptionThrown() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		sHelp.waitForAttributeToContainACertainValue("notHere", "id", "class", "someClassValue", 1);
	}
	
	@Test
	public void verifyWaitForAttributeToDisappear() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test></input>');");
		Thread.sleep(300);
		
		sHelp.waitForAttributeToDisappear("Test", "id", "value", 1);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyWaitForAttributeToDisappear_ThrowsException() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		sHelp.waitForAttributeToDisappear("Test", "id", "class", 1);
	}
	
	@Test
	public void verifyWaitForAttributeToDisappear_PredefinedElement() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<input id=Test></input>');");
		Thread.sleep(300);
		WebElement test = sHelp.getElement("Test", "id");
		
		sHelp.waitForAttributeToDisappear(test, "value", 1);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyWaitForAttributeToDisappear_PredefinedElement_ThrowsException() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = sHelp.getElement("Test", "id");
		
		sHelp.waitForAttributeToDisappear(test, "class", 1);
	}
	
	@Test
	public void verifyWaitForAttributeToEqualACertainValue() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		sHelp.waitForAttributeToEqualACertainValue("Test", "id", "class", "someClassValue", 2);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyWaitForAttributeToEqualACertainValue_ExceptionThrown() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		
		sHelp.waitForAttributeToEqualACertainValue("notHere", "id", "class", "someClassValue", 1);
	}
	
	@Test
	public void verifyWaitForAttributeToEqualACertainValue_PredefinedElement() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = sHelp.getElement("Test", "id");
		
		sHelp.waitForAttributeToEqualACertainValue(test, "class", "someClassValue", 2);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyWaitForAttributeToEqualACertainValue_PredefinedElement_ExceptionThrown() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=Test class=someClassValue></div>');");
		Thread.sleep(300);
		WebElement test = sHelp.getElement("Test", "id");
		browser.navigate().refresh();
		Thread.sleep(500);
		
		sHelp.waitForAttributeToEqualACertainValue(test, "class", "someClassValue", 1);
	}
	
	@Test
	public void verifyWaitForElementToBeClickable() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		
		sHelp.waitForElementToBeClickable("Test", "id", 1);
		
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyWaitForElementToBeClickable_ThrowsException() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		
		sHelp.waitForElementToBeClickable("NotHere", "id", 1);
		
	}
	
	@Test
	public void verifyWaitForPageLoad() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		
		sHelp.WaitForPageToLoad("Test", "id", 1);
		
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyWaitForPageLoad_ThrowsException() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		
		sHelp.WaitForPageToLoad("NotHere", "id", 1);
		
	}
	
	@Test
	public void verifyWaitForPresenceOfElementLocated() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		
		sHelp.waitForPresenceOfElementLocated("Test", "id", 1);
		
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyWaitForOfElementLocated_ThrowsException() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		
		sHelp.waitForPresenceOfElementLocated("NotHere", "id", 1);
		
	}
	
	@Test
	public void waitForTextToExistInElement() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test>Button</button>');");
		Thread.sleep(300);
		
		sHelp.waitForTextToExistInElement("Test", "id", "Button", 1);
	}
	
	@Test(expected=WebDriverException.class)
	public void waitForTextToExistInElement_ThrowsException() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test></button>');");
		Thread.sleep(300);
		
		sHelp.waitForTextToExistInElement("Test", "id", "Button", 1);
	}
	
	@Test
	public void verifyWaitForWindowCount()
	{
		sHelp.waitForWindowCount(1, 1);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyWaitForWindowCount_ThrowsException()
	{
		sHelp.waitForWindowCount(1, 2);
	}
	
	@Test
	public void verifyWaitForElementToBeInvisible() throws InterruptedException
	{	
		sHelp.waitUntilElementIsInvisible("Test", "id", 1);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyWaitForElementToBeInvisible_ThrowsException() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test></button>');");
		Thread.sleep(300);
		
		sHelp.waitUntilElementIsInvisible("Test", "id", 1);
	}
	
	@Test
	public void verifyDragAndDropViaSelenium() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test>Button1</button>');");
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test>Button2</button>');");
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test2>Button3</button>');");
		Thread.sleep(300);
		
		sHelp.dragAndDropViaSelenium("Test", "id", "Test2", "id", 1);
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyDragAndDropViaSelenium_ThrowsException() throws InterruptedException
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test>Button1</button>');");
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test>Button2</button>');");
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test2>Button2</button>');");
		Thread.sleep(300);
		
		sHelp.dragAndDropViaSelenium("NotHere", "id", "Nope", "id", 1);
	}
	
	@Test(expected=Exception.class)
	public void verifyDragAndDropElementToAnotherElement() throws Exception
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=\"resizer\" class=\"resizer ui-draggable ui-draggable-handle ui-resizable\" data-status=\"scheduled\" style=\"left: 548.792px; top: 0px; width: 10px; height: 26px; bottom: auto;\">');");
		((JavascriptExecutor)browser).executeScript("document.write('<div class=\"timeslot ui-droppable\" id=\"Ref_Encoder-02\" style=\"width: 1058px;\">');");
		((JavascriptExecutor)browser).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(300);
		
		sHelp.dragAndDropElementToAnotherElement("#resizer", "cssSelector", "", "", "0", "0", "0", "50");
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyDragAndDropElementToAnotherElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test>Button1</button>');");
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test2>Button2</button>');");
		((JavascriptExecutor)browser).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(300);
		
		sHelp.dragAndDropElementToAnotherElement("NotHere", "id", "", "", "0", "0", "0", "50");;
	}
	
	@Test(expected=Exception.class)
	public void verifyDragAndDropElementToAnotherElement_ValuesNotNull() throws Exception
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=\"resizer\" class=\"resizer ui-draggable ui-draggable-handle ui-resizable\" data-status=\"scheduled\" style=\"left: 548.792px; top: 0px; width: 10px; height: 26px; bottom: auto;\">');");
		((JavascriptExecutor)browser).executeScript("document.write('<div class=\"timeslot ui-droppable\" id=\"Ref_Encoder-02\" style=\"width: 1058px;\">');");
		((JavascriptExecutor)browser).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(300);
		
		sHelp.dragAndDropElementToAnotherElement("#resizer", "cssSelector", "#Ref_Encoder-02", "cssSelector", "0", "0", null, null);
	}
	
	@Test(expected=Exception.class)
	public void verifyDragAndDropElementToAnotherElement_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=\"resizer\" class=\"resizer ui-draggable ui-draggable-handle ui-resizable\" data-status=\"scheduled\" style=\"left: 548.792px; top: 0px; width: 10px; height: 26px; bottom: auto;\">');");
		((JavascriptExecutor)browser).executeScript("document.write('<div class=\"timeslot ui-droppable\" id=\"Ref_Encoder-02\" style=\"width: 1058px;\">');");
		((JavascriptExecutor)browser).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(300);
		WebElement test = sHelp.getElement("#resizer", "cssSelector");
		WebElement test2 = sHelp.getElement("#resizer", "cssSelector");
		
		sHelp.dragAndDropElementToAnotherElement(test, test2, "#resizer", "0", "0", "0", "0", "50");
	}
	
	@Test(expected=WebDriverException.class)
	public void verifyDragAndDropElementToAnotherElement_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test>Button1</button>');");
		((JavascriptExecutor)browser).executeScript("document.write('<button id=Test2>Button2</button>');");
		((JavascriptExecutor)browser).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(300);
		WebElement test = sHelp.getElement("#resizer", "cssSelector");
		WebElement test2 = sHelp.getElement("#resizer", "cssSelector");
		browser.navigate().refresh();
		Thread.sleep(500);
		
		sHelp.dragAndDropElementToAnotherElement(test, test2, "#resizer", "0", "0", "0", "0", "50");;
	}
	
	@Test(expected=Exception.class)
	public void verifyDragAndDropElementToAnotherElement_PredefinedElement_ValuesNotNull() throws Exception
	{
		((JavascriptExecutor)browser).executeScript("document.write('<div id=\"resizer\" class=\"resizer ui-draggable ui-draggable-handle ui-resizable\" data-status=\"scheduled\" style=\"left: 548.792px; top: 0px; width: 10px; height: 26px; bottom: auto;\">');");
		((JavascriptExecutor)browser).executeScript("document.write('<div class=\"timeslot ui-droppable\" id=\"Ref_Encoder-02\" style=\"width: 1058px;\">');");
		((JavascriptExecutor)browser).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(300);
		WebElement test = sHelp.getElement("#resizer", "cssSelector");
		WebElement test2 = sHelp.getElement("#Ref_Encoder-02", "cssSelector");
		
		sHelp.dragAndDropElementToAnotherElement(test, test2, "#resizer", "0", "0", "0", null, null);
	}
	
	@After
	public void after() throws Exception
	{
		try
		{
			browser.quit();
		}
		catch (Exception ex)
		{
			browser.quit();
			throw ex;
		}
		finally
		{
			browser.quit();
		}
	}


}
