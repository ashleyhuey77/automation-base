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
import com.warnermedia.selenium.shared.Via;
import com.warnermedia.selenium.text.Variable;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;
import com.warnermedia.utils.CookieManager;
import com.warnermedia.utils.TestUtils;
import com.warnermedia.utils.Url;

@Url(url = "/newstron/record/timeline.html#")
public class SignInPage extends PageTemplate {

	public SignInPage() throws TestException {
		super();
	}

	@Override
	public void WaitForPageLoad() throws TestException {
        try {
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(60))
            		.on(BaseGeneric.USER_NAME_TEXT_FIELD.element());
        } catch (WebDriverException ex) {
            throw LocalReport.getReport().reportException(ex);
        }
	}
	
	public SignInPage enterLoginDetails() throws TestException {
		try {
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, 
            		new WaitBuilder().forAMaxTimeOf(30)).on(BaseGeneric.USER_NAME_TEXT_FIELD.element());
            enter()
					.text(new String(SignInHelper.getName(CredentialsType.BASE)).trim())
					.into(BaseGeneric.USER_NAME_TEXT_FIELD)
					.start();
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
            		new WaitBuilder().forAMaxTimeOf(30)).on(BaseGeneric.PWD_TEXT_FIELD.element());
			enter()
					.text(new String(SignInHelper.getPassword(CredentialsType.BASE)).trim())
					.into(BaseGeneric.PWD_TEXT_FIELD)
					.start();
            LocalValidation.getValidations().assertionPass("User is able to sign in successfully.");
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
		return this;
	}
	
	public SignInPage enterLoginDetails(CredentialsType type) throws TestException {
		try {
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, 
            		new WaitBuilder().forAMaxTimeOf(30)).on(BaseGeneric.USER_NAME_TEXT_FIELD.element());
			enter()
					.text(new String(SignInHelper.getName(type)).trim())
					.into(BaseGeneric.USER_NAME_TEXT_FIELD)
					.start();
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
            		new WaitBuilder().forAMaxTimeOf(30)).on(BaseGeneric.PWD_TEXT_FIELD.element());
			enter()
					.text(new String(SignInHelper.getPassword(type)).trim())
					.into(BaseGeneric.PWD_TEXT_FIELD)
					.start();
            LocalValidation.getValidations().assertionPass("User is able to sign in successfully.");
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
		return this;
	}
	
	public SignInPage clickTheSignInButton() throws TestException {
	       try {
	       	click().on(BaseGeneric.SIGN_IN_BTN).how(Via.JAVASCRIPT).start();
           if (SHelper.get().element().isDisplayed(BaseGeneric.ERROR_MSG.element(), 3)) {
			   String errorText = getErrorText();
			   if (!TestUtils.isNullOrBlank(errorText)) {
			   	   click().on(BaseGeneric.SIGN_IN_BTN).how(Via.JAVASCRIPT).start();
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
	   return this;
	}

	private String getErrorText() {
		String result = null;
		try {
			result = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(BaseGeneric.ERROR_MSG.element());
		} catch (Exception e) {
			result = null;
		}
		return result;
	}

}
