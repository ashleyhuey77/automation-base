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

@Listeners(WebDriverListener.class)
public class ActionsTests {
	
	@BeforeMethod
	public void beforeScenario()
	{
		SHelper.set(new SeleniumHelper());
        System.setProperty("webdriver.chrome.driver", TestUtils.getRelativePath() + "/ExternalLibraries/chromedriver");
        LocalDriver.getDriver().get("http://www.google.com");
	}
	
	@Test
	public void verifyMouseOver() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test", "id");
		
		SHelper.get().actions().mouseOver(test);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyMouseOver_ExceptionThrown() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Thread.sleep(500);
		WebElement test = SHelper.get().element().get("Test", "id");
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(500);
		
		SHelper.get().actions().mouseOver(test);
	}
	
	@Test
	public void verifyMoveToElement() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Thread.sleep(500);
		
		SHelper.get().actions().moveTo("Test", "id");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyMoveToElement_ThrowsException() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		
		SHelper.get().actions().moveTo("NotHere", "id");
	}
	
	@Test
	public void verifyScrollToElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Thread.sleep(300);
		SHelper.get().actions().scrollTo("Test", "id");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyScrollToElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Thread.sleep(300);
		SHelper.get().actions().scrollTo("NotHere", "id");
	}
	
	@Test
	public void verifyScrollToElement_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		SHelper.get().actions().scrollTo(test);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyScrollToElement_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test class=testClass >Testing123</button>');");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("Test", "id");
		LocalDriver.getDriver().navigate().refresh();
		Thread.sleep(400);
		SHelper.get().actions().scrollTo(test);
	}
	
	@Test
	public void verifySelectOptionFromDropDownMenu() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<select id=Test class=testClass ><option name=test>Test1</option><option>Test2</option><option>Test3</option><option>Test4</option><option>Test5</option></select>');");
		Thread.sleep(300);
		
		SHelper.get().actions().selectFromDropDown("Test", "id", "Test2");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifySelectOptionFromDropDownMenu_ExceptionThrown() throws InterruptedException
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<select id=Test class=testClass ><option name=test>Test1</option><option>Test2</option><option>Test3</option><option>Test4</option><option>Test5</option></select>');");
		Thread.sleep(300);
		
		SHelper.get().actions().selectFromDropDown("notHere", "name", "Test2");
	}
	
	@Test
	public void verifyDragAndDropViaSelenium() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button1</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button2</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test2>Button3</button>');");
		Thread.sleep(300);
		
		SHelper.get().actions().dragAndDrop("Test", "id", "Test2", "id", 1);
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyDragAndDropViaSelenium_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button1</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button2</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test2>Button2</button>');");
		Thread.sleep(300);
		
		SHelper.get().actions().dragAndDrop("NotHere", "id", "Nope", "id", 1);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyDragAndDropElementToAnotherElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=\"resizer\" class=\"resizer ui-draggable ui-draggable-handle ui-resizable\" data-status=\"scheduled\" style=\"left: 548.792px; top: 0px; width: 10px; height: 26px; bottom: auto;\">');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div class=\"timeslot ui-droppable\" id=\"Ref_Encoder-02\" style=\"width: 1058px;\">');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(300);
		
		SHelper.get().actions().dragAndDrop("#resizer", "cssSelector", "", "", "0", "0", "0", "50");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyDragAndDropElementToAnotherElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button1</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test2>Button2</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(300);
		
		SHelper.get().actions().dragAndDrop("NotHere", "id", "", "", "0", "0", "0", "50");;
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyDragAndDropElementToAnotherElement_ValuesNotNull() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=\"resizer\" class=\"resizer ui-draggable ui-draggable-handle ui-resizable\" data-status=\"scheduled\" style=\"left: 548.792px; top: 0px; width: 10px; height: 26px; bottom: auto;\">');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div class=\"timeslot ui-droppable\" id=\"Ref_Encoder-02\" style=\"width: 1058px;\">');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(300);
		
		SHelper.get().actions().dragAndDrop("#resizer", "cssSelector", "#Ref_Encoder-02", "cssSelector", "0", "0", null, null);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void verifyDragAndDropElementToAnotherElement_PredefinedElement() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div id=\"resizer\" class=\"resizer ui-draggable ui-draggable-handle ui-resizable\" data-status=\"scheduled\" style=\"left: 548.792px; top: 0px; width: 10px; height: 26px; bottom: auto;\">');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<div class=\"timeslot ui-droppable\" id=\"Ref_Encoder-02\" style=\"width: 1058px;\">');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("#resizer", "cssSelector");
		WebElement test2 = SHelper.get().element().get("#resizer", "cssSelector");
		
		SHelper.get().actions().dragAndDrop(test, test2, "#resizer", "0", "0", "0", "0", "50");
	}
	
	@Test(expectedExceptions=WebDriverException.class)
	public void verifyDragAndDropElementToAnotherElement_PredefinedElement_ThrowsException() throws Exception
	{
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Button1</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test2>Button2</button>');");
		((JavascriptExecutor)LocalDriver.getDriver()).executeScript("var script = document.createElement('script'); script.src = 'https://code.jquery.com/jquery-1.11.0.min.js'; script.type = 'text/javascript'; document.getElementsByTagName('head')[0].appendChild(script);");
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("#resizer", "cssSelector");
		WebElement test2 = SHelper.get().element().get("#resizer", "cssSelector");
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
		Thread.sleep(300);
		WebElement test = SHelper.get().element().get("#resizer", "cssSelector");
		WebElement test2 = SHelper.get().element().get("#Ref_Encoder-02", "cssSelector");
		
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
