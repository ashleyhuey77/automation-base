import com.warnermedia.config.driver.LocalDriver;
import com.warnermedia.config.driver.WebDriverListener;
import com.warnermedia.page.core.SignInPage;
import com.warnermedia.utils.CookieHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.TestInitialization;

@Listeners(WebDriverListener.class)
public class Testing extends TestInitialization {

    @BeforeMethod
    public void goToNewsapps() {
        LocalDriver.getDriver().get("http://newstron-ref.turner.com/newstron/newstron.html");
    }

    @Test
    public void test1() throws Exception {
        SignInPage _newstronSignInPage = new SignInPage();

        _newstronSignInPage.enterLoginDetails();
    }

    @Test
    public void test2() throws Exception {
        SignInPage _newstronSignInPage = new SignInPage();

        _newstronSignInPage.enterLoginDetails();
    }

    @Test
    public void test3() throws Exception {
        SignInPage _newstronSignInPage = new SignInPage();

        _newstronSignInPage.enterLoginDetails();
    }

    @Test
    public void test4() throws Exception {
        SignInPage _newstronSignInPage = new SignInPage();

        _newstronSignInPage.enterLoginDetails();
    }

    @Test
    public void test5() throws Exception {
        SignInPage _newstronSignInPage = new SignInPage();

        _newstronSignInPage.enterLoginDetails();
    }
}
