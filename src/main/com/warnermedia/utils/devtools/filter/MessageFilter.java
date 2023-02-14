package com.warnermedia.utils.devtools.filter;

import com.warnermedia.config.TestException;
import com.warnermedia.config.report.LocalValidation;
import com.warnermedia.utils.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptException;
import java.io.File;

@Slf4j
public class MessageFilter extends AbstractFilter {
    @Override
    public void execute(JavascriptException exception) throws Exception {
        super.execute(exception);
        String name = exception.getMessage();
        Exclude exclude = new Exclude().get(TestUtils.getRelativePath() + File.separatorChar + "resources" + File.separatorChar + "exclude.json");
        if (!exclude.exceptions.contains(name)) {
            LocalValidation.getValidations().assertionFailed(log, "JS Error --> " + name);
        }
    }
}
