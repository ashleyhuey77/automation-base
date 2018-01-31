package seleniumHelper.methods;

import java.util.List;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.utils.managers.LocalDriver;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.builders.WaitBuilder;
import seleniumHelper.interfaces.IWait;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;

public class NonPresentElement extends Commands implements IWait {

	protected int time = 0;
	
	public NonPresentElement(WaitBuilder build) throws Exception {
		LocalWaitBuilder builder = new LocalWaitBuilder(build);
		this.time = builder.time;
	}

	@Override
    public void on(Locator locator, By by) throws Exception {
        try {
        		verifyMaxWaitTimeIsNotZero(time);
            new WebDriverWait(LocalDriver.getDriver(), time).until(ExpectedConditions.invisibilityOfElementLocated(getByValueBasedOnUserInput(locator, by)));
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void on(WebElement element) throws Exception {
        try {
        		verifyMaxWaitTimeIsNotZero(time);
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);

            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                    Boolean result = false;
                    try {
                        if (!element.isDisplayed()) {
                            result = true;
                            return result;
                        }
                    } catch (StaleElementReferenceException ex) {
                    		result = true;
                        return result;
                    }
                    return result;
                };
            });
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

	@Override
	public void on(List<WebElement> element) throws Exception {

	}
	
	public static class LocalWaitBuilder extends Commands {
		private int time;
		
		public LocalWaitBuilder(WaitBuilder base) throws Exception {
			this.time = base.baseTime;
        		failIfValueIsNotNull(base.baseValue);
        		failIfConditionIsNotNull(base.baseCondition);
        		failIfExpectedCountIsNotZero(base.baseExpectedTotalCount);
        		failIfAttributeIsNotNull(base.baseAttribute);
		}
		
	}

}