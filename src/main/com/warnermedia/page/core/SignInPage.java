package com.warnermedia.page.core;

import com.warnermedia.config.data.UserHelper;
import com.warnermedia.selenium.wait.Condition;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriverException;
import com.utils.CredentialsType;
import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.config.report.LocalValidation;
import com.warnermedia.page.core.web.BaseGeneric;
import com.warnermedia.selenium.shared.Via;
import com.warnermedia.selenium.text.Variable;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;
import com.warnermedia.utils.CookieManager;
import com.warnermedia.utils.TestUtils;
import com.warnermedia.utils.Url;

import java.time.Duration;

@Slf4j
@Url(url = "/newstron/record/timeline.html#")
public class SignInPage extends PageTemplate {

    public SignInPage() throws TestException {
        super();
    }

    @Override
    public void WaitForPageLoad() throws TestException {
        try {
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(60))).on(BaseGeneric.USER_NAME_TEXT_FIELD.element(), BaseGeneric.USER_NAME_TEXT_FIELD2.element(), BaseGeneric.USER_NAME_TEXT_FIELD3.element());
            LocalReport.getReport().reportDoneEvent(log, "The login page loaded as expected.");
        } catch (WebDriverException ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

    /**
     * <p>Enters the log in details (both username and password) for the Newstron Application.</p>
     * <p>Does not include the functionality to click the sign in button. There is a separate method
     * within the NewstronSignInPage class that clicks the sign in button that has to be called in the
     * test in order for sign in to be complete.</p>
     *
     * @return NewstronSignInPage
     * @throws TestException
     */
    public SignInPage enterLoginDetails() throws TestException {
        try {
            if (SHelper.get().element().isDisplayed(BaseGeneric.USER_NAME_TEXT_FIELD.element(), Duration.ofSeconds(1))) {
                enterOldCreds(CredentialsType.BASE);
            } else {
                enterNewCreds(CredentialsType.BASE);
            }
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
     *
     * @return NewstronSignInPage
     * @throws TestException
     */
    public SignInPage enterLoginDetails(CredentialsType type) throws TestException {
        try {
            if (SHelper.get().element().isDisplayed(BaseGeneric.USER_NAME_TEXT_FIELD.element(), Duration.ofSeconds(1))) {
                enterOldCreds(type);
            } else {
                enterNewCreds(type);
            }
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
     *
     * @return NewstronSignInPage
     * @throws TestException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public SignInPage clickTheSignInButton() throws TestException {
        try {
            if (SHelper.get().element().isDisplayed(BaseGeneric.SIGN_IN_BTN.element(), Duration.ofSeconds(1))) {
                selectOldLoginButton();
            } else {
                selectNewLoginButton();
            }
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

    private void enterOldCreds(CredentialsType type) throws TestException {
        try {
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
                    new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(30))).on(BaseGeneric.USER_NAME_TEXT_FIELD.element());
            enter()
                    .text(new String(UserHelper.getName(CredentialsType.BASE)).trim())
                    .into(BaseGeneric.USER_NAME_TEXT_FIELD)
                    .start();
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
                    new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(30))).on(BaseGeneric.PWD_TEXT_FIELD.element());
            enter()
                    .text(new String(UserHelper.getPassword(CredentialsType.BASE)).trim())
                    .into(BaseGeneric.PWD_TEXT_FIELD)
                    .start();
            LocalValidation.getValidations().assertionPass(log, "User is able to sign in successfully.");
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

    public void testingError() throws TestException {
        try {
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
                    new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(30))).on(BaseGeneric.USER_NAME_TEXT_FIELD.element());
            enter()
                    .text("blah")
                    .into(BaseGeneric.USER_NAME_TEXT_FIELD)
                    .start();
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
                    new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(30))).on(BaseGeneric.PWD_TEXT_FIELD.element());
            enter()
                    .text("bingo")
                    .into(BaseGeneric.PWD_TEXT_FIELD)
                    .start();
            LocalValidation.getValidations().assertionPass(log, "User is able to sign in successfully.");
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

    private void enterNewCreds(CredentialsType type) throws TestException {
        try {
            if (SHelper.get().element().isDisplayed(BaseGeneric.PWD_TEXT_FIELD2.element(), Duration.ofSeconds(1))
                && SHelper.get().element().isDisplayed(BaseGeneric.USER_NAME_TEXT_FIELD3.element(), Duration.ofSeconds(1))) {
                enter()
                        .text("Ashley.Huey@warnermedia.com")
                        .into(BaseGeneric.USER_NAME_TEXT_FIELD3)
                        .start();

                enter()
                        .text(new String(UserHelper.getPassword(CredentialsType.BASE)).trim())
                        .into(BaseGeneric.PWD_TEXT_FIELD2)
                        .start();
            } else if (SHelper.get().element().isDisplayed(BaseGeneric.NEXT_BUTTON.element(), Duration.ofSeconds(1))) {
                enter()
                        .text("Ashley.Huey@warnermedia.com")
                        .into(BaseGeneric.USER_NAME_TEXT_FIELD2)
                        .start();
                click().on(BaseGeneric.NEXT_BUTTON).start();
                SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT,
                        new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(30))).on(BaseGeneric.PWD_TEXT_FIELD2.element());
                enter()
                        .text(new String(UserHelper.getPassword(CredentialsType.BASE)).trim())
                        .into(BaseGeneric.PWD_TEXT_FIELD2)
                        .start();
            } else {
                throw LocalValidation.getValidations().assertionFailed(log, "The sign in page does not appear as expected.");
            }
            LocalValidation.getValidations().assertionPass(log, "User is able to enter credentials.");
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

    private void selectOldLoginButton() throws TestException {
        try {
            click().on(BaseGeneric.SIGN_IN_BTN).start();
            Thread.sleep(3000);
            SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(90))).on(BaseGeneric.SIGN_IN_BTN.element());
            CookieManager.setCookies(LocalDriver.getDriver().manage().getCookies());
        } catch (Exception ex) {
            try {
                SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT_TEXT, new WaitBuilder().to(Condition.CONTAIN).value("is incorrect").forAMaxTimeOf(Duration.ofSeconds(10))).on(BaseGeneric.ERROR_MSG.element());
                String errorText = getErrorText();
                if (!TestUtils.isNullOrBlank(errorText)) {
                    throw LocalValidation.getValidations().assertionFailed(log, errorText);
                }
            } catch (Exception e2) {
                throw LocalReport.getReport().reportException(ex);
            }
        }
    }

    private void selectNewLoginButton() throws TestException {
        try {
            click().on(BaseGeneric.LOG_IN_BTN).start();
            SHelper.get().waitMethod(Wait.ELEMENT_NOT_TO_BE_PRESENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(100))).on(BaseGeneric.LOG_IN_BTN.element());
            CookieManager.setCookies(LocalDriver.getDriver().manage().getCookies());
        } catch (Exception ex) {
            throw LocalReport.getReport().reportException(ex);
        }
    }

}
