package tests;

import com.warnermedia.page.core.web.BaseGeneric;
import com.warnermedia.page.core.web.Fetch;
import com.warnermedia.selenium.TestElement;
import org.testng.annotations.Test;
import utils.Variables;

//@Listeners(WebDriverListener.class)
public class UtilsTest {

	@Test
	public void verifyGetTimeStamp() {
		Fetch fetch = new Fetch();
		TestElement el = fetch.element(BaseGeneric.ALL_APPS_LINK);
		String test = fetch.name(Variables.INPUT_ID_TEST);
		System.out.println(el.locator().value());
		System.out.println(test);
    }

}
