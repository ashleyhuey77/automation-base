package seleniumHelper.methods;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import common.utils.managers.LocalDriver;
import common.utils.managers.SHelper;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.builders.WaitBuilder;
import seleniumHelper.enums.Wait;
import seleniumHelper.interfaces.IClick;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;

public class RightClick extends Commands implements IClick {

	@Override
	public void on(Locator locator, By by) throws Exception {
        try {
            SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(locator, by);
            
            Actions action = new Actions(LocalDriver.getDriver());
            action.contextClick(getElement(locator, by)).build().perform();
        } catch (WebDriverException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
	}

	@Override
	public void on(WebElement element) throws Exception {
		try {
            SHelper.get().waitMethod(Wait.CLICKABILITY_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(element);
            
            Actions action = new Actions(LocalDriver.getDriver());
            action.contextClick(element).build().perform();
        } catch (WebDriverException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
	}

	@Override
	public void on(Locator locator, By by, String index) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void on(Locator locator, By by, int index) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
