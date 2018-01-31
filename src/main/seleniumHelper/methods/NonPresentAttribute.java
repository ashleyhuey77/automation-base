package seleniumHelper.methods;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.utils.managers.LocalDriver;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.builders.WaitBuilder;
import seleniumHelper.interfaces.IWait;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;

public class NonPresentAttribute extends Commands implements IWait {

	protected int time = 0;
	protected String attribute;
	
	public NonPresentAttribute(WaitBuilder build) throws Exception{
		LocalWaitBuilder builder = new LocalWaitBuilder(build);
		this.time = builder.time;
		this.attribute = builder.attribute;
	}

	@Override
    public void on(Locator locator, By by) throws Exception {
        try {
    			verifyAttributeIsNotNull(attribute);
    			verifyMaxWaitTimeIsNotZero(time);
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                    WebElement elementToBeTested = getElement(locator, by);
                    String value = elementToBeTested.getAttribute(attribute);
                    if (value == null || value.equals("")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void on(WebElement element) throws Exception {
        try {
			verifyAttributeIsNotNull(attribute);
			verifyMaxWaitTimeIsNotZero(time);
            WebDriverWait wait = new WebDriverWait(LocalDriver.getDriver(), time);
            wait.until(new ExpectedCondition < Boolean > () {
                public Boolean apply(WebDriver driver) {
                    WebElement elementToBeTested = element;
                    String value = elementToBeTested.getAttribute(attribute);
                    if (value == null || value.equals("")) {
                        return true;
                    } else {
                        return false;
                    }
                }
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
		private String attribute;
		
		public LocalWaitBuilder(WaitBuilder base) throws Exception {
			this.time = base.baseTime;
			this.attribute = base.baseAttribute;
    			failIfConditionIsNotNull(base.baseCondition);
    			failIfExpectedCountIsNotZero(base.baseExpectedTotalCount);
    			failIfValueIsNotNull(base.baseValue);
		}

	}
}