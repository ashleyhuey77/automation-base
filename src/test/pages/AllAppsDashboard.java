package pages;

import com.config.SHelper;
import com.config.TestException;
import com.config.setup.report.LocalReport;
import com.config.setup.report.LocalValidation;
import com.page.core.PageTemplate;
import com.selenium.By;
import com.selenium.Locator;
import com.selenium.TestElement;
import com.selenium.wait.Wait;
import com.selenium.wait.WaitBuilder;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.How;

import java.time.Duration;

@Slf4j
public class AllAppsDashboard extends PageTemplate {

    public AllAppsDashboard() throws TestException {
        super();
    }

    @Override
    public void WaitForPageLoad() throws TestException {
        try {
            SHelper.get().waitMethod(Wait.PRESENCE_OF_ELEMENT, new WaitBuilder().forAMaxTimeOf(Duration.ofSeconds(20))).on(new TestElement(new Locator("div[class='dashboard-app']"), new By(How.CSS)));
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
    }

    public AllAppsDashboard reportThatPageLoadedSuccessfully() throws TestException {
        LocalValidation.getValidations().assertionPass(log, "Dashboard page loaded as expected.");
        return this;
    }
}
