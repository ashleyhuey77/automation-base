package tests;

import com.warnermedia.config.TestException;
import com.warnermedia.config.report.LocalReport;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.app.creds.CredentialsType;
import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.driver.WebDriverListener;
import com.warnermedia.page.core.NewstronSignInPage;
import com.warnermedia.utils.CookieHelper;
import pages.AllAppsDashboard;
import pages.TestInitialization;
import pages.TestPage;

@Listeners(WebDriverListener.class)
public class NewstronSignInPage_Test extends TestInitialization {
	
	@BeforeMethod
	public void goToNewsapps() {
		LocalDriver.getDriver().get("http://newstron-ref.turner.com/");
	}
	
	@Test
	public void verifyNewstronSignInPage() throws Exception {
		NewstronSignInPage<TestPage> _newstronSignInPage = new NewstronSignInPage<TestPage>(TestPage.class);
		
		_newstronSignInPage.enterLogInDetails();
		CookieHelper.newHelper().getCookies().setCookies().build();
		   				_newstronSignInPage.clickTheSignInButton();
		CookieHelper.newHelper().getCookies().setCookies().build();
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
