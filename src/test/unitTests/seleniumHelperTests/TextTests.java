package unitTests.seleniumHelperTests;

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
import seleniumHelper.enums.Variable;
import seleniumHelper.enums.Via;

@Listeners(WebDriverListener.class)
public class TextTests {
	
	@BeforeMethod
	public void beforeScenario()
	{
		SHelper.set(new SeleniumHelper());
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/ExternalLibraries/chromedriver");
        LocalDriver.getDriver().get("http://www.google.com");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyGetTextFromWebElement_PreDefinedElement_WebElementIsNull() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Thread.sleep(500);
		WebElement test = null;
		Thread.sleep(500);
		
		String value = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(test);
		
		Assert.assertTrue(TestUtils.isNullOrBlank(value), "A value was returned. Expected the value to return null");
	}
	
	@Test
	public void verifyGetTextInTextFieldViaJavascript_CharsInCssSelectorString() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Thread.sleep(500);
		
		String result = SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom("input[id='Test']", "cssSelector", "");
		
		Assert.assertEquals(result, "Testing", "The two values do not match");
	}
	
	@Test
	public void verifyGetTextInTextFieldViaJavascript_Id() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Thread.sleep(500);
		
		String result = SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom("Test", "id", "");
		
		Assert.assertEquals(result, "Testing", "The two values do not match");
	}
	
	@Test
	public void verifyGetTextInTextFieldViaJavascript_ClassName() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=className value=Testing></input>');");
		Thread.sleep(500);
		
		String result = SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom("className", "className", "0");
		
		Assert.assertEquals(result, "Testing", "The two values do not match");
	}
	
	@Test
	public void verifyGetTextInTextFieldViaJavascript_TagName() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Thread.sleep(500);
		
		String result = SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom("input", "tagName", "0");
		
		Assert.assertEquals(result, "Testing", "The two values do not match");
	}
	
	@Test
	public void verifyGetTextInTextFieldViaJavascript_Name() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test name=name value=Testing></input>');");
		Thread.sleep(500);
		
		String result = SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom("name", "name", "0");
		
		Assert.assertEquals(result, "Testing", "The two values do not match");
	}
	
	@Test
	public void verifyGetTextInTextFieldViaJavascript_DefaultCase() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test name=name value=Testing></input>');");
		Thread.sleep(500);
		
		String result = SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom("Test", "xpath", "");
		
		Assert.assertEquals(result, "Testing", "The two values do not match");
	}
	
	@Test
	public void verifyIsAnyTextDisplayedInElement_ElementContainsText() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=testClass>Some Text</div>');");
		Thread.sleep(500);
		
		Boolean result = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).isDisplayed("Test", "id", "Some Text");
		
		Assert.assertTrue(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyIsAnyTextDisplayedInElement_ElementDoesntContainText() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=testClass></div>');");
		Thread.sleep(500);
		
		Boolean result = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).isDisplayed("Test", "id", "Some Text");
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verfifyIsTextDisplayedInSpecifiedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=testClass >Testing123</div>');");
		Thread.sleep(500);
		
		SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).isDisplayed("Test", "id", null);
	}
	
	@Test
	public void verfifyIsTextDisplayedInSpecifiedElement_PredefinedElement_TextIsDisplayed() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=testClass >Testing123</div>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		Boolean result = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).isDisplayed(test, "Testing123");
		
		Assert.assertTrue(result, "The boolean results do not match");
	}
	
	@Test
	public void verfifyIsTextDisplayedInSpecifiedElement_PredefinedElement_TextIsNotDisplayed() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=testClass >Testing123</div>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		Boolean result = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).isDisplayed(test, "AlternateFacts");
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verfifyIsTextDisplayedInSpecifiedElement_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=testClass >Testing123</div>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).isDisplayed(test, null);
	}
	
	@Test
	public void verifyDoesAttributeContainTheExpectedValue_AttributeContainsValue() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		
		Boolean result = SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).isDisplayed("Test", "id", "testClass", "class");
		
		Assert.assertTrue(result, "The boolean results do not match");
		
	}
	
	@Test
	public void verifyDoesAttributeContainTheExpectedValue_AttributeDoesNotContainValue() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		
		Boolean result = SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).isDisplayed("Test", "id", "not correct", "class");
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyDoesAttributeContainTheExpectedValue_NullValue() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test >Dont click this button</button>');");
		Thread.sleep(500);
		
		Boolean result = SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).isDisplayed("Test", "id", "testClass", "class");
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyDoesAttributeContainTheExpectedValue_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		
		SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).isDisplayed("testing", "id", "testClass", "class");
	}
	
	@Test
	public void verifyGetWebElementAttribute() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		String result = SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).getFrom("Test", "id", "class");
		Assert.assertEquals(result, "testClass", "The attribute values do not match");
	}
	
	@Test
	public void verifyGetWebElementAttribute_AttributeNotPresent() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		String result = SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).getFrom("Test", "id", "test");
		Assert.assertTrue(TestUtils.isNullOrBlank(result), "The attribute values do not match");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyGetWebElementAttribute_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).getFrom("NotHere", "id", "test");
	}
	
	@Test
	public void verifyGetWebElementAttribute_ElementPredefined() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test", "id");
		String result = SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).getFrom(test, "class");
		Assert.assertEquals(result, "testClass", "The attribute values do not match");
	}
	
	@Test
	public void verifyGetWebElementAttribute_ElementPredefined_AttributeNotPresent() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test", "id");
		String result = SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).getFrom(test, "test");
		Assert.assertTrue(TestUtils.isNullOrBlank(result), "The attribute values do not match");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyGetWebElementAttribute_ElementPredefined_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test", "id");
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).getFrom(test, "test");
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
