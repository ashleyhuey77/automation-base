package pages;

import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.config.report.LocalValidation;
import com.warnermedia.page.core.PageTemplate;
import com.warnermedia.selenium.By;
import com.warnermedia.selenium.Locator;
import com.warnermedia.selenium.TestElement;
import com.warnermedia.selenium.wait.Wait;
import com.warnermedia.selenium.wait.WaitBuilder;
import org.junit.Test;
import org.openqa.selenium.support.How;

public class AllAppsDashboard extends PageTemplate {

    public AllAppsDashboard() throws TestException {
        super();
    }

    @Override
    public void WaitForPageLoad() throws TestException {
        try {
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(20)).on(new TestElement(new Locator("div[class='dashboard-app']"), new By(How.CSS)));
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
    }

    public AllAppsDashboard reportThatPageLoadedSuccessfully() throws TestException {
        LocalValidation.getValidations().assertionPass("Dashboard page loaded as expected.");
        return this;
    }
}
