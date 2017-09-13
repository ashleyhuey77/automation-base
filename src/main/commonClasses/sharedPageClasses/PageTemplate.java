package commonClasses.sharedPageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import commonClasses.sharedUtils.managers.LocalDriver;
import commonClasses.sharedUtils.managers.SHelper;
import seleniumHelper.SeleniumHelper;

/**
 * <h2>PageTemplate</h2>
 * <p>The PageTemplate class is meant to standardize the skeleton of the page classes.</p>
 * <p>It is meant to add a structure to the page class and serves as a sort of bridge between
 * the PageHelper and the PageControl.</p>
 * <p>It is meant to be inherited by all page classes and allows access to the PageHelper methods.</p>
 * <p>Initializing this method will do the following: </p>
 * <p>1. Set the instance of SeleniumHelper
 * </br>2. Set the PageFactory instance
 * </br>3. Execute the WaitForPageLoad method</p>
 * <p>It is set up this way due to the rapid growth of the automation projects. This leaves more room
 * for any new methods that might need to be executed at the page level as a part of initilization.</p>
 * @author ashleyhuey
 *
 */
public abstract class PageTemplate extends PageHelper implements PageControl {
	
	/**
	 * <h2>PageTemplate</h2>
	 * <p>The PageTemplate class is meant to standardize the skeleton of the page classes.</p>
	 * <p>It is meant to add a structure to the page class and serves as a sort of bridge between
	 * the PageHelper and the PageControl.</p>
	 * <p>It is meant to be inherited by all page classes and allows access to the PageHelper methods.</p>
	 * <p>Initializing this method will do the following: </p>
	 * <p>1. Set the instance of SeleniumHelper
	 * </br>2. Set the PageFactory instance
	 * </br>3. Execute the WaitForPageLoad method</p>
	 * <p>It is set up this way due to the rapid growth of the automation projects. This leaves more room
	 * for any new methods that might need to be executed at the page level as a part of initilization.</p>
	 * @author ashleyhuey
	 *
	 */
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
