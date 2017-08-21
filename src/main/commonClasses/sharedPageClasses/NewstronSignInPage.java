package commonClasses.sharedPageClasses;

import org.openqa.selenium.WebDriverException;
import seleniumHelper.enums.*;
import commonClasses.sharedUtils.managers.*;

public class NewstronSignInPage<T> extends PageTemplate {

	private ThreadLocal<Class<T>> type = new ThreadLocal<Class<T>>();
	
    public NewstronSignInPage(Class<T> type) throws Exception {
        super();
        setType(type);
    }
    
    private Class<T> getType() {
    	return type.get();
    }
    
    private void setType(Class<T> value) {
    	type.set(value);
    }
    
    private T getNewInstance() throws Exception {
    	return getType().newInstance();
    }

    @Override
    public void WaitForPageLoad() throws Exception {
        try {
            SHelper.get().waitMethod(WaitFor.PRESENCE_OF_ELEMENT_OR_VALUE).waitOn(BaseGeneric.usrNameTxtField, cssSelector, 60);
        } catch (WebDriverException ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

    /**
     * <summary> Method to enter the log in values into Newstron </summary>
     * 
     * @return NewstronSignInPage
     */
    public NewstronSignInPage<T> enterLogInDetails() throws Exception {
        try {
            String pwd = LocalTest.getCredentials().getNewstronPWord();
            String usrnm = LocalTest.getCredentials().getNewstronUN();
            SHelper.get().waitMethod(WaitFor.PRESENCE_OF_ELEMENT_OR_VALUE).waitOn(BaseGeneric.usrNameTxtField, cssSelector, 30);
            enterAvalueIntoATextField(usrnm.trim(), BaseGeneric.usrNameTxtField, cssSelector, "Username field");
            SHelper.get().waitMethod(WaitFor.PRESENCE_OF_ELEMENT_OR_VALUE).waitOn(BaseGeneric.pwdTxtField, cssSelector, 30);
            Thread.sleep(800);
            enterAvalueIntoATextField(pwd.trim(), BaseGeneric.pwdTxtField, cssSelector, "Password field");
            LocalValidation.getValidations().assertionPass("User is able to sign in successfully.");
        } catch (WebDriverException ex) {
            throw LocalReport.getReport().reportException(ex);
        }
        return this;
    }

    /**
     * <summary> Method to click the Sign In button </summary>
     * 
     * @return NewstronSignInPage
     */

    public T clickTheSignInButton() throws Exception {
        try {
            clickSomeElement(Via.SELENIUM, BaseGeneric.signInBtn, cssSelector, "Newstron Sign in Button");
            if (SHelper.get().element().isDisplayed(BaseGeneric.errorText, xpath, 7)) {
                String errorText = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(BaseGeneric.errorMessage, id);
                throw LocalValidation.getValidations().assertionFailed(errorText);
            }
        } catch (WebDriverException ex) {
            throw LocalReport.getReport().reportException(ex);
        }
        return getNewInstance();
    }

}