package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import common.base.NewstronSignInPage;
import common.utils.WebDriverListener;
import common.utils.managers.LocalDriver;
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
		
		_newstronSignInPage.enterLogInDetails()
		   				   .clickTheSignInButton();
	}

}
