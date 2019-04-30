package common.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import common.base.helpers.ClickHelper;
import common.base.helpers.ClickHelper.ClickBuilder;
import common.base.vobjects.ReportInfo;
import common.utils.managers.LocalDriver;
import common.utils.managers.LocalReport;
import common.utils.managers.SHelper;
import log.TestException;
import shelper.SeleniumHelper;
import shelper.builders.WaitBuilder;
import shelper.enums.Via;
import shelper.enums.Wait;

/**
 * <h2>PageTemplate</h2>
 * <p>
 * The PageTemplate class is meant to standardize
 * the skeleton of the page classes.
 * </p>
 * <p>
 * It is meant to add a structure to the page
 * class and serves as a sort of bridge between
 * the PageHelper and the PageControl.
 * </p>
 * <p>
 * It is meant to be inherited by all page classes
 * and allows access to the PageHelper methods.
 * </p>
 * <p>
 * Initializing this method will do the following:
 * </p>
 * <p>
 * 1. Set the instance of SeleniumHelper </br>
 * 2. Set the PageFactory instance </br>
 * 3. Execute the WaitForPageLoad method
 * </p>
 * <p>
 * It is set up this way due to the rapid growth
 * of the automation projects. This leaves more
 * room for any new methods that might need to be
 * executed at the page level as a part of
 * initilization.
 * </p>
 * 
 * @author ashleyhuey
 */
public abstract class PageTemplate extends PageHelper implements PageControl {

	/**
	 * <h2>PageTemplate</h2>
	 * <p>
	 * The PageTemplate class is meant to standardize
	 * the skeleton of the page classes.
	 * </p>
	 * <p>
	 * It is meant to add a structure to the page
	 * class and serves as a sort of bridge between
	 * the PageHelper and the PageControl.
	 * </p>
	 * <p>
	 * It is meant to be inherited by all page classes
	 * and allows access to the PageHelper methods.
	 * </p>
	 * <p>
	 * Initializing this method will do the following:
	 * </p>
	 * <p>
	 * 1. Set the instance of SeleniumHelper </br>
	 * 2. Set the PageFactory instance </br>
	 * 3. Execute the WaitForPageLoad method
	 * </p>
	 * <p>
	 * It is set up this way due to the rapid growth
	 * of the automation projects. This leaves more
	 * room for any new methods that might need to be
	 * executed at the page level as a part of
	 * initilization.
	 * </p>
	 * 
	 * @author ashleyhuey
	 * @throws Exception
	 */
	public PageTemplate() throws TestException {
		super();

		WebDriver browser = LocalDriver.getDriver();
		SHelper.set(new SeleniumHelper());
		PageFactory.initElements(browser, this);
		WaitForPageLoad();
		clearAllNotificationsAndRefreshPage();
	}

	/**
	 * <p>
	 * Method to clear all the notifications so they
	 * don't continue to pop up. Clearing them all at
	 * the beginning of the test prevents
	 * miscellaneous erros from occurring later on in
	 * the test. Selenium sometimes has focus issues
	 * when random elements pop up on the screen.
	 * </p>
	 * 
	 * @throws TestException
	 */
	private void clearAllNotificationsAndRefreshPage() throws TestException {
		try {
			if (SHelper.get().element().isDisplayed(BaseGeneric.NOTIFICATIONS_BUTTON.element(), 1)) {
				if (SHelper.get().element().isClickable(BaseGeneric.NOTIFICATIONS_BUTTON.element())) {
					new ClickHelper(new ClickBuilder(new ReportInfo(BaseGeneric.NOTIFICATIONS_BUTTON.name()))
							.clickOn(BaseGeneric.NOTIFICATIONS_BUTTON.element()));
					SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(4))
							.on(BaseGeneric.NOTIFICATION_CONTENT.element());
					clickTheNewsAppsToggle();
					SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT, new WaitBuilder().forAMaxTimeOf(5))
							.on(BaseGeneric.NOTIFICATION_CONTENT.element());
					clickTheNewsAppsToggle();
					WaitForPageLoad();
					LocalReport.getReport().reportDoneEvent("Notifications were successfully cleared.");
				}
			}
		} catch (Exception e) {
			LocalReport.getReport().reportDoneEvent("No notifications were cleared.");
		}
	}

	private void clickTheNewsAppsToggle() throws TestException {
		try {
			if (SHelper.get().element().isDisplayed(BaseGeneric.CORE_APPS_TOGGLE.element(), 1)) {
				if (SHelper.get().element().isClickable(BaseGeneric.CORE_APPS_TOGGLE.element())) {
					SHelper.get().click(Via.SELENIUM).on(BaseGeneric.CORE_APPS_TOGGLE.element());
				}
			} else {
				if (SHelper.get().element().isClickable(BaseGeneric.NEWS_APPS_TOGGLE.element())) {
					SHelper.get().click(Via.SELENIUM).on(BaseGeneric.NEWS_APPS_TOGGLE.element());
				}
			}
		} catch (Exception e) {
			LocalReport.getReport().reportDoneEvent("No notifications were cleared.");
		}
	}

}
