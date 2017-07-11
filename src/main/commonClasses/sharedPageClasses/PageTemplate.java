package commonClasses.sharedPageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import commonClasses.sharedUtils.managers.LocalDriver;
import commonClasses.sharedUtils.managers.SHelper;
import seleniumHelper.SeleniumHelper;

public abstract class PageTemplate extends PageHelper implements PageControl {
	
	public PageTemplate() throws Exception {
		super();
		
		WebDriver browser = LocalDriver.getDriver();
		SHelper.set(new SeleniumHelper());
		PageFactory.initElements(browser, this);
        WaitForPageLoad();
	}
	
	@Override
	public abstract void WaitForPageLoad() throws Exception;

}
