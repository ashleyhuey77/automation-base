package tests;

import com.config.TestException;
import com.config.setup.report.LocalReport;
import com.config.setup.report.LocalValidation;
import com.config.setup.browser.TestListener;
import com.page.core.SignInPage;
import com.utils.devtools.Switch;
import com.utils.devtools.ConsoleErrorLogger;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.config.setup.browser.LocalDriver;
import com.config.setup.browser.WebDriverListener;
import com.utils.devtools.CookieHelper;
import pages.TestInitialization;

@Slf4j
@Listeners({TestListener.class, WebDriverListener.class})
public class NewstronSignInPage_Test extends TestInitialization {

	
	@BeforeMethod
	public void goToNewsapps() {
		LocalDriver.getDriver().get("http://newstron-ref.turner.com/");
	}
	
	@Test
	public void verifyNewstronSignInPage() throws Exception {
		try {
			new ConsoleErrorLogger<String>(SignInPage.class.getName())
					.setState(Switch.ON);
			SignInPage _newstronSignInPage = new SignInPage();

			_newstronSignInPage.enterLoginDetails();
			CookieHelper.newHelper().getCookies().setCookies().build();
			new ConsoleErrorLogger()
					.setState(Switch.OFF);
			_newstronSignInPage.clickTheSignInButton();
			CookieHelper.newHelper().getCookies().setCookies().build();
			throw LocalValidation.getValidations().assertionFailed(log, "Test Failure");
		} catch (Exception e) {
			throw LocalReport.getReport().reportException(e);
		}
	}

	@Test(expectedExceptions= TestException.class)
	public void verifyNewstronSignInPage_3() throws Exception {

		SignInPage _newstronSignInPage = new SignInPage();

		_newstronSignInPage.testingError();
		_newstronSignInPage.clickTheSignInButton();
	}

}
