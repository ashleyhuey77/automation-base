package tests;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import common.base.helpers.ClickHelper;
import common.base.helpers.ElementHelper;
import common.base.helpers.OptionSelector;
import common.base.helpers.ClickHelper.ClickBuilder;
import common.base.helpers.OptionSelector.OptionSelectorBuilder;
import common.base.vobjects.ReportInfo;
import common.utils.WebDriverListener;
import common.utils.managers.LocalDriver;
import common.utils.managers.SHelper;
import log.TestException;
import pages.TestInitialization;
import shelper.enums.Condition;
import shelper.vobjects.By;
import shelper.vobjects.Locator;
import shelper.vobjects.TestElement;

@Listeners(WebDriverListener.class)
public class NewTest  extends TestInitialization {
	
  @Test
  public void ElementHelperTest() throws TestException {
	  try {
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>YES</button>');");
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>NO</button>');");
			Locator locator = new Locator("Test");
			By by = new By("id");
			List<WebElement> buttons = SHelper.get().element().getListOf(locator, by);
			WebElement button = new ElementHelper(buttons, "POOP").get();
		
			new ClickHelper(new ClickBuilder(new ReportInfo(button.getText() + " button"))
					.clickOn(button));

	  } catch (Exception e) {
		 
	  }
  }
  
  @Test
  public void OptionSelectorTest() throws TestException {
	  try {
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>Dont click this button</button>');");
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>YES</button>');");
			((JavascriptExecutor)LocalDriver.getDriver()).executeScript("document.write('<button id=Test>NO</button>');");
			Locator locator = new Locator("Test");
			By by = new By("id");
		
			new OptionSelector(new OptionSelectorBuilder()
					.findOption("yes")
					.thatIs(Condition.EQUAL)
					.For(new TestElement(locator, by)));

	  } catch (Exception e) {
		 
	  }
  }
  
  @Test
  public void SkipTest() throws TestException {
	  throw new SkipException("Skipping this test because there are constraints to running it headlessly.");
  }
  
}
