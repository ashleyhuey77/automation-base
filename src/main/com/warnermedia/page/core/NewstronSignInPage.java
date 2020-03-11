package com.warnermedia.page.core;

import org.openqa.selenium.WebDriverException;
import com.app.creds.CredentialsType;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.config.report.LocalValidation;
import com.warnermedia.config.settings.SignInHelper;
import com.warnermedia.page.core.web.BaseGeneric;
import com.warnermedia.page.utils.ClickHelper;
import com.warnermedia.page.utils.EnterTextHelper;
import com.warnermedia.page.utils.ReportInfo;
import com.warnermedia.page.utils.ClickHelper.ClickBuilder;
import com.warnermedia.page.utils.EnterTextHelper.EnterTextBuilder;
import com.warnermedia.selenium.shared.Via;
import com.warnermedia.selenium.text.Variable;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;
import com.warnermedia.utils.CookieManager;
import com.warnermedia.utils.TestUtils;
import org.testng.Assert;

/**
 * <p>NewstronSignInPage</p>
 * 
 * <p>The NewstronSignInPage class is meant to contain all of the
 * test step methods that occur on the Sign in page in the Newstron application. The sign in
 * page in newstron is defined as the very first page that a user with cleared 
 * cache encounters when first entering the newstron url into the browser</p>
 * 
 * <p>The NewstronSignInPage class has been included in the automation.base
 * project because it is shared between multiple projects. Typically, a page class
 * would not be included in the automation.base project, however an exception 
 * is made for page classes who are used across multiple projects.</p>
 * @author ashleyhuey
 *
 * @param <T> - This generic param should be substituted with the next page class
 * in the test step sequence. For example, if when the user signs in the next page
 * to appear is the AllAppsDashboard page, then this param should be set to the 
 * AllAppsDashboard page class object.
 */
public class NewstronSignInPage<T> extends PageTemplate {

	private ThreadLocal<Class<T>> type = new ThreadLocal<>();
	
	/**
	 * <p>NewstronSignInPage</p>
	 * 
	 * <p>The NewstronSignInPage class is meant to contain all of the
	 * test step methods that occur on the Sign in page in the Newstron application. The sign in
	 * page in newstron is defined as the very first page that a user with cleared 
	 * cache encounters when first entering the newstron url into the browser</p>
	 * 
	 * <p>The NewstronSignInPage class has been included in the automation.base
	 * project because it is shared between multiple projects. Typically, a page class
	 * would not be included in the automation.base project, however an exception 
	 * is made for page classes who are used across multiple projects.</p>
	 * @author ashleyhuey
	 *
	 * @param <T> - This generic param should be substituted with the next page class
	 * in the test step sequence. For example, if when the user signs in the next page
	 * to appear is the AllAppsDashboard page, then this param should be set to the 
	 * AllAppsDashboard page class object.
	 */
    public NewstronSignInPage(Class<T> type) throws TestException {
        super();
        setType(type);
    }
    
    private Class<T> getType() {
    	return type.get();
    }
    
    private void setType(Class<T> value) {
    	type.set(value);
    }
    
    private T getNewInstance() throws InstantiationException, IllegalAccessException {
    	return getType().newInstance();
    }

    /**
     * <p> WaitForPageLoad</p>
     * <p>The WaitForPageLoad method is a method defined in the Page control
     * interface. It is required for all pages to wait for the page to fully load
     * before attempting to execute the next test method in the chain as to reduce
     * uneccesary failures that are due to elements not yet being on the page.</p>
     * <p>The WaitForPageLoad method can be filled with any code as long as it
     * clearly defines a particular page being successfuly loaded so that methods do
     * not fail uneccesarily.</p>
     * @throws TestException 
     */
    @Override
    public void WaitForPageLoad() throws TestException {
        try {
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(60))
            		.on(BaseGeneric.USER_NAME_TEXT_FIELD.element());
        } catch (WebDriverException ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

    /**
     * <p>Enters the log in details (both username and password) for the Newstron Application.</p>
     * <p>Does not include the functionality to click the sign in button. There is a separate method 
     * within the NewstronSignInPage class that clicks the sign in button that has to be called in the
     * test in order for sign in to be complete.</p>
     * @return NewstronSignInPage
     * @throws TestException 
     */
    public NewstronSignInPage<T> enterLogInDetails() throws TestException {
        try {
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, 
            		new WaitBuilder().forAMaxTimeOf(30)).on(BaseGeneric.USER_NAME_TEXT_FIELD.element());
            new EnterTextHelper(new EnterTextBuilder(new ReportInfo(BaseGeneric.USER_NAME_TEXT_FIELD.name()))
            		.enterText(new String(SignInHelper.getName(CredentialsType.BASE)).trim())
            		.into(BaseGeneric.USER_NAME_TEXT_FIELD.element()));
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
            		new WaitBuilder().forAMaxTimeOf(30)).on(BaseGeneric.PWD_TEXT_FIELD.element());
            Thread.sleep(2000);
            new EnterTextHelper(new EnterTextBuilder(new ReportInfo(BaseGeneric.PWD_TEXT_FIELD.name()))
            		.enterText(new String(SignInHelper.getPassword(CredentialsType.BASE)).trim())
            		.into(BaseGeneric.PWD_TEXT_FIELD.element()));
            LocalValidation.getValidations().assertionPass("User is able to sign in successfully.");
            Thread.sleep(2000);
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
        return this;
    }
    
    /**
     * <p>Enters the log in details (both username and password) for the Newstron Application.</p>
     * <p>Does not include the functionality to click the sign in button. There is a separate method 
     * within the NewstronSignInPage class that clicks the sign in button that has to be called in the
     * test in order for sign in to be complete.</p>
     * @return NewstronSignInPage
     * @throws TestException 
     */
    public NewstronSignInPage<T> enterLogInDetails(CredentialsType type) throws TestException {
        try {
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, 
            		new WaitBuilder().forAMaxTimeOf(30)).on(BaseGeneric.USER_NAME_TEXT_FIELD.element());
            new EnterTextHelper(new EnterTextBuilder(new ReportInfo(BaseGeneric.USER_NAME_TEXT_FIELD.name()))
            		.enterText(new String(SignInHelper.getName(type)).trim())
            		.into(BaseGeneric.USER_NAME_TEXT_FIELD.element()));
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
            		new WaitBuilder().forAMaxTimeOf(30)).on(BaseGeneric.PWD_TEXT_FIELD.element());
            Thread.sleep(2000);
            new EnterTextHelper(new EnterTextBuilder(new ReportInfo(BaseGeneric.PWD_TEXT_FIELD.name()))
            		.enterText(new String(SignInHelper.getPassword(type)).trim())
            		.into(BaseGeneric.PWD_TEXT_FIELD.element()));
            LocalValidation.getValidations().assertionPass("User is able to sign in successfully.");
            Thread.sleep(2000);
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
        return this;
    }

    /**
     * <p>Clicks the Sign In button</p>
     * <p>There is a separate method to enter log in details. If the sign in button
     * is clicked without entering login details, then the error message will display in 
     * NewsApps and a user will not be able to sign in.</p>
     * <p>Also this contains a check for failed sign in. If the login failed error
     * message displays at any point after clicking the sign in button, then the 
     * test will fail.</p>
     * @return NewstronSignInPage
     * @throws TestException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    public T clickTheSignInButton() throws TestException, InstantiationException, IllegalAccessException {
        try {
        		new ClickHelper(new ClickBuilder(new ReportInfo(BaseGeneric.SIGN_IN_BTN.name()))
        				.clickOn(BaseGeneric.SIGN_IN_BTN.element())
        				.how(Via.JAVASCRIPT));
            if (SHelper.get().element().isDisplayed(BaseGeneric.ERROR_MSG.element(), 3)) {
                String errorText = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(BaseGeneric.ERROR_MSG.element());
                if (!TestUtils.isNullOrBlank(errorText)) {
                    	new ClickHelper(new ClickBuilder(new ReportInfo(BaseGeneric.SIGN_IN_BTN.name()))
                				.clickOn(BaseGeneric.SIGN_IN_BTN.element())
                				.how(Via.JAVASCRIPT));
                		Thread.sleep(3000);
                    String errorText2 = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(BaseGeneric.ERROR_MSG.element());
                    if (!TestUtils.isNullOrBlank(errorText2)) {
                    		throw LocalValidation.getValidations().assertionFailed(errorText2);
                    }
                } 
            }
            CookieManager.setCookies(LocalDriver.getDriver().manage().getCookies());
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
        return getNewInstance();
    }

}