package commonClasses.sharedUtils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import reporting.framework.reporting.ReportSettings;
import reporting.framework.reporting.ReportTheme;
import reporting.framework.reporting.Status;
import reporting.framework.utilities.FrameworkException;


public class HtmlReport extends reporting.framework.reporting.Report {

    public WebDriver driver;
    @SuppressWarnings("unused")
    private ReportSettings reportSettings;
    private int failCount;

    //<summary>
    //Constructor to initialize the Report
    //</summary>
    //<param name="htmlReport"></param>
    //<param name="msTestReport"></param>

    public HtmlReport(ReportSettings reportSettings, ReportTheme reportTheme) {
        super(reportSettings, reportTheme);

        this.reportSettings = reportSettings;
        failCount = 0;

    }

    @Override
    protected void TakeScreenshot(String screenshotPath) throws FrameworkException, IOException {
        try {

            if (driver == null) {
                throw new FrameworkException("Report.driver is not initialized!");
            }

            WebDriver _driver = new Augmenter().augment(driver);
            File source = ((TakesScreenshot) _driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File(screenshotPath), true);
            //FileUtils.copyFile(source, new File(screenshotPath));
        } catch (Exception exception) {
            reportWarning("TakeScreenshot", exception.getMessage());
        }
    }


    /// <summary>
    /// Add a done step to the test report
    /// </summary>
    /// <param name="stepName"></param>
    /// <param name="description"></param>

    public void reportDoneEvent(String stepName, String description) throws FrameworkException, IOException {

        super.UpdateTestLog(stepName, description, Status.DONE);

    }

    /// <summary>
    /// Add a done step to the test report
    /// </summary>
    /// <param name="stepName"></param>
    /// <param name="description"></param>

    public void reportWarning(String stepName, String description) throws FrameworkException, IOException {
        super.UpdateTestLog(stepName, description, Status.WARNING);

    }

    /// <summary>
    /// Add a Passed step to the test report </summary>
    /// <param name="stepName"> </param>
    /// <param name="description"> </param>
    public void reportPassEvent(String stepName, String description) throws FrameworkException, IOException {
        try {

            super.UpdateTestLog(stepName, description, Status.PASS);

        } catch (Exception invalidOperationException) {
            super.UpdateTestLog("reportPassEvent", invalidOperationException.getMessage(), Status.WARNING);
        }
    }

    /// <summary>
    /// Add a failed report to the test report </summary>
    /// <param name="stepName"> </param>
    /// <param name="description"> </param>
    public void reportFailEvent(String stepName, String description) throws FrameworkException, IOException {
        try {
            failCount = failCount + 1;
            super.UpdateTestLog(stepName, description, Status.FAIL);

        } catch (Exception invalidOperationException) {
            super.UpdateTestLog(stepName, description, Status.WARNING);
            super.UpdateTestLog("reportFailEvent", invalidOperationException.getMessage(), Status.WARNING);
        }
    }

}