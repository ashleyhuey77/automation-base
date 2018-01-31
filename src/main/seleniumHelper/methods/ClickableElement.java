package seleniumHelper.methods;

import java.util.List;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.utils.managers.LocalDriver;
import seleniumHelper.abstracts.Commands;
import seleniumHelper.builders.WaitBuilder;
import seleniumHelper.interfaces.IWait;
import seleniumHelper.valueObjects.By;
import seleniumHelper.valueObjects.Locator;

public class ClickableElement extends Commands implements IWait {
	
	protected int time = 0;
	
	public ClickableElement(WaitBuilder build) throws Exception {
		LocalWaitBuilder builder = new LocalWaitBuilder(build);
		this.time = builder.time;
	}

	@Override
    public void on(Locator locator, By by) throws Exception {
        try {
        		verifyMaxWaitTimeIsNotZero(time);
        		new WebDriverWait(LocalDriver.getDriver(), time).until(ExpectedConditions.elementToBeClickable(getByValueBasedOnUserInput(locator, by)));
        } catch (WebDriverException ex) {
            throw ex;
        }
    }

    @Override
    public void on(WebElement element) throws Exception {
        try {
        		verifyMaxWaitTimeIsNotZero(time);
        		new WebDriverWait(LocalDriver.getDriver(), time).until(ExpectedConditions.elementToBeClickable(element));
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