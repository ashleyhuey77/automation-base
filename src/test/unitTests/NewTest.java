package unitTests;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import common.basePage.helpers.ClickHelper;
import common.basePage.helpers.ClickHelper.ClickBuilder;
import common.basePage.helpers.ElementHelper;
import common.basePage.valueObjects.ReportInfo;
import common.utils.WebDriverListener;
import common.utils.managers.LocalDriver;
import common.utils.managers.SHelper;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;
import testPages.TestInitialization;

@Listeners(WebDriverListener.class)
public class NewTest  extends TestInitialization {
	
  @Test
  public void ElementHelperTest() throws Exception {
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
		  throw e;
	  }
  }
  
}
