package com.warnermedia.utils.observers.app;

import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.page.core.PageUtils;
import com.warnermedia.page.core.web.BaseGeneric;
import com.warnermedia.page.utils.ElementHelper;
import com.warnermedia.selenium.shared.Via;
import com.warnermedia.selenium.text.Variable;
import com.warnermedia.selenium.wait.Condition;
import com.warnermedia.utils.TestUtils;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class AppObserverHelper extends PageUtils {

    public static ThreadLocal<IssueType> issueType = new ThreadLocal<>();

    public AppObserverHelper(IssueType currentState) throws TestException {
        IssueType actualIssueType;
        if (currentState == null) {
            actualIssueType = IssueType.NONE;
        } else {
            actualIssueType = currentState;
        }
        actualIssueType = checkAuthServicesIssue();

        issueType.set(actualIssueType);
    }

    private IssueType checkAuthServicesIssue() throws TestException {
        IssueType result = IssueType.NONE;
        if (SHelper.get().element().isDisplayed(BaseGeneric.ERROR_MSG.element(), Duration.ofSeconds(1))) {
            String errorText = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(BaseGeneric.ERROR_MSG.element());
            if (!TestUtils.isNullOrBlank(errorText)) {
                if (errorText.toLowerCase().trim().contains("entered is incorrect.")) {
                    result = IssueType.AUTH_SERVICES_IS_DOWN;
                }
            }
        }
        return result;
    }

    private IssueType checkAssetStatusNotChangingIssue() throws TestException {
        IssueType result = IssueType.NONE;
        if (SHelper.get().element().isDisplayed(BaseGeneric.SEQUENCE_CATEGORY.element(), Duration.ofSeconds(1))) {
            String actualCategory = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM)
                    .getFrom(BaseGeneric.SEQUENCE_CATEGORY.element());
            if (actualCategory.toUpperCase().trim().equals("PRE-RAW")
                    || actualCategory.toUpperCase().trim().equals("PRE-CUT")) {
                result = IssueType.ASSET_STATUS_IS_NOT_CHANGING;
            }
        }
        if (SHelper.get().element().isDisplayed(BaseGeneric.TOP_CONTAINER_VALUES.element(), Duration.ofSeconds(1))) {
            List<WebElement> values = SHelper.get().element().getListOf(BaseGeneric.TOP_CONTAINER_VALUES.element());
            WebElement preraw = find(values).that(Condition.EQUAL).text("pre-raw").get();
            WebElement precut = find(values).that(Condition.EQUAL).text("pre-cut").get();
            if (preraw != null
                    || precut != null) {
                result = IssueType.ASSET_STATUS_IS_NOT_CHANGING;
            }
        }
        return result;
    }

}
