package tests.helpers;

import java.io.File;
import java.io.FileReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.driver.WebDriverListener;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.config.settings.LocalTest;
import com.warnermedia.page.utils.EnterTextHelper;
import com.warnermedia.page.utils.ReportInfo;
import com.warnermedia.page.utils.VerifyTextHelper;
import com.warnermedia.page.utils.EnterTextHelper.EnterTextBuilder;
import com.warnermedia.page.utils.VerifyTextHelper.VerifyTextBuilder;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.shared.Via;
import pages.TestInitialization;

@Listeners(WebDriverListener.class)
public class VerifyTextHelper_Tests extends TestInitialization {
	
	@Test(groups= {"vtext"}, alwaysRun=true)
	public void verifyTextInTextField_ContainsInvalidChar_RemoveAllSpacesFalse_TextIsPresent() throws Exception {
		try {
			String filePath = LocalReport.getFilePath() + 
					File.separator + "HTML Results" + 
					File.separator + LocalTest.getTestName()  + 
					"_" + LocalTest.getEnvironment().getBrowser() + ".html";
    		((JavascriptExecutor) LocalDriver.getDriver())
    				.executeScript("document.write('<textarea id=Test value=></textarea>');");
    		Thread.sleep(500);
    		Locator locator = new Locator("textarea[id='Test']");
    		By by = new By("css");
    
    		new EnterTextHelper(
    				new EnterTextBuilder(new ReportInfo("Test")).enterText("Test 123").into(new TestElement(locator, by)));
    		Thread.sleep(500);
    		new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element"))
    				.verify(new TestElement(locator, by))
    				.contains("Test 123")
    				.how(Via.JAVASCRIPT));
    		FileReader reader = new FileReader(filePath);
    		String newIS = extractText(reader);
    		Assert.assertTrue(
    				newIS.contains("Element contains Test 123 as expected. PASS"));
    
    		
		} catch (Exception e) {
			throw e;
		}
	}

	@Test(groups= {"vtext"}, alwaysRun=true)
	public void verifyTextInTextField_DoesntContainInvalidChar_RemoveAllSpacesTrue_TextIsPresent() throws Exception {
		try {
			String filePath = LocalReport.getFilePath() + 
					File.separator + "HTML Results" + 
					File.separator + LocalTest.getTestName()  + 
					"_" + LocalTest.getEnvironment().getBrowser() + ".html";
    		((JavascriptExecutor) LocalDriver.getDriver())
    				.executeScript("document.write('<input id=\"Test\" value=\"Test 123\"></input>');");
    		Thread.sleep(500);
    		Locator locator = new Locator("#Test");
    		By by = new By("css");
    
    		new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element")).verify(new TestElement(locator, by))
    				.contains("Test 123").removeAllSpaces(true).how(Via.JAVASCRIPT));
    
    		FileReader reader = new FileReader(filePath);
    		String newIS = extractText(reader);
    
    		Assert.assertNotNull(newIS);
    
    		
		} catch (Exception e) {
			throw e;
		}
	}

	@Test(groups= {"vtext"}, alwaysRun=true)
	public void verifyTextInTextField_DoesntContainInvalidChar_RemoveAllSpacesFalse_TextIsNotPresent()
			throws Exception {
		try {
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<input id=Test value=Testing123 ></input>');");
			Thread.sleep(500);
			Locator locator = new Locator("#Test");
			By by = new By("css");

			new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element"))
					.verify(new TestElement(locator, by)).contains("Test 123").how(Via.JAVASCRIPT));
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifyTextInTextField"));
			Assert.assertTrue(m.trim().contains(
					"ErrorMessage: Test Element should contain Test 123 but is retaining an incorrect value instead. The value being retained is Testing123"));
		}
	}
	
	@Test(groups= {"vtext"}, alwaysRun=true)
	public void verifyTextWebElement_RemoveAllSpacesFalse_TextIsPresent() throws Exception {
		try {
			String filePath = LocalReport.getFilePath() + 
					File.separator + "HTML Results" + 
					File.separator + LocalTest.getTestName()  + 
					"_" + LocalTest.getEnvironment().getBrowser() + ".html";
    		((JavascriptExecutor) LocalDriver.getDriver())
    				.executeScript("document.write('<div id=Test >Testing123</div>');");
    		Thread.sleep(500);
    		Locator locator = new Locator("#Test");
    		By by = new By("css");
    		WebElement element = SHelper.get().element().get(new TestElement(locator, by));
    
    		new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element")).verify(element)
    				.contains("Testing123"));
    		
    		FileReader reader = new FileReader(filePath);
    		String newIS = extractText(reader);
    
    		Assert.assertTrue(newIS.trim().contains(
    				"Test Element contains the correct text: Testing123 PASS"));
    
    		
		} catch (Exception e) {
			throw e;
		}
	}

	@Test(groups= {"vtext"}, alwaysRun=true)
	public void verifyTextInWebElement_RemoveAllSpacesFalse_TextIsNotPresent() throws Exception {
		try {
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<div id=Test >Testing123</div>');");
			Thread.sleep(500);
			Locator locator = new Locator("#Test");
			By by = new By("css");
			WebElement element = SHelper.get().element().get(new TestElement(locator, by));

			new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element"))
					.verify(element).contains("Test 123"));
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifySomeWebElementContainsTheExpectedText"));
			Assert.assertTrue(m.trim().contains(
					"Test Element does not contain the correct text. Expected text: Test 123. Actual text: Testing123"));
		}
	}
	
	@Test(groups= {"vtext"}, alwaysRun=true)
	public void verifyTextInWebElement_ExceptionThrown() throws Exception {
		try {
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<div id=Test >Testing123</div>');");
			Thread.sleep(500);
			Locator locator = new Locator("#Test");
			By by = new By("css");
			WebElement element = SHelper.get().element().get(new TestElement(locator, by));
			SHelper.get().page().refresh();
			Thread.sleep(300);

			new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element"))
					.verify(element).contains("Testing123"));
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifySomeWebElementContainsTheExpectedText"));
		}
	}

	@Test(groups= {"vtext"}, alwaysRun=true)
	public void verifyTextInWebElement_RemoveAllSpacesTrue_TextIsPresent() throws Exception {
		try {
			String filePath = LocalReport.getFilePath() + 
					File.separator + "HTML Results" + 
					File.separator + LocalTest.getTestName()  + 
					"_" + LocalTest.getEnvironment().getBrowser() + ".html";
    		((JavascriptExecutor) LocalDriver.getDriver())
    				.executeScript("document.write('<div id=Test >Testing123</div>');");
    		Thread.sleep(500);
    		Locator locator = new Locator("#Test");
    		By by = new By("css");
    		WebElement element = SHelper.get().element().get(new TestElement(locator, by));
    
    		new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element")).verify(element)
    				.contains("Testing123").removeAllSpaces(true));

    		FileReader reader = new FileReader(filePath);
    		String newIS = extractText(reader);
    
    		Assert.assertNotNull(newIS);
    
    		
		} catch (Exception e) {
			throw e;
		}
	}

	@Test(groups= {"vtext"}, alwaysRun=true)
	public void verifyTextInTestElement_RemoveAllSpacesFalse_TextIsPresent() throws Exception {
		try {
			String filePath = LocalReport.getFilePath() + 
					File.separator + "HTML Results" + 
					File.separator + LocalTest.getTestName()  + 
					"_" + LocalTest.getEnvironment().getBrowser() + ".html";
    		((JavascriptExecutor) LocalDriver.getDriver())
    				.executeScript("document.write('<div id=Test >Testing123</div>');");
    		Thread.sleep(500);
    		Locator locator = new Locator("#Test");
    		By by = new By("css");
    
    		new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element")).verify(new TestElement(locator, by))
    				.contains("Testing123"));
    		FileReader reader = new FileReader(filePath);
    		String newIS = extractText(reader);
    
    		Assert.assertNotNull(newIS);
    
    		
		} catch (Exception e) {
			throw e;
		}
	}

	@Test(groups= {"vtext"}, alwaysRun=true)
	public void verifyTextInTestElement_RemoveAllSpacesFalse_TextIsNotPresent() throws Exception {
		try {
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<div id=Test >Testing123</div>');");
			Thread.sleep(500);
			Locator locator = new Locator("#Test");
			By by = new By("css");

			new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element"))
					.verify(new TestElement(locator, by)).contains("Test 123"));
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifySomeElementContainsTheExpectedText"));
			Assert.assertTrue(m.trim().contains(
					"Test Element does not contain the correct text. Expected text: Test 123. Actual text: Testing123"));
		}
	}
	
	@Test(groups= {"vtext"}, alwaysRun=true)
	public void verifyTextInTestElement_ExceptionThrown() throws Exception {
		try {
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<div id=Test >Testing123</div>');");
			Thread.sleep(500);
			Locator locator = new Locator("#Testing");
			By by = new By("css");

			new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element"))
					.verify(new TestElement(locator, by)).contains("Testing123"));
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifySomeElementContainsTheExpectedText"));
		}
	}

	@Test(groups= {"vtext"}, alwaysRun=true)
	public void verifyTextInTestElement_RemoveAllSpacesTrue_TextIsPresent() throws Exception {
		try {
			String filePath = LocalReport.getFilePath() + 
					File.separator + "HTML Results" + 
					File.separator + LocalTest.getTestName()  + 
					"_" + LocalTest.getEnvironment().getBrowser() + ".html";
    		((JavascriptExecutor) LocalDriver.getDriver())
    				.executeScript("document.write('<div id=Test >Testing123</div>');");
    		Thread.sleep(500);
    		Locator locator = new Locator("#Test");
    		By by = new By("css");
    
    		new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element")).verify(new TestElement(locator, by))
    				.contains("Testing123").removeAllSpaces(true));
    		FileReader reader = new FileReader(filePath);
    		String newIS = extractText(reader);
    
    		Assert.assertNotNull(newIS);
    
    		
		} catch (Exception e) {
			throw e;
		}
	}

	@Test(groups= {"vtext"}, alwaysRun=true)
	public void verifyTextInTextField_ExceptionThrown() throws Exception {
		try {
			((JavascriptExecutor) LocalDriver.getDriver())
					.executeScript("document.write('<input id=Test value=Testing 123 ></input>');");
			Thread.sleep(500);
			Locator locator = new Locator("#Test");
			By by = new By("css");

			new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element"))
					.verify(new TestElement(locator, by)).contains("Test 123").how(Via.JAVASCRIPT).withIndexOf("1"));
			Assert.fail("Assertion failed error was supposed to have been thrown.");
		} catch (Exception ex) {
			String m = ex.toString();
			Assert.assertTrue(m.trim().contains("StepName: verifyTextInTextField"));
		}
	}
	
	@Test(groups= {"vtext"}, expectedExceptions=TestException.class, alwaysRun=true)
	public void verifyNoElementProvided_ExceptionThrown() throws Exception {
    		new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element"))
    				.contains("Testing123"));
	}
	
	@Test(groups= {"vtext"}, expectedExceptions=TestException.class, alwaysRun=true)
	public void verifyNoExpectedValueProvided_ExceptionThrown() throws Exception {
		new VerifyTextHelper(new VerifyTextBuilder(new ReportInfo("Test Element")).removeAllSpaces(true));
	}

}
