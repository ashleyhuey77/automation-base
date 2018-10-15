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
import shelper.enums.SelectType;
import shelper.vobjects.By;
import shelper.vobjects.Locator;

@Listeners(WebDriverListener.class)
public class ActionsTests {
	
	@BeforeMethod
	public void beforeScenario()
	{
		SHelper.set(new SeleniumHelper());
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/externalLibraries/browsers/chromedriver");
        LocalDriver.getDriver().get("http://www.google.com");
	}
	
	@Test
	public void verifyMouseOver_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Thread.sleep(500);
		Locator locator = new Locator("Test");
		By by = new By("id");
		WebElement test = SHelper.get().element().get(locator, by);
		
		SHelper.get().actions().mouseOver(test);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyMouseOver_PredefinedElement_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		
		WebElement test = SHelper.get().element().get(locator, by);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		SHelper.get().actions().mouseOver(test);
	}
	
	@Test
	public void verifyMouseOver() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Thread.sleep(500);
		Locator locator = new Locator("Test");
		By by = new By("id");
		
		SHelper.get().actions().mouseOver(locator, by);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyMouseOver_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		SHelper.get().actions().mouseOver(locator, by);
	}
	
	@Test
	public void verifyMoveToElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		
		SHelper.get().actions().moveTo(locator, by);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyMoveToElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Locator locator = new Locator("NotHere");
		By by = new By("id");
		
		SHelper.get().actions().moveTo(locator, by);
	}
	
	@Test
	public void verifyMoveToElement_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get(locator, by);
		
		SHelper.get().actions().moveTo(test);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyMoveToElement_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		WebElement test = SHelper.get().element().get(locator, by);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		SHelper.get().actions().moveTo(test);
	}
	
	@Test
	public void verifySelectFromDropDownByValue_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript(
				"document.write('<select id=Test>" + 
				"  <option value=1>Volvo</option>" + 
				"  <option value=2>Saab</option>" + 
				"  <option value=3>Mercedes</option>" + 
				"  <option value=4>Audi</option>" + 
				"</select>');"
				);
		Locator locator = new Locator("Test");
		By by = new By("id");
		WebElement test = SHelper.get().element().get(locator, by);
		Thread.sleep(500);
		
		SHelper.get().actions().selectFromDropDown(test, "2", SelectType.byValue);
	}
	
	@Test
	public void verifySelectFromDropDownByVisibleText_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript(
				"document.write('<select id=Test>" + 
				"  <option value=1>Volvo</option>" + 
				"  <option value=2>Saab</option>" + 
				"  <option value=3>Mercedes</option>" + 
				"  <option value=4>Audi</option>" + 
				"</select>');"
				);
		Locator locator = new Locator("Test");
		By by = new By("id");
		WebElement test = SHelper.get().element().get(locator, by);
		Thread.sleep(500);
		
		SHelper.get().actions().selectFromDropDown(test, "Saab", SelectType.byVisibleText);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifySelectFromDropDown_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript(
				"document.write('<select id=Test>" + 
				"  <option value=1>Volvo</option>" + 
				"  <option value=2>Saab</option>" + 
				"  <option value=3>Mercedes</option>" + 
				"  <option value=4>Audi</option>" + 
				"</select>');"
				);
		Locator locator = new Locator("Test");
		By by = new By("id");
		WebElement test = SHelper.get().element().get(locator, by);
		Thread.sleep(500);
		
		SHelper.get().actions().selectFromDropDown(test, "Test", SelectType.byVisibleText);
	}
	
	@Test
	public void verifyScrollToElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(300);
		SHelper.get().actions().scrollTo(locator, by);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyScrollToElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Locator locator = new Locator("NotHere");
		By by = new By("id");
		Thread.sleep(300);
		SHelper.get().actions().scrollTo(locator, by);
	}
	
	@Test
	public void verifyScrollToElement_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(locator, by);
		SHelper.get().actions().scrollTo(test);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyScrollToElement_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(locator, by);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(400);
		SHelper.get().actions().scrollTo(test);
	}
	
	@Test
	public void verifySelectOptionFromDropDownMenu() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<select id=Test class=testClass ><option name=test>Test1</option><option>Test2</option><option>Test3</option><option>Test4</option><option>Test5</option></select>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Thread.sleep(300);
		
		SHelper.get().actions().selectFromDropDown(locator, by, "Test2", SelectType.byIndex);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifySelectOptionFromDropDownMenu_ExceptionThrown() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<select id=Test class=testClass ><option name=test>Test1</option><option>Test2</option><option>Test3</option><option>Test4</option><option>Test5</option></select>');");
		Locator locator = new Locator("NotHere");
		By by = new By("name");
		Thread.sleep(300);
		
		SHelper.get().actions().selectFromDropDown(locator, by, "Test2", SelectType.byIndex);
	}
	
	@Test
	public void verifyDragAndDropViaSelenium() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button1</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button2</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test2>Button3</button>');");
		Locator locator = new Locator("Test");
		By by = new By("id");
		Locator locator2 = new Locator("Test");
		By by2 = new By("id");
		Thread.sleep(300);
		
		SHelper.get().actions().dragAndDrop(locator, by, locator2, by2, 1);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyDragAndDropViaSelenium_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button1</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button2</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test2>Button2</button>');");
		Locator locator = new Locator("NotHere");
		By by = new By("id");
		Locator locator2 = new Locator("Nope");
		By by2 = new By("id");
		Thread.sleep(300);
		
		SHelper.get().actions().dragAndDrop(locator, by, locator2, by2, 1);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyDragAndDropElementToAnotherElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=\"resizer\" class=\"resizer ui-draggable ui-draggable-handle ui-resizable\" data-status=\"scheduled\" style=\"left: 548.792px; top: 0px; width: 10px; height: 26px; bottom: auto;\">');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div class=\"timeslot ui-droppable\" id=\"Ref_Encoder-02\" style=\"width: 1058px;\">');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Locator locator = new Locator("#resizer");
		By by = new By("css");
		Locator locator2 = new Locator("");
		By by2 = new By("");
		
		Thread.sleep(300);
		
		SHelper.get().actions().dragAndDrop(locator, by, locator2, by2, "0", "0", "0", "50");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyDragAndDropElementToAnotherElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button1</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test2>Button2</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Locator locator = new Locator("NotHere");
		By by = new By("id");
		Locator locator2 = new Locator("");
		By by2 = new By("");
		Thread.sleep(300);
		
		SHelper.get().actions().dragAndDrop(locator, by, locator2, by2, "0", "0", "0", "50");;
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyDragAndDropElementToAnotherElement_ValuesNotNull() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=\"resizer\" class=\"resizer ui-draggable ui-draggable-handle ui-resizable\" data-status=\"scheduled\" style=\"left: 548.792px; top: 0px; width: 10px; height: 26px; bottom: auto;\">');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div class=\"timeslot ui-droppable\" id=\"Ref_Encoder-02\" style=\"width: 1058px;\">');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Locator locator = new Locator("#resizer");
		By by = new By("css");
		Locator locator2 = new Locator("#Ref_Encoder-02");
		By by2 = new By("css");
		Thread.sleep(300);
		
		SHelper.get().actions().dragAndDrop(locator, by, locator2, by2, "0", "0", null, null);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyDragAndDropElementToAnotherElement_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=\"resizer\" class=\"resizer ui-draggable ui-draggable-handle ui-resizable\" data-status=\"scheduled\" style=\"left: 548.792px; top: 0px; width: 10px; height: 26px; bottom: auto;\">');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div class=\"timeslot ui-droppable\" id=\"Ref_Encoder-02\" style=\"width: 1058px;\">');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Locator locator = new Locator("#resizer");
		By by = new By("css");
		Locator locator2 = new Locator("#Ref_Encoder-02");
		By by2 = new By("css");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(locator, by);
		WebElement test2 = SHelper.get().element().get(locator2, by2);
		
		SHelper.get().actions().dragAndDrop(test, test2, "#resizer", "0", "0", "0", "0", "50");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyDragAndDropElementToAnotherElement_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button1</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test2>Button2</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Locator locator = new Locator("#resizer");
		By by = new By("css");
		Locator locator2 = new Locator("#Ref_Encoder-02");
		By by2 = new By("css");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(locator, by);
		WebElement test2 = SHelper.get().element().get(locator2, by2);
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		SHelper.get().actions().dragAndDrop(test, test2, "#resizer", "0", "0", "0", "0", "50");;
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyDragAndDropElementToAnotherElement_PredefinedElement_ValuesNotNull() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=\"resizer\" class=\"resizer ui-draggable ui-draggable-handle ui-resizable\" data-status=\"scheduled\" style=\"left: 548.792px; top: 0px; width: 10px; height: 26px; bottom: auto;\">');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div class=\"timeslot ui-droppable\" id=\"Ref_Encoder-02\" style=\"width: 1058px;\">');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Locator locator = new Locator("#resizer");
		By by = new By("css");
		Locator locator2 = new Locator("#Ref_Encoder-02");
		By by2 = new By("css");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get(locator, by);
		WebElement test2 = SHelper.get().element().get(locator2, by2);
		
		SHelper.get().actions().dragAndDrop(test, test2, "#resizer", "0", "0", "0", null, null);
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
