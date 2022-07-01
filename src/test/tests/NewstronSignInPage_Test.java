package tests;

import com.warnermedia.config.TestException;
import com.warnermedia.config.data.UserHelper;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.config.testng.TestListener;
import com.warnermedia.data.mongo.config.DataMapper;
import com.warnermedia.page.core.SignInPage;
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
		LocalDriver.getDriver().get("http://newstron-ref.turner.com/newstron/newstron.html");
	}
	
	@Test
	public void verifyNewstronSignInPage() throws Exception {
		System.out.println(new String(UserHelper.getName(CredentialsType.BASE)));
		System.out.println(new String(UserHelper.getPassword(CredentialsType.BASE)));
		System.out.println(new String(UserHelper.getName(CredentialsType.MIRA)));
		System.out.println(new String(UserHelper.getPassword(CredentialsType.MIRA)));

		SignInPage _newstronSignInPage = new SignInPage();
		
		_newstronSignInPage.enterLoginDetails();
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

	@Test
	public void verifyLocalTestData() throws Exception {
		try {
			System.out.println(DataMapper.local().fsBaseUrl());
		} catch (Exception e) {
			throw LocalReport.getReport().reportException(e);
		}
	}

}
