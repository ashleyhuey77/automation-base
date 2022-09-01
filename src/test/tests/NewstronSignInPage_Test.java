package tests;

import com.warnermedia.config.TestException;
import com.warnermedia.config.data.UserHelper;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.config.testng.TestListener;
import com.warnermedia.data.mongo.config.DataMapper;
import com.warnermedia.page.core.SignInPage;
import com.warnermedia.utils.ex.SeleniumException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.utils.CredentialsType;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.driver.WebDriverListener;
import com.warnermedia.page.core.NewstronSignInPage;
import com.warnermedia.utils.CookieHelper;
import pages.AllAppsDashboard;
import pages.TestInitialization;
import pages.TestPage;

@Listeners({TestListener.class, WebDriverListener.class})
public class NewstronSignInPage_Test extends TestInitialization {

	
	@BeforeMethod
	public void goToNewsapps() {
		LocalDriver.getDriver().get("http://newstron-ref.turner.com/");
	}
	
	@Test
	public void verifyNewstronSignInPage() throws Exception {

		SignInPage _newstronSignInPage = new SignInPage();
		
		_newstronSignInPage.enterLoginDetails();
		CookieHelper.newHelper().getCookies().setCookies().build();
		   				_newstronSignInPage.clickTheSignInButton();
		CookieHelper.newHelper().getCookies().setCookies().build();
	}

	@Test(expectedExceptions= TestException.class)
	public void verifyNewstronSignInPage_3() throws Exception {

		SignInPage _newstronSignInPage = new SignInPage();

		_newstronSignInPage.testingError();
		_newstronSignInPage.clickTheSignInButton();
	}
	
	@Test
	public void verifyNewstronSignInPage_2() throws Exception {
		try {
			NewstronSignInPage<TestPage> _newstronSignInPage = new NewstronSignInPage<TestPage>(TestPage.class);

			_newstronSignInPage.enterLogInDetails(CredentialsType.BASE)
					.clickTheSignInButton();
			new AllAppsDashboard().reportThatPageLoadedSuccessfully();
		} catch (Exception e) {
			throw LocalReport.getReport().reportException(e);
		}
	}

}
