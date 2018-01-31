package seleniumHelper.methods;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.utils.managers.LocalDriver;
import common.utils.managers.SHelper;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.builders.WaitBuilder;
import seleniumHelper.enums.Wait;
import seleniumHelper.interfaces.IWait;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;

public class ElementCount extends Commands implements IWait {

	protected int time = 0;
	protected int expectedTotalCount = 0;
	
	public ElementCount(WaitBuilder build) throws Exception {
		LocalWaitBuilder builder = new LocalWaitBuilder(build);
		this.time = builder.time;
		this.expectedTotalCount = builder.expectedTotalCount;
	}

	@Override
    public void on(Locator locator, By by) throws Exception {
        try {
        		verifyExpectedCountIsNotZero(expectedTotalCount);
        		verifyMaxWaitTimeIsNotZero(time);
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                	Boolean result = false;
                    try {
                        SHelper.get().page().refresh();
                        SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(30)).on(locator, by);
                        int actualElementCount = getElements(locator, by).size();
                        if (actualElementCount == expectedTotalCount) {
                            result = true;
                        } else {
                            result = false;
                        }
                    } catch (Exception e) {
						// TODO: handle exception
                    }
					return result;
                }
            });
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

	@Override
	public void on(WebElement element) throws Exception {
	}

	@Override
	public void on(List<WebElement> element) throws Exception {
        try {
        		verifyExpectedCountIsNotZero(expectedTotalCount);
        		verifyMaxWaitTimeIsNotZero(time);
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                	Boolean result = false;
                    try {
                    	//refresh can't live in this method. If refresh is necessary, then a new loop will need to be created that contains the refresh
                    	//The list of web elements needs to be refreshed every time the page is refreshed or this method will not work correctly.
                        //SHelper.get().page().refresh();
                    		SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(30)).on(element);
                        int actualElementCount = element.size();
                        if (actualElementCount == expectedTotalCount) {
                            result = true;
                        } else {
                            result = false;
                        }
                    } catch (Exception e) {
                    	
                    }
					return result;
                }
            });
        } catch (WebDriverException ex) {
            throw ex;
        }
	}

	public static class LocalWaitBuilder extends Commands {
		private int time;
		private int expectedTotalCount;
		
		public LocalWaitBuilder(WaitBuilder base) throws Exception {
			this.time = base.baseTime;
			this.expectedTotalCount = base.baseExpectedTotalCount;
        		failIfValueIsNotNull(base.baseValue);
        		failIfConditionIsNotNull(base.baseCondition);
        		failIfAttributeIsNotNull(base.baseAttribute);
		}
		
	}
}