package tests.shelper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.config.SHelper;
import com.config.setup.browser.LocalDriver;
import com.config.setup.browser.WebDriverListener;
import com.selenium.By;
import com.selenium.Locator;
import com.selenium.SeleniumHelper;
import com.selenium.TestElement;
import com.selenium.shared.Via;
import com.selenium.text.Variable;
import com.utils.TestUtils;

@Listeners(WebDriverListener.class)
public class TextTests {
	
	@BeforeMethod
	public void beforeScenario()
	{
		SHelper.set(new SeleniumHelper());
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/externalLibraries/browsers/chromedriver");
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
		Locator locator = new Locator("input[id='Test']");
		By by = new By("css");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		
		String result = SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom(element, "");
		
		Assert.assertEquals(result, "Testing", "The two values do not match");
	}
	
	@Test
	public void verifyGetTextInTextFieldViaJavascript_Id() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		
		String result = SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom(element, "");
		
		Assert.assertEquals(result, "Testing", "The two values do not match");
	}
	
	@Test
	public void verifyGetTextInTextFieldViaJavascript_ClassName() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test class=className value=Testing></input>');");
		Locator locator = new Locator("className");
		By by = new By("class_name");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		
		String result = SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom(element, "0");
		
		Assert.assertEquals(result, "Testing", "The two values do not match");
	}
	
	@Test
	public void verifyGetTextInTextFieldViaJavascript_TagName() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Thread.sleep(500);
		Locator locator = new Locator("input");
		By by = new By("tag_name");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		
		String result = SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom(element, "0");
		
		Assert.assertEquals(result, "Testing", "The two values do not match");
	}
	
	@Test
	public void verifyGetTextInTextFieldViaJavascript_Name() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test name=name value=Testing></input>');");
		Locator locator = new Locator("name");
		By by = new By("name");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		
		String result = SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom(element, "0");
		
		Assert.assertEquals(result, "Testing", "The two values do not match");
	}
	
	@Test
	public void verifyIsAnyTextDisplayedInElement_ElementContainsText() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=testClass>Some Text</div>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		
		Boolean result = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).isDisplayed(element, "Some Text");
		
		Assert.assertTrue(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyIsAnyTextDisplayedInElement_ElementDoesntContainText() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=testClass></div>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		
		Boolean result = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).isDisplayed(element, "Some Text");
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verfifyIsTextDisplayedInSpecifiedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=testClass >Testing123</div>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		
		SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).isDisplayed(element, null);
	}
	
	@Test
	public void verfifyIsTextDisplayedInSpecifiedElement_PredefinedElement_TextIsDisplayed() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=testClass >Testing123</div>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		
		Boolean result = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).isDisplayed(test, "Testing123");
		
		Assert.assertTrue(result, "The boolean results do not match");
	}
	
	@Test
	public void verfifyIsTextDisplayedInSpecifiedElement_PredefinedElement_TextIsNotDisplayed() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=testClass >Testing123</div>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		
		Boolean result = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).isDisplayed(test, "AlternateFacts");
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verfifyIsTextDisplayedInSpecifiedElement_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=Test class=testClass >Testing123</div>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		
		SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).isDisplayed(test, null);
	}
	
	@Test
	public void verifyDoesAttributeContainTheExpectedValue_AttributeContainsValue() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		
		Boolean result = SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).isDisplayed(element, "testClass", "class");
		
		Assert.assertTrue(result, "The boolean results do not match");
		
	}
	
	@Test
	public void verifyDoesAttributeContainTheExpectedValue_AttributeDoesNotContainValue() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		
		Boolean result = SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).isDisplayed(element, "not correct", "class");
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyDoesAttributeContainTheExpectedValue_NullValue() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		
		Boolean result = SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).isDisplayed(element, "testClass", "class");
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyDoesAttributeContainTheExpectedValue_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Testing");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		
		SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).isDisplayed(element, "testClass", "class");
	}
	
	@Test
	public void verifyGetWebElementAttribute() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		String result = SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).getFrom(element, "class");
		Assert.assertEquals(result, "testClass", "The attribute values do not match");
	}
	
	@Test
	public void verifyGetWebElementAttribute_AttributeNotPresent() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		String result = SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).getFrom(element, "test");
		Assert.assertTrue(TestUtils.isNullOrBlank(result), "The attribute values do not match");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyGetWebElementAttribute_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("NotHere");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).getFrom(element, "test");
	}
	
	@Test
	public void verifyGetWebElementAttribute_ElementPredefined() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		String result = SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).getFrom(test, "class");
		Assert.assertEquals(result, "testClass", "The attribute values do not match");
	}
	
	@Test
	public void verifyGetWebElementAttribute_ElementPredefined_AttributeNotPresent() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		String result = SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).getFrom(test, "test");
		Assert.assertTrue(TestUtils.isNullOrBlank(result), "The attribute values do not match");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyGetWebElementAttribute_ElementPredefined_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).getFrom(test, "test");
	}
	
	@Test
	public void verifyJSGetPageTextFrom_AttributeIsNull() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		String[] var = {};
		
		String result = SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom(element, var);
		
		Assert.assertEquals(result, "Testing", "The two values do not match");
	}
	
	@Test
	public void verifyJSGetPageTextFrom_PredefinedElement() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		
		String result = SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom(test);
		
		Assert.assertEquals(result, "Testing", "The two values do not match");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyJSGetPageTextFrom_PredefinedElement_ThrowsException() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		SHelper.get().page().refresh();
		Thread.sleep(900);
		
		SHelper.get().text(Variable.ELEMENT, Via.JAVASCRIPT).getFrom(test);
		
	}
	
	@Test
	public void verifyGetTextFromElement() throws Exception {
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<input id=Test value=Testing></input>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		
		String value = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(element);
		
		Assert.assertTrue(TestUtils.isNullOrBlank(value), "A value was returned. Expected the value to return null");
	}
	
	@Test
	public void verifyDoesAttributeContainTheExpectedValue_PredefinedElement_AttributeContainsValue() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		
		Boolean result = SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).isDisplayed(test, "testClass", "class");
		
		Assert.assertTrue(result, "The boolean results do not match");
		
	}
	
	@Test
	public void verifyDoesAttributeContainTheExpectedValue_PredefinedElement_AttributeDoesNotContainValue() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		
		Boolean result = SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).isDisplayed(test, "not correct", "class");
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@Test
	public void verifyDoesAttributeContainTheExpectedValue_PredefinedElement_NullValue() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement element = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(element);
		
		Boolean result = SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).isDisplayed(test, "testClass", "class");
		
		Assert.assertFalse(result, "The boolean results do not match");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyDoesAttributeContainTheExpectedValue_PredefinedElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Dont click this button</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		TestElement el = new TestElement(locator, by);
		Thread.sleep(500);
		WebElement element = SHelper.get().element().get(el);
		SHelper.get().page().refresh();
		Thread.sleep(900);
		
		SHelper.get().text(Variable.ATTRIBUTE, Via.SELENIUM).isDisplayed(element, "testClass", "class");
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
